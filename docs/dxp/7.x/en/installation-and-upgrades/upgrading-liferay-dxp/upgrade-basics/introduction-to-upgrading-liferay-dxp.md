# Upgrade Overview

The complexity and scale of a DXP installation has correlates directly to the planning and effort that may be required to perform a full upgrade. Installations that make minimal use of custom code and have smaller data sets can likely perform the [Basic Upgrade Steps](./basic-upgrade-steps.md) with success. Larger, more complex, or enterprise level installations may require additional planning to efficiently and safely execute. This article provides an overview of the guidelines and considerations that should be taken into account when performing an upgrade.

These guidelines and considerations fall into three major categories:

* [Preparation and Planning](#preparation-and-planning)
* [Stability and Performance Tuning](#stability-and-performance-tuning)
* [Executing the Upgrade](#executing-the-upgrade)
* [Migrating Configurations and Settings](#migrating-configurations-and-settings)

```warning::
   **Always** back up and perform upgrade testing on copies of backed up data.
```

## Preparation and Planning

Preparation and planning may be less consequential for smaller more casual installations but is *mandatory* for larger enterprise grade installations.

### Review Available Upgrade Paths

Look up your current Liferay DXP/Portal version in this table to determine your installation upgrade path.

| Upgrade Path                            | Description |
| --------------------------------------- | ----------- |
| Liferay Portal 5.x and 6.0.x &rarr; Liferay Portal 6.2 &rarr; Liferay DXP 7.3 | Support life ended for Liferay Portal 5.0, 5.1, 5.2, and 6.0 |
| Liferay Portal 6.1.x &rarr; DXP/Portal 7.1 &rarr; DXP 7.3 | Support life ended for Liferay Portal 6.1 |
| Liferay DXP/Portal 6.2+ &rarr; DXP 7.3      |             |

If your path includes upgrading to Liferay Portal 6.2, follow the [Liferay Portal 6.2 upgrade instructions](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay) first.

### Review Deprecations and Changes to Default Settings

Things change in latest version, see these the reference section (or these articles:) for the most recent deprecations / changes to default settings.

### Review and Update Apps and Custom Code

Marketplace apps need to be updated to the latest for the version you're currently on, before upgrading - this reduces chances of any upgrade issues. Custom code should be reviewed against deprecations / changes and updated for compatibility to the latest version before upgrading. See these articles for more information.

### Request an Upgrade Patch (Subscription)

```note::
   Subscription
```

Users with a Liferay DXP subscription should update to the latest fix pack and/or request an upgrade patch in preparation for performing an upgrade. File a ticket here to start this process: [Help Center](link)

## Stability and Performance Tuning

For users with larger data sets upgrades can take a prohibitively long time, if mitigating steps are not taken in advance of executing the upgrade.

### Tune Database Performance

Users should consider adjusting the configurations for their database to optimize for upgrade efficiency before performing an upgrade. See this link on recommendations on how to do this. Users should also review their data and reduce its overall size by trimming data from the database that is unnecessary to perform the upgrade. See this link on some guidelines and tips to pruning a database of unnecessary data.

```tip::
   Don't forget to re-enable your typical production database configurations after your upgrade is complete.
```

### Tune the Search Engine

The search engine typically indexes regularly while Liferay DXP is running. However, this indexing can have a detrimental impact to upgrade performance if it is left on. Indexing should be disabled before performing an upgrade, and re-enabled once an upgrade is complete. See [this article](link) on how to do this.

## Executing the Upgrade

There are three primary methods to available to execute an upgrade. [Using a Liferay DXP Docker image](./basic-upgrade-steps.md#using-the-latest-docker-image), [Using a DXP Bundle](./basic-upgrade-steps.md#using-the-latest-bundle), and [Using the Liferay Upgrade Tool](./using-the-liferay-upgrade-tool.md). For larger installations and production environments we strongly recommend using the Liferay Upgrade Tool.

## Migrating Configurations and Settings

Once an upgrade is complete, you can begin migrating and updating any configurations over from your previous installation. These articles walk through common migration and update tasks that can be done post upgrade:

* [Migrating Configurations and Properties](../configuration-and-infrastructure/migrating-configurations-and-properties.md)
* [Updating the Database Driver](../configuration-and-infrastructure/updating-the-database-driver.md)
* [Updating the File Store](../configuration-and-infrastructure/updating-the-file-store.md)

If you are upgrading from an older version (7.1 and below) you may also need to [install Elasticsearch](../configuration-and-infrastructure/dxp-and-elasticsearch.md) to handle search indexing.

## Conclusion

Finally, review [Post-Upgrade Considerations](./post-upgrade-considerations.md) to ensure configurations and content are correct for your upgraded Liferay DXP installation. Your upgrade is now complete.

## Related Information

* [Basic Upgrade Steps](./basic-upgrade-steps.md)
* [Using the Upgrade Tool](./using-the-upgrade-tool.md)
