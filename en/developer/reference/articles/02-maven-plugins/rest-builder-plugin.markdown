---
header-id: rest-builder-plugin
---

# REST Builder Plugin

[TOC levels=1-4]

The REST Builder plugin lets you generate a REST layer defined in the REST
Builder `rest-config.yaml` and `rest-openapi.yaml` files.

## Usage

To use the plugin, include it in your project's root `pom.xml` file:

```xml
<build>
    <plugins>
    ...
        <plugin>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.portal.tools.rest.builder</artifactId>
            <version>1.0.22</version>
            <configuration>
            </configuration>
        </plugin>
    ...
    </plugins>
</build>
```

You can view an example POM containing the REST Builder configuration
[here](https://github.com/liferay/liferay-portal/blob/master/modules/util/portal-tools-rest-builder/samples/pom.xml).

## Goals

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`rest-builder:build` | Runs the Liferay REST Builder.

## Available Parameters

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`copyrightFile` | `File` | `null` | The file that contains the copyright header.
`restConfigDir` | `File` | `${project.projectDir}` | The directory that contains the `rest-config.yaml` and `rest-openapi.yaml` files.
