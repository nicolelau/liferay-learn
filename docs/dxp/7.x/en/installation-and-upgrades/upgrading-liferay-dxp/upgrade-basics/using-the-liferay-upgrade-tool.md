# Using the Liferay Upgrade Tool

The data upgrade tool upgrades the core and installed modules. You can use text files or the tool's command line interface to configure your upgrade.

## Installing and Configuring the Upgrade Tool

Liferay DXP bundles (e.g., the DXP Tomcat bundle) include the upgrade tool. If you installed Liferay DXP on an application server, you can download the upgrade tool separately:

* _Liferay DXP_ (Subscribers only): Go to the [*Downloads* page](https://customer.liferay.com/group/customer/downloads) and select the DXP version and the _Product/Service Packs_ file type. In the listing that appears, click _Download_ for the _Liferay DXP Upgrade Client_.

* _Liferay DXP CE_: Go to the [_Downloads_ page](https://www.liferay.com/downloads-community) and select _Download_ for _Liferay Portal Tools for 7.x_.

<!-- Straight forward configuration notes -->

Additional information on running and configuring the tool is available in the [Upgrade Tool Reference].

## Running the Upgrade Tool

```warning::
   Review the guidelines discussed in from the `Upgrade Overview <./upgrade-overview.md>`_before proceeding.
```

Here's how you run an upgrade using the tool:

<!-- steps -->

1. If data upgrade issues occur, see [Upgrading Modules Using Gogo Shell](./advanced-upgrade-topics/upgrading-modules-using-gogo-shell.md) to resolve issues per module.

1. Start your DXP server.

You have completed the upgrade and started your newly upgraded DXP server!

### Upgrade Tool Precautions

Only execute the upgrade process on a server with ideal memory, CPU, and database connection configurations. If executing an upgrade remotely using `ssh`, make sure to guard against interruptions:

* If you're executing the upgrade using `ssh` and connection is lost, connect again and check the upgrade logs since the process will continue running.
* If execution stopped during an upgrade process for DXP 7.1 or higher, restart the upgrade tool to continue the upgrade from that point. You can also use Gogo shell to [check module upgrade status](./upgrading-modules-using-gogo-shell.md#checking-upgrade-status).
* If execution stopped during a core upgrade process for DXP 7.0 or lower, you must [restore the data from a backup](../../10-maintaining-a-liferay-dxp-installation/backing-up.md) and start the upgrade again.

```warning::
   To prevent the tool's expanded command from growing too large for Windows, execute the upgrade tool script from the `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client` folder.
```

## Additional Information

* [Upgrade Tool Reference](../reference/upgrade-tool-reference.md)
