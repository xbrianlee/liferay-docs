---
header-id: understanding-liferay-faces-portal
---

# Understanding Liferay Faces Portal

[TOC levels=1-4]

*Liferay Faces Portal* is distributed in a `.jar` file. You can add Liferay
Faces Portal as a dependency for your portlet projects to use its
Liferay-specific utilities and UI components. When Liferay Faces Portal is
included in a JSF portlet project, the `com.liferay.faces.portal.[version].jar`
file resides in the portlet's library. 

![Figure 1: The required `.jar` files are downloaded for your JSF portlet based on the JSF UI Component Suite you configured.](../../images/jsf-jars-package-explorer.png)

Some of the features included in Liferay Faces Portal are: 

- Utilities: Provides the `LiferayPortletHelperUtil` which contains a variety
  Portlet-API and Liferay-specific convenience methods.

- JSF Components: Provides a set of JSF equivalents for popular @product@ JSP
  tags (not exhaustive):
    - `liferay-ui:captcha` &rarr; `portal:captcha`
    - `liferay-ui:input-editor` &rarr; `portal:inputRichText`
    - `liferay-ui:search` &rarr; `portal:inputSearch`
    - `liferay-ui:header` &rarr; `portal:header`
    - `aui:nav` &rarr; `portal:nav`
    - `aui:nav-item` &rarr; `portal:navItem`
    - `aui:nav-bar` &rarr; `portal:navBar`
    - `liferay-security:permissionsURL` &rarr; `portal:permissionsURL`
    - `liferay-portlet:runtime` &rarr; `portal:runtime`

    For more information, visit
    [https://liferayfaces.org/web/guest/portal-showcase](https://liferayfaces.org/web/guest/portal-showcase).

- Expression Language: Adds a set of EL keywords such as `liferay` for getting
  Liferay-specific info, and `i18n` for integration with out-of-the-box Liferay
  internationalized messages. 

Great! You now have an understanding of what Liferay Faces Portal is, and what
it accomplishes in your JSF application. 

## Related Topics

[Developing a JSF Portlet Application](/docs/7-2/appdev/-/knowledge_base/a/developing-a-jsf-portlet-application)

[Understanding Liferay Faces Bridge](/docs/7-2/reference/-/knowledge_base/r/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Alloy](/docs/7-2/reference/-/knowledge_base/r/understanding-liferay-faces-alloy)
