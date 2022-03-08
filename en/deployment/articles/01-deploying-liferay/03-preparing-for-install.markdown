---
header-id: preparing-for-install
---

# Preparing for Install

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/configuring-a-database.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

@product@ doesn't require much to deploy. You need a Java Development Kit (JDK)
and a database. Several configuration topics (e.g.,
[search engine integration](/docs/7-2/deploy/-/knowledge_base/d/installing-a-search-engine),
[document repository configuration](/docs/7-2/deploy/-/knowledge_base/d/document-repository-configuration),
[security management](/docs/7-2/deploy/-/knowledge_base/d/securing-product),
[clustering](/docs/7-2/deploy/-/knowledge_base/d/liferay-clustering),
and more) can be addressed *after* deploying @product@. 

| **Note:** If you are installing @product@ to multiple machines (e.g., in a 
| [cluster](/docs/7-2/deploy/-/knowledge_base/d/liferay-clustering))
| or prefer centralizing configuration in a file, using portal properties in a
| [`[LIFERAY_HOME]/portal-ext.properties` file](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
| is the recommended way to configure. The install preparation topics here and
| the configuration topics throughout this guide demonstrate using applicable
| portal properties. 

| **Note:** `LIFERAY_HOME` is the location from which @product@ launches 
| applications, applies configurations, loads JAR files, and generates logs.
| Liferay Home is customizable and can differ between application servers. The
| [Liferay Home reference](/docs/7-2/deploy/-/knowledge_base/d/liferay-home)
| describes its folder structure. 

Start preparing for @product@ install by installing a supported Java
Development Kit. 

## JDK Requirements

@product@ deployment requires a JDK. The 
[Support page](https://help.liferay.com/hc/categories/360000894391-Product-Support)
lists the supported JDKs from various vendors. You must use one of these JDK
versions:

- JDK 8
- JDK 11

JDK 11 is backwards compatible with JDK 8 applications. Applications and
customizations developed on JDK 8 run on JDK 8 or JDK 11 runtimes. This makes
JDK 8 best for developing on @product-ver@. 

## JVM Requirements

@product@ requires that the application server JVM use the GMT time zone and
UTF-8 file encoding. Include these JVM arguments to set the required values. 

```properties
-Dfile.encoding=UTF8 -Duser.timezone=GMT
```

On JDK 11, it's recommended to add this JVM argument to display four-digit years.

```properties
-Djava.locale.providers=JRE,COMPAT,CLDR
```

| **Note:** Since JDK 9, the Unicode Common Locale Data Repository (CLDR) is the
| default locales provider. CLDR, however, is not providing years in a
| four-digit format (see
| [LPS-87191](https://issues.liferay.com/browse/LPS-87191)).
| The setting `java.locale.providers=JRE,COMPAT,CLDR` works around this issue by
| using JDK 8's default locales provider. 

The recommended maximum heap size is 2GB. Setting the minimum heap size to the
maximum heap size value minimizes garbage collections. 

```properties 
-Xms2560m -Xmx2560m
```

If you're using JDK 11, you may see *Illegal Access* warnings like these:

```
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.liferay.petra.reflect.ReflectionUtil (file:/Users/malei/dev/project/bundles/master-bundles/tomcat-9.0.10/lib/ext/com.liferay.petra.reflect.jar) to field java.lang.reflect.Field.modifiers
WARNING: Please consider reporting this to the maintainers of com.liferay.petra.reflect.ReflectionUtil
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```

This is a known issue: [LPS-87421](https://issues.liferay.com/browse/LPS-87421). As a workaround, you can eliminate these warnings by adding these properties after your application server JVM options:

```properties
 --add-opens=java.base/java.io=ALL-UNNAMED \
 --add-opens=java.base/java.lang.reflect=ALL-UNNAMED \
 --add-opens=java.base/java.lang=ALL-UNNAMED \
 --add-opens=java.base/java.net=ALL-UNNAMED \
 --add-opens=java.base/java.nio=ALL-UNNAMED \
 --add-opens=java.base/java.text=ALL-UNNAMED \
 --add-opens=java.base/java.util=ALL-UNNAMED \
 --add-opens=java.base/sun.nio.ch=ALL-UNNAMED \
 --add-opens=java.desktop/java.awt.font=ALL-UNNAMED \
 --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED \
 --add-opens=java.xml/com.sun.org.apache.xerces.internal.parsers=ALL-UNNAMED
```

If you're using JDK 11 on Linux or UNIX and are activating @product@ using an LCS 5.0.0 client, you may see an error like this:

```
ERROR [LCS Worker 2][BaseScheduledTask:92] java.lang.reflect.InaccessibleObjectException: Unable to make public long com.sun.management.internal.OperatingSystemImpl.getOpenFileDescriptorCount() accessible: module jdk.management does not
 "opens com.sun.management.internal" to unnamed module @1a3325e5
java.lang.reflect.InaccessibleObjectException: Unable to make public long com.sun.management.internal.OperatingSystemImpl.getOpenFileDescriptorCount() accessible: module jdk.management does not "opens com.sun.management.internal" to unnamed module @1a3325e5
at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:
at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:
at java.base/java.lang.reflect.Method.checkCanSetAccessible(Method.java:198)
at java.base/java.lang.reflect.Method.setAccessible(Method.java:192)
```

To workaround this issue, add this property after your application server JVM options:

```properties
 --add-opens=jdk.management/com.sun.management.internal=ALL-UNNAMED
```

It's time to prepare your database. 

## Preparing a Database

The recommended way to set up your @product@ database is also the simplest.
@product@ takes care of just about everything. Here are the steps:

1.  Create a blank database encoded with the character set UTF-8. @product@ is a
    multilingual application and needs UTF-8 encoding to display all of its
    supported character sets.

    | **Note:** If you plan to migrate from one database vendor to another,
    | [configure the database to use the default query result order you expect for entities @product@ lists](/docs/7-2/deploy/-/knowledge_base/d/sort-order-changed-with-a-different-database). 

2.  Create a database user for accessing this database. Grant this database user
    all rights, including the rights to create and drop tables, to the blank
    @product@ database.

@product@ uses this database user's credentials to connect to the database
either
[directly](#using-the-built-in-data-source)
or
[through its application server](#using-a-data-source-on-your-application-server). 
After you've configured the database connection, @product@ creates its tables in
the database automatically, complete with indexes.

This is the recommended way to set up @product@. It enables @product@ to
maintain its database automatically during upgrades or when various @product@
plugins that create database tables of their own are installed. This method is
by far the best way to set up your database. 

| **Warning:** If you're using an Oracle database, use the `ojdbc8.jar` driver 
| library with at least Oracle 12.2.0.1.0 JDBC 4.2 versioning because
| [data truncation issues](https://issues.liferay.com/browse/LPS-79229)
| have been detected reading data from CLOB columns. 

You can connect @product@ with your database using @product@'s built-in data
source (recommended) or using a data source you create on your app server. 

### Using the Built-in Data Source

You can configure the built-in data source from the
[Basic Configuration page](/docs/7-2/deploy/-/knowledge_base/d/installing-product#using-the-setup-wizard)
(available when @product@ starts up the first time) or by specifying it using 
portal properties. 

Here's how set it using portal properties:

1.  Create a 
    [`portal-ext.properties` file](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    if you haven't created one already. 

2.  Copy a set of `jdbc.*` properties from one of the
    [JDBC templates](/docs/7-2/deploy/-/knowledge_base/d/database-templates)
    into your `portal-ext.properties` file.

3.  Modify the `jdbc.*` property values to specify your database and database 
    user credentials. 

4.  Put the `portal-ext.properties` file into your
    [LIFERAY_HOME](/docs/7-2/deploy/-/knowledge_base/d/liferay-home)
    once you've established it based on your installation. 

@product@ connects to the data source on startup. 

As an alternative to the built-in data source, you can use your application
server's data source. 

### Using a Data Source on Your Application Server

Here's how to use your application server's data source: 

1.  Create your data source based on the instructions in the *Installing 
    @product@ on [Application Server]* article (for your application server)
    and your application server's documentation.

2.  Create a
    [`portal-ext.properties` file](/docs/7-2/deploy/-/knowledge_base/d/portal-properties),
    if you haven't created one already. 

3.  Add the `jdbc.default.jndi.name` property set to the data source's JNDI 
    name. Here's an example:

    ```properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    ```

4.  Put the `portal-ext.properties` file into your
    [LIFERAY_HOME](/docs/7-2/deploy/-/knowledge_base/d/liferay-home),
    once you've established your LIFERAY_HOME based on your installation. 

@product@ connects to your data source on startup.

Allowing the database user you're using to initialize the @product@ database to
continue with all database rights is recommended. If you're fine with that user
having the recommended permissions, skip the next section on limiting
database access. 

### Limiting Database Access

| **Warning:** The instructions below are not ideal for @product@ installations
| The following procedure is documented so that enterprises with more
| restrictive standards can install @product@ with stricter (but sub-optimal)
| database settings. If it's at all possible, allow the database user that
| initializes the database to continue using the database with the same
| recommended permissions. The start of this section
| ([Database Preparation](#preparing-a-database)) 
| describes the recommended procedure for initializing the @product@ database
| and preserving that user's permissions for maintaining the @product@ database
| and updating the database as plugin installations and plugin updates require. 

Even though it's recommended for @product@ to use the same database user to
create and maintain its database automatically, your organizations might insist
on revoking database initialization and maintenance permissions from that user
once the database is initialized. If permissions for Select, Insert, Update and
Delete operations are the only ones you allow for that user, you must initialize
and maintain the database manually (even though it's not recommended). Here is
the manual procedure: 

1.  Create a new, blank, database for @product@.

2.  Grant full rights for the @product@ database user to do anything to the 
    database. 

3.  Install @product@ and start it so that it automatically populates the
    database.

4.  Once the database has been populated with the @product@ tables, remove all
    permissions from that user except permissions to perform Select, Insert,
    Update and Delete operations. 

There are some caveats to running @product@ like this. Many plugins create new
tables when they're deployed. Additionally, you must run the database upgrade
function to upgrade @product@. If the @product@ database user doesn't have
adequate rights to create/modify/drop tables in the database, you must grant
those rights to that user before you deploy one of these plugins or start
upgrading @product@. Once the tables are created or the upgrade completes, you
can remove those rights until the next deploy or upgrade. Additionally, your own
developers might create plugins that must create their own tables. These are
just like @product@ plugins that do the same thing, and they can only be
installed if @product@ can create database tables. Installing these plugins
requires granting the @product@ database user rights to create tables in the
database before you attempt to install the plugins. 

@product@ has many more configurable features; but they
can wait until *after* deployment. The
[Configuring @product@](/docs/7-2/deploy/-/knowledge_base/d/configuring-product)
section explains them. 

Now it's time to install @product@. 
