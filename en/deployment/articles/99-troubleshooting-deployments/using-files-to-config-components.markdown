---
header-id: using-files-to-configure-product-modules
---

# Using Files to Configure Module Components

[TOC levels=1-4]

@product@ uses 
[Felix File Install](http://felix.apache.org/documentation/subprojects/apache-felix-file-install.html)
to monitor file system folders for new/updated configuration files, and the
[Felix OSGi implementation](http://felix.apache.org/)
of
[Configuration Admin](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html)
to let you use files to configure module service components. 

To learn how to work with configuration files, first review 
[Understanding System Configuration Files](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files). 

## Configuration File Formats

There are two different configuration file formats: 

-   `.cfg`: An older, simple format that only supports `String` values as 
    properties. 
-   `.config`: A format that supports strings, type information, and other
    non-string values in its properties. 

Although @product@ supports both formats, use `.config` files for their
flexibility and ability to use type information. Since `.cfg` files lack type
information, if you want to store anything but a `String`, you must use
properties utility classes to cast `String`s to intended types (and you must
carefully document properties that aren't `String`s). `.config` files eliminate
this need by allowing type information. The articles below explain the file
formats: 

-   [Understanding System Configuration Files](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)
-   [Configuration file (`.config`) syntax](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#configuration-files-config)
-   [Properties file(`.cfg`) syntax](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#property-files-cfg)

## Naming Configuration Files

Before you [create a configuration
file](/docs/7-1/user/-/knowledge_base/u/creating-configuration-files), follow
these steps to determine whether multiple instances of the component can be
created or if the component is intended to be a singleton:

1.  Deploy the component's module if you haven't done so already. 

2.  In @product@'s UI, go to *Control Panel* &rarr; *Configuration* &rarr; 
    *System Settings*. 

3.  Find the component's settings by searching or browsing for the component. 

4.  If the component's settings page has a section called *Configuration 
    Entries*, you can create multiple instances of the component configured 
    however you like. Otherwise, you should treat the component as a singleton. 

![Figure 1: You can create multiple instances of components whose System Settings page has a *Configuration Entries* section.](../../images/system-settings-page-lists-configuration-entries.png)

*All* configuration file names must start with the component's PID (PID stands
for *persistent identity*) and end with `.config` or `.cfg`. 

For example, this class uses  [Declarative
Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)  to
define a component:

```java
package com;
@Component
class Foo {}
```

The component's PID is `com.Foo`. All the component's configuration files must
start with the PID `com.Foo`. 

For each non-singleton component instance you want to create or update with a
configuration, you must use a uniquely named configuration file that starts with
the component's PID and ends with `.config` or `.cfg`. Creating configurations
for multiple component instances requires that the configuration files use
different *subnames*. A subname is the part of a configuration file name after
the PID and before the suffix `.config` or `.cfg`. Here's the configuration file
name pattern for non-singleton components: 

-   `[PID]-[subname1].config`
-   `[PID]-[subname2].config`
-   etc. 

For example, you could configure two different instances of the component 
`com.Foo` by using configuration files with these names: 

-   `com.Foo-one.config`
-   `com.Foo-two.config`

Each configuration file creates and/or updates an instance of the component that
matches the PID. The subname is arbitrary---it doesn't have to match a specific
component instance. This means you can use whatever subname you like. For
example, these configuration files are just as valid as the two above: 

-   `com.Foo-puppies.config`
-   `com.Foo-kitties.config`

Using the subname `default`, however, is @product@'s convention for configuring 
a component's first instance. The file name pattern is therefore 

    [PID]-default.config

A singleton component's configuration file must also start with `[PID]` and end
with `.config` or `.cfg`. Here's the common pattern used for singleton component
configuration file names: 

    [PID].config

When you're done creating a configuration file, you can
[deploy it](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files#deploying-a-configuration-file). 

## Resolving Configuration File Deployment Failures

The following `IOException` hints that the configuration file has a syntax
issue: 

    Failed to install artifact: [path to .config or .cfg file]
    java.io.IOException: Unexpected token 78; expected: 61 (line=0, pos=107)

To resolve this, fix the 
[configuration file's syntax](#configuration-file-formats). 

Great! Now you know how to configure module components using configuration 
files. 

## Related Articles

[Understanding System Configuration Files](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)
