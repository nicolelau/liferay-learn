# The Patching Process 

DXP bundles include the Patching Tool preconfigured. Here are the steps for patching a DXP Bundle. 

1.  [Back up your DXP system](../backing-up.md) in case you want to revert to it. 

1.  Download the patch you need (fix pack or service pack) from the [Customer Portal](https://customer.liferay.com/downloads) or from the ticket you opened in the [Help Center](https://help.liferay.com/hc).

1.  Copy the patch to your `[Liferay Home]/patching-tool/patches` folder---don't unzip the patch.

1.  Shut down your application.

    Reasons:

    * On Windows systems, files in use are locked, and can't be patched.
    * On Unix-style systems, you can usually replace files that are running, but the old ones reside in memory.

1.  Install the patch by running the `patching-tool install` command:

    ```bash
    /liferay-home/patching-tool> patching-tool  install
    One patch is ready to be installed. Applying dxp...
    Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
    Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%...100%]
    The installation was successful. One patch is installed on the system.
    ```

1.  Verify that the patch installed by executing the `patching-tool info` command:

    ```
    patching-tool>patching-tool info
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

1.  Check for [micro or minor schema/data changes](https://help.liferay.com/hc/en-us/articles/360030959231-Meaningful-Schema-Versioning) in the service pack release notes. (Service packs only)

    Use the [Upgrade Tool](../upgrading-liferay-dxp/upgrade-basics/using-the-database-upgrade-tool.md) to apply minor changes (required) and any micro changes you want.

1.  Clear the OSGi state information by deleting all the files in your `[Liferay Home]/osgi/state` folder.

    ```note::
       If a module's changes are internal only, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next DXP startup makes sure that such modules reinstall with the appropriate state.
    ```

    ```warning::
       The `osgi/state` folder should only be deleted when working in a development environment or when applying a patch.
    ```

1.  Restart the application server

Congratulations! Your DXP instance is patched and running.

## Additional Information 

* [Patching a DXP WAR:](./patching-the-dxp-war.md)

* [Installing the Patching Tool](./installing-the-patching-tool.md)

* [Configuring the Patching Tool](./configuring-the-patching-tool.md)

* [Working With Patches](./working-with-patches.md)

* [Keeping Up With Fix Packs and Service Packs](./keeping-up-with-fix-packs.md)

* Comparing Patch Levels (Coming soon)