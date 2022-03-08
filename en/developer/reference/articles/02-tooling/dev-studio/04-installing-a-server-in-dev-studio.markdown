---
header-id: installing-a-liferay-server-in-dev-studio
---

# Installing a Liferay Server in Dev Studio

[TOC levels=1-4]

Dev Studio offers a single GUI for managing a Liferay server and its deployed
projects. A server is installed and managed from the Servers view (lower left
corner in Eclipse).

For reference, here's how the Dev Studio server buttons work with your @product@
instance:

- *Start* (![Start Server](../../../images/icon-start-server.png)): Starts the
  server.
- *Stop* (![Stop Server](../../../images/icon-stop-server.png)): Stops the
  server.
- *Debug* (![Debug Server](../../../images/icon-debug-server.png)): Starts the
  server in debug mode. For more information on debugging in Dev Studio, see the
  [Debugging @product@ source in Dev Studio](/docs/7-2/reference/-/knowledge_base/r/debugging-product-source-in-dev-studio)
  article.

Follow these steps to install your server. Note you must have already downloaded
and de-compressed the server bundle: 

1.  In the Servers view, click the *No Servers are available* link. If you
    already have a server installed, you can install a new server by
    right-clicking in the Servers view and selecting *New* &rarr; *Server*.
    This brings up a wizard that walks you through the process of defining a new
    server.

2.  Select the type of server you would like to create from the list of
    available options. For a standard server, open the *Liferay, Inc.* folder
    and select the *Liferay 7.x* option. You can change the server name to
    something more unique too; this is the name displayed in the Servers view.
    Then click *Next*.

    ![Figure 1: Choose the type of server you want to create.](../../../images/define-new-server.png)

    **Note:** If you've already configured previous Liferay servers, you'll be
    provided the *Server runtime environment* field, which lets you choose
    previously configured runtime environments. If you want to re-add an
    existing server, select one from the dropdown menu. You can also add a new
    server by selecting *Add*, or you can edit existing servers by selecting
    *Configure runtime environments*. Once you've configured the server runtime
    environment, select *Finish*. If you selected an existing server, your
    server installation is finished; you can skip steps 3-5.

3.  Enter a name for your server. This is the name for the @product@ runtime
    configuration used by Dev Studio. This is not the display name used in the
    Servers tab.

4.  Browse to the installation folder of the @product@ bundle. For example,
    `C:\liferay-ce-portal-7.2.0-m2\tomcat-9.0.10`.

    ![Figure 2: Specify the installation folder of the bundle.](../../../images/specify-bundle-directory.png)

5.  Select a runtime JRE and click *Finish*. Your new server appears under the
    Servers view.

    ![Figure 3: Your new server appears under the *Servers* view.](../../../images/new-server-added.png)

Congratulations! Your server is now available in Liferay Dev Studio!
