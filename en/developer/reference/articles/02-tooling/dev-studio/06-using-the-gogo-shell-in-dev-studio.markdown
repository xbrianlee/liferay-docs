---
header-id: using-the-gogo-shell-in-dev-studio
---

# Using the Gogo Shell in Dev Studio

[TOC levels=1-4]

If you're using Dev Studio to develop and deploy your projects, you may be
interested in managing them after they're deployed with Dev Studio too. You can
do this with the Dev Studio's
[Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell)
feature.

1.  Right-click your started portal instance in the Servers view.

2.  Select *Open Gogo Shell*. 

    ![Figure 1: Select *Open Gogo Shell* to open a terminal window in Dev Studio using Gogo shell.](../../../images/open-gogo-shell.png)

    A Gogo shell terminal appears, allowing you to enter Gogo commands to
    inspect your Liferay instance and the projects deployed to it.

3.  A common use case for the Gogo Shell is verifying successful project
    deployment. Enter the `lb` command to view a list of deployed bundles. If
    the project status is active, then it deployed successfully.

    ![Figure 2: You can check to see if your project deployed successfully to Liferay using the Gogo shell.](../../../images/gogo-deploy-successful.png)

**Important:** Dev Studio's Gogo shell usage requires
[Developer Mode](/docs/7-2/frameworks/-/knowledge_base/f/using-developer-mode-with-themes)
to be enabled. Developer Mode is enabled in
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace)
by default.

Excellent! You've learned how to manage your deployed projects with Dev Studio's
Gogo Shell integration.
