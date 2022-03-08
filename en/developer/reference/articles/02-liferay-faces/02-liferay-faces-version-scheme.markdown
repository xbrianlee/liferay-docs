---
header-id: liferay-faces-version-scheme
---

# Liferay Faces Version Scheme

[TOC levels=1-4]

In this article, you'll learn which Liferay Faces artifacts should be used with
your portlet and explore the Liferay Faces versioning scheme by discovering what
each component of a version means. Once you have the versioning scheme mastered,
you can view several example configurations.

## Using The Liferay Faces Archetype Portlet

The [Liferay Faces Archetype portlet](http://liferayfaces.org) can be used to
determine the Liferay Faces artifacts and versions that you must include in
your portlet. Select your preferred Liferay Portal version, JSF version,
component suite (optional), and build tool, and the portlet will provide you
with both a command to generate your portlet from a Maven archetype and a list
of dependencies that can be copied into your build files. In the next section,
you'll be provided with compatibility information about each version of the
Liferay Faces artifacts.

## Liferay Faces Alloy

Provides a suite of JSF components that utilize [AlloyUI](http://alloyui.com/).

|Branch|Example Artifact|AlloyUI|JSF API|Additional Info|
|------|----------------|-------|-------|---------------|
|[master \(4.x\)](https://github.com/liferay/liferay-faces-alloy/tree/master)|com.liferay.faces.alloy-4.1.0.jar|3.1.x|2.2+|*AlloyUI 3.1.x is the version that comes bundled with Liferay Portal 7.3.*|
|[3.x](https://github.com/liferay/liferay-faces-alloy/tree/3.x)|com.liferay.faces.alloy-3.1.0.jar|3.0.x|2.2+|*AlloyUI 3.0.x is the version that comes bundled with Liferay Portal 7.0/7.1/7.2.*|
|[2.x](https://github.com/liferay/liferay-faces-alloy/tree/2.x)|com.liferay.faces.alloy-2.0.1.jar|2.0.x|2.1+|*AlloyUI 2.0.x is the version that comes bundled with Liferay Portal 6.2.*|
|[1.x](https://github.com/liferay/liferay-faces-alloy/tree/1.x)|com.liferay.faces.alloy-1.0.1.jar|2.0.x|1.2|*AlloyUI 2.0.x is the version that comes bundled with Liferay Portal 6.2.*|

## Liferay Faces Bridge

Provides the ability to deploy JSF web applications as portlets within
[Apache Pluto](https://portals.apache.org/pluto/), the reference implementation
for JSR 286 (Portlet 2.0) and JSR 362 (Portlet 3.0).

|Branch|Example Artifacts|Portlet API|JSF API|JCP Specification|Additional Info|
|------|-----------------|:-----------:|:-------:|:-----------------:|---------------|
|API: [5.x](https://github.com/liferay/liferay-faces-bridge-api/tree/5.x)<br/>IMPL: [5.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/5.x)|com.liferay.faces.bridge.api-5.0.0.jar<br/>com.liferay.faces.bridge.impl-5.0.0.jar|3.0|2.2|[JSR 378](https://www.jcp.org/en/jsr/detail?id=378)|*Under "Final Review" by the JCP and scheduled for release in 2020.*|
|API: [4.x](https://github.com/liferay/liferay-faces-bridge-api/tree/4.x)<br/>IMPL: [4.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/4.x)|com.liferay.faces.bridge.api-4.1.0.jar<br/>com.liferay.faces.bridge.impl-4.0.0.jar|2.0|2.2|[JSR 329](https://www.jcp.org/en/jsr/detail?id=329)|*Includes non-standard bridge extensions for JSF 2.2.*|
|API: [3.x](https://github.com/liferay/liferay-faces-bridge-api/tree/3.x)<br/>IMPL: [3.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/3.x)|com.liferay.faces.bridge.api-3.1.0.jar<br/>com.liferay.faces.bridge.impl-3.0.0.jar|2.0|2.1|[JSR 329](https://www.jcp.org/en/jsr/detail?id=329)|*Includes non-standard bridge extensions for JSF 2.1.*|
|API: [2.x](https://github.com/liferay/liferay-faces-bridge-api/tree/2.x)<br/>IMPL: [2.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/2.x)|com.liferay.faces.bridge.api-2.1.0.jar<br/>com.liferay.faces.bridge.impl-2.0.0.jar|2.0|1.2|[JSR 329](https://www.jcp.org/en/jsr/detail?id=329) (MR1)|*Includes support for Maintenance Release 1 (MR1).*|
|1.x|N/A|1.0|1.2|[JSR 301](https://www.jcp.org/en/jsr/detail?id=301)|*N/A (Not Applicable) since Liferay Faces Bridge has never implemented JSR 301.*|

## Liferay Faces Bridge Ext

Extension to Liferay Faces Bridge that provides compatibility with
[Liferay Portal](https://liferay.dev/-/portal)
and also takes advantage of Liferay-specific features such as friendly URLs.

|Branch           |Example Artifact                  |&nbsp;&nbsp;Liferay Portal API&nbsp;&nbsp;|&nbsp;&nbsp;Bridge API&nbsp;&nbsp;|&nbsp;&nbsp;Portlet API&nbsp;&nbsp;|JSF API|
|-----------------|------------------------------------|:--------------:|:----------:|:-----------:|:-------:|
|[8.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/master)|com.liferay.faces.bridge.ext-8.0.0.jar|7.3.0+|5.x|3.0|2.3|
|[7.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/7.x)|com.liferay.faces.bridge.ext-7.0.0.jar|7.3.0+|5.x|3.0|2.2|
|[6.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/6.x)|com.liferay.faces.bridge.ext-6.0.0.jar|7.3.0+|4.x|2.0|2.2|
|[5.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/5.x)|com.liferay.faces.bridge.ext-5.0.4.jar|7.0.x/7.1.x/7.2.x|4.x|2.0|2.2|
|[4.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/4.x)|UNUSED|N/A|N/A|N/A|N/A|
|[3.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/3.x)|com.liferay.faces.bridge.ext-3.0.1.jar|6.2.x|4.x|2.0|2.2|
|[2.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/2.x)|com.liferay.faces.bridge.ext-2.0.1.jar|6.2.x|3.x|2.0|2.1|
|[1.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/1.x)|com.liferay.faces.bridge.ext-1.0.1.jar|6.2.x|2.x|2.0|1.2|

## Liferay Faces Portal

Provides a suite of JSF components that are based on the JSP tags provided by
[Liferay Portal](https://liferay.dev/-/portal).

|Branch|Example Artifact|Liferay Portal API&nbsp;&nbsp;|&nbsp;&nbsp;Portlet API|&nbsp;&nbsp;JSF API|
|------|----------------|:------------------:|:-------:|:-------:|
|[6.x](https://github.com/liferay/liferay-faces-portal/tree/master)|com.liferay.faces.portal-6.0.0.jar|7.2+|3.0|2.3|
|[5.x](https://github.com/liferay/liferay-faces-portal/tree/5.x)|com.liferay.faces.portal-5.0.0.jar|7.2+|3.0|2.2|
|[4.x](https://github.com/liferay/liferay-faces-portal/tree/4.x)|com.liferay.faces.portal-4.0.0.jar|7.2/7.3|2.0|2.2|
|[3.x](https://github.com/liferay/liferay-faces-portal/tree/3.x)|com.liferay.faces.portal-3.0.1.jar|7.0/7.1/7.2|2.0|2.2|
|[2.x](https://github.com/liferay/liferay-faces-portal/tree/2.x)|com.liferay.faces.portal-2.0.1.jar|6.2|2.0|2.1/2.2|
|[1.x](https://github.com/liferay/liferay-faces-portal/tree/1.x)|com.liferay.faces.portal-1.0.1.jar|6.2|2.0|1.2|

## Liferay Faces Util

Library that contains general purpose JSF utilities to support many of the
sub-projects that comprise Liferay Faces.

|Branch|Example Artifact|&nbsp;&nbsp;JSF API|
|------|----------------|:-------:|
|[4.x](https://github.com/liferay/liferay-faces-util/tree/4.x)|com.liferay.faces.util-3.1.0.jar|2.3|
|[3.x](https://github.com/liferay/liferay-faces-util/tree/3.x)|com.liferay.faces.util-3.1.0.jar|2.2|
|[2.x](https://github.com/liferay/liferay-faces-util/tree/2.x)|com.liferay.faces.util-2.1.0.jar|2.1|
|[1.x](https://github.com/liferay/liferay-faces-util/tree/1.x)|com.liferay.faces.util-1.1.0.jar|1.2|

Now that you know all about the Liferay Faces versioning scheme, you may be
curious as to how these components interact with each other. Refer to the
following figure to view the Liferay Faces dependency diagram.

![Figure 1: The Liferay Faces dependency diagram helps visualize how components interact and depend on each other.](../../images/liferay-faces-dependency-diagram.png)


Next, you can view some example configurations to see the new versioning scheme
in action.
