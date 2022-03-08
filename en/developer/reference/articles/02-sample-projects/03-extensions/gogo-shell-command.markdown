---
header-id: gogo-shell-command
---

# Gogo Shell Command

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120"> This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/liferay-internals/fundamentals/using-the-gogo-shell.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

The Gogo Shell Command sample demonstrates adding a custom command to
@product@'s Gogo shell environment. All @product@ installations have a Gogo
shell environment, which lets system administrators interact with @product@'s
module framework on a local server machine.

This example adds a new custom Gogo shell command called `usercount` under the
`blade` scope. It prints out the number of registered users on your @product@
installation.

To test this sample, follow the instructions below:

1.  Start a @product@ installation.
2.  Navigate to the Control Panel &rarr; *Configuration* &rarr; *Gogo Shell*.
3.  Execute `help` to view all the available commands. The sample Gogo shell
    command is listed.

    ![Figure 1: The sample Gogo shell command is listed with all the available commands.](../../../images/gogo-shell-1.png)

4.  Execute `usercount` to execute the new custom command. The number of users
    on your running Liferay Portal installation is printed.

    ![Figure 2: The outcome of executing the `usercount` command.](../../../images/gogo-shell-2.png)

## What API(s) and/or code components does this sample highlight?

This sample demonstrates creating a new Gogo shell command by leveraging
`osgi.command.*` properties in a Java class.

## How does this sample leverage the API(s) and/or code component?

To add this new Gogo shell command, you must implement the logic in a Java
class with the following two properties:

- `osgi.command.function`: the command's name, which must match the method name
   in the registered service implementation.
- `osgi.command.scope`: the general scope or namespace for the command.

These properties are set in your class's `@Component` annotation like this:

```java
@Component(
    property = {"osgi.command.function=usercount", "osgi.command.scope=blade"},
    service = Object.class
)
```

The logic for the `usercount` command is specified in the method with the same
name:

```java
public void usercount() {
    System.out.println(
        "# of users: " + getUserLocalService().getUsersCount());
}
```

This method uses *Declarative Services* to get a reference for the
`UserLocalService` to invoke the `getUsersCount` method. This lets you find the
number of users currently in the system.

For more information on using the Gogo shell, see the
[Using the Felix Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell)
tutorial.

## Where Is This Sample?

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.2/gradle/extensions/gogo)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace/extensions/gogo)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.2/maven/extensions/gogo)
