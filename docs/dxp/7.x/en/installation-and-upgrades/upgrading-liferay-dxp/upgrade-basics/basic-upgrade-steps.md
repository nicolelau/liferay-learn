# Basic Upgrade Steps

There are three primary methods available to upgrade from a previous version of Liferay DXP to the latest version: using the Docker image, using a bundle, and using the Liferay Upgrade tool. The Liferay Upgrade tool is the recommended choice for enterprises and for upgrading critical production environments. For other situations, using the latest Liferay DXP Docker image or bundle can be a suitable and expedient alternative.

For an overview on upgrades and information on upgrading larger and more complex environments see [Introduction to Upgrading Liferay DXP](./introduction-to-upgrading-liferay-dxp.md).

```warning::
   Regardless of upgrade method, **always** back up your data and only perform the upgrade on a copy of your backed up data.
```

```important::
   For production and enterprise level deployments of Liferay use the Upgrade Tool to perform upgrades. For more information see `Using the Upgrade Tool <./using-the-upgrade-tool.md>`_.
```

## Using the Latest Docker Image

The fastest way to upgrade to the latest version of Liferay DXP is by using the Liferay DXP Docker image. The following upgrade steps assumes the following:

The database is accessible with the following values:

* **JDBC Driver:** `com.mysql.cj.jdbc.Driver`
* **JDBC URL:** `jdbc:mysql://localhost/my-liferay-database?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&serverTimezone=GMT&useFastDateParsing=false&useUnicode=true`
* **JDBC User Name:** `my-username`
* **JDBC Password:** `my-password`

The Document Library is accessible with the following values:

* **Document Library Path:** `/home/liferay/document-library`

To perform the upgrade using the latest Docker Image follow these steps:

1. Shutdown the server running the previous version of Liferay DXP and backup your database and Document Library directory.

1. Download and run the latest image of Liferay DXP using the following command, substituting your environment values as needed: <!-- I don't have these steps working as of yet. @Jim can you help me with this? -->

    ```bash
      docker run --name my_liferay_container -it -p 8080:8080 liferay/portal:7.3.0-ga1 \
        --env LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=com.mysql.cj.jdbc.Driver \
        --env LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL=jdbc:mysql://localhost/my-liferay-database?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&serverTimezone=GMT&useFastDateParsing=false&useUnicode=true \
        --env LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME=my-username \
        --env LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD=my-password
    ```

1. Confirm successful Liferay DXP upgrade and startup by reviewing the logs:

    ```bash
    Example tail end of upgrade log indicating success.
    ```

1. Shutdown the Docker image, [migrate any desired configurations and properties](../configuration-and-infrastructure/migrating-configurations-and-properties.md) from your previous version installation to your new docker based installation. Start the docker image when you have completed the migration.

    ```tip::
       Check the `Reference section <../14-reference/README.md>`_ for information on deprecations and property and configuration default changes.
    ```

1. Validate your upgraded data and configurations.

    ![The Liferay DXP landing screen.](./basic-upgrade-steps/images/01.png)

Your upgrade to the latest version of Liferay DXP is now complete!

## Using the Latest Bundle

Coming soon! <!-- Similar to the above section but w/ a bundle -->

## Additional Information

* [Upgrade Stability and Performance](./upgrade-stability-and-performance/upgrade-stability-and-performance-overview.md)
* [Migrating and Updating Configurations](../configuration-and-infrastructure/README.md)
* [Updating a Cluster](../10-Maintaining-a-liferay-dxp-installation/10-maintaining-clusters/01-maintaining-clustered-installations.md)
* [Custom Code Upgrade](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2)
