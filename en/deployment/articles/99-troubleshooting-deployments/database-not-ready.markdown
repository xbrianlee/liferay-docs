---
header-id: portal-failed-to-initialize-because-the-database-wasnt-ready
---

# @product@ Failed to Initialize Because the Database Wasn't Ready

[TOC levels=1-4]

If you start your database server and application server at the same time,
@product@ might try connecting to the data source before the database is ready.
By default, @product@ doesn't retry connecting to the database; it just fails.
But there is a way to avoid this situation: database connection retries.

1.  Create a `portal-ext.properties` file in your
    [Liferay Home](/docs/7-1/deploy/-/knowledge_base/d/liferay-home)
    folder.

2.  Set the property `retry.jdbc.on.startup.max.retries` equal to the number of
    times to retry connecting to the data source. 

3.  Set property `retry.jdbc.on.startup.delay` equal to the number of seconds
    to wait before retrying connection.

If at first the connection doesn't succeed, @product@ uses the retry settings to
try again. 

## Related Topics

[Connecting to JNDI Data Sources](/docs/7-2/appdev/-/knowledge_base/a/connecting-to-data-sources-using-jndi)
