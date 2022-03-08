---
header-id: jsp-overrides-using-osgi-fragments
---

# JSP Overrides Using OSGi Fragments

[TOC levels=1-4]

You can completely override JSPs using OSGi fragments. This approach is powerful 
but can make things unstable when the host module is upgraded: 

1.  By overriding an entire JSP, you might not account for new content or new 
    widgets essential to new host module versions. 
2.  Fragments are tied to a specific host module version. If the host module is 
    upgraded, the fragment detaches from it. In this scenario, the original 
    JSPs are still available and the module is functional (but lacks your JSP 
    enhancements). 
3.  Liferay cannot guarantee that JSPs overridden by fragments can be upgraded. 

Using OSGi fragments to override JSPs is a bad practice, equivalent to using Ext 
plugins to customize @product@. They should only be used as a last resort. 
Liferay's API based approaches to overriding JSPs (i.e., [Dynamic Includes](/docs/7-2/customization/-/knowledge_base/c/customizing-jsps-with-dynamic-includes)
and [Portlet Filters](/docs/7-2/customization/-/knowledge_base/c/jsp-overrides-using-portlet-filters)), 
on the other hand, provide more stability as they customize specific parts of 
JSPs that are safe to override. Also, the API based approaches don't limit your 
override to a specific host module version. If you are maintaining existing JSP 
overrides that use OSGi fragments, however, this tutorial explains how they 
work. 

An OSGi fragment that overrides a JSP requires these two things:

-  The host module's symbolic name and version in the OSGi header 
   `Fragment-Host` declaration.

-  The original JSP with any modifications you need to make.

For more information about fragment modules, you can refer to section 3.14 of
the 
[OSGi Alliance's core specification document](https://osgi.org/specification/osgi.core/7.0.0/framework.module.html).

## Declaring a Fragment Host

There are two players in this game: the fragment and the host. The fragment is 
a parasitic module that attaches itself to a host. That sounds harsh, so let's 
compare the fragment-host relationship to the relationship between a pilot fish 
and a huge, scary shark. It's symbiotic, really. Your fragment module benefits 
by not doing much work (like the pilot fish who benefits from the shark's 
hunting prowess). In return, the host module gets whatever benefits you've 
conjured up in your fragment's JSPs (for the shark, it gets free dental 
cleanings!). To the OSGi runtime, your fragment is part of the host module. 

Your fragment must declare two things to the OSGi runtime regarding the host 
module:

1.  The Bundle Symbolic Name of the host module. This is the module containing 
    the original JSP.

2.  The exact version of the host module to which the fragment belongs.

Both are declared using the OSGi manifest header `Fragment-Host`.

```properties
Fragment-Host: com.liferay.login.web;bundle-version="[1.0.0,1.0.1)"
```

Supplying a specific host module version is important. If that version of the 
module isn't present, your fragment won't attach itself to a host, and that's a 
good thing. A new version of the host module might have changed its JSPs, so if 
your now-incompatible version of the JSP is applied to the host module, you'll 
break the functionality of the host. It's better to detach your fragment 
and leave it lonely in the OSGi runtime than it is to break the functionality of 
an entire application. 

## Provide the Overridden JSP

There are two possible naming conventions for targeting the host original JSP: 
`portal` or `original`. For example, if the original JSP is in the folder 
`/META-INF/resources/login.jsp`, then the fragment bundle should contain a JSP 
with the same path, using the following pattern:

```markup
<liferay-util:include 
    page="/login.original.jsp" (or login.portal.jsp) 
    servletContext="<%= application %>" 
/>
```

After that, make your modifications. Just make sure you mimic the host module's 
folder structure when overriding its JAR. If you're overriding Liferay's login 
application's `login.jsp` for example, you'd put your own `login.jsp` in 

```markup
my-jsp-fragment/src/main/resources/META-INF/resources/login.jsp
```

If you must post-process the output, you can update the pattern to include 
@product@'s buffering mechanism. Below is an example that overrides the original 
`create_account.jsp`:

```markup
<%@ include file="/init.jsp" %>

<liferay-util:buffer var="html">
    <liferay-util:include page="/create_account.portal.jsp" 
    servletContext="<%= application %>"/>
</liferay-util:buffer>

<liferay-util:buffer var="openIdFieldHtml"><aui:input name="openId" 
type="hidden" value="<%= ParamUtil.getString(request, "openId") %>" />
</liferay-util:buffer>

<liferay-util:buffer var="userNameFieldsHtml"><liferay-ui:user-name-fields />
</liferay-util:buffer>

<liferay-util:buffer var="errorMessageHtml">
    <liferay-ui:error 
    exception="<%= com.liferay.portal.kernel.exception.NoSuchOrganizationException.class %>" message="no-such-registration-code" />
</liferay-util:buffer>

<liferay-util:buffer var="registrationCodeFieldHtml">
            <aui:input name="registrationCode" type="text" value="">
                    <aui:validator name="required" />
            </aui:input>
</liferay-util:buffer>

<%
    html = com.liferay.portal.kernel.util.StringUtil.replace(html, 
      openIdFieldHtml, openIdFieldHtml + errorMessageHtml);
    html = com.liferay.portal.kernel.util.StringUtil.replace(html, 
      userNameFieldsHtml, userNameFieldsHtml + registrationCodeFieldHtml);
%>

<%=html %>
```

## Using Fragment Host Internal Packages

To use an internal (unexported) host package, the fragment must explicitly
exclude the package from its `Import-Package:` manifest header. For example,
this `Import-Package` header excludes packages that match
`com.liferay.portal.search.web.internal.*`.

```groovy
Import-Package: !com.liferay.portal.search.web.internal.*,*
```

Unless you explicitly exclude the package, bnd adds the package to the
`Import-Package:` header. Attempting to start the fragment while requiring an
unexported package fails because the package is an unresolved requirement. For
this reason, make sure to exclude such packages from your fragment's
`Import-Package:` header. 

Each fragment has full access to the host packages, including its internal
(unexported) packages already. 

Now you can easily modify the JSPs of any application in Liferay.

![Figure 1: Liferay's applications are swimming in the OSGi runtime, waiting for your fragment modules to clean their teeth, so to speak.](../../images/sharks.jpg)
<!--https://commons.wikimedia.org/wiki/File:Carcharhinus_perezi_bahamas_feeding.jpg-->

<!--
Add back once sample is ported:

see the [Module JSP Override sample project](/docs/7-2/reference/-/knowledge_base/r/module-jsp-override) 
for an example of a JSP-modifying fragment in action.
--> 


## Related Topics

- [JSP Overrides Using Portlet Filters](/docs/7-2/customization/-/knowledge_base/c/jsp-overrides-using-portlet-filters)
