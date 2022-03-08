---
header-id: theme-contributor
---

# Theme Contributor

[TOC levels=1-4]

The Theme Contributor sample contributes updates to the UI of the theme
body, Control Menu, Product Menu, and Simulation Panel. When deploying this
sample with no customizations, the colors of the theme and aforementioned menus
are updated.

![Figure 1: Your @product@ pages and menu fonts now have a yellow tint.](../../../images/theme-contributor-yellow.png)

Also, there's a simple JavaScript update that is provided, which logs a message
to the browser's console window that states *Hello Blade Theme Contributor!*.

![Figure 2: The message is printed to your browser's console window using JavaScript.](../../../images/theme-contributor-console-output.png)

## What API(s) and/or code components does this sample highlight?

This sample demonstrates a way to contribute updates to a @product@ theme. Theme
Contributors let you package UI resources (e.g., CSS and JS) independent of a
theme to include on a @product@ page.

## How does this sample leverage the API(s) and/or code component?

To modify this sample, replace the corresponding JS or SCSS file with the
JavaScript or styles that you want, or add your own JS or SCSS files. For
example, this sample provides an update to the Control Menu's `background-color`
in its
`src/main/resources/META-INF/resources/css/blade.theme.contributor/_control_menu.scss`
file:

```css
body {
        .control-menu {
                background-color: darkkhaki;
        }
}
```

All of the SCSS files used in this sample are imported into the main
`blade.theme.contributor.scss` file:

```css
@import "bourbon";
@import "mixins";

@import "blade.theme.contributor/body";
@import "blade.theme.contributor/control_menu";
@import "blade.theme.contributor/product_menu";
@import "blade.theme.contributor/simulation_panel";
```

If you add your own `SCSS` files, you must add them to the list of imports in 
the `blade.theme.contributor.scss` file.

Likewise, the sample `blade.theme.contributor.js` logs a message to your
browser's console window using the following JS logic:

```js
console.log('Hello Blade Theme Contributor!');
```

For more information on Theme Contributors, visit the
[Theme Contributors](/docs/7-2/frameworks/-/knowledge_base/f/packaging-independent-ui-resources-for-your-site)
tutorial.

## Where Is This Sample?

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.2/gradle/themes/theme-contributor)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace/themes/theme-contributor)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.2/maven/themes/theme-contributor)
