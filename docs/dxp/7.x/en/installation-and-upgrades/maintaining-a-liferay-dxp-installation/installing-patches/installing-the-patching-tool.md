# Installing the Patching Tool 

DXP bundles come with the Patching Tool pre-installed (`[Liferay Home]/patching-tool`) and pre-configured. If you installed DXP manually you must install the Patching Tool yourself. Here you'll learn how to install the Patching Tool and update it.

## Installation

1.  Download the Patching Tool from the [Customer Portal](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191019&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066).

2.  Unzip the Patching Tool to your [Liferay Home](../../reference/liferay-home.md) folder (recommended) or to another folder. 

The Patching Tool folder `patching-tool` includes the `patching-tool.sh` script.

To print the Patching Tool help message, execute this command in the `patching-tool` folder: 

```bash
cd patching-tool
./patching-tool.sh help
```

## Updating the Patching Tool

When a patch you're trying to install requires a newer version of the Patching Tool, the Patching Tool tells you. To update the Patching Tool:

1. Download the latest [Patching Tool](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191019&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191066).

2.  Overwrite the existing Patching Tool by unzipping the new one to the `patching-tool` folder's parent folder (typically Liferay Home). 

The Patching Tool is ready to configure with your DXP installation. 

## Additional Information 

* [Configuring the Patching Tool](./06-configuring-the-patching-tool.md)

* [The Patching Process](./03-the-patching-process.md)

* [Patching a DXP WAR](./patching-dxp-on-an-application-server.md)

* [Comparing Patch Levels](../../14-reference/07-comparing-patch-levels.md) 

* [Keeping Up With Fix Packs and Service Packs](./08-keeping-up-with-fix-packs.md)
