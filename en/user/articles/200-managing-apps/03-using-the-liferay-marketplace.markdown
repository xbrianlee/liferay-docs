---
header-id: using-the-liferay-marketplace
---

# Using the Liferay Marketplace

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/installing-and-managing-apps/getting-started/using-marketplace.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Liferay Marketplace is a hub for sharing, browsing, and downloading apps.
Marketplace leverages the entire Liferay ecosystem to release and share apps in
a user-friendly, one-stop shop.

There are two ways to access the Marketplace.

1.  **Via the website:** Using your favorite browser, navigate to the
    Marketplace at
    [web.liferay.com/marketplace](https://web.liferay.com/marketplace).
    If you're new to Marketplace, this is the easiest way to access it. You can
    browse Marketplace without signing in with your liferay.com account.

2.  **Via the Control Panel:** In the Control Panel, navigate to
    *Apps* &rarr; *Store*. To view Marketplace, you must sign in with your
    liferay.com account.

No matter how you access Marketplace, you'll see the same content. Note that to
download apps, you must have a [liferay.com](https://www.liferay.com) account
and agree to the Marketplace Terms of Use.

Here you'll learn how to,

-   [Find and purchase apps](#finding-and-purchasing-apps)

-   [Manage purchased apps](#managing-purchased-apps)

-   [Renew purchased apps](#renewing-a-purchased-app)

Start with finding and purchasing the apps you want.

## Finding and Purchasing Apps

If you've used an app store before, Marketplace should be familiar. You'll see
apps in the center of the page, in the following sections:

-   Featured Apps: Liferay features a different set of apps each month.

-   New and Interesting: The latest apps added to Marketplace.

-   Most Viewed in the Past Month: The top 5 most viewed apps in the last month.

-   Themes / Site Templates: Apps that change your Liferay instance's look and
    feel.

-   App categories: Communication, productivity, security, etc.

-   Weekly Stats: The newest apps, latest apps updated, and trend chart for app
    downloads and views.

Each section's *See All* link shows more section info. At the top of the
page, you can search Marketplace by category, @product@ version, and price. To
browse by category, click the *Categories* menu at the top of the page.

![Figure 1: The Liferay Marketplace home page lets you browse and search for apps.](../../images/marketplace-homepage.png)

Click an app to view its details. This includes its description, screenshots,
price, latest version, number of downloads, a link to the developer's website, a
link to the app's license agreement, and a purchase button (labeled Free or Buy,
depending on the price). You can also view the app's version history, read
reviews, or write your own review.

The purchase button prompts you to choose a purchase type. You can purchase an
app for your personal account, or for a Liferay project associated with your
company. If you have the necessary permissions, you can also create a new
project for your company. Once you select a purchase type, accept the EULA and
Terms of Service, and click *Purchase*.

![Figure 2: Click an app to view its details.](../../images/marketplace-app-details.png)

Once you purchase an app, you can download and install it.

| **Warning:** Not all apps are designed to be "auto deployed"---deployed while
| the server is running. Deploying that way can cause instabilities, such as
| class loading leaks and memory leaks. On production systems, avoid "auto
| deploying" apps whenever possible. See the
| [best practices for managing apps in production](/docs/7-2/user/-/knowledge_base/u/managing-and-configuring-apps#managing-apps-in-production).

An app downloads and installs immediately if you purchase it from the Control
Panel. If you purchase the app on the Marketplace website, however, your receipt
is displayed immediately after purchase. To download the app, click the *See
Purchased* button on the bottom of the receipt, and then click the *App* button
to start the download. You must then [install the app
manually](/docs/7-2/user/-/knowledge_base/u/installing-apps-manually).
Alternatively, you can use Marketplace from the Control Panel to download and
install the app after purchase on the Marketplace website. The next section
shows you how to do this.

Note that sometimes administrators disable automatic app installations so they
can manage installations manually. In this case, Marketplace apps downloaded
from the Control Panel are placed in the `deploy` folder in [Liferay
Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home). Administrators must
then manually install the app from this folder. Manual install is also required
if the server is behind a corporate firewall or otherwise lacks direct
Marketplace access. Regardless of how the app is downloaded, the manual install
process is the same. For details, see the article [Installing Apps
Manually](/docs/7-2/user/-/knowledge_base/u/installing-apps-manually).

## Managing Purchased Apps

| **Important**: When uninstalling an app or module, make sure to use the same
| agent you used to install the app. For example, if you installed it with
| Marketplace, uninstall it with Marketplace. If you installed it with the file
| system, use the
| [file system](/docs/7-2/user/-/knowledge_base/u/installing-apps-manually)
| to uninstall it. If you installed it with the App Manager, however, use
| [Blacklisting](/docs/7-2/user/-/knowledge_base/u/blacklisting-osgi-bundles-and-components)
| to uninstall it.

There are two places to manage your purchased apps:

1.  Your [liferay.com](https://www.liferay.com) account's home page. After
    signing in, click the user menu at the top-right and select *Account Home*.
    Note that your home page is distinct from your profile page. Your home page
    is private, while your profile page is public. On your home page, select
    *Apps* from the menu on the left to view your projects. Select a project to
    view its registered apps. Clicking an app lets you view its versions. You
    can download the version of the app that you need. This is especially useful
    if you need a previous version of the app, or can't download the app from
    the Control Panel.

    ![Figure 3: You can manage your purchased apps from your liferay.com account's home page.](../../images/marketplace-project-apps.png)

2.  From the Control Panel. Navigate to *Apps* &rarr; *Purchased* to see your
    purchased apps. A button next to each app lets you install or uninstall the
    app. If the app isn't compatible with your @product@ version, *Not
    Compatible* is displayed in place of the button. Additional compatibility
    notes are also shown, such as whether a newer version of the app is
    available. You can also search for an app here by project, category, and
    title. Clicking the app takes you to its Marketplace entry.

    ![Figure 4: You can also manage your purchased apps from within a running Liferay instance.](../../images/marketplace-purchased.png)

## Renewing a Purchased App

To continue using a purchased app whose license terms are non-perpetual, you
must renew your app subscription, register your server to use the app, and
generate a new activation key to use on your server. Here are the steps:

1.  Go to
    [https://web.liferay.com/marketplace](https://web.liferay.com/marketplace).

2.  Click your profile picture in the upper right corner and select *Purchased
    Apps*. The Purchased Apps page appears and shows your app icons organized by
    project.

3.  Click your app's icon. Your app's details page appears.

4.  Click *Manage Licenses*.

5.  Select *Register New Server*.

6.  Select the most recent *Order ID* (typically the order that has no
    registered servers).

7.  Fill in your server's details.

8.  Click *Register*.

9.  Click *Download*. The new app activation key to use on your server
    downloads.

10. Copy the activation key file to your `deploy/` folder in your [`[Liferay
    Home]`](/docs/7-2/deploy/-/knowledge_base/d/liferay-home).

You can continue using the application on your server.
