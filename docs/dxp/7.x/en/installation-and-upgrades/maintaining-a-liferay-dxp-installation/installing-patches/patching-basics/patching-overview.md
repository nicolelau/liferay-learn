# Patching Overview 

As you develop and maintain DXP instances, you'll want to update them with the latest fixes. Liferay aggregates fixes and makes them available in ZIP files called _patches_.

## Patch Types 

There are several different kinds of patches, and they serve different purposes. There are patches for critical security issues, patches for larger numbers of the most recent issues, and patches that include the latest versions of the Upgrade Tool and DXP-bundled Marketplace applications. And of course we provide a way to request resolutions to business critical issues fast. The [Patch Types](./understanding-patch-types) article explains all of the patch options.

## The Patching Process and Patching Tool

Once you have a patch you need you can use Liferay's Patching Tool to apply it. [The Patching Process](./the-patching-process.md) demonstrates the basic patching steps for patching DXP safely and comprehensively.

## Configuring the Patching Tool 

The [`patching-tool.sh auto-discovery` command](./automatic-patching-tool-configuration.md) configures the tool automatically for Tomcat bundles and common app server configurations. For DXP installation variations, there's [Advanced Patching Tool configuration](../advanced-patching-topics/advanced-patching-tool-configuration.md) and a Patching Tool [property reference](../advanced-patching-topics/patching-tool-properties.md).

## Advanced Patching

As you start applying patches regularly, adding new DXP environments, or even working with the DXP source code, you'll want to know the best way to manage patching. Some advanced patching topics include:

* [Using Patching Profiles](../advanced-patching-topics/advanced-patching-tool-configuration.md)
* [Getting Patch Information](../advanced-patching-topics/getting-patch-information.md)
* [Uninstalling Patches](../advanced-patching-topics/managing-and-uninstalling-patches.md) 
* [Patch Cleanup](../advanced-patching-topics/managing-and-uninstalling-patches.md)
* [Handling Collisions between Patches and Custom Plugins](../advanced-patching-topics/custom-code-and-patch-compatibility.md)

Lastly, you'll want to stay updated on the latest DXP patches. [Keeping up with Patches](./keeping-up-with-patches.md) covers this. 

Now that you've digested the patching overview, learn about the [Patch Types](./understanding-patch-types.md) available to you. Then you'll be ready to apply patches following [The Patching Process](./the-patching-process.md)