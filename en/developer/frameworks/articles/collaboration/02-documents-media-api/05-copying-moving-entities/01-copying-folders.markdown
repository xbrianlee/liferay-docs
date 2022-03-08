---
header-id: copying-folders
---

# Copying Folders

[TOC levels=1-4]

To copy a folder with the Documents and Media API, use the `copyFolder` method 
discussed in 
[Copying and Moving Entities](/docs/7-2/frameworks/-/knowledge_base/f/copying-and-moving-entities). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/docs/7-2/frameworks/-/knowledge_base/f/documents-and-media-api). 

Follow these steps to use `copyFolder` to copy a folder: 

1.  Get a reference to `DLAppService`: 

    ```java
    @Reference
    private DLAppService _dlAppService;
    ```

2.  Get the data needed to populate the `copyFolder` method's arguments. How you 
    do this depends on your use case. The copy operation in this example takes 
    place in the default Site repository and retains the folder's existing name 
    and description. It therefore needs the folder's group ID (to specify the 
    default site repository), name, and description. Also note that because the 
    destination folder in this example is the repository's root folder, the 
    parent folder ID isn't needed---@product@ supplies a constant for specifying 
    a repository's root folder. 

    In the following code, 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html) 
    gets the folder's ID from the request (`javax.portlet.ActionRequest`), and 
    the service reference's 
    [`getFolder`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFolder-long-) 
    method gets the corresponding folder object. The folder's `getGroupId()`, 
    `getName()`, and `getDescription()` methods then get the folder's group ID, 
    name, and description, respectively: 

    ```java
    long folderId = ParamUtil.getLong(actionRequest, "folderId");

    Folder folder = _dlAppService.getFolder(folderId);
    long groupId = folder.getGroupId();
    String folderName = folder.getName();
    String folderDescription = folder.getDescription();

    ServiceContext serviceContext = ServiceContextFactory.getInstance(
                DLFolder.class.getName(), actionRequest);
    ```

<!--Uncomment once article is available
    For more information on `ServiceContext`, see the tutorial 
    Understanding ServiceContext. 
-->

3.  Call the service reference's `copyFolder` method with the data from the 
    previous step. Note that this example uses the 
    [`DLFolderConstants`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/model/DLFolderConstants.html) 
    constant `DEFAULT_PARENT_FOLDER_ID` to specify the repository's root folder 
    as the destination folder: 

    ```java
    _dlAppService.copyFolder(
            groupId, folderId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
            folderName, folderDescription, serviceContext);
    ```

Note that you can change any of these values to suit your copy operation. For 
example, if your copy takes place in a repository other than the default Site 
repository, you would specify that repository's ID in place of the group ID. You 
could also specify a different destination folder, and/or change the new 
folder's name and/or description. 

## Related Topics

[Creating Folders](/docs/7-2/frameworks/-/knowledge_base/f/creating-folders)

[Updating Folders](/docs/7-2/frameworks/-/knowledge_base/f/updating-folders)

[Deleting Folders](/docs/7-2/frameworks/-/knowledge_base/f/deleting-folders)

[Moving Folders and Files](/docs/7-2/frameworks/-/knowledge_base/f/moving-folders-and-files)
