# The Patching Process

The patching steps for DXP bundles and DXP application server installations are similar. Since DXP bundles include the Patching Tool preconfigured, you can apply patches right away. DXP application server installations, however, require installing and configuring the Patching Tool before patching.

```warning::
   **Always** `back up <../../backing-up.md>`_ your database and installation before patching.
```
   
If you're patching a DXP bundle, continue with the basic patching steps below. If you're patching DXP on an application server, [configure your environment for patching](#configuring-a-dxp-application-server-for-patching) _before_ following the basic patching steps.

## Basic Patching Steps

1.  Download your patch to your `patching-tool/patches` folder---don't unzip the patch.

    * Fix Packs and Service Packs are on the [Downloads](https://customer.liferay.com/downloads) page in the Help Center.
    * Hotfixes are in [Help Center](https://help.liferay.com/hc) tickets

1.  Shut down your application server.

    Reasons:

    * On Windows systems, files in use are locked, and can't be patched.
    * On Unix-style systems, you can usually replace files that are running, but the old ones reside in memory.

1.  Install the patch by running the `install` command:

    ```bash
    /liferay-home/patching-tool> ./patching-tool.sh  install
    One patch is ready to be installed. Applying dxp...
    Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
    Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%...100%]
    The installation was successful. One patch is installed on the system.
    ```

1.  Verify that the patch installation by executing the `info` command and checking the information on the currently installed patches:

    ```
    patching-tool>./patching-tool.sh info
    Loading product and patch information...
    Product information:
      * installation type: binary
      * build number: 7210
      * service pack version:
        - available SP version: 1
        - installable SP version: 1
      * patching-tool version: 2.0.13
      * time: 2019-12-06 20:26Z
      * host: 91WRQ72 (8 cores)
      * plugins: no plugins detected
    
    Currently installed patches:
    ...
    ```

1.  If you installed a Service Pack and its release notes mention [micro or minor schema/data changes](https://help.liferay.com/hc/en-us/articles/360030959231-Meaningful-Schema-Versioning), use the [Database Upgrade Tool](../upgrading-liferay-dxp/upgrade-basics/using-the-database-upgrade-tool.md) to apply minor changes (required) and any micro changes you want.

1.  Clear the OSGi state information by deleting all the files in your `[Liferay Home]/osgi/state` folder.

    ```note::
       If a module's changes are internal only, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next DXP startup makes sure that such modules reinstall with the appropriate state.
    ```

    ```warning::
       The `osgi/state` folder should only be deleted when working in a development environment or when applying a patch.
    ```

1.  Start the application server again.

Congratulations! Your DXP instance is patched and running.

## Patching DXP on an Application Server 

If you installed DXP on an application server, you must first install and configure the Patching Tool before patching DXP.

1.  [Install the Patching Tool](./installing-the-patching-tool.md) to your [Liferay Home](../../reference/liferay-home.md), if you have not yet installed it.

1.  [Configure the Patching Tool](./automatic-patching-tool-configuration.md) for your DXP installation by running the `auto-discovery` command.

    ```bash
    cd patching-tool 
    ./patching-tool.sh auto-discovery 
    ```

1. Make sure the properties in the generated `patching-tool/default.properties` file match your environment; correct any paths as needed.

    For example, if you're using an OSGi folder structure different from the typical one in [Liferay Home](../../reference/liferay-home.md), adjust your module framework properties.

    ```properties
    module.framework.core.path=path/to/modules/core/folder
    module.framework.marketplace.path=path/to/modules/marketplace/folder
    module.framework.modules.path=path/to/modules/modules/folder
    module.framework.portal.path=path/to/modules/portal/folder
    module.framework.static.path=path/to/modules/static/folder
    ``` 

1. Continue with the [basic patching steps](#basic-patching-steps) in the previous section.

Now you know how to patch a DXP Bundle and DXP installed on an application server.

## Additional Information 

* [Installing the Patching Tool](./installing-the-patching-tool.md)

* [Configuring the Patching Tool](./automatic-patching-tool-configuration.md)

* [Working With Patches](./working-with-patches.md)

* [Keeping Up With Fix Packs and Service Packs](./keeping-up-with-patches.md)

* Comparing Patch Levels (Coming soon)