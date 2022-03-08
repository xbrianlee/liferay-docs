---
header-id: adding-a-wysiwyg-editor-to-a-portlet
---

# Adding a WYSIWYG Editor to a Portlet

[TOC levels=1-4]

It's easy to include WYSIWYG editors in your portlet, thanks to the 
`<liferay-editor:editor />` tag. Follow these steps:

1.  Add the liferay-editor taglib declaration to your portlet's JSP:

    ```markup
    <%@ taglib uri="http://liferay.com/tld/editor" prefix="liferay-editor" %>
    ```

2.  Add the editor to your JSP with the `<liferay-editor:editor />` tag. 
    Configure it using the attributes shown in the table below:
    
    | Attribute | Type | Description |
    | --- | --- | --- |
    | `autoCreate` | `java.lang.String` | Whether to show the HTML edit view of the editor initially |
    | `contents` | `java.lang.String` | Sets the initial contents of the editor |
    | `contentsLanguageId` | `java.lang.String` | Sets the language ID for the input editor's text |
    | `cssClass` | `java.lang.String` | A CSS class for styling the component. |
    | `data` | `java.util.Map` | Data that can be used as the editorConfig |
    | `editorName` | `java.lang.String` | The editor you want to use (alloyeditor, ckeditor, tinymce, simple) |
    | `name` | `java.lang.String` | A name for the input editor. The default value is `editor`. |
    | `onBlurMethod` | `java.lang.String` | A function to be called when the input editor loses focus. |
    | `onChangeMethod` | `java.lang.String` | A function to be called on a change in the input editor. |
    | `onFocusMethod` | `java.lang.String` | A function to be called when the input editor gets focus. |
    | `onInitMethod` | `java.lang.String` | A function to be called when the input editor initializes. |
    | `placeholder` | `java.lang.String` | Placeholder text to display in the input editor. |
    | `showSource` | `java.lang.String` | Whether to enable editing the HTML source code of the content. The default value  is `true`. |
    
    See the [taglibdocs](@app-ref@/frontend-editor/latest/taglibdocs/liferay-editor/editor.html) 
    for the complete list of supported attributes. 

    Below is an example configuration:

    ```html    
    <div class="alloy-editor-container">
        <liferay-editor:editor
        		contents="Default Content"
        		cssClass="my-alloy-editor"
        		editorName="alloyeditor"
        		name="myAlloyEditor"
        		placeholder="description"
        		showSource="true" 
        /> 
    </div>
    ```

3.  Optionally pass JavaScript functions through the `onBlurMethod`, 
    `onChangeMethod`, `onFocusMethod`, and `onInitMethod` attributes. Here is an 
    example configuration that uses the `onInitMethod` attribute to pass a 
    JavaScript function called `OnDescriptionEditorInit`:

    ```markup
    <%@ taglib uri="http://liferay.com/tld/editor" prefix="liferay-editor" %>

    <div class="alloy-editor-container">
        <liferay-editor:editor
            contents="Default Content"
            cssClass="my-alloy-editor"
            editorName="alloyeditor"
            name="myAlloyEditor"
            onInitMethod="OnDescriptionEditorInit"
            placeholder="description"
            showSource="true" />
    </div>
    ```

    ```javascript 
    <aui:script>
        function <portlet:namespace />OnDescriptionEditorInit() {
            <c:if test="<%= !customAbstract %>">
                document.getElementById(
                  '<portlet:namespace />myAlloyEditor'
                ).setAttribute('contenteditable', false);
            </c:if>
        }
    </aui:script>
    ```

As you can see, it's easy to include WYSIWYG editors in your portlets! 

## Related Topics

- [Adding New Behavior to an Editor](/docs/7-2/frameworks/-/knowledge_base/f/adding-new-behavior-to-an-editor)
- [Modifying an Editor's Configuration](/docs/7-2/frameworks/-/knowledge_base/f/modifying-an-editors-configuration)
- [Modifying the AlloyEditor](/docs/7-2/frameworks/-/knowledge_base/f/alloyeditor)
