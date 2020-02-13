# Introduction to Upgrading Liferay DXP

Upgrading to the latest Liferay DXP version involves updating your files, apps, custom code, and data. Upgrade time scales with the amount of data and custom code. Liferay's upgrade optimization guidelines, code upgrade tooling in Dev studio, and a data upgrade tool facilitate upgrading as quickly and smoothly as possible.

For trial purposes on a private, non-clustered DXP instance, you can follow the steps in [Example: A Simple DXP Upgrade](./a-simple-dxp-upgrade.md).

If your DXP instance is in production or is being used collaboratively for testing, development, or user-acceptance, you should test upgrading a backup instance _before_ upgrading your real instance. The [Advanced Upgrade Topics](./advanced-upgrade-topics/introduction-to-advanced-upgrade-topics.md) detail the process.

```note::
   Dev Studio's [Upgrade Planner](https://help.liferay.com/hc/en-us/articles/360029147451-Liferay-Upgrade-Planner) walks through the upgrade process and automates parts of it. The Planner uses a terse [step listing](../reference/liferay-upgrade-planner-steps.md) you can follow or refer to throughout upgrading production-grade instances.
```

Here's an overview of the upgrade steps.

## Adapting to Feature Changes

New DXP versions can deprecate features, remove features, or [move features into maintenance mode](./reference/features-in-maintenance-mode.md). The [DXP deprecations article](./reference/deprecations-in-liferay-dxp-7-2.md) explains the ramifications so you can adapt to the changes.

## Upgrading Custom Code and Plugins

Custom code upgrade involves adapting any themes and apps you've developed to the new DXP version. This can be as simple as updating dependencies, or it may involve major code changes. [Upgrading Code](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2) (a separate guide) demonstrates the process. The code upgrade can be done in parallel with the data upgrade.

### Upgrading Marketplace Apps

You must also upgrade your installation's Marketplace apps (Kaleo, Calendar, Notifications, etc.) to their latest version for your current Liferay DXP installation. Troubleshoot any issues with these apps on your current DXP installation before upgrading to the new DXP version.

## Upgrading Your Database

Upgrading the database is the biggest milestone in the upgrade process. See [Upgrading the Database](./advanced-upgrade-topics/upgrading-the-database.md) for a data upgrade overview.

## Preparing a New Liferay DXP Server

[New DXP server setup](./advanced-upgrade-topics/preparing-a-new-application-server.md) involves installing the new DXP version, migrating and updating your portal properties and OSGi properties, and installing any necessary patches.

## Installing the Search Engine

If you're running a production-grade DXP instance and you do not already have your own Elasticsearch/Solr installation running, you must set one up. By default, DXP ships with an embedded configuration for Elasticsearch. The embedded configuration works great for demo purposes, but is not supported in production. See [Installing Elasticsearch](https://help.liferay.com/hc/en-us/articles/360028711132-Installing-Elasticsearch) or [Installing Solr](https://help.liferay.com/hc/en-us/articles/360032264052-Installing-Solr) for more information.

Before tackling a production upgrade, examine [A Simple DXP Upgrade](./a-simple-dxp-upgrade.md) next.

===

## Determining Your Upgrade Path

Look up your current Liferay DXP/Portal version in this table to determine your installation upgrade path.

| Upgrade Path                            | Description |
| --------------------------------------- | ----------- |
| Liferay Portal 5.x and 6.0.x &rarr; Liferay Portal 6.2 &rarr; Liferay DXP 7.3 | Support life ended for Liferay Portal 5.0, 5.1, 5.2, and 6.0 |
| Liferay Portal 6.1.x &rarr; DXP/Portal 7.1 &rarr; DXP 7.3 | Support life ended for Liferay Portal 6.1 |
| Liferay DXP/Portal 6.2+ &rarr; DXP 7.3      |             |

If your path includes upgrading to Liferay Portal 6.2, follow the [Liferay Portal 6.2 upgrade instructions](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay) first.

If your path includes upgrading to Liferay DXP/Portal 7.1, continue following these upgrade instructions.

## Using Backup Data for Upgrade Testing

Doing trial runs of the upgrade process and testing an upgraded copy of your production DXP instance is essential for identifying and resolving issues that would otherwise affect your production server. Upgrade trial runs, and even the pre-production upgrade, should be done using backed up copies of the production database and document library repository.

```important::
   The tasks discussed in the [Preparing for the Data Upgrade](#preparing-for-the-data-upgrade) and [Performing the Data Upgrade](#performing-the-data-upgrade) sections, should be done as a trial run first on a test server using a copy of your production server backups.
```

## Preparing for the Data Upgrade

Unnecessary data and improperly tuned databases slow down the upgrade process. Large data sets that have not been optimized may severely impact performing during a data upgrade. Take these measures to optimize your data upgrades:

* [Prune unneeded data.](./pruning-the-database.md)

* [Tune the database for upgrades.](./tuning-for-the-data-upgrade.md)

If Staging is enabled in production and you have staged changes, you should publish them to Live before the data upgrade. If you skip this step, publishing staged changes requires a full publish.

Lastly for your pre-production upgrade, completely [back up](../10-maintaining-a-liferay-dxp-installation/backing-up.md) your current Liferay DXP/Portal installation, pruned database, and document repository.

## Performing the Data Upgrade

Data upgrade is the biggest upgrade process milestone. [Configure the upgrade tool on your new DXP server](./configuring-the-data-upgrade-tool.md) and then [use it to execute the data upgrade](using-the-upgrade-tool.md).

## Executing Post-Upgrade Tasks

After completing the data upgrade, you must optimize your database and DXP server for production. The [post-upgrade tasks](./post-upgrade-tasks.md) include re-tuning database settings and running search indexes.

===

# Preparing a New Application Server for Liferay DXP

To upgrade your Liferay DXP database, prepare a new application server for hosting Liferay DXP. You'll use this server to run the data upgrade and then run Liferay DXP.

```note::
   If you are upgrading from a version prior to DXP 7.0 and using a sharded environment, you must migrate your shards to multiple application servers, because sharding is no longer supported. Prepare one new application server for each shard.
```

## Install the New Liferay DXP Version

Install the new Liferay DXP version using a bundle or to an application server of choice. See the [Installing a Liferay DXP Tomcat Bundle](../../installing-liferay-dxp-on-premises/installing-a-liferay-dxp-tomcat-bundle.md) for more information.

```warning::
   Do not start your application server with your database yet. Wait until after you have completed the data upgrade.
```

### Install the Latest Upgrade Patch or Fix Pack

> Subscription Required

An *upgrade patch* contains the latest fix pack and hot fixes planned for the next service pack. Upgrade patches provide the latest fixes available for your data upgrade.

Get the latest fixes for Liferay DXP by requesting an upgrade patch. Request the patch in advance to ensure you will have it ready when you need it.

Install the upgrade patch (if you requested it from Liferay Support) or the [latest Fix Pack](https://help.liferay.com/hc/en-us/articles/360028810452-Patching-Liferay-DXP).

## Additional Information

* [Blade CLI](https://help.liferay.com/hc/en-us/articles/360029147071-Blade-CLI)
* [Configuring the Data Upgrade Tool](./configuring-the-data-upgrade-tool.md)
