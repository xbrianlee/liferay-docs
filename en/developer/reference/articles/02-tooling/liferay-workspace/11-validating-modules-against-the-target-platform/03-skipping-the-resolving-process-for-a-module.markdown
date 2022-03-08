---
header-id: skipping-the-resolving-process-for-a-module
---

# Skipping the Resolving Process for a Module

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="
https://learn.liferay.com/dxp/latest/en/building-applications/tooling/liferay-workspace/configuring-liferay-workspace.html#managing-the-target-platform
">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

It may be easiest to skip validating a particular module during the resolve
process.

1.  Open your workspace's root `build.gradle` file.

2.  Insert the following Gradle code at the bottom of the file:

    ```groovy
    targetPlatform {
        resolveOnlyIf { project ->
            project.name != 'PROJECT_NAME'
        }
    }
    ```

    Be sure to replace the `PROJECT_NAME` filler with your module's name (e.g.,
    `test-api`).

3.  (Optional) If you prefer to disable the Target Platform plugin altogether,
    you can add a slightly different directive to your `build.gradle` file:

    ```groovy
    targetPlatform {
        onlyIf { project ->
            project.name != 'PROJECT_NAME'
        }
    }
    ```

    This both skips the `resolve` task execution and disables BOM dependency
    management. 

Now the `resolve` task skips your module project.
