---
header-id: theme-builder-plugin
---

# Theme Builder Plugin

[TOC levels=1-4]

The Theme Builder plugin lets you build Liferay theme files in your project.
Visit the
[Building a Theme with Maven](/docs/7-2/reference/-/knowledge_base/r/building-a-theme-with-maven)
tutorial to learn more about applying Theme Builder to your Maven project.

## Usage

To use the plugin, include it in your project's root `pom.xml` file:

```xml
<build>
    <plugins>
    ...
        <plugin>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.portal.tools.theme.builder</artifactId>
            <version>1.1.7</version>
            <executions>
                <execution>
                    <phase>generate-resources</phase>
                    <goals>
                        <goal>build</goal>
                    </goals>
                    <configuration>
                    </configuration>
                </execution>
            </executions>
        </plugin>
        ...
    </plugins>
</build>
```

You can view an example POM containing the Theme Builder configuration
[here](https://github.com/liferay/liferay-portal/blob/master/modules/util/portal-tools-theme-builder/samples/pom.xml).

## Goals

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`theme-builder:build` |  Builds the theme files.

## Available Parameters

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`diffsDir` | `File` | `${maven.war.src}` | The directory that contains the files to copy over the parent theme.
`name` | `String` | `${project.artifactId}` | The name of the new theme.
`outputDir` | `File` | `${project.build.directory}/${project.build.finalName}` | The directory where to build the theme.
`parentDir` | `File` | `null` | The directory of the parent theme.
`parentName` | `String` | `null` | The name of the parent theme.
`templateExtension` | `String` | `"ftl"` |  The extension of the template files, usually `"ftl"` or `"vm"`.
`unstyledDir` | `File` | `null` | The directory of [Liferay Frontend Theme Unstyled](https://github.com/liferay/liferay-portal/tree/master/modules/apps/frontend-theme/frontend-theme-unstyled).

You can also manage the `com.liferay.frontend.theme.styled` and
`com.liferay.frontend.theme.unstyled` default theme dependencies provided by the
Theme Builder in your `pom.xml`. They can be modified by adding them as project
dependencies:

```xml
<project>
    ...
    <dependencies>
        ...
        <dependency>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.frontend.theme.styled</artifactId>
            <version>3.0.4</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.frontend.theme.unstyled</artifactId>
            <version>3.0.4</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
</project>
```

There is an additional Liferay theme-related dependency you can manage this
way that's provided by the CSS Builder. See
[this section](/docs/7-2/reference/-/knowledge_base/r/css-builder-plugin) for
more information.
