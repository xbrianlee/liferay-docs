---
header-id: how-to-resolve-common-output-errors-reported-by-the-resolve-task
---

# How to Resolve Common Output Errors Reported by the Resolve Task

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="
https://learn.liferay.com/dxp/latest/en/building-applications/tooling/liferay-workspace/configuring-liferay-workspace.html#managing-the-target-platform
">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Liferay Workspace provides the `resolve` Gradle task to validate modules. This
is very useful for finding issues and reporting them as output before
deployment. For general help with OSGi related issues, visit the
[Troubleshooting FAQ](/docs/7-2/appdev/-/knowledge_base/a/troubleshooting-application-development-issues)
section.

For help interpreting the `resolve` task's output, see the list below for common
output errors, what they mean, and how to fix them.

## Missing Import Error

When your module refers to an unavailable import, the container throws this
error. For example, suppose you have a module `test-service` that depends on
the `com.google.common.base` package. If the container can't find that package,
it throws this error:

```bash
Resolution exception in project 'modules:test-service': Unresolved requirements in root project 'modules:test-service':
    Mandatory:
        [osgi.wiring.package ] com.google.common.base; version=[23.0.0,24.0.0)
        [osgi.identity       ] test.service
```

This kind of error can also occur when separate modules require different
versions of another module. If you have *module A* requiring *module Test
version 1* and *module B* requiring *module Test version 4*, without running
the resolver, both modules A and B would compile successfully. When they
were deployed, however, one would fail in the OSGi runtime because both
dependencies cannot be satisfied. These types of scenarios are difficult to
diagnose, but with the `resolve` task, can be found with ease.

To fix missing import errors, you may need to adjust the
[export](/docs/7-2/customization/-/knowledge_base/c/exporting-packages) and/or
[import](/docs/7-2/customization/-/knowledge_base/c/importing-packages)
configuration of your modules. Also, see the
[Resolving Third Party Library Package Dependencies](/docs/7-2/customization/-/knowledge_base/c/adding-third-party-libraries-to-a-module)
tutorial for more information on resolving import errors. Sometimes, this kind
of error can be solved by editing the `resolve` task's list of capabilities. See
the
[Resolving Third Party Library Package Dependencies](/docs/7-2/customization/-/knowledge_base/c/adding-third-party-libraries-to-a-module)
section to learn how to do this.

## Missing Service Reference

If your module references a non-existent service, an error is thrown.
This is helpful because service reference issues are hard to diagnose during
deployment without using the
[Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell).

For example, if your module `test-portlet` references a service (e.g.,
`test.api.TestApi`) it does not have access to, the following error is thrown:

```bash
Resolution exception in project 'modules:test-portlet': Unresolved requirements in project 'modules:test-portlet':
    Mandatory:
        [osgi.identity ] test.portlet
        [osgi.service  ] objectClass=test.api.TestApi
```

To fix this, you must make the service available to your module. If you're
expecting the service to be provided by your target platform, check to make sure
it's being provided. If it's a service provided by a custom module, check that
service provider module and ensure it's correctly providing that service to
your module. To check the target platform for available services, follow the
steps below:

1.  Start your target platform instance.

2.  Open the Gogo shell.

3.  List all services containing a keyword by running `services | grep
    "SERVICE_NAME"`. It's easiest to do this rather than listing all services
    since there are usually too many to sift through.

4.  You can also list services provided by a component. Run `lb -s` to list all
    provided bundles by their bundle symbolic name (BSN). Find the BSN for the
    desired component and then run `scr:info <BSN>`.

If you're unable to track down your missing service, it may be provided by a
customized @product@ core feature or an external @product@ feature. If this is
the case, it isn't included in the target platform's default capabilities. You
can make the custom service capability available to reference by
[generating a new custom distro JAR](/docs/7-2/reference/-/knowledge_base/r/depending-on-a-customized-distribution-of-product).

## Missing Fragment Host

Referring to a non-existent fragment host throws an error. For example, if your
`test.login` fragment is configured to modify a fragment host named
`com.liferay.login.web` that cannot be referenced, the following error is
thrown:

```bash
Resolution exception in project 'modules:test.login': Unresolved requirements in project 'modules:test-login':
    Mandatory:
        [osgi.identity    ] test.login
        [osgi.wiring.host ] com.liferay.login.web; version=1.0.10
```

Configuring a fragment host in your module is typically done with the
`Fragment-Host` header in the `bnd.bnd` file:

```properties
Fragment-Host: com.liferay.login.web;bundle-version="[1.0.0,1.0.1)"
```

To fix this, inspect your target platform to ensure it includes the JAR you're
attempting to add a fragment for. Your fragment host header may be referencing
an incorrect bundle symbolic name (BSN) or version. The easiest way to check
this is by using the
[Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell).
Follow the steps below to find the bundle symbolic name:

1.  Start your target platform instance.

2.  Open the Gogo shell.

3.  List all installed bundles by BSN with the command `lb -s`. You can search
    through the output to find the BSN. If you already know the BSN and want to
    check the version, run `lb -s | grep "<BSN>"`.

Once you know the correct BSN/version to reference, update your `Fragment-Host`
header to resolve the error.

For more information on fragments, see the
[JSP Overrides Using OSGi Fragments](/docs/7-2/customization/-/knowledge_base/c/jsp-overrides-using-osgi-fragments)
tutorial.
