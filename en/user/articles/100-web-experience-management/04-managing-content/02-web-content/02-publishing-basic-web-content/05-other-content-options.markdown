---
header-id: other-content-options
---

# Other Content Options

[TOC levels=1-4]

Here are some options and tools that you can use to enhance your content and
user experience.

## Localizing Content

When you create a new web content article, you can choose a default language.
First, you must change the system configuration to enable the option to change
the default language.

1.  Go to the *Control Panel* &rarr; *Configuration* &rarr; *System Settings*. 

2.  Locate *Web Content* &rarr; *Administration* by scrolling or using the 
    search bar.

3.  Check the box labeled *Changeable Default Language*.

4.  Click *Save*.

You must now add translations for any languages you need. Adding translations
works like this:

1.  Open a web content article.

2.  Click the flag icon with a country code on it next to any localizable web 
    content field.

3.  Select a language from the list.

When you select a language, all fields in the article switch to the new
language. To create the new translation, fill in the fields in the selected
language and publish the article. 

![Figure 1: Adding a translation to an article works like adding the default translation.](../../../../../images/web-content-translation.png)

| **Note:** To view localizable fields in a given language, you must have your 
| Portal set to that language. This includes friendly URLs for the web content as 
| well. When you navigate to the localized friendly URL 
| (e.g. `http://localhost:8080/web/guest/-/espanol`), the web content is always 
| displayed in the current language. You can change the language with the 
| [Language Selector app](/docs/7-2/user/-/knowledge_base/u/personalizing-pages#customization-example).

You can modify the language translation list by inserting `locales.enabled=`
followed by your preferred languages in your `portal-ext.properties` file. For
example, `locales.enabled=ar_SA,nl_NL,hi_IN` offers *Arabic (Saudi Arabia)*,
*Dutch (Netherlands)*, and *Hindi (India)*.

| **Warning:** If you switch your Site's default language (e.g., via friendly
| URL), but do not have the necessary translations for localizable fields, your
| Site's language values are used from the old default language. Therefore, you
| should change the default language of your Site *only* when you have translated
| values for all localizable entities.

When you create a new web content structure, each field you create has a
*Localizable* checkbox displayed next to it. This enables you to control what
can and can't be changed in the translation process. For example, if you don't
want images or content titles to be changed when the content is translated, you
can make sure those fields aren't localizable. When you follow the steps above
to localize content, only fields within the structure that had the *Localizable*
box checked appear within the translation window.

## Xuggler for Embedding Video

Xuggler is a tool which generates video previews and makes it possible to embed
videos from your Documents and Media library in web content and elsewhere on the
site. To enable Xuggler,

1.  Navigate to the *Control Panel*.

2.  Click on *Configuration* &rarr; *Server Administration* &rarr; *External Services*.

3.  Scroll to the bottom and click *Install* in the *Xuggler* section.

    This downloads the necessary libraries and prompts you to restart the server
    to enable Xuggler.

4.  After the server restarts, you can enable Xuggler from the same page.

Once Xuggler has been installed and enabled, you can embed a video or audio 
file in a web content article the same way you added images previously. 

![Figure 2: If you've installed and enabled Xuggler from the *Server Administration* &rarr; *External Tools* section of the Control Panel, you can add audio and video to your web content!](../../../../../images/web-content-audio-video.png)

## XML Format Downloads

Tools like the 
[Resource Importer](/docs/7-2/frameworks/-/knowledge_base/f/importing-resources-with-a-theme)
and Site Initiators can be deployed to build a site almost instantly. Before you
can use them to import Web Content, however, you first need to have the content
exported individually in XML format. To export the content,

1.  Go to *Site Administration* &rarr; *Content & Data* &rarr; *Web Content*.

2.  Start editing the article you want to download.

3.  Click the *Options* icon (![Options](../../../../../images/icon-options.png)) in 
    the top right of the page and select *View Source*.

This displays the raw XML source of the article. You can copy this content to 
save into an XML file locally.

![Figure 3: The *View Source* button is available from the *Options* button.](../../../../../images/web-content-download.png)

## Subscribing to Content

An administrator or web content writer can subscribe to an article or folder to
follow changes being made to it. 

1.  Go to *Content & Data* &rarr; *Web Content* for your Site.

2.  Click *Actions* 
    (![Actions](../../../../../images/icon-app-options.png)) &rarr; *Subscribe*
    next to the article or folder you want to follow.

Anytime an asset that you follow is modified, you receive an email notifying you
of the change.

![Figure 4: Click the Subscribe icon in the web content entity's *Options* menu to begin receiving web content notifications.](../../../../../images/web-content-subscribe.png)

That's pretty much all there is to basic content creation. Whole sites have
been created this way. But if you want to take advantage of the full power of
@product@'s WCM, you'll want to use structures and templates or Fragments.
You'll cover these topics next.

## Organizing Structure Names

By default, when you select a structure to add a new Web Content article, the
structures are ordered by their IDs, not their names. This can be confusing,
but---never fear---there's a configuration property to sort them alphabetically.

![Figure 5: The default ordering for Web Content Structures can yield confusing results.](../../../../../images/web-content-default-order.png)

To enable this property for Site Administration,

1.  Go to *Configuration* &rarr; *System Settings* &rarr; *Web Content* &rarr; 
    *Web Content Administration*.

2.  Check the box labeled *Journal Browse by Structures Sorted by Name*.

![Figure 6: Web Content Administration will now display structures in alphabetical order.](../../../../../images/web-content-admin-alphabetical.png)

You can also set this property for the Web Content Display widget. To enable
this property for the Web Content Display,

1.  Go to *Configuration* &rarr; *System Settings* &rarr; *Web Content* &rarr; 
    *Web Content Display*.

2.  Check the box labeled *Sort Structures by Name*.

After this option is checked, the structures are sorted alphabetically. Note 
that enabling this property can degrade performance with large structure 
libraries.
