# Maintaining a Liferay DXP Installation

Once you have Liferay DXP deployed, you can add to it, update it, and apply fixes. Additionally in case of any failure or unwanted side affects, you should implement a plan for backing up your system and restoring it. The backup copies are also valuable for testing updates (e.g., DXP upgrades, new plugins, and more). In this way, DXP backups and updates work hand in hand. 

## Backing Up Your System

Storing copies of your DXP files, databases, and customization source code allows you to restore your system. [Backing Up](./backing-up.md) provides guidance for persisting and restoring your system components. 

## Updating DXP 

Maintaining your DXP instance involves [patching](./installing-patches.md) it, deploying [configuration changes](https://help.liferay.com/hc/en-us/articles/360029131651-Understanding-System-Configuration-Files), [updating apps](https://help.liferay.com/hc/en-us/articles/360029134911-Managing-and-Configuring-Apps), and updating third-party software (e.g., the application server, JVM, and more). Any updates that affect your production instance require careful planning. The [DXP cluster update instructions](../10-maintaining-clusters/01-maintaining-clustered-installations.md) describe ways to handle these updates.

[Patching your system](./installing-patches.md) regularly (if you're a DXP subscriber) or [upgrading your CE instance](./upgrading-to-liferay-dxp-7-2.md) to a new GA gives you the latest bug fixes. The [Installing Patches section] explains the different DXP patch types and how to install them using the patching tool. 

**Warning:** Upgrading DXP CE to a new minor version GA can require [data upgrade](./upgrading-to-liferay-dxp-7-2.md). Until you perform all required data upgrades (if any), @product@ startup fails with messages like these:

```bash
2019-03-06 17:22:35.025 INFO  [main][StartupHelper:72] There are no patches installed
You must first upgrade to @product@ 7210
2019-03-06 17:22:35.098 ERROR [main][MainServlet:277] java.lang.RuntimeException: You must first upgrade to @product@ 7201
```

Read on to learn how to keep your system running well. 
