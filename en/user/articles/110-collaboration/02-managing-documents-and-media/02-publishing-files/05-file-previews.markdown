---
header-id: viewing-file-previews
---

# Viewing File Previews

[TOC levels=1-4]

File previews help users browse and find media efficiently. To view a preview of 
a file, click the file's name in the Document Library. If the file is an image,
the image appears. If an app is installed that can render a preview of the file
type, a representative image of the file appears (e.g., the opening frame of
a video file or a presentation's first slide). If there are no such preview apps
for the file, a generic image based on the file type appears. 

![Figure 1: File previews let you view and manage a file.](../../../../images/dm-file-entry-details.png)

## File Preview Apps

Whenever possible, @product@ generates previews of documents added to the
Document Library. Out of the box, Java-based APIs generate previews. The only
tool available that is 100% Java and has a compatible license to be distributed
with @product@ is 
[PDFBox](https://pdfbox.apache.org). 
A separate thread generates a preview for PDFs when uploaded. This process may
last only a few seconds for a small file. The larger the file, the longer it
takes. 

While PDFBox provides a default implementation of image generation for document 
previews and thumbnails, you must install and configure additional tools to
harness the full power of document previews. These tools include: 

-   [OpenOffice](http://www.openoffice.org) or
    [LibreOffice](http://www.libreoffice.org): 
    Using one of these in server mode lets you generate thumbnails and previews 
    for supported file types (`.pdf`, `.docx`, `.odt`, `.ppt`, `.odp`, etc.), 
    view documents in your browser, and convert documents. 

-   [ImageMagick](http://www.imagemagick.org) (also requires
    [Ghostscript](http://www.ghostscript.com)): 
    Enables faster and higher-quality previews and conversions. 

-   [Xuggler](http://www.xuggle.com/xuggler): 
    Enables audio and video previews, lets you play audio and video files in 
    your browser, and extracts thumbnails from video files. 

After installing these tools, you can configure them via portal properties in 
the Control Panel's Server Administration screen, or in a 
`portal-ext.properties` file. To learn how to use these tools, see 
[Configuring @product@](/docs/7-2/user/-/knowledge_base/u/setting-up). 

With these tools installed and configured, a customized viewer displays 
Documents and Media content, depending on the content type. For example, you can 
view a document with a customized viewer that lets you navigate through the 
document's pages. You can also view and play multimedia documents (audio or 
video). If the browser supports HTML5, the viewer uses the browser's native 
player. Otherwise it falls back to a Flash player. 

## Managing Files

You can also manage a file from its preview. The bar above the preview contains 
these buttons: 

**Info** (![Info](../../../../images/icon-information-dm.png)): 
Open/close the file's info panel. This panel contains more detailed information 
about the file. For more information on this, see 
[The Info Panel](#the-info-panel). 

**Share**: Share the file with other users. For more information, see 
[Sharing Files](/docs/7-2/user/-/knowledge_base/u/sharing-files). 

**Download**: Download the file. 

**Actions** (![Actions](../../../../images/icon-actions.png)): 
Opens a menu that lets you perform these actions on the file: 

-   **Download**

-   **Edit:** Modify the file's name, description, document type, 
    categorization, and 
    [related assets](/docs/7-2/user/-/knowledge_base/u/defining-content-relationships).
    You can even upload a new file to replace it. Note that modifying the file
    increments its version. 

-   **Edit with Image Editor:** Edit the image in the Image Editor. The Image 
    Editor is explained in 
    [Editing Images](/docs/7-2/user/-/knowledge_base/u/editing-images). 

-   **Checkout/Checkin:** Checkout prevents others from editing the document 
    while you are working on it. Other users can still view the current version 
    of the document, if they have permission. You can check in the document when 
    you're done with it. 

-   **Move:** Relocate the file to a different parent folder. 

-   **Permissions:** Specify which actions each role can perform on the file. 

-   **Move to Recycle Bin:** Move the file from the Documents and Media library 
    to the Recycle Bin. 

-   **Share** 

Also note that the *Options* menu 
(![Options](../../../../images/icon-options.png)) at the top-right of the 
screen contains the same actions as the Actions menu.

The comments area (below the preview area) lets you comment on and subscribe to 
comments on the file. 

### The Info Panel

As mentioned above, clicking the *Info* icon 
(![Info](../../../../images/icon-information-dm.png)) opens the info panel. The 
top of the info panel displays the file's name, version, and 
[workflow status](/docs/7-2/user/-/knowledge_base/u/workflow). 
There are two tabs in the info panel: Details, and Versions. Details is selected 
by default and shows the following: 

**Owner:** The file's owner. 

**Download:** A button to download the file. 

**Latest Version URL:** A URL to access the newest version of the file. 

**WebDAV URL:** A WebDAV URL for accessing the file via a desktop.

**Document Type:** The file's document type. 

**Extension:** The file's extension (e.g., JPG, PDF, etc.).

**Size:** The file's size on disk. 

**Modified:** The user that last modified the file, and when it was last 
modified.

**Created:** The user that created the file, and when it was created. 

**Ratings:** The file's average user rating. 

**Automatically Extracted Metadata:** Any and all metadata automatically 
extracted from the file. When adding new documents or viewing existing 
documents, a process is triggered automatically that extracts the file's 
metadata. The library used by this process is TIKA and it's included out of the
box. Depending on your file's type and the metadata written with the file, you
can find out all kinds of details. In the case of audio or video files, the
media's duration is displayed.

To instead view the file's version history, select the *Versions* tab near the 
top of the info panel. The info panel then changes to list the different 
versions of the file and lets you view, download, remove, and revert to specific 
file versions. File version history actions are explained in 
[Checking Out and Editing Files](/docs/7-2/user/-/knowledge_base/u/checking-out-and-editing-files). 
