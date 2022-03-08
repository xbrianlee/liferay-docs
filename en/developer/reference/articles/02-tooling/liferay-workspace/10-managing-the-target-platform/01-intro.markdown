---
header-id: managing-the-target-platform
---

# Managing the Target Platform

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="
https://learn.liferay.com/dxp/latest/en/building-applications/tooling/liferay-workspace/configuring-liferay-workspace.html#managing-the-target-platform
">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

| **Note:** The Target Platform articles currently assume you're using Gradle
| as a build tool. If your projects are built with Maven, you can still
| leverage the Target Platform features, but it is not built into Liferay
| Workspace *yet* ([LPS-90524](https://issues.liferay.com/browse/LPS-90524)).
| See the
| [Targeting a Platform with Maven](/docs/7-2/reference/-/knowledge_base/r/targeting-a-platform-with-maven)
| article to set the Target Platform for Maven-based projects.

Liferay Workspace helps you target a specific release of @product@, so
dependencies get resolved properly. This makes upgrades easy: specify your
target platform, and Workspace points to the new version. All your dependencies
are updated to the latest ones provided in the targeted release.

| **Note:** There are times when configuring dependencies based on a version
| range is better than tracking exact versions. See the
| [Semantic Versioning](/docs/7-2/customization/-/knowledge_base/c/semantic-versioning)
| tutorial for more details.

Next, you'll discover how all of this is possible.

## Dependency Management with BOMs

You can target a version by importing a predefined bill of materials (BOM). This
only requires that you specify a property in your workspace's
`gradle.properties` file (see
[this article](/docs/7-2/reference/-/knowledge_base/r/setting-the-target-platform)
for details).

| **Note:** The Target Platform feature is only supported for Gradle projects at
| this time.

Each @product@ version has a predefined BOM that you can specify for your
workspace to reference. Each BOM defines the artifacts and their versions used
in the specific release. BOMs list all dependencies in a management fashion, so
it doesn't **add** dependencies to your project; it only **provides** your build
tool (e.g., Gradle or Maven) the versions needed for the project's defined
artifacts. This means you don't need to specify your dependency versions; the
BOM automatically defines the appropriate artifact versions based on the BOM.

You can override a BOM's defined artifact version by specifying a different
version in your project's `build.gradle`. Artifact versions defined in your
project's build files override those specified in the predefined BOM. Note that
overriding the BOM can be dangerous; make sure the new version is compatible in
the targeted platform.

For more information on BOMs, see the
[Importing Dependencies](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism#Importing_Dependencies)
section in Maven's official documentation. To view a BOM file and its mapping of
artifacts and versions, visit [repository.liferay.com](https://repository.liferay.com)
and search for the BOM artifacts (e.g.,
[release.portal.bom](https://repository.liferay.com/nexus/index.html#nexus-search;quick~release.portal.bom)
and
[release.dxp.bom](https://repository.liferay.com/nexus/index.html#nexus-search;quick~release.dxp.bom)).

Pretty cool, right? Next, you'll learn how to leverage platform targeting in Dev
Studio.

## Leveraging Target Platform in Dev Studio

[Liferay Dev Studio 3.2+](/docs/7-2/reference/-/knowledge_base/r/liferay-dev-studio) 
helps you streamline targeting a specific version even more. Dev Studio can
index the configured @product@ source code to

- provide advanced Java search (Open Type and Reference Searching)
  ([article](/docs/7-2/reference/-/knowledge_base/r/searching-product-source-in-dev-studio))
- debug @product@ sources
  ([article](/docs/7-2/reference/-/knowledge_base/r/debugging-product-source-in-dev-studio))

To enable this functionality, set the following property in your workspace's
`gradle.properties` file:

```properties
target.platform.index.sources=true
```

| **Note:** Portal source indexing is disabled in Gradle workspace version
| 2.0.3+ (Target Platform plugin version 2.0.0+). See the
| [Updating Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/updating-liferay-workspace)
| article for instructions on how to update your workspace.

These options in Dev Studio are only available when developing in a Liferay
Workspace, or if you have the
[Target Platform Gradle plugin](/docs/7-2/reference/-/knowledge_base/r/target-platform-gradle-plugin)
applied to your multi-module Gradle project with specific configurations. See
the
[Targeting a Platform Outside of Workspace](/docs/7-2/reference/-/knowledge_base/r/targeting-a-platform-outside-of-workspace)
article for more info on applying the Target Platform Gradle plugin.

Continue on to learn how to set the target platform.
