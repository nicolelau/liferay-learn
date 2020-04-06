# Updating DXP CE

Fixes for DXP CE are delivered as new DXP CE GA releases. For example, DXP CE 7.2 GA2 fixes bugs that exist in DXP CE 7.2 GA1. There is no Patching Tool for DXP CE. Here are the steps for updating to your DXP CE installation to a new GA release:

1. [Back up your DXP system](../../backing-up.md) in case you want to revert to it.

1. Shut down the application server.

1. Install the new DXP CE GA release to a new location or source control branch.

1. Migrate your DXP CE files to the new location, making sure to point your new server at your current DXP database.

1. Clear the OSGi state information by deleting all the files in the `[Liferay Home]/osgi/state` folder.

    ```note::
       If a module's changes are internal only, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next DXP startup makes sure that such modules reinstall with the appropriate state.
    ```

    ```warning::
       The `osgi/state` folder should only be deleted when working in a development environment or when applying a patch.
    ```

1. Start the application server again.

1. Run the `upgrade:check` [Gogo Shell command](https://help.liferay.com/hc/en-us/articles/360029070351-Using-the-Felix-Gogo-Shell) to list all modules whose data hasn't been upgraded.

1. Use [Gogo Shell commands](../../upgrading-liferay-dxp/upgrade-stability-and-performance/upgrading-modules-using-gogo-shell-commands.md) to upgrade data for these modules.

Congratulations! You've updated your DXP CE installation to the new GA release!