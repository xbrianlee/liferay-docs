---
header-id: adding-asset-features-to-your-user-interface
---

# Adding Asset Features to Your User Interface

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Adding Asset Features to Your UI</p><p>Step 1 of 5</p>
</div>

Now that your guestbook and guestbook entry entities are asset-enabled, you can
add asset functionality to your application. You'll start by implementing
comments, ratings, tags, categories, and related assets for guestbooks. Then
you'll do the same for guestbook entries. All the back-end support for these
features is already implemented. Your only task is to update your applications'
user interfaces to use these features. 

Now you'll create several new JSPs that need new imports. Add the following
imports to the `guestbook-web` module project's `init.jsp` file: 

```markup
<%@ taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %>
<%@ taglib uri="http://liferay.com/tld/comment" prefix="liferay-comment" %>

<%@ page import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil" %>
<%@ page import="com.liferay.asset.kernel.service.AssetTagLocalServiceUtil" %>
<%@ page import="com.liferay.asset.kernel.model.AssetEntry" %>
<%@ page import="com.liferay.asset.kernel.model.AssetTag" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.comment.Discussion" %>
<%@ page import="com.liferay.portal.kernel.comment.CommentManagerUtil" %>
<%@ page import="com.liferay.portal.kernel.service.ServiceContextFunction" %>
```

Add these imports now so you don't run into errors as you work through the steps. 
