# Upgrading to a New Liferay Portal CE Rolling Release

Fixes and improvements for Liferay Portal CE are delivered *rolling releases*. That is fixes for a Portal CE GA are published in all newer GA releases for that version of Poral CE. For example, fixes to CE 7.3 GA1 bugs are included in all newer CE 7.3 GA releases (i.e., GA2+).

Here are the steps for upgrading a Portal CE installation to a new rolling release (this only applies when upgrading between GAs in the same Liferay CE version):

1. Download the new Portal CE GA release files.

1. Shut down your Liferay Portal CE server.

1. [Back up your DXP system](../../maintaining-a-liferay-dxp-installation/backing-up.md), including any files that you have added or edited.

1. Install the new Portal CE GA release files in the previous installation folder on in a new folder.

1. Replace the installation's `[Liferay Home]/data` folder and with the `[Liferay Home]/data` folder from your backup.

1. Merge the files that you have added and edited from your backup to your installation. Here are some commonly added/edited files:

    `portal-*.properties`: Portal properties files, such as `portal-ext.properties`.

    `/osgi/*.config`: OSGi configuration files.

    `web.xml`: Portal web application descriptor.

    `setenv.sh`: Application server configuration scripts.

    `/license/*`: Activation keys.

    `/log/*`: Log files.

1. Copy your apps, plugins and modules from your backup to your installation.

1. Disable search indexing during database upgrade by setting `indexReadOnly="true"` in a `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`file:

    ```bash
    mkdir -p new-version/files/osgi/configs
    echo "indexReadOnly=\"true\"" > new-version/files/osgi/configs/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config
    ```

1. Upgrade your database with the [database upgrade tool](using-the-database-upgrade-tool.md)

1. After the upgrade completes, re-enable search indexing by setting `indexReadOnly="false"` or by deleting the `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config` file.

    ```bash
    rm new-version/files/osgi/configs/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config
    ```

1. Start the Liferay Portal CE server.

1. [Optional] Reindex your search indexes in the Control Panel by navigating to *Search* &rarr; *Index Actions* and clicking *Execute* for *Reindex All Search Indexes.* (this is not usually required when upgrading between GAs, you just need to keep your previous search engine configuration to reuse indexes from previous GA)

Congratulations! You've upgraded your Portal CE installation to the new rolling release!