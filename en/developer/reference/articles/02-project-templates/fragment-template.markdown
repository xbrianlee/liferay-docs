---
header-id: using-the-fragment-template
---

# Fragment Template

[TOC levels=1-4]

In this article, you'll learn how to create a Liferay fragment as a Liferay
module. You can learn more about fragment modules in the
[Declaring a Fragment Host](/docs/7-2/customization/-/knowledge_base/c/jsp-overrides-using-osgi-fragments#declaring-a-fragment-host)
article and in section 3.14 of the
[OSGi Alliance's core specification document](https://osgi.org/download/r6/osgi.core-6.0.0.pdf).

To create a Liferay fragment via the command line using Blade CLI or
Maven, use one of the commands with the following parameters:

```bash
blade create -t fragment [-h hostBundleName] [-H hostBundleVersion] projectName
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.fragment \
    -DartifactId=[projectName] \
    -Dpackage=[packageName] \
    -DclassName=[className] \
    -DliferayVersion=7.2
```

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `fragment`. Suppose you want to create
a fragment project called `my-fragment-project` with a host bundle symbolic name
of `com.liferay.login.web` and host bundle version of `1.0.0`. You could run the
following command to accomplish this:

```bash
blade create -t fragment -h com.liferay.login.web -H 1.0.0 my-fragment-project
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.fragment \
    -DgroupId=com.liferay \
    -DartifactId=my-fragment-project \
    -Dversion=1.0 \
    -Dpackage= \
    -DhostBundleSymbolicName=com.liferay.login.web \
    -DhostBundleVersion=1.0.0 \
    -DliferayVersion=7.2
```

The folder structure is created, but there are no files. The only files created
are the `bnd.bnd` and `build.gradle` files, which specify your host bundle and
its information, and your build tool's files. After running the Blade command
above, your project's directory structure looks like this:

- `my-fragment-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `my/fragment/project`
            -`resources`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.
