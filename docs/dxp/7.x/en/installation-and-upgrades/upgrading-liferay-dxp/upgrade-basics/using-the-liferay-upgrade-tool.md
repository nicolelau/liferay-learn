# Using the Database Upgrade Tool

The Liferay Database Upgrade Tool (upgrade tool) is a client program for upgrading DXP databases offline. Modifying a database while it's detached from DXP allows you to do these things:

* [Tune the database for upgrade operations](../upgrade-stability-and-performance/improving-database-upgrade-performance.md)
* [Prune unnecessary data](../upgrade-stability-and-performance/improving-database-upgrade-performance.md) (e.g., unneeded versions of Web Content, Documents, and more) to improve upgrade performance
* Resolve upgrade issue

The topics mentioned above are especially important for upgrading large, enterprise-level DXP environments safely and as quickly as possible. The [Upgrade Overview](./introduction-to-upgrading-liferay-dxp.md) introduces the topics and links to articles that demonstrate them. After you've accounted for the tasks described in these upgrade topics, you're ready to upgrade the database using the upgrade tool

```warning::
   **Always** back up your data and installation before upgrading. Testing the upgrade process on backup copies is advised.
```

## Installing and Configuring the Upgrade Tool

The Liferay DXP Tomcat Bundle includes the upgrade tool in this folder:

```
[Liferay Home]/tools/portal-tools-db-upgrade-client
```

The tool is available to download separately, as described in the table below.

| DXP Edition | Download Instructions |
| :---------- | :-------------------- |
| Liferay DXP (Subscription) | Go to the [*Downloads* page](https://customer.liferay.com/group/customer/downloads) and select the DXP version and the _Product/Service Packs_ file type. In the listing that appears, click _Download_ for the _Liferay DXP Upgrade Client_. |
| _Liferay DXP CE_ | Go to the [_Downloads_ page](https://www.liferay.com/downloads-community) and select _Download_ for _Liferay Portal Tools for 7.x_. |

You can a configure the upgrade before invoking the upgrade tool using properties files or at the start of the upgrade tool run using the tool's command line interface (CLI). Both ways produce properties files that specify the database connection, DXP and application server library locations, and required portal properties. To create the properties files ahead of time, follow the [Upgrade Tool Reference](../reference/upgrade-tool-reference.md). Otherwise, configure the upgrade on the fly as you run the upgrade tool next.

## Running the Upgrade Tool

The `db_upgrade.sh` script in the `[Liferay Home]/tools/portal-tools-db-upgrade-client` folder invokes the upgrade tool. The `--help` option describes the tool's usage.

```bash
db_upgrade.sh --help
```

Here are the default JVM parameters:

| Parameter | Default value | Comments |
| :-------- | :------------ | :------- |
| `file.encoding` | `UTF-8` | DXP file encoding |
| `user.country` | `US` | DXP user country  |
| `user.language` | `en` | DXP user language |
| `user.timezone` | `GMT` | DXP user timezone |
| `-Xmx` | `10240m` | Initial memory allocation |

You can override the defaults by entering a `-j` followed by your list of parameter values in double quotes. Here's an example:

```bash
db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.country=ES\
 -Duser.language=es -Duser.timezone=CET -Xmx8000m"
```

Here's how you run an upgrade using the tool:

1. Shutdown any DXP server that's using the database. 

1. [Set up the latest database driver](../configuration-and-infrastructure/updating-the-database-driver.md) in your new DXP installation.

1. On a command line, navigate to `[Liferay Home]/tools/portal-tools-db-upgrade-client`.

1. Execute the upgrade tool, overriding any JVM parameters as needed and optionally sending output to a log file. Here's an example command:

    ```bash
    db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.country=ES\
     -Duser.language=es -Duser.timezone=CET -Xmx8000m"\
     -l "output.log"
    ```

   If you haven't created the upgrade properties files, the upgrade tool prompts you for configuration values, and shows default values in parentheses. Here's an example interaction:

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

    To use the default value shown in a prompt, click enter.

    After configuration is complete, the upgrade starts. You can monitor the log file. Log messages are reported for the start and completion of each upgrade process.

1. After the upgrade completes, check the log for any database upgrade failures or errors. You can use [Gogo Shell commands](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) to troubleshoot them and finish the upgrades.

1. Prepare for testing DXP by undoing any upgrade-specific tuning and reviewing the [Post-Upgrade Considerations](./post-upgrade-considerations.md).

1. Start your server and validate DXP with its upgraded database.

You've upgraded your DXP database using the upgrade tool.

If this was a trial upgrade and you want to shorten the upgrade time, tune your database for upgrade (if you haven't already) and if you suspect there's unnecessary data you can prune from the database, go [prune it](../upgrade-stability-and-performance/improving-database-upgrade-performance.md). Then do another trial upgrade on DXP data backup copy. Otherwise, congratulations on your completed data upgrade!

## Additional Information

* [Upgrade Overview](./introduction-to-upgrading-liferay-dxp.md)
* [Post-Upgrade Considerations](./post-upgrade-considerations.md)
* [Gogo Shell commands](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md)
* [Backing Up](../../10-maintaining-a-liferay-dxp-installation/backing-up.md)