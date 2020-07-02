# Upgrading to a New Liferay Portal CE Rolling Release

Fixes for Liferay Portal CE are delivered *rolling releases*. That is fixes for a Portal CE GA are published in all newer GA releases for that version of Poral CE. For example, fixes to CE 7.3 GA1 bugs are included in all newer CE 7.3 GA releases (i.e., GA2+). Here are the steps for upgrading a Portal CE installation to a new rolling release:

1. Shut down your Liferay Portal CE server.

1. Delete cache.

    Delete the `[Liferay Home]/osgi/state` folder.

    ```bash
    rm -rf [Liferay Home]/osgi/state
    ```

    Empty the `[Liferay Home]/work` folder.

    ```bash
    rm -rf work/*
    ```

    Delete the application server cache. Please consult the application vendor documentation on where where to find the cache.

    ```note::
       If a module's changes are only internal, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next DXP startup ensures that such modules reinstall with the appropriate state.
    ```

1. [Back up your DXP system](../../maintaining-a-liferay-dxp-installation/backing-up.md), including any files that you have added or edited.

1. Install the new Portal CE GA release files on top of your existing installation.

1. Replace the installation's `[Liferay Home]/data` folder and with the `[Liferay Home]/data` folder from your backup.

1. Merge the files that you have added and edited from your backup to your installation. Here are some commonly added/edited files:

    `portal-*.properties`: Portal properties files, such as `portal-ext.properties`.

    `/osgi/*.config`: OSGi configuration files.

    `web.xml`: Portal web application descriptor.

    `setenv.sh`: Application server configuration scripts.

    `/license/*`: Activation keys.

    `/log/*`: Log files.

1. Copy your apps, plugins and modules from your backup to your installation.

1. Start the Liferay Portal CE server again.

1. Run the `upgrade:check` [Gogo Shell command](https://help.liferay.com/hc/en-us/articles/360029070351-Using-the-Felix-Gogo-Shell) to list all modules whose data hasn't been upgraded.

1. Use [Gogo Shell commands](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) to upgrade data for these modules.

1. Reindex your search indexes in the Control Panel by navigating to *Search* &rarr; *Index Actions* and clicking *Execute* for *Reindex All Search Indexes.*

Congratulations! You've upgraded your Portal CE installation to the new rolling release!