# Configuring the Data Upgrade Tool

The data upgrade tool upgrades the core and installed modules. You can use text files or the tool's command line interface to configure your upgrade. 

Liferay DXP bundles (e.g., the DXP Tomcat bundle) include the upgrade tool. If you installed Liferay DXP on an application server, you can download the upgrade tool separately:

* _Liferay DXP_ (Subscribers only): Go to the [*Downloads* page](https://customer.liferay.com/group/customer/downloads) and select the DXP version and the _Product/Service Packs_ file type. In the listing that appears, click _Download_ for the _Liferay DXP Upgrade Client_.

* _Liferay DXP CE_: Go to the [_Downloads_ page](https://www.liferay.com/downloads-community) and select _Download_ for _Liferay Portal Tools for 7.x_.

## Using the Command Line to Configure the Upgrade Tool

The core upgrade requires configuration. The simplest way is to use the upgrade tool to create your configuration files. Here's an example interaction with the upgrade tool's command line interface:

```
Please enter your application server (tomcat):
tomcat

Please enter your application server directory (../../tomcat-9.0.17):

Please enter your extra library directories (../../tomcat-9.0.17/bin):

Please enter your global library directory (../../tomcat-9.0.17/lib):

Please enter your portal directory (../../tomcat-9.0.17/webapps/ROOT):

[ db2 mariadb mysql oracle postgresql sqlserver sybase ]
Please enter your database (mysql):
mariadb

Please enter your database host (localhost):

(etc.)
```

```note::
   Omitted values use the defaults displayed in the parentheses.
```

## Manually Configuring the Upgrade Tool

You can also pre-configure the upgrade tool to set more values than the tool generates. Use these files in `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client/` to manually configure the core upgrade:

* `app-server.properties`: Specifies the server location and libraries.
* `portal-upgrade-database.properties`: Configures the database connection.
* `portal-upgrade-ext.properties`: Sets the rest of the portal properties that the upgrade requires. To replicate your current DXP server, you can copy your current portal properties (except your database properties) into this file. Before using your current properties, make sure to [update them for the current DXP version](./preparing-a-new-application-server.md#migrate-your-portal-properties).

### Configuring app-server.properties

Specify the following information to configure DXP's application server:

| Property Name | Meaning | Notes |
| --- | ---------- | --- |
| `dir` | The absolute path of the application server folder. | |
| `extra.lib.dirs` | A comma-delimited list of extra directories containing any binaries or resources to add to the class path. | Use all absolute paths or all paths relative to `dir`. |
| `global.lib.dir` | The application server's global library directory. | Use the absolute path or a path relative to `dir`. |
| `portal.dir` | The directory where portal is installed in your application server. | Use the absolute path or a path relative to `dir`. |
| `server.detector.server.id` | The ID of a supported application server. | Supported IDs: `jboss`, `jonas`, `resin`, `tomcat`, `weblogic`, `websphere`, `wildfly` |

Relative paths must use Unix style format. For example, the following properties are for Windows and use relative paths:

```properties
dir=D:\
extra.lib.dirs=Liferay/liferay-portal-master/tomcat-9.0.10/bin
global.lib.dir=Liferay/liferay-portal-master/tomcat-9.0.10/lib
portal.dir=Liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
server.detector.server.id=tomcat
```

As another example, the following properties are for Linux and use absolute paths:

```properties
dir=/
extra.lib.dirs=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/bin
global.lib.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/lib
portal.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
server.detector.server.id=tomcat
```

### Configuring portal-upgrade-database.properties

Specify the following information to configure the database you're upgrading. Note that these properties correspond to the [JDBC portal properties](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#JDBC) you'd use in a `portal-ext.properties` file.

* `jdbc.default.driverClassName`
* `jdbc.default.url`
* `jdbc.default.username`
* `jdbc.default.password`

See the latest [portal properties reference](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) for a reference on these values.

### Configuring portal-upgrade-ext.properties

Specify the following information to configure the upgrade: 

* `liferay.home`: The [LIFERAY_HOME folder](../../14-reference/01-liferay-home.md).

* `dl.store.impl`: The implementation for persisting documents to the document library store. This property is only mandatory if you're using a `*FileSystemStore` implementation. If you [updated this property in your `portal-ext.properties`](./preparing-a-new-application-server.md#configure-your-documents-and-media-file-store), copy the new value here. Otherwise, set the property one of these ways:

```properties
dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
dl.store.impl=com.liferay.portal.store.db.DBStore
dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
dl.store.impl=com.liferay.portal.store.s3.S3Store
```

* `hibernate.jdbc.batch_size`: The JDBC batch size used to improve performance (set to _250_ by default). _This property may improve upgrade performance, but it is not required._

### Example Upgrade Configurations

Here are example upgrade configuration files that you can customize and copy into `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client/`:

* `app-server.properties`:

    ```properties
    dir=../../tomcat-9.0.17
    global.lib.dir=/lib
    portal.dir=/webapps/ROOT
    server.detector.server.id=tomcat
    extra.lib.dirs=/bin
    ```

* `portal-upgrade-database.properties`:

    ```properties
    jdbc.default.url=jdbc:mysql://lportal62?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
    jdbc.default.username=root
    jdbc.default.password=
    ```

* `portal-upgrade-ext.properties`:

    ```properties
    liferay.home=/home/user/servers/liferay7
    module.framework.base.dir=/home/user/servers/liferay7/osgi
    dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
    ```

## Additional Information

* [Preparing a New Application Server](./preparing-a-new-application-server.md)
* [Using the Upgrade Tool](./using-the-upgrade-tool.md)
