---
header-id: configuring-dependencies
---

# Configuring Dependencies

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120"> This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/liferay-internals/fundamentals/configuring-dependencies.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

@product@'s modular environment lets modules provide and consume capabilities
via Java packages. To leverage packages from other modules or traditional
libraries in your project, you must configure them as dependencies. Here you'll
learn how to find artifacts (modules or libraries) and configure dependencies on
them.

-   [Finding Artifacts](/docs/7-2/customization/-/knowledge_base/c/finding-artifacts)
    explains how to use the Application Manager, Gogo Shell, and @product@
    reference documentation to find artifacts deployed on @product@ and
    available in repositories.

-   [Specifying Dependencies](/docs/7-2/customization/-/knowledge_base/c/specifying-dependencies)
    demonstrates specifying artifacts to Maven and Gradle build frameworks. It
    shows you how to determine whether @product@ already exports packages from
    an artifact and how to configure such artifacts as compile-time
    dependencies.

-   [Resolving Third-Party Library Package Dependencies](/docs/7-2/customization/-/knowledge_base/c/adding-third-party-libraries-to-a-module)
    provides a workflow for using packages that are only available in
    traditional library JARs (JARs that aren't OSGi modules). It involves
    minimizing transitive dependencies so you can resolve dependencies quicker
    and prevent bloating your project with unnecessary JARs.

Your first step is to find the artifacts you need.
