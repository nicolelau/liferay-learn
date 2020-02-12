# High Security Database User Practices

Liferay DXP requires database user credentials to persist data. The simplest, easiest way is through a database user that has full database permissions up to and including permissions to create and drop tables. With such a user DXP plugins interact with the database seamlessly and upgrade operations are done automatically. Restricting the database user permissions more than this is not recommended.

However, your organization may have more stringent security policies that require limiting DXP database user permissions. If permissions for Select, Insert, Update and Delete operations are the only ones allowed, you must initialize and maintain the database manually. Here's what's recommended to accomplish this:

1. Grant full rights for the DXP database user to do anything to the database.
1. Install DXP and start it so that it automatically populates the database.
1. Once the database has been populated with the DXP tables, remove all permissions from the DXP database user except permissions to perform Select, Insert, Update and Delete operations.

```warning::
There are some caveats to running DXP with these constraints. Many plugins create new tables when they’re deployed. Additionally, you must manually run the database upgrade function to upgrade DXP. If the DXP database user does not have adequate rights to create/modify/drop tables in the database, you must grant those rights to that user before deploying one of these plugins or starting the DXP upgrade. Once the tables are created or the upgrade completes, you can remove those rights until the next deploy or upgrade. If your team creates plugins that create their own tables, you must similarly grant temporary rights to the DXP database user before deploying the plugin.
```
