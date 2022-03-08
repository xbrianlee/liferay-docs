---
header-id: checking-in-files
---

# Checking In Files

[TOC levels=1-4]

To check in a file with the Documents and Media API, use the 
`checkInFileEntry` method discussed in 
[File Checkout and Checkin](/docs/7-2/frameworks/-/knowledge_base/f/file-checkout-and-checkin). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/docs/7-2/frameworks/-/knowledge_base/f/documents-and-media-api). 

Follow these steps to use `checkInFileEntry` to check in a file: 

1.  Get a reference to `DLAppService`: 

    ```java
    @Reference
    private DLAppService _dlAppService;
    ```

2.  Get the data needed to populate the `checkInFileEntry` method's arguments. 
    Since it's common to check in a file in response to an action by the end 
    user, you can extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

    ```java
    long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
    boolean majorVersion = ParamUtil.getBoolean(actionRequest, "majorVersion");
    String changeLog = ParamUtil.getString(actionRequest, "changeLog");

    ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
    ```

<!--Uncomment once article is available
    For more information on `ServiceContext`, see the tutorial 
    Understanding ServiceContext.
--> 

3.  Call the service reference's `checkInFileEntry` method with the data from 
    the previous step: 

    ```java
    _dlAppService.checkInFileEntry(
            fileEntryId, majorVersion, changeLog, serviceContext);
    ```

You can find the full code for this example in the `checkInFileEntries` method 
of @product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileEntry` actions that the Documents and Media app supports. Also note that 
this `checkInFileEntries` method, as well as the rest of 
`EditFileEntryMVCActionCommand`, contains additional logic to suit the specific 
needs of the Documents and Media app. 

## Related Topics

[Checking Out Files](/docs/7-2/frameworks/-/knowledge_base/f/checking-out-files)

[Canceling a Checkout](/docs/7-2/frameworks/-/knowledge_base/f/canceling-a-checkout)

[Updating Files](/docs/7-2/frameworks/-/knowledge_base/f/updating-files)
