# Migrate Your OSGi Configurations (7.0+)

Copy any [OSGi configuration files](https://help.liferay.com/hc/en-us/articles/360029131651-Understanding-System-Configuration-Files) (i.e., `.config` files) to your new application server's `[LIFERAY_HOME]/osgi/configs` folder.

## Update Your Portal Properties

Update any portal properties files that you may have configured for DXP 7.2 (e.g., `portal-setup-wizard.properties` and `portal-ext.properties`).

For features that use OSGi Config Admin in the new DXP version, you must [convert your properties to OSGi configurations](#convert-applicable-properties-to-osgi-configurations). As you do this, you must account for property changes in all versions of Liferay DXP since your current version up to and including the new version.

### Migrating Existing Portal Properties

If you're coming from a version prior to Liferay Portal 6.2, start with these
property-related updates:

* If you're on Liferay Portal 6.1, [adapt your properties to the new defaults that Liferay Portal 6.2 introduced](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay#review-the-liferay-62-properties-defaults).

* If you have a sharded environment, [configure your upgrade to generate a non-sharded environment](./upgrading-a-sharded-environment.md).

* Liferay's image sprite framework is deprecated as of 7.2 and is disabled by default. The framework requires scanning plugins for image sprites. If you don't use the framework, there's no need for it to scan for images sprites. If you use the framework yourself, enable it by overriding the default `sprite.enabled` portal property (since 7.2) value with the following setting in your [`portal-ext.properties`](../../14-reference/03-portal-properties.md) file:

    ```properties
    sprite.enabled=true
    ```

```note::
   You can build image sprites using any framework you like and deploy them in your plugins.
```

### Convert Applicable Properties to OSGi Configurations

Properties in modularized features have changed and must now be deployed separately in [OSGi configuration files](https://help.liferay.com/hc/en-us/articles/360029131591-System-Settings#exporting-and-importing-configurations) (OSGi Config Admin).

For example, use these steps to create a `.config` file specifying a root file location for a Simple File Store or Advanced File Store:

1. Create a `.config` file named after your store implementation class.

    Simple File Store:
    `com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.config`

    Advanced File Store:
    `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config`

1. Set the following `rootDir` property and replace `{document_library_path}` with your file store's path.

    ```properties
    rootDir="{document_library_path}"
    ```

1. Copy the `.config` file to your `[LIFERAY_HOME]/osgi/configs` folder.

```tip::
   The Control Panel's _System Settings_ screens (under _Configuration_) are the most accurate way to create `.config` files. Use them to [export a screen's configuration](https://help.liferay.com/hc/en-us/articles/360029131591-System-Settings#exporting-and-importing-configurations) to a `.config` file.
```

#### Using Blade CLI to Find Migrated Properties

The [Blade CLI](https://help.liferay.com/hc/en-us/articles/360029147071-Blade-CLI) is a tool that can be used to analyze your `portal-ext.properties` file to check for removed properties, or properties that have migrated to OSGi configurations. It is helpful to use this tool when upgrading from older versions for this purpose.

The `blade upgradeProps` command is used in the following format:

```cmd
blade upgradeProps -p {old_liferay_home_path}/portal-ext.properties -d {new_liferay_home_path}
```

Running this command will provide the names of migrated (or removed) properties, as well as where they were migrated to. The command will provide output like the following:

```
ERROR [main][VerifyProperties:161] Portal property "layout.first.pageable[link_to_layout]" is obsolete
ERROR [main][VerifyProperties:136] Portal property "journal.article.check.interval" was modularized to com.liferay.journal.web as "check.interval"
```
