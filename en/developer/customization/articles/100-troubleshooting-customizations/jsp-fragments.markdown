---
header-id: why-arent-jsp-overrides-i-made-using-fragments-showing
---

# Why Aren't JSP overrides I Made Using Fragments Showing?

[TOC levels=1-4]

| **Important:** It's strongly recommended to
| [customize JSPs using @product@'s API](/docs/7-2/customization/-/knowledge_base/c/customizing-jsps).
| Since overriding a JSP using an OSGi fragment is not based on APIs there's no
| way to guarantee that it will fail gracefully. Instead, if your customization 
| is buggy (because of your code or because of a change in Liferay), you are
| most likely to find out at runtime, where functionality breaks and nasty log
| errors greet you. Overriding a JSP using a fragment should only be used as a
| last resort.

The fragment module must specify the exact version of the host module. A 
@product@ upgrade might have changed some JSPs in the host module, prompting a 
version update. If this occurs, check that your JSP customizations are 
compatible with the updated host JSPs and then update your fragment module's 
targeted version to match the host module. 

For example, this `bnd.bnd` file from a fragment module uses `Fragment-Host` to 
specify the host module and host module version: 

```
Bundle-Name: custom-login-jsp
Bundle-SymbolicName: custom.login.jsp
Bundle-Version: 1.0.0
Fragment-Host: com.liferay.login.web;bundle-version="1.1.18"
```

[Finding versions of deployed modules](/docs/7-2/customization/-/knowledge_base/c/finding-artifacts)
is straightforward.  

## Related Topics

[JSP Overrides using Portlet Filters](/docs/7-2/customization/-/knowledge_base/c/jsp-overrides-using-portlet-filters)

[Customizing JSPs](/docs/7-2/customization/-/knowledge_base/c/customizing-jsps)

[Finding Artifacts](/docs/7-2/customization/-/knowledge_base/c/finding-artifacts)
