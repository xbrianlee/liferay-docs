---
header-id: configuring-google-docs-integration
---

# Configuring Google Docs™ Integration

[TOC levels=1-4]

Before you can use Google Docs&trade; to create and edit Documents and Media 
files, you must configure @product@ to connect with an application in the 
[Google API Console](https://console.developers.google.com). 

| **Note:** You must be an administrator to complete these steps. 

## Configure Your Google Project

First, you must configure your Google project to use the Google Drive&trade; 
API and set up OAuth 2 for use with that project. 

1.  Go to the
    [Google API Console](https://console.developers.google.com). 
    If you don't have a suitable project, 
    [create a new one](https://support.google.com/googleapi/answer/6251787?hl=en&ref_topic=7014522). 

2.  Enable the Google Drive&trade; API for your project. For instructions, see 
    the Google API Console documentation on 
    [enabling and disabling APIs](https://support.google.com/googleapi/answer/6158841). 

3.  Create an OAuth 2 client ID for your Google project. For instructions, see 
    the Google API Console documentation on 
    [setting up OAuth 2.0](https://support.google.com/googleapi/answer/6158849). 
    Select *Web application* when prompted to select your application type. Take 
    note of the client ID and client secret that appear---you'll need them to 
    configure the portal to use the Google Drive&trade; API. 

## Configuring the Portal

Now that you have a Google project set up for use with @product@, you must 
connect your installation to that project. You can do this at two scopes: 

1.  Globally, for all instances in your @product@ installation.
2.  At the instance scope, for one or more instances in your @product@ 
    installation. 

You can override the global configuration for one or more instances by 
configuring those instances separately. Similarly, you can configure only the 
instances you want to connect to your Google project and leave the global 
configuration empty. 

Follow these steps to configure your @product@ installation to connect to your 
Google project: 

1.  Note that the configuration options are the same in the global and 
    instance-level configurations. 

    To access the global configuration, go to *Control Panel* &rarr; 
    *Configuration* &rarr; *System Settings* &rarr; *Documents and Media*. 

    To access the instance-level configuration, go to *Control Panel* &rarr; 
    *Configuration* &rarr; *Instance Settings* &rarr; *Documents and Media*. 

2.  Under *VIRTUAL INSTANCE SCOPE*, select *Google Drive*. 

3.  Enter your Google project's OAuth 2 client ID and client secret into the 
    *Client ID* and *Client Secret* fields. 

4.  Click *Save*. 

| **Note:** To turn this feature off, delete the client ID and client secret 
| values from the form. 

| **Note:** You can ignore the *Picker API Key* field. This field is unrelated 
| to the Google Docs&trade; online editing features in @product@. 

![Figure 1: Enter your Google project's OAuth 2 client ID and client secret.](../../../../images/google-drive-system-settings.png)
