---
header-id: getting-started-with-lcs
---

# Getting Started with LCS

[TOC levels=1-4]

| **Note:** LCS is deprecated and will be shut down on December 31, 2021. 
| Customers who activate LCS are advised to replace it with our latest activation
| key type which is suitable for virtualized environments. 
|
| For further information, please see [Changes to Liferay Product Activation](https://help.liferay.com/hc/en-us/articles/4402347960845-Changes-to-Liferay-Product-Activation).

To use LCS, you must register a server in an LCS environment. An LCS environment 
represents a physical cluster of servers or a virtual or logical aggregation of 
servers. Each environment is part of an LCS project. An LCS project represents a 
group of users belonging to a company or organization. For example, a project 
can consist of all the users from a project team or business unit, or it can 
include the entire company. 

LCS projects don't initially contain any environments. You must therefore create 
one before you can register any servers in LCS. The first time you log in to 
[lcs.liferay.com](https://lcs.liferay.com), 
LCS presents you with a wizard that walks you through the environment creation 
process. Click *Get Started* to begin. 

![Figure 1: Click *Get Started* to begin the wizard.](../../images-dxp/lcs-onboarding-00.png)

Each of these steps corresponds to a step in the wizard: 

1.  Select the LCS project for your new environment. You can select any of 
    your available LCS projects. Note that each project lists its available 
    subscriptions and whether it supports elastic subscriptions. 

    ![Figure 2: Select the LCS project you want to create the environment in, and click *Next*.](../../images-dxp/lcs-onboarding-01.png)

2.  Name and describe the environment. The name is mandatory, but the 
    description is optional. Although you can enter anything you wish in these 
    fields, it's best to choose a name and description that accurately identify 
    the environment (e.g., Development, Production, Test, etc.). Note that you 
    can change these values after creating the environment. 

    ![Figure 3: Name and describe the environment, then click *Next*.](../../images-dxp/lcs-onboarding-02.png)

3.  Select the environment's subscription type from the project's available
    subscriptions. Even if you won't use LCS to activate the servers defined for
    this environment, you must still select a subscription type. Also note that
    you can't change this selection after creating the environment. 

    ![Figure 4: Select the environment's subscription type, then click *Next*.](../../images-dxp/lcs-onboarding-03.png)

4.  Select whether servers that connect to this environment are part of a 
    cluster. LCS provides additional tools in clustered environments that help
    you manage the cluster. For example, clustered environments show
    cluster-specific metrics, and fix packs apply to all cluster nodes. There
    are a few things to keep in mind if you set the environment as clustered: 

    -   You can't change this setting after creating the environment.
    -   Each clustered environment can only support nodes that belong to a 
        single cluster. To connect a different cluster's nodes, you must create 
        a separate clustered environment exclusively for those nodes. 
    -   You must set the portal property `cluster.link.enabled` to `true` in any 
        servers that connect to a clustered environment. 

    ![Figure 5: Select whether this is a clustered environment, then click *Next*.](../../images-dxp/lcs-onboarding-04.png)

5.  Select whether the environment allows elastic subscriptions. Elastic 
    subscriptions let you register an unlimited amount of servers. This is 
    critical for auto-scaling situations in which servers are created and 
    destroyed automatically in response to demand. Elastic environments are also 
    useful for bringing additional servers online on a temporary basis for any 
    other purpose, such as business continuity planning. For more information, 
    see 
    [the documentation on elastic subscriptions](/docs/7-2/deploy/-/knowledge_base/d/managing-liferay-dxp-subscriptions#elastic-subscriptions). 
    Also note that you can't change this selection after creating the 
    environment. 

    ![Figure 6: Select whether this is an elastic environment, then click *Next*.](../../images-dxp/lcs-onboarding-05.png)

6.  Enable the LCS service you want to use with servers that connect to this 
    environment. The following service is available: 

    **Liferay Instance Activation:** Enabling this lets LCS activate any 
    @product@ instances that connect to the environment. If you disable this 
    service, you must activate via an XML file from Liferay support, and 
    such instances must run version 5.0.0 or newer of the LCS client app. 

    Note that you **must** use LCS for activation of Elastic subscriptions. 
    Otherwise, you don't have to use LCS for activation. 

    Portal Analytics, Fix Pack Management and Portal Properties Analysis have been removed from the list of available services. For more information about this change, please read [this article](https://help.liferay.com/hc/en-us/articles/360037317691-Liferay-Connected-Services-Feature-Deprecation-Update-March-2020)

    ![Figure 7: Enable or disable the LCS services you want to use for servers that connect to the environment, then click *Next*.](../../images-dxp/lcs-onboarding-06.png)

7.  A completed form presents your selections. Review them and make any changes 
    that you want. When you're finished, click *Create Environment*. 

    ![Figure 8: This form contains each of your selections from the previous steps. Make any changes you want, then click *Create Environment*.](../../images-dxp/lcs-onboarding-07.png)

After creating your environment, the wizard shows a screen that lets you
download the LCS client app, download the environment's token file, and go to
your project's dashboard in LCS. Before registering a server in your new
environment, however, you must complete the 
[preconfiguration steps](/docs/7-2/deploy/-/knowledge_base/d/lcs-preconfiguration) 
for that server. 
