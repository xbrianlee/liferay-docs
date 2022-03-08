---
header-id: opensso-single-sign-on-authentication
---

# OpenAM Single Sign On Authentication


<aside class="alert alert-info">
   <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/securing-liferay/configuring-sso/using-openam.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

[TOC levels=1-4]

OpenAM is an open source single sign-on solution that comes from the code base
of Sun's System Access Manager product. @product@ integrates with OpenAM,
allowing you to use OpenAM to integrate @product@ into an infrastructure that
contains a multitude of different authentication schemes against different
repositories of identities.

Note that OpenAM relies on cookie sharing between applications. Thus, in order
for OpenAM to work, **all applications that require SSO must be in the same web
domain**. You should  also add the following property if you have enabled
HTTPOnly cookies due to the way some web containers (like Apache Tomcat™) parse
cookies that contain special characters: 

```properties
com.iplanet.am.cookie.encode=true
```

You can install OpenAM on the same or different server as @product@. Be sure to
review the context path and server hostname for your OpenAM server. 

If you want to install OpenAM on the same server as @product@, you must deploy
the OpenAM `.war`, downloadable from 
[here](https://backstage.forgerock.com/downloads/browse/am/archive/productId:openam).
Otherwise, follow the instructions at the 
[OpenAM 13 site](https://backstage.forgerock.com/docs/openam/13/install-guide/) to install
OpenAM. 

| **Note**: OpenAM 12 and below work with @product@, but are at end of life.
| Because of this, we recommend only OpenAM 13 for production use.

Once you have it installed, create the @product@
administrative user in it. Users are mapped back and forth by screen names. By
default, the @product@ administrative user has a screen name of *test*, so if
you were to use that account, register the user in OpenAM with the ID of *test*
and the email address specified in the [`admin.email.from.address`](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Admin%20Portlet) [portal property](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)).
Once you have the user set up, log in to OpenAM using this user.

In the same browser window, log in to @product@ as the administrative user (using
the previous admin email address). Go to the Control Panel and click
*Configuration* &rarr; *Instance Settings* &rarr; *Security* &rarr;
*SSO*. Then choose *OpenSSO* in the list on the left.

![Figure 1: OpenSSO Configuration.](../../images/opensso-configuration.png)

Modify the three URL fields (Login URL, Logout URL, and
Service URL) so they point to your OpenAM server (in other words, only modify the host
name portion of the URLs), check the *Enabled* box, and click *Save*.
@product@ then redirects users to OpenAM when they request the `/c/portal/login`
URL *for example, when they click on the *Sign In* link).

@product@'s OpenAM configuration can be applied at either the system scope or at
the instance scope. To configure the OpenAM SSO module at the system scope,
navigate to the Control Panel, click on *Configuration* &rarr; *System Settings*
&rarr; *Security* &rarr; *SSO* &rarr; *OpenSSO*. Click on it and you'll find
these settings to configure. The values configured here provide the default
values for all portal instances. Enter them in the same format as you would when
initializing a Java primitive type with a literal value.

Property Label | Property Key | Description | Type
----- | ----- | ----- | -----
**Version** | `version` | OpenAM version to use (12 and below or 13) | `String`
**Enabled** | `enabled` | Check this box to enable OpenAM authentication. Note that OpenAM works only if LDAP authentication is also enabled and @product@'s authentication type is set to screen name. | `boolean`
**Import from LDAP** | `importFromLDAP` | If this is checked, users authenticated from OpenAM that do not exist in @product@ are imported from LDAP. LDAP must be enabled. | `boolean`
**Login URL** | `loginURL` | The URL to the login page of the OpenAM server | `String`
**Logout URL** | `logoutURL` | The URL to the logout page of the OpenAM server | `String`
**Service URL** | `serviceURL` | The URL by which OpenAM can be accessed to use the authenticated web services. If you are using OpenAM Express 8 or higher, you need to have the server running Java 6. | `String`
**Screen Name Attribute** | `screenNameAttr` | The name of the attribute on the OpenAM representing the user's screen name | `String`
**Email Address Attribute** | `emailAddressAttr` | The name of the attribute on the OpenAM representing the user's email address | `String`
**First Name Attribute** | `firstNameAttr` | The name of the attribute on the OpenAM representing the user's first name | `String`
**Last Name Attribute** | `lastNameAttr` | The name of the attribute on the OpenAM representing the user's last name | `String`

To override these default settings for a particular portal instance, navigate
to the Control Panel and click *Configuration* &rarr; *Instance Settings* &rarr;
*Security* &rarr; *SSO*. Then choose *OpenSSO* in the list on the left.
