---
header-id: simulation-panel-entry-template
---

# Simulation Panel Entry Template

[TOC levels=1-4]

In this article, you'll learn how to create a Liferay simulation panel entry as
a Liferay module. To create a simulation panel entry via the command line using
Blade CLI or Maven, use one of the commands with the following parameters:

```bash
blade create -t simulation-panel-entry [-p packageName] [-c className] projectName
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.simulation.panel.entry \
    -DartifactId=[projectName] \
    -Dpackage=[packageName] \
    -DclassName=[className] \
    -DliferayVersion=7.2
```

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `simulation-panel-entry`. Suppose you
want to create a simulation panel entry project called
`my-simulation-panel-entry` with a package name of
`com.liferay.docs.application.list` and a class name of
`SampleSimulationPanelApp`. You could run the following command to accomplish
this:

```bash
blade create -t simulation-panel-entry -p com.liferay.docs -c Sample my-simulation-panel-entry
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.simulation.panel.entry \
    -DgroupId=com.liferay \
    -DartifactId=my-simulation-panel-entry \
    -Dpackage=com.liferay.docs \
    -Dversion=1.0 \
    -DclassName=Sample \
    -Dauthor=Joe Bloggs \
    -DliferayVersion=7.2
```

After running the Blade command above, your project's directory structure would
look like this

- `my-simulation-panel-entry`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/application/list`
                    - `SampleSimulationPanelApp.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `simulation_panel.jsp`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[simulation-panel-app](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/simulation-panel-app)
sample project for a more expanded sample of a control menu entry. 

<!--Uncomment once article is available
Likewise, see
the
Extending the Simulation Menu
tutorial for instructions on customizing a simulation panel entry project.
-->
