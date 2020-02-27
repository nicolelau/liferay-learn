# Updating the File Store

<!-- What is the main objective of this article? Helping users who are on 7.0 and below know that file stores have changed for 7.2+? -->

General document store configuration (e.g., `dl.store.impl=[File Store Impl Class]`) continues to be done using `portal-ext.properties`. But here's what's changed for document storage:

* Store implementation class package names changed from `com.liferay.portlet.documentlibrary.store.*` in Liferay Portal 6.2 to `com.liferay.portal.store.*` in DXP 7.0+. Make sure your `portal-ext.properties` file sets `dl.store.impl` in one of these ways:

    ```properties
    dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
    dl.store.impl=com.liferay.portal.store.db.DBStore
    dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
    dl.store.impl=com.liferay.portal.store.s3.S3Store
    ```

* JCR Store was deprecated in DXP 7.0. The [Document Repository Configuration](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration) documentation describes other store options. [Migrate to a supported document store](https://help.liferay.com/hc/en-us/articles/360029131691-Server-Administration) before upgrading your data.

* CMIS Store was deprecated in 7.0.10 Fix Pack 14 and was removed in DXP 7.2. The [Document Repository Configuration](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration) documentation describes other store options. [Migrate to a supported document store](https://help.liferay.com/hc/en-us/articles/360029131691-Server-Administration) before upgrading your data.

* Since DXP 7.0, document store type-specific configuration (e.g., specific to Simple File Store, Advanced File Store, S3, etc.) is done in the Control Panel at _Configuration_ → _System Settings_ → _File Storage_, or using OSGi configuration files (`.config` files). Type-specific configuration is no longer done using `portal-ext.properties`.

The [Document Repository Configuration](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration) provides more document store configuration details.
