# Upgrading Via Docker

Running a Liferay Docker image with auto-upgrade enabled upgrades your database on Liferay startup. After the upgrade completes, you can continue [using Liferay via that Docker container](../../../installation-and-upgrades/installing-liferay/using-liferay-dxp-docker-images/dxp-docker-container-basics.md) or point your existing Liferay installation to the upgraded database.

```important::
   Don't have Docker? Go here first: `Linux <https://docs.docker.com/install/linux/docker-ce/ubuntu/>`_ | `Windows <https://docs.docker.com/docker-for-windows/install/>`_ | `OSX <https://docs.docker.com/docker-for-mac/install/>`_
```

| DXP Edition | Image | Tags |
| :---------- | :---- | :--- |
| Liferay DXP (Subscription)| [`dxp`](https://hub.docker.com/r/liferay/dxp) | [here](https://hub.docker.com/r/liferay/dxp/tags) |
| Liferay Portal CE | [`portal`](https://hub.docker.com/r/liferay/portal) | [here](https://hub.docker.com/r/liferay/portal/tags) |

```important::
   For critical installations and Subscribers, use the Database Upgrade Tool. For more information see `Using the Database Upgrade Tool <./using-the-database-upgrade-tool.md>`_.
```

```warning::
   **Always** `back up <../../maintaining-a-liferay-dxp-installation/backing-up.md>`_ your database and installation before upgrading. Testing the upgrade process on backup copies is advised.
```

## Upgrading with the Latest Docker Image

Here are the steps for using the Docker image:

1. Create an arbitrary folder to use with the Docker image and create subfolders called `files` and `deploy`. For example,

    ```bash
    mkdir -p new-version/files
    mkdir -p new-version/deploy
    ```

    `files`: These files are copied to the Docker container's [Liferay Home](../../reference/liferay-home.md) folder.

    `deploy`: Artifacts here are copied to the Docker container's auto-deploy folder.

1. If you're using an embedded [Elasticsearch](../../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md) engine or a local [File Store \(Document Library\)](../../../system-administration/file-storage/configuring-file-storage.md), copy the `[Liferay Home]/data` folder from your installation backup to the `files` folder (e.g., creating `new-version/files/data`).

1. Copy the [Liferay Home files](../../maintaining-a-liferay-dxp-installation/backing-up.md#liferay-home) and [application server files](../../maintaining-a-liferay-dxp-installation/backing-up.md#application-server) from your installation backup to their corresponding locations in the `files` folder (your new `[Liferay Home]`). The files may include but are not limited to these:

    `/license/*`: Activation keys. (Subscription)

    `/log/*`: Log files.

    `/osgi/*.config`: OSGi configuration files.

    `portal-*.properties`: Portal properties files, such as `portal-ext.properties`.

    `setenv.sh`, `startup.sh`, and more: Application server configuration scripts.

    `web.xml`: Portal web application descriptor.

1. Make sure you're using the JDBC database driver your database vendor recommends. If you're using MySQL, for example, set `jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver` in [`portal-ext.properties`](../../reference/portal-properties.md) and replace the MySQL JDBC driver JAR your app server uses. See [Database Drivers](../configuration-and-infrastructure/migrating-configurations-and-properties.md#database-drivers) for more details.

1. Run the Docker image [mounted](./../installing-liferay/using-liferay-dxp-docker-images/providing-files-to-the-container.md) to your new version folder using the following command, substituting your image name, tag, and environment values as needed:

    ```bash
    docker run -it -p 8080:8080 \
     -v $(pwd)/new-version:/mnt/liferay \
     -e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true \
     liferay/[image]:[tag]
    ```

    The `-v new-version:/mnt/liferay` arguments bind mount the `new-version` folder on the host to `/mnt/liferay` in the container. Please see [Providing Files to the Container](../../installing-liferay/using-liferay-dxp-docker-images/providing-files-to-the-container.md) for more information on the mapping files to Liferay Home in the Docker container.

    The parameter `-e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true` triggers the database upgrade process.

1. In the console or log, confirm successful database upgrade and server startup. Upgrade process messages report starting and completing each upgrade process. A message like this one indicates server startup completion:

    ```bash
    org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds
    ```

    If there are any upgrade failures or errors, they're printed to the logs. You can use [Gogo Shell commands](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) to troubleshoot issues and finish the upgrade.

1. Validate your upgraded database.

    ![Here is the Liferay landing screen.](./upgrading-via-docker/images/01.png)

Your database upgrade is now complete!

```note::
   If you're done upgrading the database, leave off the ``-e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true`` environment setting from any new Docker container you create to use with the database via ``docker run ...``. See `Docker Container Basics <../../../installation-and-upgrades/installing-liferay/using-liferay-dxp-docker-images/dxp-docker-container-basics.md>`_ for more information on using Docker with DXP.
```

## Conclusion

If the upgraded database is all you need, then consider any applicable [post-upgrade tasks](./post-upgrade-considerations.md) and enjoy using your new Liferay instance! If there's more to completing your upgrade, these articles can help you finish:

* [Upgrade Overview](./upgrade-overview.md) describes all of the upgrade topics. Maybe there's a topic you still need to address.

* [Using the Database Upgrade Tool](./using-the-database-upgrade-tool.md) demonstrates upgrading the database while the Liferay server is offline. If the upgrade took too long, [tuning the database](../upgrade-stability-and-performance/database-tuning-for-upgrades.md), [pruning unneeded data](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md), and using database upgrade tool is recommended.

* [Custom Code Upgrade](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2) guides you in adapting custom plugin code you've developed to the new Liferay version.

* [Maintaining Clustered Installations](../../maintaining-a-liferay-dxp-installation/maintaining-clustered-installations/maintaining-clustered-installations.md) describes how to upgrade in a clustered environment.