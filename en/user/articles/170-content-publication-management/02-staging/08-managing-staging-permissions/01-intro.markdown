---
header-id: managing-permissions
---

# Managing Permissions

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/site-building/publishing-tools/staging/managing-staging-permissions.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

The staging environment has many different options for building and managing
a Site and its pages. Sometimes administrators want to limit access to staging's
subset of powerful features. A Role with permissions can accomplish this. To
create/modify a Role, complete the following steps:

1.  Navigate to the *Control Panel* &rarr; *Users* &rarr; *Roles*.

2.  To create a new Role, select the *Add* button
    (![Add](../../../../images/icon-add.png)) and complete the New Role menu.
    Once you have a new Role created, or you've decided on the Role you want to
    modify, select the Role's *Actions* icon
    (![Actions](../../../../images/icon-actions.png)) and select *Edit*.

3. From the top menu, select *Define Permissions*.

The most obvious permissions for staging are the general permissions that look
similar to the permissions for most Liferay apps. These permissions are in the
*Site Administration* &rarr; *Publishing* &rarr; *Staging* section of the Define
Permissions menu. They include

- *Access in Site Administration*
- *Add to Page*
- *Configuration*
- *Permissions*
- *Preferences*
- *View*

Also, there are some Site resource permissions that deal directly with staging.
These permissions are in the *Control Panel* &rarr; *Sites* &rarr; *Sites*
section in the Define Permissions menu. The relevant Site resource permissions
related to staging are listed below:

**Add Page Variation:** Hides/shows the *Add Page Variation* button on the
Staging Bar's Manage Page Variations screen.

**Add Site Pages Variation:** Hides/shows the *Add Site Pages Variation* button
on the Staging Bar's Manage Site Page Variations screen.

**Export/Import Application Info:** If the Publish Staging permission is not
granted, hides/shows the application level Export/Import menu. The Configuration
permission for the Export/Import app is also required.

**Export/Import Pages:** If the Publish Staging permission is not granted,
hides/shows the Export/Import app in the Site Administration menu.

**Manage Staging:** Hides/shows the Staging Configuration menu in the
Site Administration &rarr; *Publishing* &rarr; *Staging* &rarr; *Options*
(![Options](../../../../images/icon-options.png)) menu.

**Publish Application Info:** Hides/shows the application level Staging menu.

**Publish Staging:** Hides/shows the *Publish to Live* button on the Staging Bar
and hides/shows the *Add Staging Process* button
(![Add](../../../../images/icon-add.png)) in the Site Administration menu's
Staging app. This permission automatically applies the *Export/Import
Application Info*, *Export/Import Pages*, and *Publish Application Info*
permission functionality regardless of whether they're unselected. 

**View Staging:** If Publish Staging, Manage Pages, Manage Staging, or Update
permissions are not granted, hides/shows the Site Administration menu's Staging
app.

Notice that some of the permissions above are related to the export/import
functionality. Since these permissions are directly affected by the Publish
Staging permission, they are important to note. Visit the
[Importing/Exporting Pages and Content](/docs/7-2/user/-/knowledge_base/u/importing-exporting-pages-and-content)
section for more details on importing/exporting Site and page content.
