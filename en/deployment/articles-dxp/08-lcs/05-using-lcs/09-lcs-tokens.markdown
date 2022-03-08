---
header-id: understanding-environment-tokens
---

# Understanding Environment Tokens

[TOC levels=1-4]

| **Note:** LCS is deprecated and will be shut down on December 31, 2021. 
| Customers who activate LCS are advised to replace it with our latest activation
| key type which is suitable for virtualized environments. 
|
| For further information, please see [Changes to Liferay Product Activation](https://help.liferay.com/hc/en-us/articles/4402347960845-Changes-to-Liferay-Product-Activation).

To register a server in an environment, you must use that environment's token 
file. LCS Administrators and Environment Managers can generate and distribute 
this file. It contains all the information the LCS client app needs to register 
the server in the environment. When the server starts up, it uses the token to 
connect to LCS. If you use LCS for activation, the server automatically consumes 
an activation key from the environment's subscription upon connection. This 
makes it possible to activate servers automatically on startup with no 
interaction required. 

| **Note:** For instructions on using and managing your environment tokens, see 
| the instructions on 
| [registering your server with LCS](/docs/7-2/deploy/-/knowledge_base/d/activating-your-liferay-dxp-server-with-lcs). 

There are a few things to keep in mind when using environment tokens: 

-   Each environment can have only one token file. If you regenerate the token, 
    servers using the old file are disconnected from LCS and can't reconnect 
    until receiving the new file. If the server disconnects due to token 
    regeneration and is running version 4.0.2 or later of the LCS client app, 
    the server enters a 30-day grace period during which it functions normally. 
    This gives the administrator time to use the new token file to reconnect to 
    LCS. Servers running earlier versions of the LCS client app present users 
    with an error page until the administrator reconnects with the new token. 

-   Use caution when distributing the token file, as anyone can use it to 
    connect to your environment (and consume an activation key in your 
    subscription if you're using LCS for activation). 

-   Minimal information (server name, location, etc...) is used to register a 
    server with LCS. You can change this information from 
    [the server view in LCS](/docs/7-2/deploy/-/knowledge_base/d/managing-lcs-servers) 
    at any time. 

-   Environment tokens connect using OAuth. Using an environment token overrides 
    the OAuth authorization cycle. If LCS Administrators or Environment Managers 
    have never registered servers in LCS, the first time they do so an OAuth 
    authorization entry is created in LCS. If they've previously registered 
    servers in LCS, their existing credentials are used when they create a token 
    file. 

-   If the credentials of the LCS user who generated the token become invalid, 
    you must generate a new token and use it to reconnect to LCS. An LCS user's 
    credentials become invalid if the user leaves the LCS project or becomes an 
    LCS Environment Manager or LCS Environment Viewer in a different 
    environment. 

So why bother with environment tokens at all? Besides simplifying the LCS 
connection process, environment tokens are valuable in auto-scaling environments 
where algorithms create and destroy servers automatically. In this situation, 
having clients that activate and configure themselves is crucial. 

| **Note**: If your auto-scaling environment creates new server nodes from a 
| server in a system image, that server can't require human interaction during 
| setup. When creating such an image, you must change any portal property 
| settings that prevent automatic setup. By default, @product@'s setup wizard 
| requires human interaction. You must therefore set the `setup.wizard.enabled` 
| property to `false` if you want your auto-scaling environment to create new 
| nodes from the server. 
