---
header-id: using-the-service-builder-template
---

# Service Builder Template

[TOC levels=1-4]

In this article, you'll learn how to create a Liferay portlet application that
uses Service Builder as Liferay modules. To create a Liferay Service Builder
project via the command line using Blade CLI or Maven, use one of the commands
with the following parameters:

```bash
blade create -t service-builder [-p packageName] projectName
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.service.builder \
    -DartifactId=[projectName] \
    -Dpackage=[packageName] \
    -DapiPath=[apiPath] \
    -DdependencyInjector=[dependencyInjector] \
    -DliferayVersion=7.2
```

By default, the Service Builder project uses OSGi Declarative Services (`ds`)
for its dependency injector. If you prefer using Spring, you can set the
parameter `--dependency-injector spring` with Blade CLI or
`-DdependencyInjector=spring` with Maven. See the
[Dependency Injection](/docs/7-2/frameworks/-/knowledge_base/f/dependency-injection)
section for more information on these options.

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `service-builder`. Suppose you want to
create a Service Builder project called `tasks` with a package name of
`com.liferay.docs.tasks` using OSGi Declarative Services. You could run the
following command to accomplish this:

```bash
blade create -t service-builder -p com.liferay.docs.tasks tasks
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.service.builder \
    -DgroupId=com.liferay \
    -DartifactId=tasks \
    -Dpackage=com.liferay.docs.tasks \
    -Dversion=1.0 \
    -DapiPath=com.liferay.api.path \
    -DdependencyInjector=ds \
    -DliferayVersion=7.2
```

This task creates the `tasks-api` and `tasks-service` folders. In many cases, a
Service Builder project also requires a `-web` folder to hold, for example,
portlet classes. This should be created manually. After running the Blade
command above, your project's directory structure looks like this:

- `tasks`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `tasks-api`
        - `bnd.bnd`
        - `build.gradle`
    - `tasks-service`
        - `bnd.bnd`
        - `build.gradle`
        - `service.xml`
    - `build.gradle`
    - `gradlew`
    - `settings.gradle`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

To generate your service and API classes for the `*-api` and `*-service`
modules, replace the `service.xml` file in the `*-service` module. Depending on
your build tool, you can build your services by executing

```bash
blade gw buildService
```

or

```bash
mvn service-builder:build
```

from the `tasks` root directory. Note that `blade gw` only works if the Gradle
wrapper can be detected. To ensure the availability of the Gradle wrapper, be
sure to work in a Liferay Workspace.

The `mvn service-builder:build` command only works if you're using the
`com.liferay.portal.tools.service.builder` plugin version 1.0.145+. Maven
projects using an earlier version of the Service Builder plugin should update
their POM accordingly.

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.

For more information on Service Builder, see the
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
section.
