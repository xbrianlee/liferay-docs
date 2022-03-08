---
header-id: updating-file-shortcuts
---

# Updating File Shortcuts

[TOC levels=1-4]

To update a file shortcut with the Documents and Media API, you must use the 
`updateFileShortcut` method discussed in 
[Updating Entities](/docs/7-2/frameworks/-/knowledge_base/f/updating-entities). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/docs/7-2/frameworks/-/knowledge_base/f/documents-and-media-api). 

Follow these steps to update a file shortcut:

1.  Get a reference to `DLAppService`: 

    ```java
    @Reference
    private DLAppService _dlAppService;
    ```

2.  Get the data needed to populate the `updateFileShortcut` method's arguments. 
    Since it's common to update a file shortcut with data submitted by the end 
    user, you can extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

    ```java
    long fileShortcutId = ParamUtil.getLong(actionRequest, "fileShortcutId");
    long folderId = ParamUtil.getLong(actionRequest, "folderId");
    long toFileEntryId = ParamUtil.getLong(actionRequest, "toFileEntryId");

    ServiceContext serviceContext = ServiceContextFactory.getInstance(
                DLFileShortcutConstants.getClassName(), actionRequest);
    ```

<!-- Uncomment once article is available
    For more information on `ServiceContext`, see the tutorial 
    Understanding ServiceContext. 
-->

3.  Call the service reference's `updateFileShortcut` method with the data from 
    the previous step: 

    ```java
    _dlAppService.updateFileShortcut(
            fileShortcutId, folderId, toFileEntryId, serviceContext);
    ```

You can find the full code for this example in the `updateFileShortcut` method 
of @product@'s 
[`EditFileShortcutMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileShortcut` actions that the Documents and Media app supports. Also note that 
this `updateFileShortcut` method, as well as the rest of 
`EditFileShortcutMVCActionCommand`, contains additional logic to suit the 
specific needs of the Documents and Media app. 

## Related Topics

[Creating File Shortcuts](/docs/7-2/frameworks/-/knowledge_base/f/creating-file-shortcuts)

[Deleting File Shortcuts](/docs/7-2/frameworks/-/knowledge_base/f/deleting-file-shortcuts)
