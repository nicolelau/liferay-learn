# Upgrade Tool Reference

This article provides an overview of the upgrade tool within your application server.

Start the upgrade tool using the `db_upgrade.sh` script in the `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client` folder (`db_upgrade.bat` on Windows). Here are the core upgrade stages:

1. Show the upgrade patch level
1. Execute the core upgrade processes
1. Execute the core verifiers

## Upgrade Tool Usage

This command prints the upgrade tool usage:

```bash
db_upgrade.sh --help
```

### Default Parameters

Here are the tool's default Java parameters:

```bash
-Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx2048m
```

The `-j` option overrides the JVM parameters. For example, these options set the
JVM memory to 10GB, which is a good starting point for this process type:

```bash
db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx10240m"
```

The `-l` option specifies the tool's log file name:

```bash
db_upgrade.sh -l "output.log"
```

### Upgrade Tool Memory Allocation

Make sure to provide adequate memory for the database upgrade tool's Java process. Make sure to also set the file encoding to UTF-8 and the time zone to GMT.

Using a test scenario with a 3.2 GB database and a 15 GB Document Library, the following Java process settings were optimal:

* Xmx 8 GB RAM
* File encoding UTF-8
* User time zone GMT

Here is the `db_upgrade.sh` command corresponding to these settings:

```bash
db_upgrade.sh -j "-Xmx8000m -Dfile.encoding=UTF-8 -Duser.timezone=GMT"
```

## Configuring the Upgrade Tool

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

### Manual Configuration

You can also pre-configure the upgrade tool to set more values than the tool generates. Use these files in `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client/` to manually configure the core upgrade:

* `app-server.properties`: Specifies the server location and libraries.
* `portal-upgrade-database.properties`: Configures the database connection.
* `portal-upgrade-ext.properties`: Sets the rest of the portal properties that the upgrade requires. To replicate your current DXP server, you can copy your current portal properties (except your database properties) into this file. Before using your current properties, make sure to [update them for the current DXP version](./preparing-a-new-application-server.md#migrate-your-portal-properties).

#### Configuring app-server.properties

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

#### Configuring portal-upgrade-database.properties

Specify the following information to configure the database you're upgrading. Note that these properties correspond to the [JDBC portal properties](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#JDBC) you'd use in a `portal-ext.properties` file.

* `jdbc.default.driverClassName`
* `jdbc.default.url`
* `jdbc.default.username`
* `jdbc.default.password`

See the latest [portal properties reference](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) for a reference on these values.

#### Configuring portal-upgrade-ext.properties

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

#### Example Upgrade Configurations

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

## Upgrade Tool Runtime Options

Here are all the upgrade tool command line options:

**--help** or **-h**: Prints the tool's help message.

**--jvm-opts** or **-j** + **[arg]**: Sets any JVM options for the upgrade
process.

**--log-file** or **-l** + **[arg]**: Specifies the tool's log file name---the
default name is `upgrade.log`.

**--shell** or **-s**: Automatically connects you to the Gogo shell after
finishing the upgrade process.
