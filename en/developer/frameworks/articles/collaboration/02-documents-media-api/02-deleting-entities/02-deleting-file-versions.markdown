---
header-id: deleting-file-versions
---

# Deleting File Versions

[TOC levels=1-4]

To delete a file version with the Documents and Media API, you must use the 
`deleteFileVersion` method discussed in 
[Deleting Entities](/docs/7-2/frameworks/-/knowledge_base/f/deleting-entities). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/docs/7-2/frameworks/-/knowledge_base/f/documents-and-media-api). 

Follow these steps to use `deleteFileVersion` to delete a file version:

1.  Get a reference to `DLAppService`: 

    ```java
    @Reference
    private DLAppService _dlAppService;
    ```

2.  Get the file entry ID and version for the file you want to delete. Since 
    it's common to delete a file version specified by the end user, you can 
    extract these parameters from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can do this any way you wish: 

    ```java
    long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
    String version = ParamUtil.getString(actionRequest, "version");
    ```

3.  Use the service reference to call the `deleteFileVersion` method with the 
    file entry ID and version from the previous step: 

    ```java
    _dlAppService.deleteFileVersion(fileEntryId, version);
    ```

You can find the full code for this example in the `deleteFileEntry` method of 
@product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileEntry` actions that the Documents and Media app supports. Also note that 
this `deleteFileEntry` method, as well as the rest of 
`EditFileEntryMVCActionCommand`, contains additional logic to suit the specific 
needs of the Documents and Media app. 

## Related Topics

[Deleting Files](/docs/7-2/frameworks/-/knowledge_base/f/deleting-files)

[Deleting File Shortcuts](/docs/7-2/frameworks/-/knowledge_base/f/deleting-file-shortcuts)

[Deleting Folders](/docs/7-2/frameworks/-/knowledge_base/f/deleting-folders)

[Moving Entities to the Recycle Bin](/docs/7-2/frameworks/-/knowledge_base/f/moving-entities-to-the-recycle-bin)
