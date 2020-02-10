# Using the Upgrade Tool

This article provides an overview of how to use the upgrade tool within your application server.

Start the upgrade tool using the `db_upgrade.sh` script in the `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client` folder (`db_upgrade.bat` on Windows). Here are the core upgrade stages:

1. Show the upgrade patch level
1. Execute the core upgrade processes
1. Execute the core verifiers

## Upgrade Tool Usage

This command prints the upgrade tool usage:

```bash
db_upgrade.sh --help
```

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

### Memory Allocation

Make sure to provide adequate memory for the database upgrade tool's Java process. Make sure to also set the file encoding to UTF-8 and the time zone to GMT.

Using a test scenario with a 3.2 GB database and a 15 GB Document Library, the following Java process settings were optimal:

* Xmx 8 GB RAM
* File encoding UTF-8
* User time zone GMT

Here is the `db_upgrade.sh` command corresponding to these settings:

```bash
db_upgrade.sh -j "-Xmx8000m -Dfile.encoding=UTF-8 -Duser.timezone=GMT"
```

### Upgrade Tool Options

Here are all the upgrade tool command line options:

**--help** or **-h**: Prints the tool's help message.

**--jvm-opts** or **-j** + **[arg]**: Sets any JVM options for the upgrade
process.

**--log-file** or **-l** + **[arg]**: Specifies the tool's log file name---the
default name is `upgrade.log`.

**--shell** or **-s**: Automatically connects you to the Gogo shell after
finishing the upgrade process.

### Ensuring the Upgrade Runs Smoothly

Only execute the upgrade process on a server with ideal memory, CPU, and database connection configurations. If executing an upgrade remotely using `ssh`, make sure to guard against interruptions:

* If you're executing the upgrade using `ssh` and connection is lost, connect again and check the upgrade logs since the process will continue running.
* If execution stopped during an upgrade process for DXP 7.1 or higher, restart the upgrade tool to continue the upgrade from that point. You can also use Gogo shell to [check module upgrade status](./upgrading-modules-using-gogo-shell.md#checking-upgrade-status).
* If execution stopped during a core upgrade process for DXP 7.0 or lower, you must [restore the data from a backup](../../10-maintaining-a-liferay-dxp-installation/backing-up.md) and start the upgrade again.

**Warning:** To prevent the tool's expanded command from growing too large for Windows, execute the upgrade tool script from the `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client` folder.

## Additional Information

* [Executing Post-Upgrade Tasks](./executing-post-upgrade-tasks.md)
* [Upgrading Modules Using Gogo Shell](./upgrading-modules-using-gogo-shell.md)
