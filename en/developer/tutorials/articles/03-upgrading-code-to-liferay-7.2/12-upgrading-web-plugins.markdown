---
header-id: upgrading-web-plugins
---

# Upgrading Web Plugins

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Upgrading Web Plugins</p><p>Step 1 of 1</p>
</div>

Web plugins are regular
[Java EE web modules](https://docs.oracle.com/cd/E19226-01/820-7627/bnadx/index.html)
designed to work with @product@. These plugins were stored in the `webs` folder
of the legacy Plugins SDK.

Upgrading a Liferay web plugin involves these steps:

1.  Adapt the plugin to @product-ver@'s API using the Liferay Upgrade Planner.
    When running the planner's *Fix Upgrade Problems* step, many of the existing
    issues are autocorrected. For remaining issues, the planner identifies code
    affected by the new API and ways to adapt it.

2.  [Resolve its dependencies](/docs/7-2/tutorials/-/knowledge_base/t/resolving-a-projects-dependencies)

3.  [Deploy it](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project)

After deploying the upgraded portlet, the server prints messages that indicate
the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

You've upgraded and deployed your Liferay web plugin on your @product-ver@
instance. Great job!
