---
header-id: control-menu-entry
---

# Control Menu Entry

[TOC levels=1-4]

The Control Menu Entry sample provides a customizable button that is added to
Liferay Portal's default Control Menu. When deploying this sample with no
customizations, an additional button is added to the User (right side) portion
of the Control Menu.

![Figure 1: The User area of the Control Menu is provided an additional link button when the Control Menu Entry sample is deployed to @product@.](../../../images/controlmenuentry.png)

The button navigates the user to Liferay's website: https://www.liferay.com.

## What API(s) and/or code components does this sample highlight?

This sample leverages the
[ProductNavigationControlMenuEntry](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/control/menu/ProductNavigationControlMenuEntry.html)
API.

## How does this sample leverage the API(s) and/or code component?

This sample first leverages the `ProductNavigationControlMenuEntry` interface as
an OSGi service via the `@Component` annotation:

```java
@Component(
    immediate = true,
    property = {
        "product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.USER,
        "product.navigation.control.menu.entry.order:Integer=1"
    },
    service = ProductNavigationControlMenuEntry.class
)
```

There are also two properties provided via the `@Component` annotation:

- `product.navigation.control.menu.category.key`: the category in which your
   entry should reside. The default Control Menu provides three categories:
   *SITES* (left portion), *TOOLS* (middle portion), and *USER* (right portion).
- `product.navigation.control.menu.entry.order:Integer`: the order in which your
   entry will be displayed in the category. Entries are ordered from left to
   right. For example, an entry with order `1` will be listed to the left of an
   entry with order `2`. If the order is not specified, it's chosen at random
   based on which service was registered first in the OSGi container.

This sample also implements the `ProductNavigationControlMenuEntry` interface.
The following methods are implemented:

- `getIcon(HttpServletRequest)`
- `getLabel(Locale)`
- `getURL(HttpServletRequest)`
- `isShow(HttpServletRequest)`

Refer to this sample's `BladeProductNavigationControlMenuEntry` class for
Javadocs describing these methods. 

<!--Uncomment once article is available
For more information on how to customize
Liferay Portal's Control Menu, visit the
Customizing the Control Menu
tutorial.
-->

## Where Is This Sample?

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.2/gradle/extensions/control-menu-entry)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace/extensions/control-menu-entry)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.2/maven/extensions/control-menu-entry)
