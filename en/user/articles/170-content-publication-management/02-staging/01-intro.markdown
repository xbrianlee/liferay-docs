---
header-id: staging
---

# Staging

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/site-building/publishing-tools/staging/staging-overview.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Staging is a tool used to manage content publication. The concept of staging is
simple: you can modify your site behind the scenes and then publish all your
updates in one shot. You don't want users seeing your web site change before
their eyes as you're modifying it, do you? The staging environment lets you make
changes to your site in a specialized *staging area* that's linked to a
production environment. Typically the staging site is used only by content
editors and site administrators, while the production environment is public.
Content is published from staging to production all at once.

Site administrators can set up their staging environments locally or remotely.
With Local Live staging, your staging environment and live environment are
hosted on the same server. Remote Live staging has the staging and live
environments on separate servers. You'll learn more about the differences
between these two staging environments and how to enable them for your portal
instance.

You can also leverage the Page Versioning feature. This feature works with both
Local Live and Remote Live staging and lets site administrators create multiple
variations of staged pages. This allows several different versions of sites and
pages to be developed at the same time. Variations can be created, merged, and
published using a Git-like versioning system. In the next section, you'll jump
in to see how to enable staging.
