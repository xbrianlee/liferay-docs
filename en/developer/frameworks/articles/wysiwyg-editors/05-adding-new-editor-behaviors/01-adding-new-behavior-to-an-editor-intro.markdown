---
header-id: adding-new-behavior-to-an-editor
---

# Adding New Behavior to an Editor

[TOC levels=1-4]

You can select from several different WYSIWYG editors for your users, and each 
is configurable and has its strengths and weaknesses. Configuration alone, 
however, doesn't always expose the features you want. In these cases, you can 
programmatically access the editor instance to create the editor experience you 
want, using the `liferay-util:dynamic-include` JavaScript extension point. It 
injects JavaScript code right after the editor instantiation to configure/change 
the editor. 

| **Note:** By default, the CKEditor strips empty `<i>` tags, such as those used
| for Font Awesome icons, from published content, when switching between the Code
| View and the Source View of the editor. You can disable this behavior by using
| the `ckeditor#additionalResources` or `alloyeditor#additionalResources`
| [extension points](/docs/7-2/customization/-/knowledge_base/c/wysiwyg-editor-dynamic-includes)
| to
| add the code shown below to the editor:
| 
|     CKEDITOR.dtd.$removeEmpty.i = 0

The `liferay-util:dynamic-include` extension point is in configurable editors' 
JSP files: it's the gateway for injecting JavaScript into your editor instance. 
In this article, you'll learn how to use this JavaScript extension point. Follow 
these steps to inject JavaScript into the WYSIWYG editor to modify its behavior:

1.  Create a JS file containing your editor functionality in a folder that makes 
    sense to reference, since you must register the file in your module. The 
    extension point injects the JavaScript code right after editor 
    initialization. 

    Liferay injects JavaScript code for some applications: 

    - [creole_dialog_definition.js](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/_diffs/extension/creole_dialog_definition.js) for the wiki
    - [creole_dialog_show.js](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/_diffs/extension/creole_dialog_show.js) also for the wiki
    - [dialog_definition.js](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/_diffs/extension/dialog_definition.js) for various applications

    These JS files redefine the fields that show in dialogs, depending on what 
    the selected language (HTML, BBCode, Creole) supports. For example, Creole 
    doesn't support background color in table cells, so the table cells are 
    removed from the options displayed to the user when running in Creole 
    mode. 

2.  [Create a module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project) 
    that can register your new JS file and inject it into your editor instance. 

3.  Create a unique package name in the module's `src` directory, and create a 
    new Java class in that package. To follow naming conventions, your class 
    name should begin with the editor you're modifying, followed by custom 
    attributes, and ending with *DynamicInclude* (e.g., 
    `CKEditorCreoleOnEditorCreateDynamicInclude.java`). Your Java class should 
    implement the [`DynamicInclude`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/DynamicInclude.html) 
    interface. 

4.  Directly above the class's declaration, insert the following annotation:

    ```java
    @Component(immediate = true, service = DynamicInclude.class)
    ```

    This declares the component's implementation class and starts the module 
    once deployed to Portal. 

5.  If you have not yet overridden the abstract methods from `DynamicInclude`, do
    that now. There are two implemented methods to edit: `include(...)` and 
    `register(...)`.

6.  In the `include(...)` method, retrieve the bundle containing your custom JS 
    file. Retrieve the JS file as a URL and inject its contents into the editor. 
    Here's the code that does this for the `creole_dialog_definition.js` 
    file:

    ```java
    Bundle bundle = _bundleContext.getBundle();

    URL entryURL = bundle.getEntry(
        "/META-INF/resources/html/editors/ckeditor/extension" +
            "/creole_dialog_definition.js");

    StreamUtil.transfer(entryURL.openStream(), response.getOutputStream());
    ```

    In the `include(...)` method, you can also retrieve editor configurations
    and choose the JS file to inject based on the configuration selected by the
    user. For example, this would be applicable for the use case that was
    suggested previously dealing with Creole's deficiency with displaying
    background colors in table cells. Liferay implemented this in the
    `include(...)` method in the
    [`CKEditorCreoleOnEditorCreateDynamicInclude`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/frontend-editor/frontend-editor-ckeditor-web/src/main/java/com/liferay/frontend/editor/ckeditor/web/internal/servlet/taglib/CKEditorCreoleOnEditorCreateDynamicInclude.java)
    class.

7.  Make sure you've instantiated your bundle's context so you can successfully 
    retrieve your bundle. As a best practice, do this by creating an activation 
    method and then setting the `BundleContext` as a private field. Here's an 
    example: 

    ```java
    @Activate
    protected void activate(BundleContext bundleContext) {
        _bundleContext = bundleContext;
    }

    private BundleContext _bundleContext;
    ```

    This method uses the `@Activate` annotation, which specifies that it 
    should be invoked once the service component has satisfied its requirements. 
    For this default example, the `_bundleContext` was used in the 
    `include(...)` method. 

8.  Now register the editor you're customizing. For example, if you were 
    injecting JS code into the CKEditor's JSP file, the code would look like 
    this:

    ```java
    dynamicIncludeRegistry.register(
        "com.liferay.frontend.editor.ckeditor.web#ckeditor#onEditorCreate");
    ```

    This registers the CKEditor into the Dynamic Include registry and specifies 
    that JS code will be injected into the editor once it's created. 

    Just as you can configure individual JSP pages to use a specific 
    implementation of the available WYSIWYG editors, you can use those same 
    implementation options for the registration process. Visit the 
    [Editors](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Editors) 
    section of `portal.properties` for more details. For example, to configure 
    the Creole implementation of the CKEditor, you could use the following 
    key:

        "com.liferay.frontend.editor.ckeditor.web#ckeditor_creole#onEditorCreate"

That's it! The JS code that you created is now injected into the editor instance 
you've specified. You're now able to use JavaScript to add new behavior to your 
@product@ supported WYSIWYG editor! 

## Related Topics

- [Adding New Behavior to an Editor](/docs/7-2/frameworks/-/knowledge_base/f/adding-new-behavior-to-an-editor)
- [Embedding Portlets in Themes](/docs/7-2/frameworks/-/knowledge_base/f/embedding-portlets-in-themes)
- [Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)
