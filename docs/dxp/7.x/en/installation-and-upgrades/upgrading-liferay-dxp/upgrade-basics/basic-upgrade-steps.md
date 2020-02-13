# Basic Upgrade Steps

The fastest way to upgrade to the latest version of Liferay DXP can be done by using a bundle of the latest version of Liferay DXP. This method is best for **development scenarios**. For an overview on upgrades and information on upgrading larger and more complex environments see [Introduction to Upgrading Liferay DXP](./introduction-to-upgrading-liferay-dxp.md).

```warning::
   *Always* back up your data and only perform the upgrade on a copy of your backed up data.
```

Perform a basic upgrade by following these steps:

1. Download and unzip the latest [Liferay DXP Bundle (Tomcat)](link) or [Docker Image](link)

1. Configure the latest version bundle to [connect to your database](../../installing-liferay-dxp-on-premises/configuring-a-database.md) and specify `upgrade.database.auto.run=true` in portal-ext.properties. The MySQL connection template and `upgrade.database.auto.run` property are listed below as an example:

    ```properties
    jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/my-database?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&serverTimezone=GMT&useFastDateParsing=false&useUnicode=true
    jdbc.default.username=my-username
    jdbc.default.password=my-password

    upgrade.database.auto.run=true
    ```

1. [Migrate any desired configurations and properties](../configuration-and-infrastructure/migrating-configurations-and-properties.md) from your previous version installation to your new installation. This can include any particular portal.property or OSGi configurations that you had in our previous installation.

    ```tip::
       Check the `Reference section <../reference/README.md>`_ for information on deprecations and property and configuration default changes.
    ```

1. Start your new installation using the startup script (or Docker image). Tomcat bundle startup example:

    ```bash
    ./liferay-dxp-version/tomcat-version/bin/startup.sh
    ```

    Example upgrade log:

    ```bash
    Example tail end of upgrade log indicating success.
    ```

1. Validate your upgraded data and configurations.

    ![The Liferay DXP landing screen.](./basic-upgrade-steps/images/01.png)

Your upgrade to the latest version of Liferay DXP is now complete.

<!-- If your DXP instance isn't in production and you can afford downtime, these steps may suit you. They demonstrate upgrading a simple, non-clustered DXP 7.x installation.

```warning::
   These steps are not intended for upgrading a DXP installation that is in production or is production grade (e.g, uses a cluster or multiple servers). See [Advanced Upgrade Topics](./advanced-upgrade-topics/introduction-to-advanced-upgrade-topics.md) for such cases.
```

Here are the steps:

1. Adapt to feature deprecations. Examine the deprecations made in the new DXP version and consider options for adapting to them. See the [latest deprecations](./reference/deprecations-in-liferay-dxp-7-2.md).

1. Upgrade your Marketplace apps for your current DXP installation.

1. Back up your current DXP installation and database. Do this in case something doesn't upgrade the way you want. See [Backing Up](../10-maintaining-a-liferay-dxp-installation/backing-up.md) for details.

1. Install the new DXP version ([DXP Tomcat bundle](../installing-liferay-dxp-on-premises/installing-a-liferay-dxp-tomcat-bundle.md) or the [DXP WAR on a new application server](../installing-liferay-dxp-on-premises/installing-liferay-on-an-application-server/installing-liferay-on-tomcat.md)) in a new location. Don't start the server yet---there's more upgrading to do first.

1. Install the latest upgrade patch. See [Installing Patches](https://help.liferay.com/hc/en-us/articles/360028810452-Patching-Liferay-DXP) for details. (Subscribers only)

1. [Upgrade your custom code](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2) at any time in parallel or at the end of your upgrade.

1. Copy your `.config` files from the `[LIFERAY_HOME]/osgi/configs` folder to the same folder in your new installation.

1. Copy your `[LIFERAY_HOME]/portal-*.properties` files (e.g., `portal-ext.properties`, `portal-setup-wizard.properties`, etc.) to your new installation.

1. Detect portal properties changes using [Blade CLI](https://help.liferay.com/hc/en-us/articles/360029147071-Blade-CLI).

    Use [Blade CLI](https://help.liferay.com/hc/en-us/articles/360029147071-Blade-CLI) to detect portal properties that are obsolete or that have migrated to [OSGi configurations](https://help.liferay.com/hc/en-us/articles/360029131651-Understanding-System-Configuration-Files). Then create OSGi configurations (`.config` files that specify the configurations) where applicable and remove obsolete properties. Here's the command format:

    ```cmd
    blade upgradeProps -p {old_liferay_home_path}/portal-ext.properties -d {new_liferay_home_path}
    ```

    Here's example command output:

    ```
    ERROR [main][VerifyProperties:161] Portal property "layout.first.pageable[link_to_layout]" is obsolete
    ERROR [main][VerifyProperties:136] Portal property "journal.article.check.interval" was modularized to com.liferay.journal.web as "check.interval"
    ```

1. Remove obsolete portal properties from your new installation and create (`.config` files) for properties that are replaced by OSGi configurations.

1. In your portal properties file, update the class name of the database driver for the new DXP version. Refer to the database templates for the new DXP version (e.g., the latest DXP version templates are [here](../14-reference/05-database-templates.md)). Here's an example property:

    ```properties
    jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
    ```

1. Copy your upgraded Marketplace apps and custom code to your new DXP installation's `[LIFERAY_HOME]/deploy` folder (create the folder if it doesn't exist).

1. If you're not using a DXP Tomcat bundle, [install the Upgrade Tool](./advanced-upgrade-topics/configuring-the-data-upgrade-tool.md) to your new installation's `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client` folder. DXP Tomcat bundles include the tool.

1. Run the data upgrade from your `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client` folder:

    ```bash
    db-upgrade.sh
    ```

    The command line interface prompts you for configuration values.

    ```
    Please enter your application server (tomcat):
    tomcat

    Please enter your application server directory (../../tomcat-9.0.17):

    ...
    ```

    To use the default value (in parentheses), press enter. Otherwise, enter the configuration you want. See [Configuring the Data Upgrade Tool](./advanced-upgrade-topics/configuring-the-data-upgrade-tool.md) for details.

1. If data upgrade issues occur, see [Upgrading Modules Using Gogo Shell](./advanced-upgrade-topics/upgrading-modules-using-gogo-shell.md) to resolve issues per module.

1. Start your DXP server.

You have completed the upgrade and started your newly upgraded DXP server! -->

## Additional Information

* [Upgrade Stability and Performance](./upgrade-stability-and-performance/upgrade-stability-and-performance-overview.md)
* [Migrating and Updating Configurations](../configuration-and-infrastructure/README.md)
* [Updating a Cluster](../10-Maintaining-a-liferay-dxp-installation/10-maintaining-clusters/01-maintaining-clustered-installations.md)
* [Custom Code Upgrade](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2)
