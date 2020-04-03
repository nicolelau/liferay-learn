# Patch Types 

The Liferay Support Team creates and releases fixes to issues discovered in DXP. The fixes are packaged in ZIP files referred to generally as _patches_. There are patches for security issues, customer-specific issues, and issues reported by the Liferay community. Here you'll learn about the patch types provided to DXP subscribers, [fix delivery](#ce-ga-releases) for DXP CE users, and the [levels of testing for each patch type](#patch-testing).

The following patch types for DXP subscribers are covered first.

* [Fix packs](#fix-packs) 
* [Hotfixes](#Hotfixes)
* [Service packs](#service-packs)

## Fix Packs

A Fix Pack is a bundled collection of the latest fixes for DXP subscribers. They address issues in the DXP core and Marketplace applications that ship with DXP. Fix Packs are cumulative: each one contains all the fixes from previous Fix Pack and the latest Security Fix Packs.

Fix Packs are available on the Help Center's [Downloads](https://customer.liferay.com/downloads) page and are installed using the [Patching Tool](./the-patching-process.md). Release notes and a changelogs accompany each Fix Pack. Release notes highlight key information, such as important changes and security fixes. Changelogs provide detailed information about each fix.

### Security Fix Packs

[DXP Security Fix Packs](https://help.liferay.com/hc/en-us/articles/360035038331) address the latest critical security issues. Each Security Fix Pack depends on a base Fix Pack (typically the latest one). The number following `liferay-security_dxp_` in the Security Fix Pack file name indicates the base Fix Pack. For example, the base Fix Pack for `liferay-security-dxp-1-201902-1-7210.zip` is DXP Fix Pack 1.

Security Fix Packs are available on the Help Center's [Downloads](https://customer.liferay.com/downloads) page and are installed using the [Patching Tool](./the-patching-process.md).

```note::
   Security Fix Packs do not change your DXP installation's regular Fix Pack level. 
```

## Hotfixes

If you encounter an issue that is not yet addressed in a Fix Pack, you can request at Hotfix via a [Help Center ticket](https://help.liferay.com/hc). The Liferay Support Team works with you to determine if the issue is with the product, and provides a fix for any unintended product behavior.

## Service Packs

A Service Pack is built on top of the original DXP GA major release and includes the latest Fix Pack, latest Patching Tool, and latest bundled Marketplace applications. The Fix Pack in a Service Pack can include fixes that require more extensive testing that a regular Fix Pack. 

Service Packs are available on the Help Center's [Downloads](https://customer.liferay.com/downloads) page and are installed using the [Patching Tool](./the-patching-process.md).

It's best to start a new project on the latest Service Pack for DXP, because it includes the latest fixes and versions of Marketplace apps and the Patching Tool. 

If you're maintaining an existing DXP installation, it's best to keep it updated with the latest Fix Pack (according to your own deployment schedule). This way, you're integrating changes in smaller doses. 

## Fix Delivery for CE

Liferay DXP CE GA releases are based on DXP Service Packs and are typically available a few weeks after the corresponding Service Pack. They include the same fixes, minus those for EE-only features. See [Updating DXP CE](./updating-community-edition.md) for more information.

## Patch Testing

The following table describes the testing applied to each patch type. 

| **Patch Type** | **Testing** |
| :------------- | :---------- |
| Hotfixes       | Hotfixes receive automated regression testing similar to Fix Packs (see below), and the support engineer who fixes the reported issue tests it. |
| Fix packs      | Go through both automated regression testing and manual testing. |
| Service packs  | Liferay runs test suites on the packaged service pack. |
| CE GA Releases | The same testing as the corresponding service pack, plus upgrade testing from the previous GA. | 

## Conclusion 

Now that you know the patch types Liferay provides for delivering fixes, you can [stay updated on relevant patches](./keeping-up-with-fix-packs.md) and [install](./the-patching-process.md) them.

## Additional Information 

* [The Patching Process](./the-patching-process.md)

* [Installing the Patching Tool](./installing-the-patching-tool.md)

* [Keeping Up With Fix Packs and Service Packs](./keeping-up-with-fix-packs.md)

* [Updating DXP CE](./updating-community-edition.md)
