# Advanced Patching Tool Configuration

Using auto-discovery and working with the default profile `default.properties` is the easiest way to use the Patching Tool, and is great for single server installations. It generates a properties file (e.g., `default.properties`) that typically looks like this:

```properties
patching.mode=binary
war.path=../tomcat-9.0.17/webapps/ROOT/
global.lib.path=../tomcat-9.0.17/lib/ext/
liferay.home=../
```

The properties above (described fully in [Patching Tool Configuration Properties]) specify these things:

* Patching mode (binary or source)
* Path to your DXP WAR file
* Global library path (this is where the DXP Dependency JARs reside)
* Location of [Liferay Home](../../reference/liferay-home.md)

You can manually specify all of these properties, and HTTP proxy settings, too. They're all described in the [Patching Tool Properties](./patching-tool-properties.md) reference.

The [auto-discovery](../patching-basics/automatic-patching-tool-configuration.md) command bases the OSGi module framework paths on the Liferay Home (See the default [Liferay Home](../../reference/liferay-home.md) folder structure). If, however, you changed the OSGi module framework paths to something different than those under the default folder `[Liferay Home]/osgi`, you must manually specify the following properties: 

```properties
module.framework.core.path=path/to/modules/core/folder
module.framework.marketplace.path=path/to/modules/marketplace/folder
module.framework.modules.path=path/to/modules/modules/folder
module.framework.portal.path=path/to/modules/portal/folder
module.framework.static.path=path/to/modules/static/folder
```

Furthermore, if you're using DXP servers in a clustered environment or have DXP servers for different purposes, such as DevOps environments for development, testing, and integration testing, you'll want an easier way to manage patching all of them. That's where Patching Profiles are helpful. 

## Using Patching Profiles 

You can create profiles for multiple runtimes by running auto-discovery or creating profiles manually. To auto-discover a DXP runtime, run the Patching Tool with parameters like this: 

```bash
./patching-tool.sh [name of profile] auto-discovery [path/to/Liferay Home]
```

This runs the same discovery process and writes the profile information to a file called `[name of profile].properties`. Alternatively, you can create and edit profile property files in your `patching-tool/` folder. See [Patching Tool configuration properties](./patching-tool-properties.md) for a complete list of properties. 

Once you've created a profile, you can use it in your Patching Tool commands. For example, this command installs a patch using a profile file called `test-server.properties`:

```bash
./patching-tool.sh test-server install 
```

## Additional Information 

* [The Patching Process](./the-patching-process.md)

* [Installing the Patching Tool](./installing-the-patching-tool.md)

* [Comparing Patch Levels](../../reference/comparing-patch-levels.md) 

* [Keeping Up With Fix Packs and Service Packs](./keeping-up-with-patches.md)
