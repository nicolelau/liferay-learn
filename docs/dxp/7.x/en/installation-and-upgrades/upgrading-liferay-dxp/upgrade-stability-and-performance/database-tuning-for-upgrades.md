# Database Tuning for Upgrades

## Tuning the Database Transaction Engine for Executing Updates

Many more update statements are executed during data upgrade than in production. Consult your database vendor's official documentation for more help on tuning your database for this purpose. Listed below are some optimizations you can make for each database.

## Extra Tuning Configurations

You can make even more optimizations to further improve your upgrade performance. The steps used to optimize your upgrade further are database-specific, and may vary depending on your data set.

The data upgrade tuning instructions given here are a starting point for tuning your Liferay DXP data upgrade. They account for data upgrade activities and a safe data upgrade environment:

* Deactivate data integrity measures that impact performance. Restore to a backup if failures occur.

* Disable or minimize transaction logging, because it is insignificant for data upgrades.

* Make commit-related transaction I/O operations asynchronous.

* Increase the interval to flush commits to disk.

```warning::
   Some database properties and configurations are global and affect schemas in the same database.
```

## Database Vendor Specific Tuning

### IBM DB2

Please consult [IBM's official DB2 documentation](https://www.ibm.com/support/pages/db2-database-product-documentation-4).

### MariaDB

Turn off InnoDB double-write and set the InnoDB flush log at transaction commit to `0`.

### Microsoft SQL Server

Set [transaction durability](https://docs.microsoft.com/en-us/sql/relational-databases/logs/control-transaction-durability) to `FORCED`.

### MySQL

Turn off [InnoDB double-write](https://dev.mysql.com/doc/refman/5.7/en/innodb-parameters.html#sysvar_innodb_doublewrite) and set the [InnoDB flush log at transaction commit](https://dev.mysql.com/doc/refman/5.7/en/innodb-parameters.html#sysvar_innodb_flush_log_at_trx_commit) to `0`.

### Oracle Database

The default configuration works well. It configures [asynchronous I/O to disk](https://docs.oracle.com/database/121/REFRN/GUID-FD8D1BD2-0F85-4844-ABE7-57B4F77D1608.htm#REFRN10048) automatically.

### PostgreSQL

Turn off [synchronous commits](https://www.postgresql.org/docs/10/wal-async-commit.html) and set the [write ahead log writer delay](https://www.postgresql.org/docs/10/wal-async-commit.html) to `1000` milliseconds.