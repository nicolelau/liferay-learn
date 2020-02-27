# Basic Upgrade Steps

<!-- Update verbiage to make it clear this is the fast path for upgrading for 7.1 (or 7.2)+ -->

If your data set is small and you're upgrading to DXP 7.3+, these basic upgrade steps may be right for you. They are a more streamlined version of the advanced upgrade steps in that they forgo database tuning, data pruning, and [custom code upgrades](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2), in favor of upgrading the database sooner. The biggest difference about the basic upgrade steps is that the database upgrade is done during server startup via the _Auto Upgrade_ feature. Auto Upgrade is a mode of running DXP that invokes all DXP upgrade processes on DXP startup. When Auto Upgrade completes, the latest version of DXP is fully operational with the upgraded database.

Auto Upgrade can be run on any DXP 7.3+ installation, including a Docker image or on-premises installation. You can migrate from an on-premises installation to a Docker image and vice-versa. You can download the Docker Desktop from [here](https://www.docker.com/products/docker-desktop). Before upgrading make sure that you review the [Introduction to Upgrading](./introduction-to-upgrading-liferay-dxp.md) and have already updated your apps and exported your configurations.

<!--

The basic upgrade steps involve:

1. [Preliminary Steps](#preliminary-steps) to run on your current DXP version _before_ upgrading.

2. Upgrading to the latest DXP version [using the latest Docker image](#using-the-latest-docker-image) OR [using the latest on-premises installation](#using-the-latest-on-premises-installation). -->

```important::
   For production and enterprise level deployments of Liferay, use the Upgrade Tool to perform upgrades. For more information see `Using the Liferay Upgrade Tool <./using-the-liferay-upgrade-tool.md>`_.
```

```warning::
   Regardless of upgrade method, **always** back up your database and installation before upgrading. Testing the upgrade process on backup copies is advised.
```

Upgrade using the latest Liferay Docker image or skip to [Using an On-Premises DXP Installation](#using-an-on-premises-installation).

<!-- ## Preliminary Steps

Execute these steps on your current DXP version _before_ upgrading:

1. Update all [Liferay Marketplace](https://help.liferay.com/hc/en-us/articles/360029134931-Using-the-Liferay-Marketplace) apps to the latest version for your _current_ DXP version. Skipping app updates can be problematic and prevent the apps from enabling on the new DXP version.

1. On 7.x, preserve your System Settings by [exporting and downloading all of them](https://help.liferay.com/hc/en-us/articles/360029131591-System-Settings#exporting-and-importing-configurations) in a ZIP file of `.config` files. Extract the `.config` files to your `[Liferay_Home]/osgi/configs` folder for easy setup on the new DXP server.

1. [Backup your current Liferay DXP installation](../../10-maintaining-a-liferay-dxp-installation/backing-up.md), including your database, document file store, and [Liferay Home](../../14-reference/01-liferay-home.md) folder.

Upgrade using the latest Liferay Docker image (next) or skip to [Using an On-Premises DXP Installation](#using-an-on-premises-installation). -->

## Using the Latest Docker Image

The fastest way to upgrade to the latest Liferay version is by using the latest Liferay Docker image ([DXP](https://hub.docker.com/r/liferay/dxp) or [Community Edition](https://hub.docker.com/r/liferay/portal)). Here are the steps:

1. Set up a new [Liferay Home](../../14-reference/01-liferay-home.md) folder with the contents of your current Liferay Home. You'll bind this new Liferay Home to the Docker image in a later step.

    ```bash
      cp /old-version/liferay/home/ /new-version/liferay/home/
    ```

    If you're using a DevOps based approach, then branch your `liferay-home` directory.

<!-- May only apply to 6.2 and below? In which case - maybe make special note of these steps in the intro? Or at the top of this article?

1. In the new Liferay Home, update these items:

    * [Configurations and properties](../configuration-and-infrastructure/migrating-configurations-and-properties.md)
    * [Database driver](../configuration-and-infrastructure/updating-the-database-driver.md)
    * [File store](../configuration-and-infrastructure/updating-the-file-store.md)

-->

1. Disable search indexing during database upgrade by creating `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config` in your `[Liferay_Home]/osgi/configs` folder:

    ```bash
      echo {new/liferay/home/path/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config} "indexReadOnly="true""
    ```

<!-- 
    ```properties
    indexReadOnly="true"
    ``` -->

1. Enable auto upgrade by setting this property in your `[Liferay_Home]/portal-ext.properties` file:

    ```bash
      echo append [Liferay_Home]/portal-ext.properties upgrade.database.auto.run=true
    ```

<!-- Move down? or move to post-upgrade considerations?

1. Download the latest versions of your [Marketplace apps](https://help.liferay.com/hc/en-us/articles/360029134931-Using-the-Liferay-Marketplace) compatible with the _new_ DXP version and copy them to the new `[Liferay_Home]/deploy` folder.

-->

1. Download and run the latest DXP Docker image mounted to your new Liferay Home using the following command, substituting your environment values as needed:

    ```
    docker run -it -p 8080:8080\
     --name my_liferay_container\
     -v /path/to/new_liferay_home:/mnt/liferay\
     liferay/portal:7.3.0-ga1
    ```

    The `-v /path/to/new_liferay_home:/mnt/liferay` arguments bind mount the `/path/to/new_liferay_home` folder on the host to `/mnt/liferay` in the container.

    Liferay DXP and the database upgrade initialize.

1. In the console or log, confirm successful database upgrade and DXP startup. Upgrade process messages report starting and completing each upgrade process.

    A message like this one indicates DXP startup completion:

    ```
    org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds
    ```

    If there are any database upgrade failures or errors, you can troubleshoot them and finish the upgrades using [Gogo Shell commands](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md).

1. Re-enable search indexing by setting `indexReadOnly="false"` or deleting the `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config` file.

    ```bash
      rm path/to/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config
    ```

1. Validate your upgraded database and configurations. <!-- probably should update the image to the 7.3 landing page -->

    ![Here is the Liferay DXP landing screen.](./basic-upgrade-steps/images/01.png)

Your Liferay DXP database upgrade is now complete!

## Using the Latest DXP On-Premises Installation

<!-- Update to be reflect the changes we discussed for the docker image path above -->

After completing the [preliminary steps](#preliminary-steps), perform the upgrade using the latest DXP on-premises installation following these steps:

1. Install the latest [Liferay DXP Tomcat bundle](../../installing-liferay-dxp-on-premises/installing-a-liferay-dxp-tomcat-bundle.md) (or the latest Liferay DXP on an application server).

1. Set up a new Liferay Home folder with the contents of your current Liferay Home.

1. In the new Liferay Home, update these items:

    * [Configurations and properties](../configuration-and-infrastructure/migrating-configurations-and-properties.md)
    * [Database driver](../configuration-and-infrastructure/updating-the-database-driver.md)
    * [File store](../configuration-and-infrastructure/updating-the-file-store.md)

1. Disable search indexing during data upgrade by creating a file called `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config` in your `[Liferay_Home]/osgi/configs` folder and saving the following setting to it:

    ```properties
    indexReadOnly="true"
    ```

1. Enable auto upgrade by setting this property in your `[Liferay_Home]/portal-ext.properties` file:

    ```properties
    upgrade.database.auto.run=true
    ```

1. Download the latest versions of your [Marketplace apps](https://help.liferay.com/hc/en-us/articles/360029134931-Using-the-Liferay-Marketplace) compatible with the _new_ DXP version and copy them to the new `[Liferay_Home]/deploy` folder.

1. Shut down any DXP instance that is using the database.

1. Start the new Liferay DXP application server. Liferay DXP initializes and the database upgrade executes.

1. In the console or log, confirm successful database upgrade and DXP startup. Upgrade process messages report starting and completing each upgrade process.

    If there are any database upgrade failures or errors, you can troubleshoot them and finish the upgrades using [Gogo Shell commands](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md).

    A message like this one indicates DXP startup completion:

    ```
    org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds
    ```

1. Re-enable search indexing by setting `indexReadOnly="false"` in your `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config` file and saving that file.

1. Validate your upgraded database and configurations.

    ![Here is the Liferay DXP landing screen.](./basic-upgrade-steps/images/01.png)

Your Liferay DXP database upgrade is now complete!

## Conclusion

If the upgraded DXP database and upgraded configuration are all you need, then enjoy using your new version of DXP! If you need more to complete your upgrade, these articles can help you finish:

* [Using the Liferay Database Upgrade Tool](./using-the-liferay-upgrade-tool.md) demonstrates upgrading the database while the DXP server is offline. If auto-upgrade (above) took too long or you'd like to upgrade a larger data set or an enterprise-level DXP environment, [tuning the database, pruning unneeded data](../upgrade-stability-and-performance/improving-database-upgrade-performance), and using database upgrade tool is recommended.

* [Custom Code Upgrade](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2) guides you in adapting custom plugin code you've developed to the new DXP version.

* [Updating a Cluster](../10-Maintaining-a-liferay-dxp-installation/10-maintaining-clusters/01-maintaining-clustered-installations.md) describes how to upgrade DXP in a clustered environment.
