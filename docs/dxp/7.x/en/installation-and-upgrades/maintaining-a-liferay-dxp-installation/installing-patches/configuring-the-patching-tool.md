# Configuring the Patching Tool 

Liferay DXP bundles ship with the Patching Tool preconfigured. If any of the following scenarios describes your DXP installation, however, you must configure the Patching Tool:

- Installed DXP manually on an existing application server
- Customized your DXP folder structure
- Running in a cluster

The following sections demonstrate how to configure the Patching Tool using auto-discovery (easiest) and using other ways DXP runtime environments may require. 

* [Automatic Configuration Using `auto-discovery`](#automatic-configuration-using-auto-discovery) 
* [Editing Configurations](#editing-configurations) 
* [Troubleshooting](#troubleshooting)

## Automatic Configuration Using Auto-Discovery

The Patching Tool is configured using a properties file (the default is `patching-tool/default.properties`). The tool's `auto-discovery` command generates or updates the default properties file based on local DXP files it discovers. 

Run the auto-discovery command in your `patching-tool/` folder: 

```bash
./patching-tool.sh auto-discovery
```

By default the Patching Tool looks for DXP files in the parent folder. If DXP is not installed in the parent folder, specify its location: 

```bash
./patching-tool.sh auto-discovery /path/to/liferay-dxp
```

You can test the configuration by running the `info` command. When the Patching Tool is configured, running the `info` command reports product information and patch information like this: 

``` 
/patching-tool>./patching-tool.sh info
Loading product and patch information...
Product information:
  * installation type: binary
  * build number: 7210
  * service pack version:
    - available SP version: 1
    - installable SP version: 1
  * patching-tool version: 2.0.13
  * time: 2019-12-05 14:02Z
  * host: 91WRQ72 (8 cores)
  * plugins: no plugins detected
  ...
```

The Patching Tool is ready to install patches! 

Note: If auto-discovery can't find your DXP files, follow the [troubleshooting steps](#troubleshooting). If your DXP files still aren't discovered or you're using a custom folder structure for DXP, manually edit the Patching Tool configuration. 

## Editing Configurations 

Patching Tool configuration files (e.g., `default.properties`) typically looks like this:

```properties
patching.mode=binary
war.path=../tomcat-9.0.17/webapps/ROOT/
global.lib.path=../tomcat-9.0.17/lib/ext/
liferay.home=../
```

The properties above (described fully in [Patching Tool Configuration Properties](../../reference/patching-tool-configuration-properties.md)) specify these things:

* Patching mode (binary or source)
* Path to your DXP WAR file
* Global library path (this is where the DXP Dependency JARs reside)
* Location of [Liferay Home](../../reference/liferay-home.md)

The auto-discovery command bases the OSGi module framework paths on the Liferay Home (See the default [Liferay Home](../../reference/liferay-home.md) folder structure). If, however, you changed the OSGi module framework paths to something different than those under the default folder `[Liferay Home]/osgi`, you must manually specify the following properties: 

```properties
module.framework.core.path=path/to/modules/core/folder
module.framework.marketplace.path=path/to/modules/marketplace/folder
module.framework.modules.path=path/to/modules/modules/folder
module.framework.portal.path=path/to/modules/portal/folder
module.framework.static.path=path/to/modules/static/folder
```

Using auto-discovery and working with the default profile `default.properties` is the easiest way to use the Patching Tool, and is great for single server installations. But if you're using DXP servers in a clustered environment or have DXP servers for different purposes, such as DevOps environments for development, testing, and integration testing, you'll want an easier way to manage patching all of them. That's where Patching Profiles are helpful. See [Patching Profiles](TODO) for more information.

## Troubleshooting 

If you specify the wrong DXP location to auto-discovery or DXP is not in the parent folder, the Patching Tool can't find the [Liferay Home](../../14-reference/01-liferay-home.md) and reports an error like this: 

```
The .liferay-home has not been detected in the given directory tree.

Configuration:
patching.mode=binary
war.path=../tomcat-9.0.17/webapps/ROOT/
global.lib.path=../tomcat-9.0.17/lib/ext/
liferay.home=**[please enter manually]**

The configuration hasn't been saved. Please save this to the default.properties file.
```

Here are ways to resolve the Liferay Home issue:

- Specify the Liferay Home path in the `default.properties` file.
- If the Liferay Home is in the Patching Tool's tree, create a `.liferay-home` file in the Liferay Home folder and re-run the  auto-discovery process. 

## Additional Information 

* [Comparing Patch Levels](../../reference/comparing-patch-levels.md) 

* [The Patching Process](./the-patching-process.md)

* [Installing the Patching Tool](./installing-the-patching-tool.md)

* [Keeping Up With Fix Packs and Service Packs](./keeping-up-with-fix-packs.md)
