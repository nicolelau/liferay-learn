# Post-Upgrade Considerations

After upgrading the DXP database, you should configure DXP for production again and check up on feature changes that might affect your DXP instance.

## Re-enable Production Settings

Now that your done upgrading your DXP database, you should re-enable your production settings.

### Search Indexing

Before starting the upgrade process in your new installation, you must disable indexing to prevent upgrade process performance issues that arise when the indexer attempts to re-index content.

To disable indexing, create a file called `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config` in your `[LIFERAY_HOME]/osgi/configs` folder and add the following content:

```properties
indexReadOnly="true"
```

After you complete the upgrade, re-enable indexing by removing the `.config` file or setting `indexReadOnly="false"`.

```note::
If you upgraded from an older version (7.1 and below) consider [installing Elasticsearch](https://help.liferay.com/hc/en-us/articles/360029031631-Elasticsearch) to handle search indexing.
```

### Database Configurations

Prior to upgrading your Liferay database, you tuned it for upgrade (see [Tuning for the Data Upgrade](../upgrade-stability-and-performance/improving-database-upgrade-performance.md)). Now that the upgrade is complete, restore the production database tuning you used previously.

```note::
   If you migrated from a sharded environment during your data upgrade, then you must make more adjustments to your configurations to complete the transition to virtual instances. See the [Upgrade and Update Properties](../other-upgrade-scenarios/upgrading-a-sharded-environment.md#Upgrade-and-Update-Properties) section for more information.
```

## Accounting for Feature Changes

Features and behaviors change with new DXP versions. Investigate the how the following changes affect your DXP instance.

### Enable Web Content View Permissions

Prior to DXP 7.1, all users could view Web Content articles by default. Now view permissions are checked by default instead. Here are the main options for opening view permissions:

**Option 1:** Open view permissions for all web content articles by navigating to _Control Panel_ → _Configuration_ → _System Settings_ → _Web Content_ → _Virtual Instance Scope_ → _Web Content_ and de-selecting _Article view permissions check enabled_.

![Disable the permissions check for viewing web content by scrolling down in the Web Content menu in the System Settings.](./post-upgrade-considerations/images/02.png)

**Option 2:** If not many Web Content articles exist, then edit view permissions per Web Content article per role.

### Check Web Content Images

Upgrading to DXP 7.2 moves Web Content images to the Document Library and then deletes their former table, `JournalArticleImage`. To ensure this process went smoothly, check your Web Content articles and verify that their images still show correctly.

### Account for Deprecations and Features in Maintenance Mode 

If you haven't done so already, review the [deprecations](../reference/deprecations-in-liferay-dxp-7-3.md) and [features in maintenance mode](../reference/features-in-maintenance-mode.md) and plan for handling how they affect your DXP instance.

## Conclusion

Once you've completed all necessary post-upgrade tasks, your Liferay DXP installation is ready for normal operations as before. Congratulations!