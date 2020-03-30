# Advanced Patching

Here are some things you might need to do with patches:

* [Get Patch Information](#getting-patch-information)
* [Include support-info in Support Tickets](#including-support-info-in-support-tickets)
* [Uninstall Patches](#uninstalling-patches)
* [Handle collisions between patches and deployed plugins](#handling-collisions-between-patches-and-deployed-plugins)
* [Patch Cleanup](#patch-cleanup)
* [Patching Profiles](#patching-profiles)

## Understanding Patch Compatibility 

The Patching Tool applies fixes to Fix Packs automatically. If a new (fixed) version of a Fix Pack is released, install it with the Patching Tool. The Patching Tool uninstalls the old Fix Pack and installs the new version in its place. 

<!-- TODO where to put this next sentence? jhinkey -->

Security Fix Packs do not change your DXP installation's Fix Pack level.

Some Hotfixes depend on Fix Packs. If you attempt to install a Hotfix that depends on a Fix Pack, the Patching Tool notifies you. Go to the [Customer Portal](https://customer.liferay.com/downloads) and obtain the Fix Pack. Once all the necessary patches are downloaded to the `patches` folder, the Patching Tool installs them.

If you already have a Hotfix installed and a Fix Pack that contains that Hotfix is released, the Patching Tool manages this for you. Fix packs always supersede Hotfixes; so when you install a Fix Pack, any Hotfixes it contains are uninstalled and the Fix Pack version of the fix is installed in its place. 

## Getting Patch Information 

The Patching Tool's `info` command reports the following information:

- Service pack version 
- Patching tool version 
- Plugins detected 
- Patches Installed
- Patches available (in your `patching-tool/patches/` folder)

Here's an example execution:

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

Currently installed patches: dxp-2-7210

Available patches: dxp-2-7210, dxp-3-7210

Detailed patch list:
  [*-] dxp-2-7210 :: Installed; Won't be installed: dxp-3 contains the fixes included in this one :: Built for LIFERAY
  [ I] dxp-3-7210 :: Currently not installed; Will be installed. :: Built for LIFERAY
```

## Including support-info in Support Tickets

Providing your environment's information (e.g., hardware architecture) and patch level to Liferay Support is critical for reproducing your issues. Write your support information (including your patch level) to a file by executing this command: 

```bash
./patching-tool.sh support-info
```

The support information is written to file `patching-tool-support-info-actual-timestamp.txt` in your `patching-tool` folder. Please upload this file to the [Help Center](https://help.liferay.com/hc) ticket.

## Handling collisions between patches and deployed plugins

Some patches update files you might have customized via a plugin. The `list-collisions` command lists  JSP file differences (collisions) in OSGi fragment bundles:

```bash
./patching-tool.sh list-collisions
```

It is an alias for the following diff command:

```bash
./patching-tool.sh diff collisions files _base
```

`_base` is the literal patch level name. Collisions are only listed for
installed patches that contain source code files. 

If you removed certain patches or there is a collision of some kind use the"-force" argument to force the Patching Tool to install the currently available patches no matter what.

Example:

```bash
./patching-tool.sh profile_name install -force
```

## Uninstalling Patches 

The `revert` command removes ALL patches from the DXP installation, bringing it back to the original installed version (e.g., GA1).

```bash
./patching-tool.sh revert
```

To bring your installation up to a desired patch level, install the applicable fix packs.

> **Tip:** It's helpful to store your patches in a convenient location in case you want to restore a patch level after reverting. You can always download fix packs again from the [Customer Portal](https://customer.liferay.com/downloads).

Now you know how to uninstall patches. 

## Patch Cleanup 

A patched installation is large because the patch files (files used for metadata, verification, and validation) are stored inside the web application's `WEB-INF` folder by default. The Patching Tool requires these files for installing new patches and restoring previous patch levels; so you must hold on to them. You can reduce your DXP installation size, however, by extracting the patch files out of the installation. When you're ready to install new patches or restore a previous patch level, you can safely restore the patch files. 

Here are the patch cleanup topics:

* [Separating Patch Files from the Installation](#separating-patch-files-from-the-installation)
* [Restoring Separated Patch Files](#restoring-separated-patch-files)

### Separating Patch Files from the Installation

The Patching Tool's `separate` command extracts the patche files from the default location (the web application's `WEB-INF` folder) and packages them in a ZIP file. Here's the command:

```bash
./patching-tool.sh separate [separation_name] 
```

The command moves the patch files from the patch file default location into a `liferay-patching-files-[separation-name].zip` file in the Patching Tool's `patches` folder. Store the ZIP file elsewhere to reduce your installation's size. 

**WARNING:** If DXP is separated from its patches in this way, only the following Patching Tool commands can be used:

- `auto-discovery`
- `info`
- `setup`

All other commands return this:

```bash
This installation does not include data for patching. Please copy the
liferay-patching-files-[separation-name].zip file into the 'patches' directory
and run patching-tool setup. 
```

### Restoring Separated Patch Files
 
When you need to patch DXP again, you must restore the separated patch files. 

1. Copy the `liferay-patching-files-[separation-name].zip` back to the Patching Tool's `patches` folder

1. Run this command:

    ```bash 
    ./patching-tool.sh setup
    ```

The patch files are restored to the installations patch file default location. All of the Patching Tool commands are available. 

You now know how to store patch files so that your DXP installation doesn't take up unnecessary space. And you know how to restore the patch files so that you can install new patches. 

## Using Patching Profiles 

You can create profiles for multiple runtimes by running auto-discovery or creating profiles manually. To auto-discover a DXP runtime, run the Patching Tool with parameters like this: 

```bash
./patching-tool.sh [name of profile] auto-discovery [path/to/Liferay Home]
```

This runs the same discovery process and writes the profile information to a file called `[name of profile].properties`. Alternatively, you can create and edit profile property files in your `patching-tool/` folder. See [Patching Tool configuration properties](../../14-reference/08-patching-tool-configuration-properties.md) for a complete list of properties. 

Once you've created a profile, you can use it in your Patching Tool commands. For example, this command installs a patch using a profile file called `test-server.properties`:

```bash
./patching-tool.sh test-server install 
```

## Additional Information

* [Comparing Patch Levels](../../14-reference/07-comparing-patch-levels.md) 

* [Keeping Up With Fix Packs and Service Packs](./08-keeping-up-with-fix-packs.md)

* [The Patching Process](./03-the-patching-process.md)

* [Patching a DXP WAR](./patching-dxp-on-an-application-server.md)
