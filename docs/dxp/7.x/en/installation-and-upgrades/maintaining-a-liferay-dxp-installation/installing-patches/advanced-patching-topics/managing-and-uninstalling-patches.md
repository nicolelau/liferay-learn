# Uninstalling and Managing Patches

DXP patch information is retained in the installation by default. This facilitates restoring DXP to previous patch levels (uninstalling patches). Patch files and information, however, can take up lots of space. Here you'll learn how to uninstall patches and keep your environment lean by separating patch files from your installation, and restoring them back to your installation when you need them.

## Uninstalling Patches 

The `revert` command removes ALL patches from the DXP installation, bringing it back to the original installed version (e.g., GA1).

```bash
./patching-tool.sh revert
```

To bring your installation up to a desired patch level, install the applicable fix packs.

```tip::
It's helpful to store your patches in a convenient location in case you want to restore a patch level after reverting. You can always download fix packs again from the `Customer Portal <https://customer.liferay.com/downloads>`_.
```

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