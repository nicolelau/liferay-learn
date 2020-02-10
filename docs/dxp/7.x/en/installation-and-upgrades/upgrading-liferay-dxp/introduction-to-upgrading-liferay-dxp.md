# Introduction to Upgrading Liferay DXP

Upgrading to the latest Liferay DXP version involves updating your files, apps, custom code, and data. Upgrade time scales with the amount of data and custom code. Liferay's upgrade optimization guidelines, code upgrade tooling in Dev studio, and a data upgrade tool facilitate upgrading as quickly and smoothly as possible.

For trial purposes on a private, non-clustered DXP instance, you can follow the steps in [Example: A Simple DXP Upgrade](./a-simple-dxp-upgrade.md).

If your DXP instance is in production or is being used collaboratively for testing, development, or user-acceptance, you should test upgrading a backup instance _before_ upgrading your real instance. The [Advanced Upgrade Topics](TODO) detail the process.

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

Once the major preparation tasks are identified, it's time to examine the data upgrade tasks.
