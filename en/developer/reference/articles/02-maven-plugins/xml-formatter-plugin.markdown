---
header-id: xml-formatter-plugin
---

# XML Formatter Plugin

[TOC levels=1-4]

The XML Formatter plugin lets you format a project's XML files.

## Usage

To use the plugin, include it in your project's root `pom.xml` file:

```xml
<build>
    <plugins>
    ...
        <plugin>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.xml.formatter</artifactId>
            <version>1.0.5</version>
            <configuration>
            </configuration>
        </plugin>
    ...
    </plugins>
</build>
```

You can view an example POM containing the XML Formatter configuration
[here](https://github.com/liferay/liferay-portal/blob/master/modules/util/xml-formatter/samples/pom.xml).

## Goals

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`xml-formatter:format` | Runs the Liferay XML Formatter to format the project files.

## Available Parameters

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`fileName` | `String` | `null` | The XML file to format. This plugin only lets you format one XML file at a time.
`stripComments` | `boolean` | `false` | Whether to remove all the comments from the XML file.
