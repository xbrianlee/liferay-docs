---
header-id: rest-template
---

# REST Template

[TOC levels=1-4]

In this article, you'll learn how to create a Liferay RESTful web service
packaged in a Liferay module. To create a Liferay RESTful web service via the
command line using Blade CLI or Maven, use one of the commands with the
following parameters:

```bash
blade create -t rest [-p packageName] [-c className] projectName
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.rest \
    -DartifactId=[projectName] \
    -Dpackage=[packageName] \
    -DclassName=[className] \
    -DliferayVersion=7.2
```

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `rest`. Suppose you want to create a
RESTful web service project called `my-rest-project` with a package name of
`com.liferay.docs.application` and a class name prefix of `Rest`. You could run
one of the following commands to accomplish this:

```bash
blade create -t rest -p com.liferay.docs -c Rest my-rest-project
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.rest \
    -DgroupId=com.liferay \
    -DartifactId=my-rest-project \
    -Dpackage=com.liferay.docs \
    -Dversion=1.0 \
    -DclassName=Rest \
    -Dauthor=Joe Bloggs \
    -DliferayVersion=7.2
```

After running the Blade command above, your project's directory structure looks
like this:

- `my-rest-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/application`
                    - `RestApplication.java`
            - `resources`
                - `configuration`
                    - `com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-cxf.properties`
                    - `com.liferay.portal.remote.rest.extender.configuration.RestExtenderConfiguration-rest.properties`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is a working RESTful web service and is deployable to a
@product@ instance. To build upon the generated app, modify the project by
adding logic and additional files to the folders outlined above.
