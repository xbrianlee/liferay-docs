---
header-id: adjusting-module-logging
---

# Adjusting Module Logging

[TOC levels=1-4]

@product@ uses [Log4j](http://logging.apache.org/log4j/1.2/) logging
services. Here are the ways to configure logging for module classes and class
hierarchies.

-   *Log Levels* in
    [@product@'s UI](/docs/7-2/user/-/knowledge_base/u/server-administration)
-   Configure Log4j for multiple modules in a
    `[anyModule]/src/main/resources/META-INF/module-log4j.xml` file.
-   Configure Log4j for a specific module in a
    `[Liferay Home]/osgi/log4j/[symbolicNameOfBundle]-log4j-ext.xml` file.
-   Configure Log4j for an OSGi fragment host module in a
    `/META-INF/module-log4j-ext.xml` file

Here's an example Log4j XML configuration:

```xml
<?xml version="1.0"?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
    <category name="org.foo">
        <priority value="DEBUG" />
    </category>
</log4j:configuration>
```

Use `category` elements to specify each class or class hierarchy to log messages
for. Set the `name` attribute to that class name or root package. The example
category sets logging for the class hierarchy starting at package `org.foo`. Log
messages at or above the `DEBUG` log level are printed for classes in `org.foo`
and classes in packages starting with `org.foo`.

Set each category's `priority` element to the log
[level](http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/Level.html)
(priority) you want.

-   ALL
-   TRACE
-   DEBUG
-   INFO
-   WARN
-   ERROR
-   FATAL
-   OFF

The log messages are printed to Liferay log files in `[Liferay_Home]/logs`.

You can see examples of module logging in several Liferay sample projects. For
example, the [action-command-portlet](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/action-command-portlet),
[document-action](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/document-action), and
[service-builder/jdbc](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/service-builder/jdbc)
samples (among others) leverage module logging.

| **Note:** If the log level configuration isn't appearing (e.g., you set the 
| log level to `ERROR` but you're still getting `WARN` messages), make sure the 
| log configuration file name prefix matches the module's symbolic name. If you
| have bnd installed, output from command `bnd print [path-to-bundle]` includes
| the module's symbolic name
| ([Here](https://github.com/bndtools/bnd/wiki/Install-bnd-on-the-command-line)
| are instructions for installing bnd for the command line).

That's it for module log configuration. You're all set to print the information
you want.

## Related Topics

[Implementing Logging](/docs/7-2/appdev/-/knowledge_base/a/implementing-logging)
