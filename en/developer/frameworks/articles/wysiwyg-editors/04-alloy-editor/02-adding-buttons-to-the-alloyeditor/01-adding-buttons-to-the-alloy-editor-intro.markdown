---
header-id: adding-buttons-to-alloyeditor-toolbars
---

# Adding Buttons to AlloyEditor's Toolbars

[TOC levels=1-4]

AlloyEditor's toolbars contain several useful functions out-of-the-box. You 
may, however, want to customize the default configuration to include a button 
you've created, to add an existing button to a toolbar, or to add an 
[existing CKEditor button that's bundled with @product@'s AlloyEditor](/docs/7-2/reference/-/knowledge_base/r/ckeditor-plugin-reference-guide).
The 
[`EditorConfigContributor` interface](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html), 
provides everything you need to modify an editor's configuration, including 
adding buttons to AlloyEditor's toolbars. 
[CKEditor Configuration settings](https://docs.ckeditor.com/ckeditor4/latest/api/CKEDITOR_config.html) 
that modify the editor's behavior (excluding UI modifications) can also be 
passed down through this configuration object. 

The `com.liferay.docs.my.button` module is used as an example throughout this 
section. If you want to use it as a starting point for your own configuration 
or follow along with the articles, you can download the module's zip file from 
the [Github repo](https://github.com/liferay/liferay-docs/tree/7.1.x/develop/tutorials/code/osgi/modules/com.liferay.docs.my.button). 
