---
header-id: enabling-local-live-staging
---

# Enabling Local Live Staging

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/site-building/publishing-tools/staging/configuring-local-live-staging.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Local Live staging places both your staging environment and your live
environment on the same server. When it's enabled, a clone of the site is
created containing copies of all of the site's existing pages. This means the
staging and live environments share the same JVM, database, portlet data
(depending on which portlets are selected when staging is enabled), and
configurations, such as the properties set in the `portal-ext.properties` file.
The cloned site becomes the staging environment and the original site becomes
the live environment.

You can enable local staging for a site by navigating to the *Publishing* &rarr;
*Staging* menu. To get some hands-on experience with enabling Local Live
staging, you can complete a brief example which creates a Local Live staging
environment for your site. 

1.  Navigate to the Product Menu (left side) and select *Publishing* &rarr;
    *Staging*.

2.  Select *Local Live*. You can also enable page versioning and select staged
    content. For more information on these options, see the
    [Enabling Page Versioning and Staged Content](/docs/7-2/user/-/knowledge_base/u/enabling-page-versioning-and-staged-content)
    article.

3.  Click *Save*.

You've officially begun the staging process!

Because Local Live staging creates a clone of your site, you should only
activate staging on new, clean sites. Having a few pages and some widgets (like
those of the example site you created) is no big deal. If you've already created
a large amount of content, however, enabling staging can take a lot of time
since it's a resource intensive operation. Also, if you intend to use page
versioning to track the history of updates to your site, you should enable it as
early as possible, *before* your site has many pages and lots of content. Your
site's update history isn't saved until you enable page versioning. Page
versioning requires staging (either Local Live or Remote Live) to be enabled.

If you ever need to turn off the staging environment, return back to *Staging*
from the Publishing dropdown. For more information on this, see the
[Disabling Staging](/docs/7-2/user/-/knowledge_base/u/disabling-staging)
article.

Great! Now you're ready to use Local Live Staging.
