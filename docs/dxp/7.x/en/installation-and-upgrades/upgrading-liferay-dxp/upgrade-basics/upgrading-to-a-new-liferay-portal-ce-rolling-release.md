# Upgrading to a New Liferay Portal CE Rolling Release

Fixes and improvements for Liferay Portal CE are delivered in *rolling releases*. That is, fixes and improvements to a Portal CE GA are published in all newer GA releases for that Portal CE version. For example, fixes to CE 7.3 GA1 are included in CE 7.3 GA2 (and any newer CE 7.3 GA).

```note::
   Upgrading from a Liferay Portal CE *version* to a newer version requires doing a full-on upgrade. An example would be upgrading from version 7.2 to 7.3. Please see `Upgrade Overview <./upgrade-overview.md>`_ for more information.
```

Here are steps for upgrading from a from one GA to another in the same Liferay Portal CE version:

1. Shut down your Liferay Portal CE server.

1. [Back up your DXP system](../../maintaining-a-liferay-dxp-installation/backing-up.md), including any files that you have added or edited.

1. Install the new Portal CE GA release files to your existing installation folder or to a new folder. The remaining instructions demonstrate working with an existing installation.

1. Replace the installation's `[Liferay Home]/data` folder and with the `[Liferay Home]/data` folder from your backup.

1. Merge the files that you have added and edited from your backup to your installation. Here are some commonly added/edited files:

    `portal-*.properties`: Portal properties files, such as `portal-ext.properties`.

    `/osgi/*.config`: OSGi configuration files.

    `web.xml`: Portal web application descriptor.

    `setenv.sh`, `startup.sh`, and more: Application server configuration scripts.

    `/license/*`: Activation keys.

    `/log/*`: Log files.

1. Copy your apps, plugins and modules from your backup to your installation.

1. Make sure you're using the JDBC database driver your database vendor recommends. If you're using MySQL, for example, set `jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver` in [`portal-ext.properties`](../../reference/portal-properties.md) and replace the MySQL JDBC driver JAR your app server uses. See [Database Drivers](../configuration-and-infrastructure/migrating-configurations-and-properties.md#database-drivers) for more details.

1. Disable search indexing during database upgrade by setting `indexReadOnly="true"` in a `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config` file:

    ```bash
    mkdir -p liferay-home/osgi/configs
    echo "indexReadOnly=\"true\"" > liferay-home/osgi/configs/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config
    ```

1. Run the Database Upgrade Tool. Please see [Using the Database Upgrade Tool](./using-the-database-upgrade-tool.md) for more information. Here's an example execution sequence:

    ```bash
    cd liferay-home/tools/portal-tools-db-upgrade-client
    db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.timezone=GMT -Xmx4096m" -l "output.log"
    ```

   If you haven't created [upgrade properties files](../reference/database-upgrade-tool-reference.md#manual-configuration), the upgrade tool prompts you for configuration values, and shows default values in parentheses. Here's an example interaction:

    ```
    Please enter your application server (tomcat):
    tomcat

    Please enter your application server directory (../../tomcat-9.0.17):

    Please enter your extra library directories (../../tomcat-9.0.17/bin):

    Please enter your global library directory (../../tomcat-9.0.17/lib):

    Please enter your portal directory (../../tomcat-9.0.17/webapps/ROOT):

    [ db2 mariadb mysql oracle postgresql sqlserver sybase ]
    Please enter your database (mysql):
    mariadb

    Please enter your database host (localhost):

    (etc.)
    ```

    If you want to use the default value shown in a prompt, press enter.

    After configuration is complete, the upgrade starts. You can monitor the log file. Log messages are reported for the start and completion of each upgrade process.

1. After the upgrade completes, check the log for any database upgrade failures or errors. You can use [Gogo Shell commands](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) to troubleshoot them and finish the upgrades.

1. After the upgrade completes, re-enable search indexing by setting `indexReadOnly="false"` or by deleting the `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config` file.

    ```bash
    rm liferay-home/osgi/configs/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config
    ```

1. Start the Liferay Portal CE server.

1. Optionally reindex your search indexes in the Control Panel by navigating to *Search* &rarr; *Index Actions* and clicking *Execute* for *Reindex All Search Indexes.* (this is not usually required when upgrading between GAs, you just need to keep your previous search engine configuration to reuse indexes from previous GA)

Congratulations! You've upgraded your Portal CE installation to the new rolling release!