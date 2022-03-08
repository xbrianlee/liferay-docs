---
header-id: disabling-cache-for-table-mapper-tables
---

# Disabling Cache for Table Mapper Tables

[TOC levels=1-4]

Service Builder creates relational mappings between entities. It uses mapping
<!--
Add link back for 'relational mappings between entities' once article is available
-->
tables to associate the entities. In your `service.xml` file, both entities have 
a `mapping-table` column attribute of the format `mapping-table="table1_table2"`. 
For example, a `service.xml` that maps `AssetEntry`s to `AssetCategory`s has an 
`AssetCategory` entity with this column: 

```xml
<column entity="AssetEntry" 
mapping-table="AssetEntries_AssetCategories" 
name="entries" type="Collection" />
```

and an `AssetEntry` entity element with this column: 

```xml
<column entity="AssetCategory" 
mapping-table="AssetEntries_AssetCategories" 
name="categories" type="Collection" />
```

By default, a table mapper cache is associated with each mapping table. The
cache optimizes object retrieval. In some cases, however, it's best to disable a
table mapper cache. 

## Why would I want to disable cache on a table mapper?

Super-large entity tables can result in a memory-hogging table mapper cache. For
this reason, consider disabling cache on a table mapper. 

The
[`table.mapper.cacheless.mapping.table.names` Portal property](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Table%20Mapper)
disables cache for table mappers associated with the specified mapping tables.
Here's the default property setting:

```properties
##
## Table Mapper
##

  #
  # Set a list of comma delimited mapping table names that will not be using
  # cache in their table mappers.
  #
  table.mapper.cacheless.mapping.table.names=\
    Users_Groups,\
    Users_Orgs,\
    Users_Roles,\
    Users_Teams,\
    Users_UserGroups
```

All of the disabled caches above pertain to the `User` object because the table
mappers tend to be much too large to have a useful cache---each `User` can
have several entries in each related table. 

Potential race conditions retrieving objects from the cache is another reason to
disable a table mapper.

For example,
[LPS-84374](https://issues.liferay.com/browse/LPS-84374) describes a race
condition in which a custom entity's table mapper cache can be cleared while in
use, causing transactional rollbacks. Publishing `AssetEntry`s clears all
associated table mapper caches. If they're published at the same time getter
methods are retrieving objects from the `AssetEntries_AssetCategories` mapping
table, transaction rollbacks occur. 

## Disabling a Table Mapper Cache

Adding a mapping table name to the `table.mapper.cacheless.mapping.table.names`
Portal property disables the associated table mapper cache.

1.  In your
    [`[Liferay_Home]/portal-ext.properties` file](/docs/7-1/deploy/-/knowledge_base/d/liferay-home), 
    add the current `table.mapper.cacheless.mapping.table.names` property
    setting. The setting is in your @product@ installation's
    `portal-impl.jar/portal.properties` file.

2.  Append your mapping table name to the list. For example, to disable the
    cache associated with a mapping table named `AssetEntries_AssetCategories`,
    add that name to the list. 

    ```properties
    table.mapper.cacheless.mapping.table.names=\
        Users_Groups,\
        Users_Orgs,\
        Users_Roles,\
        Users_Teams,\
        Users_UserGroups,\
        AssetEntries_AssetCategories
    ```

3.  Restart the @product@ instance to delete the table mapper cache. 

You've disabled an unwanted table mapper cache. 
