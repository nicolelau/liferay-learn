# Migrating Configurations and Properties 

Your current DXP installation specifies properties (e.g., portal properties and system properties) and OSGi configurations (7.0+) that set up your DXP instance to fit your needs. To use these settings in your new DXP installation, you must update and migrate them. Here are some of the settings changes:

- New default values
- Property removed
- Property replaced by a new property
- Property replaced by OSGi configuration 

Updating your settings for your new DXP installation is part of the upgrade.

## Overview

* [Property Updates](#property-updates)
* [Converting Properties to OSGi Config Admin](#converting-properties-to-osgi-config-admin)
* [Using Blade CLI to Find Migrated Properties](#using-blade-to-find-migrated-properties)
* [Migrating Properties and Configurations](#migrating-properties-and-configurations)

## Property Updates

If you're coming from a version prior to Liferay Portal 6.2, start with these
property-related updates:

* If you're on Liferay Portal 6.1, [adapt your properties to the new defaults that Liferay Portal 6.2 introduced](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay#review-the-liferay-62-properties-defaults).

* If you have a sharded environment, [configure your upgrade to generate a non-sharded environment](../other-upgrade-scenarios/upgrading-a-sharded-environment.md).

* Liferay's image sprite framework is deprecated as of 7.2 and is disabled by default. The framework requires scanning plugins for image sprites. If you don't use the framework, there's no need for it to scan for images sprites. If you use the framework yourself, enable it by overriding the default `sprite.enabled` portal property (since 7.2) value with the following setting in your [`portal-ext.properties`](../../14-reference/03-portal-properties.md) file:

    ```properties
    sprite.enabled=true
    ```

```note::
   You can build image sprites using any framework you like and deploy them in your plugins.
```

## Converting Properties to OSGi Config Admin

Properties in modularized features have changed and are now deployed in [OSGi configuration files](https://help.liferay.com/hc/en-us/articles/360029131591-System-Settings#exporting-and-importing-configurations) (OSGi Config Admin).

For example, in 6.2, the Simple File Store used this portal property to specify the store root directory:

```properties
dl.store.file.system.root.dir=${liferay.home}/data/document_library
```

Now the store is configured in a `.config` file called `com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.config` that specifies the root directory with a setting like this:

```properties
rootDir="{document_library_path}"
```

The `.config` files are deployed in the `[Liferay Home]/osgi/configs` folder.

```tip::
   The Control Panel's _System Settings_ screens (under _Configuration_) manage the OSGi Config Admin values. These screens are the most accurate way to create `.config` files. Find the screen that configures the feature you want to configure, click _Save_, and then use the options button to [export the screen's configuration](https://help.liferay.com/hc/en-us/articles/360029131591-System-Settings#exporting-and-importing-configurations) to a `.config` file.
```

## Using Blade CLI to Find Modified Properties

The [Blade CLI](https://help.liferay.com/hc/en-us/articles/360029147071-Blade-CLI) tool's `upgradeProps` command checks your portal properties file for removed properties and properties that have migrated to OSGi configurations.

The `blade upgradeProps` command is used in the following format:

```cmd
blade upgradeProps -p {old_liferay_home_path}/portal-ext.properties -d {new_liferay_home_path}
```

This command reports the names of modified properties, as well as how they must be migrated to the new DXP version. Here is a command output example:

```
ERROR [main][VerifyProperties:161] Portal property "layout.first.pageable[link_to_layout]" is obsolete
ERROR [main][VerifyProperties:136] Portal property "journal.article.check.interval" was modularized to com.liferay.journal.web as "check.interval"
```

## Migrating Properties and Configurations

After you've updated your properties and configurations, set them up in your new DXP installation.

| Files | Destination Folder |
| :--- | :----------------- |
| `.config` files | `[Liferay Home]/osgi/configs` |
| `portal-ext.properties` | `[Liferay Home]` |
| `system-ext.properties` | `[Liferay Home]` |

## Next Steps

Your Liferay settings are ready to use in your new DXP installation. Next, make these preparations:

* [Update the Database Driver](./updating-the-database-driver.md)
* [Update the File Store](./updating-the-file-store.md)