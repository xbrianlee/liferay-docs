---
header-id: resolving-breaking-changes
---

# Resolving Breaking Changes

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Fixing Upgrade Problems</p><p>Step 2 of 2</p>
</div>

Liferay goes to great lengths to maintain backwards compatibility. Sometimes,
breaking changes are necessary to improve @product@. There may be cases where
breaking changes affect your code upgrade process and must be resolved. A
breaking change can include

- Functionality that is removed or replaced
- API incompatibilities: Changes to public Java or JavaScript APIs
- Changes to context variables available to templates
- Changes in CSS classes available to Liferay themes and portlets
- Configuration changes: Changes in configuration files, like
  `portal.properties`, `system.properties`, etc.
- Execution requirements: Java version, J2EE Version, browser versions, etc.
- Deprecations or end of support: For example, warning that a certain
  feature or API will be dropped in an upcoming version.
- Recommendations: For example, recommending using a newly introduced API that
  replaces an old API, in spite of the old API being kept in Liferay Portal for
  backwards compatibility.

Liferay provides a list of breaking changes for every major release to ensure
you can easily adapt your code during the upgrade process.

- [@product@ 7.0 Breaking Changes](/docs/7-0/reference/-/knowledge_base/r/breaking-changes)
- [@product@ 7.1 Breaking Changes](/docs/7-1/reference/-/knowledge_base/r/breaking-changes)
- [@product-ver@ Breaking Changes](/docs/7-2/reference/-/knowledge_base/r/breaking-changes)

The easiest way to resolve breaking changes is by using the
[Liferay Upgrade Planner](/docs/7-2/reference/-/knowledge_base/r/liferay-upgrade-planner).
It automatically finds all documented breaking changes and can automatically
resolve some of them on its own.

If you're resolving breaking changes manually, make sure to investigate each
breaking change document if you're upgrading code across multiple versions. For
example, if you're upgrading from Liferay Portal 6.2 to @product-ver@, you must
resolve all the breaking changes listed in the three documents listed above.

Now that you've resolved your breaking changes, you'll learn how to upgrade
service builder services next.
