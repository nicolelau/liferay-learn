# Upgrade Overview

The complexity and scale of a DXP installation correlates directly to the planning and effort that may be required to upgrade it. Installations that have few custom apps and small data sets can likely be upgraded successfully using the [Basic Upgrade Steps](./basic-upgrade-steps.md). Large, complex installations and enterprise-level installations typically require additional planning and testing to upgrade safely and efficiently. The Liferay DXP upgrade topics fall into these categories:

* [Preparation and Planning](#preparation-and-planning)
* [Updating Custom Plugin Code](#update-custom-plugin-code)
* [Migrating Configurations and Settings](#migrate-configurations-and-settings)
* [Updating the Database Driver](#update-the-database-driver)
* [Improving Upgrade Performance](#improve-upgrade-performance)
* [Executing the Database Upgrade](#execute-the-database-upgrade)

```warning::
   **Always** [back up](../../10-maintaining-a-liferay-dxp-installation/backing-up.md) your database and installation before upgrading. Testing the upgrade process on backup copies is advised.
```

## Preparation and Planning

Preparation and planning may be of little consequence for small, casual installations but is *mandatory* for large enterprise-level installations.

### Review Available Upgrade Paths

Look up your current Liferay DXP/Portal version in this table to determine your installation upgrade path.

| Upgrade Path                            | Description |
| --------------------------------------- | ----------- |
| Liferay Portal 5.x and 6.0.x &rarr; Liferay Portal 6.2 &rarr; Liferay DXP 7.3 | Support life ended for Liferay Portal 5.0, 5.1, 5.2, and 6.0 |
| Liferay Portal 6.1.x &rarr; DXP/Portal 7.1 &rarr; DXP 7.3 | Support life ended for Liferay Portal 6.1 |
| Liferay DXP/Portal 6.2+ &rarr; DXP 7.3      |             |

If your path includes upgrading to Liferay Portal 6.2, follow the [Liferay Portal 6.2 upgrade instructions](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay) first.

### Review Deprecations and Changes to Default Settings

Features and configuration defaults may change in new versions of Liferay DXP. See the reference section or the following articles for the most recent deprecations and changes to features and to settings:

* [Deprecations](../reference/deprecations-in-liferay-dxp-7-3.md)
* [Features in Maintenance Mode](../reference/features-in-maintenance-mode.md)
* [Changes to Default Settings](../reference/changes-to-default-settings-in-liferay-dxp-7-3.md)

### Request an Upgrade Patch (Subscription)

> Subscription

If you have a Liferay DXP subscription, update to the latest fix pack and/or request an upgrade patch to prepare for the database upgrade. File a ticket in the [Help Center](https://help.liferay.com/hc/requests/new) to start this process.

### Update Marketplace Apps

Marketplace apps should be updated to the latest version for the DXP/Portal version you're currently on before upgrading the DXP database. Skipping app updates can be problematic and prevent the apps from enabling on the new DXP version.

```important::
   Do this on your current installation before upgrading.
```

After upgrading the DXP database, install the latest app versions for your new DXP instance.

## Update Custom Plugin Code

Plugins (e.g., themes, apps, and customizations) you've developed need to be adapted to the new DXP version. This can be as simple as updating dependencies or involve updating code to API changes. If you forgo updating your custom plugins, they may become disabled on the new DXP version. [Upgrading Code](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2) walks through the process and demonstrates using the [Liferay Upgrade Planner](https://help.liferay.com/hc/en-us/articles/360029147451-Liferay-Upgrade-Planner) to adapt code for an upgrade.

## Migrate Configurations and Settings

New DXP installations use your configurations and settings in the upgrade and at run time. You must migrate and update them from your previous installation to your new one. These articles walk through the migration and update tasks:

* [Migrating Configurations and Properties](../configuration-and-infrastructure/migrating-configurations-and-properties.md)
* [Updating the File Store](../configuration-and-infrastructure/updating-the-file-store.md)

## Update the Database Driver

Check your database vendor documentation for the recommended database driver. If a new driver is recommended, replace the existing driver JAR file and update the `jdbc.default.driverClassName` property in your `portal-ext.properties` file with the new driver class name. 

MySQL example:

```properties 
jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
```

 See the [Database Templates](../../14-reference/05-database-templates.md) for more driver examples.

## Improve Upgrade Performance

Upgrading large data sets can take a prohibitively long time, if you leave unnecessary data intact or forgo performance tuning.

### Prune Data

If your DXP server has instances, sites, pages, or versioned content items (e.g., Web Content articles, Documents and Media files, and more) that are unnecessary, removing them can cut down upgrade time considerably. See [Improving Database Upgrade Performance](../upgrade-stability-and-performance/improving-database-upgrade-performance.md) on ways to prune your database of unnecessary data.

### Tune Database Performance

Adjusting your database for upgrade operations (more data writes than in production) improves database upgrade performance. See [Improving Database Upgrade Performance](../upgrade-stability-and-performance/improving-database-upgrade-performance.md) for details.

### Tune the Search Engine

The search engine typically indexes regularly while Liferay DXP is running. However, this indexing can have a detrimental impact to upgrade performance if it is left on. Indexing should be disabled before performing an upgrade, and re-enabled once an upgrade is complete. See [Search Indexing and Upgrades](../upgrade-stability-and-performance/search-indexing-and-upgrade.md) for more information.

## Execute the Database Upgrade

There are two primary database upgrade methods:

* [Using Auto Upgrade \(Basic Upgrade Steps\)](./basic-upgrade-steps.md): This involves setting a property and launching the new DXP server. The database upgrades execute as DXP starts up. After the upgrades and DXP startup complete successfully, your new DXP server is operating with the upgraded database.

* [Using the Database Upgrade Tool](./using-the-liferay-upgrade-tool.md): This client program updates a DXP database while it's offline, detached from any DXP instance. [Tuning a database for upgrade](../upgrade-stability-and-performance/improving-database-upgrade-performance.md) and using the upgrade tool is recommended for upgrading large data sets. Additionally, the upgrade tool works well in cycles where you're [pruning data](../upgrade-stability-and-performance/improving-database-upgrade-performance.md), upgrading, and testing.

For larger installations and production environments we recommend using the Liferay Database Upgrade Tool.

## Conclusion

Once you complete the tasks outlined above, your upgrade is complete. But before using DXP, you must re-establish desired runtime settings and undo any upgrade-specific tuning. Plus there may be applications that weren't available on your previous Liferay version that are recommended for new DXP production instances. See the [Post-Upgrade Considerations](./post-upgrade-considerations.md) for more information.

Now that you're familiar with the DXP upgrade components, you can start upgrading your DXP instance. First examine the [Basic Upgrade Steps](./basic-upgrade-steps.md) and consider whether they fit your upgrade. If they don't, follow the topics outlined above and execute the database upgrade using the [Database Upgrade Tool](./using-the-upgrade-tool.md).

Additionally, refer to these other upgrade scenarios if they relate to your upgrade:

* [Upgrading a Sharded Environment](../other-upgrade-scenarios/upgrading-a-sharded-environment.md)
* [Updating a Cluster](../10-Maintaining-a-liferay-dxp-installation/10-maintaining-clusters/01-maintaining-clustered-installations.md)
