---
header-id: updating-folders
---

# Updating Folders

[TOC levels=1-4]

To update a folder with the Documents and Media API, you must use the 
`updateFolder` method discussed in 
[Updating Entities](/docs/7-2/frameworks/-/knowledge_base/f/updating-entities). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/docs/7-2/frameworks/-/knowledge_base/f/documents-and-media-api). 

Follow these steps to update a folder: 

1.  Get a reference to `DLAppService`: 

    ```java
    @Reference
    private DLAppService _dlAppService;
    ```

2.  Get the data needed to populate the `updateFolder` method's arguments. Since 
    it's common to update a folder with data submitted by the end user, you can 
    extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

    ```java
    long folderId = ParamUtil.getLong(actionRequest, "folderId");
    String name = ParamUtil.getString(actionRequest, "name");
    String description = ParamUtil.getString(actionRequest, "description");

    ServiceContext serviceContext = ServiceContextFactory.getInstance(
                DLFolder.class.getName(), actionRequest);
    ```

<!--Uncomment once article is available
    For more information on `ServiceContext`, see the tutorial 
    Understanding ServiceContext. 
-->

3.  Call the service reference's `updateFolder` method with the data from the 
    previous step: 

    ```java
    _dlAppService.updateFolder(folderId, name, description, serviceContext);
    ```

You can find the full code for this example in the `updateFolder` method of 
@product@'s 
[`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`Folder` actions that the Documents and Media app supports. Also note that 
this `updateFolder` method, as well as the rest of `EditFolderMVCActionCommand`, 
contains additional logic to suit the specific needs of the Documents and Media 
app. 

## Related Topics

[Creating Folders](/docs/7-2/frameworks/-/knowledge_base/f/creating-folders)

[Deleting Folders](/docs/7-2/frameworks/-/knowledge_base/f/deleting-folders)

[Copying Folders](/docs/7-2/frameworks/-/knowledge_base/f/copying-folders)

[Moving Folders and Files](/docs/7-2/frameworks/-/knowledge_base/f/moving-folders-and-files)
