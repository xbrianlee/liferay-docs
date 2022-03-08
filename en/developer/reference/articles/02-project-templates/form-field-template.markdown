---
header-id: form-field-template
---

# Form Field Template

[TOC levels=1-4]

In this article, you'll learn how to create a Liferay form field as a Liferay
module. To create a Liferay form field via the command line using Blade CLI or
Maven, use one of the commands with the following parameters:

```bash
blade create -t form-field [-p packageName] [-c className] projectName
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.form.field \
    -DartifactId=[projectName] \
    -Dpackage=[packageName] \
    -DclassName=[className] \
    -DliferayVersion=7.2
```

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `form-field`. Suppose you want to
create a form field project called `my-form-field-project` with a package name
of `com.liferay.docs.form.field` and a class name prefix of `MyFormField`. You
could run one of the following commands to accomplish this:

```bash
blade create -t form-field -p com.liferay.docs -c MyFormField my-form-field-project
```

or

```bash
mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.form.field \
    -DgroupId=com.liferay \
    -DartifactId=my-form-field-project \
    -Dpackage=com.liferay.docs \
    -Dversion=1.0 \
    -DclassName=MyFormField \
    -Dauthor=Joe Bloggs \
    -DliferayVersion=7.2
```

After running the Blade command above, your project's directory structure looks
like this:

- `my-form-field-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/form/field`
                    - `MyFormFieldDDMFormFieldRenderer.java`
                    - `MyFormFieldDDMFormFieldType.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `config.js`
                        - `my-form-field-project.soy`
                        - `my-form-field-project_field.js`
                        - `my-form-field-project_field.es.js`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is a working form field and is deployable to a @product@
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
