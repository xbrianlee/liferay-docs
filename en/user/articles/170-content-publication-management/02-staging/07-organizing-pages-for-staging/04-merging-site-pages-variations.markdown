---
header-id: merging-site-pages-variations
---

# Merging Site Pages Variations

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/site-building/publishing-tools/staging/page-versioning.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Another powerful feature of Staging's Page Versioning framework is the
possibility of *merging* Site Pages Variations. To merge two Site Pages
Variations, follow the instructions below.

1.  Click the Staging Bar's *Options* button
    (![Options](../../../../images/icon-staging-bar-options.png)) and select
    *Site Pages Variation*.

2.  Click the Site Pages Variation's *Actions* button
    (![Actions](../../../../images/icon-actions.png)) you want to use as the
    base for merging and select *Merge*.

3.  Select the Site Pages Variation to merge on top of the base Site Pages
    Variation.

    ![Figure 1: Select the site pages variation you'd like to merge with your base variation.](../../../../images/merge-site-pages-variation.png)

    Merging works like this:

    - New pages that don't exist in the base variation are added.
    - If a page exists in both Site Pages Variations, and at least one version
      of the page was marked as ready for publication, the latest version marked
      as ready is added as a new page variation in the target page of the
      base variation. Note that older versions or page variations not marked as
      ready for publication aren't copied. Merging can be executed, however,
      as many times as needed and creates the needed page variations in the
      appropriate page of the base site pages variation.
    - Merging does not affect content and doesn't overwrite anything in the base
      variation; it adds more versions, pages, and page variations as needed.

Great! You've merged site pages variations!
