---
header-id: page-fragments-desktop-tools
---

# Developing A Fragment Using Desktop Tools

[TOC levels=1-4]

You can develop a fragment using any preferred desktop tools. Since the Fragment
is HTML, CSS, and JavaScript, you could use a text editor or a specialized tool
with its own built in previews. 

## Collection Format

To import a Collection into @product@, it must be archived in a `.zip` with the
contents in the following format:

- `collection.json`: a text file which describes your collection with the 
     format `{"name":"<collection-name>","description":"<collection-description>"}`.

- `language.properties`: the language keys defined for the collection.

    - `[fragment-name]/`: a folder containing all of the files for a single 
     Page Fragment.

        - `fragment.json`: a text file that describes a Page Fragment with the 
          format

          ```json
          {
              "cssPath": "index.css",
              "configurationPath": "index.json",
              "htmlPath": "index.html",
              "jsPath": "index.js",
              "name": "<fragment-name>",
              "type": "<fragment-type>"
           }
           ```

          Update the `*Path` properties in your `fragment.json` file if you
          change the names of your `index.*` files.

        - `index.css`: the CSS source for the fragment.

        - `index.html`: the HTML source for the fragment.

        - `index.json`: a JSON file that defines the fragment's configuration.

        - `index.js`: the JavaScript source for the fragment.

        - `thumbnail.png`: the thumbnail that will display when the Fragment is
          displayed in a list.

    - `[resources]/`: a folder containing any additional images or other
      external files needed for the fragment.

A collection can contain any number of fragments, so you can have multiple
subfolders in the collection. This format is the same as what's exported from
within Liferay. If you import a `.zip` file that is not organized like this, 
any fragments that are found will be imported into special collection called 
*Imported* which is created for orphaned fragments.

## Fragment CLI

You can manage Fragment creation and deployment manually, or you can use 
Liferay's Fragment CLI:

1.  Follow the project instructions to
    [set up the Fragments Toolkit](https://github.com/liferay/generator-liferay-fragments/blob/master/README.md).

2.  Run `yo liferay-fragments`.

3.  Follow the prompts to create a fragment.

Now you will have the basic structure created, but there's still more that the 
Fragments Toolkit can help you with.

| **Note:** You can see all of the available tasks inside the `scripts` section
| in the Fragment CLI `package.json`.

### Creating Collections

Before you can create any Page Fragments, you must create a Collection. You can
learn more about Collections in the
[Creating Page Fragments](/docs/7-2/user/-/knowledge_base/u/creating-content-pages#creating-page-fragments)
section. Creating a Collection will create the base folder structure and some
information about your Collection. To do this,

1.  From inside of your project, run `npm run add-collection`.

2.  Follow the prompts to name your Collection.

You can now create Page Fragments inside of this Collection.

### Creating Fragments

A Page Fragment is made up of three primary files, `index.html`, `index.css`, 
and `index.js`. However, the files must be properly arranged in the folder 
structure and have the appropriate metadata to be imported onto your server. 
The Fragments Toolkit will create the files in the correct hierarchy with all 
of the necessary information.

1.  From inside of the Collection you created, run `npm run add-fragment`.

2.  Follow the prompts to add the necessary information about your Page 
    Fragment.
    
Now the files are all created and you can edit them using your editor of 
choice.

## Importing and Exporting Fragments

The Fragments Toolkit can connect to your currently running @product@ to import
and export fragments. You can even have fragments that you create with the 
toolkit imported into @product@ automatically.

- To get collections and fragments from a running server, run `npm run export`.

- To send the collections and fragments from your current project to a running
  server, run `npm run import`. If your Fragment's configuration JSON (if
  available) is invalid, the import fails and provides an error message.

- To have collections and fragments automatically imported into @product@ as
  they are created or modified, run `npm run import:watch`.

- To preview how a fragment will look when it's imported, run `npm run preview`.
  This renders a fragment on a specified Liferay server without importing it.
  When changes are made to the fragment while it's previewed, changes are auto
  reloaded to rapidly display updates. Note, this is available for Liferay DXP
  7.2 SP1+ and Liferay Portal 7.2 GA2+. You must install the
  [OAuth 2](https://web.liferay.com/marketplace/-/mp/application/109571986)
  plugin in your portal instance for this command to work properly.

- To create a `.zip` file that can be manually imported into @product@, run
  `npm run compress`.

With these tools at your disposal, you can more efficiently manage creating 
and editing Page Fragments with whatever tools and environments work best for 
you. 
