# Updating Liferay Portal CE

Fixes for Liferay Portal CE are delivered as new Portal CE GA releases. For example, CE 7.2 GA2 fixes bugs that exist in CE 7.2 GA1. There is no Patching Tool for Liferay Portal CE. Here are the steps for updating to a new GA release:

1. [Back up your DXP system](./backing-up.md)

1. Install the new Portal CE GA release to a new location.

1. Copy the [Liferay Home files](../../maintaining-a-liferay-dxp-installation/backing-up.md#liferay-home) and [application server files](../../maintaining-a-liferay-dxp-installation/backing-up.md#application-server) from your installation backup to your new DXP installation.

1. Delete any DXP cache that you copied from the backup.

    Delete the `[Liferay Home]/osgi/state` folder.

    ```bash
    rm -rf [Liferay Home]/osgi/state
    ```

    Empty the `[Liferay Home]/work` folder.

    ```bash
    rm -rf work/*
    ```

1. Delete any application server cache that you copied from the backup. Please consult the application vendor documentation on where where to find the cache.

    ```note::
       If a module's changes are only internal, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next DXP startup ensures that such modules reinstall with the appropriate state.
    ```

1. Start your new Portal CE GA instance.

1. Run the [`upgrade:check`](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md#checking-upgrade-status) [Gogo Shell](https://help.liferay.com/hc/en-us/articles/360029070351-Using-the-Felix-Gogo-Shell) command to list all modules whose data hasn't been upgraded.

1. Use the [`upgrade:module [module name]`](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md#executing-module-upgrades) Gogo Shell command to upgrade data for these modules.

Congratulations! You've updated to the new Portal CE GA release!