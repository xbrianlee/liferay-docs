# Publishing Blogs [](id=publishing-blogs)

Since blogs are a mainstay on the web, bloggers and blog administrators expect
feature-rich environments for publishing blogs. They want both a powerful
editor and a pleasurable authoring experience. Blog admins demand an intuitive
environment that facilitates configuring blog instances and managing blog
entries efficiently. Liferay's Blogs meets these demands. 

Its editor delivers features you'd expect in an elegant in-context manner. It
has a complete set of WYSIWYG controls that appear where and when you need them.
You can stay in this easy-to-use mode or switch to source mode to edit your
content's HTML code. In source mode, you can work with light text on a dark
background or dark text on a light background. To cap things off, you can open
the dual screen HTML editor to see your code rendered in real time. You'll have
a satisfying experience creating awesome blog posts. 

Lastly, Liferay Blogs empowers you to show off your blogs using powerful display
apps. You can leverage Liferay's built-in display templates or create your own,
to present blogs the way you like them. And you can now add a beautiful cover
image to each of your blog entries, like album covers for your posts. Let's face
it--you might not be able to judge a book by its cover, but you're more likely
to draw readers to your blog entry by decorating it with an enticing cover
image! 

By now you're probably chomping at the bit to start blogging, right? Let's get
started. 

## Adding Blog Entries [](id=adding-blog-entries)

Each site comes with a built-in blog instance, so let's explore adding an entry
to your site's blog. In Site Administration, the Blogs app provides the best
place to draft blogs. Click the Product Menu icon
(![Product Menu](../../../images/icon-menu.png)) to open the Product Menu. Then
navigate to *Sites* &rarr; *\[Site Name\]* &rarr; *Content* &rarr; *Blogs*. The
*Entries* screen appears, listing the site's blog entries. 

Click the *Add* icon (![Add](../../../images/icon-add.png)) to bring up the blog
entry screen. 

![Figure 1: This screenshot highlights the blog entry editor's controls for formatting text, justifying images, and editing tables.](../../../images/blogs-blogs-entry-mars.png)

The screen presents fields to set a cover image, title, and subtitle, and an
area for creating the entry's content. You may be asking yourself, "Where are
the content editor's controls?" The editor gives you a seamless writing experience,
displaying controls when you need them and hiding them from view when you don't
need them. This keeps your canvas uncluttered, so you can focus on writing. As
you create content, context-specific controls appear. 

Go ahead and enter text in the *Content* area. If you highlight text, controls
appear. These controls let you style the text or convert it to a link or a
tweet to share your blog post on Twitter. 

Whenever you park your cursor in the content area, the *Add* icon (`+`) appears.
If you click on it, it shows controls for inserting an image, table, or
horizontal line (![Controls](../../../images/icon-content-insert-controls.png)). To
insert an image, click the icon that depicts the mountain silhouette. The image
file selector screen appears. It lets you choose an existing image upload a new
one. You can also drag-and-drop image files into the content area. After you've
added the image to the blog entry, clicking it brings up controls for justifying
it to the right or left side of the article. 

You can also insert a table with as many rows and columns as you like. If you
click inside the table, table editing controls appear. They let you designate
the first row and/or column as table headers. The controls also enable you to
add rows, columns, and cells. 

Now you're familiar with the editor's regular mode. If you'd rather work with
the content's HTML code, you can. To switch the editor to source view, select
the *Source* icon (`</>`). Note that a *Roller* icon
(![Roller](../../../images/icon-roller.png)) for regular mode appears, giving you
the option to switch back to regular view. To satisfy your eyes, source view's
moon icon and sun icon let you switch between a dark theme and a light theme.
The built-in syntax coloring helps you identify HTML elements, regardless of the
mode you're using. 

You can even work in a dual pane view that shows your HTML code on the left and
a preview pane on the right. To open this view, click the *Enlarge* icon
(![Enlarge](../../../images/icon-enlarge.png)). You can arrange the HTML and
preview panes horizontally or vertically. You can also hide the preview pane,
so the HTML editor takes up the entire window space. No matter how you want to
use the HTML editor, it can really help you stay "in the zone" as you create
awesome blog entries. 

![Figure 2: The enlarged source editor helps you minimize screen clutter and render changes in real time.](../../../images/blogs-full-screen-editor.png)

Every 25 seconds, the entry you're editing is automatically saved as a draft, so
a browser crash or network interruption won't cause you to lose your entry. You
can exit the enlarged editor by clicking *Done* (which saves your content) or
clicking *Cancel* to abandon any changes since the last auto-save. From the
normal-sized source view, you can click the *abc* icon to switch back to the
WYSIWYG editor. 

You've learned all the ins and outs of the content editor. Next, you'll learn
how to specify your blog entry's other characteristics. 

The edit screen's first input field--*Drag \& Drop to Upload*--lets you add a
cover image (optional) for your entry. This might be an image that represents
your entry's purpose and is designed to attract readers. You can drag and drop
onto this field any image you like. As an alternative to dragging and dropping
an image, you can click the *Select File* button to use the image selector to
choose an existing image in the blog, an image file from Documents and Media, or
an image outside the Liferay instance. You can browse to an image file and
upload it. After you set the entry's cover image, you can add a caption to it.
And if you want to select a different image, you can click the *Change* icon
(![Change](../../../images/icon-change.png)) to bring up the image selector again.

Clicking the *Configuration* icon (![Gear](../../../images/icon-gear.png)) at the
top of the entry editor lets you configure the blog entry. You can set a
particular Display Date for the entry, choose a 400 character text-only abstract
or a custom abstract containing a thumbnail image and a manually written
description. You can also send emails on entry updates. 

The Categorization heading is next in the entry configuration screen. Its
options allow you to attribute tags and/or categories to your blog entry. Doing
this improves search results for blog entries, and you get more navigation
options for your users. For example, you can add the Tags Navigation application
to another column on your blogs page, which lets users browse blog entries by
tag. 

Below this is the Related Assets heading. If there's some other content in the
Liferay instance that's related to your entry, you can choose it here. For
example, you might want to write a blog entry talking about a discussion that
happened on the forums. To link those two assets together, select the forum
thread under Related Assets. 

Blog entries also support *pingbacks*. Pingbacks are XML-RPC requests that are
automatically sent when you link to another site. If you link to another site in
your blog entry, Liferay sends a pingback to the other site to notify that site
that you linked to it. Similarly, if someone links to your blog entry, Liferay
can receive a pingback from that person's site and record the link. 

Once you've finished your blog entry, click *Publish*. Your blog entry appears
with the site's other blog entries.

![Figure 3: Blogs in Site Administration is the perfect place to create and manage blog entries. It has several options for modifying, displaying, filtering, and finding entries.](../../../images/blogs-admin-entries.png)

Congratulations on creating your blog entry! Before displaying it on your site's
pages, you may want to learn how to manage blog entries. 

## Managing Blog Entries [](id=managing-blog-entries)

The Blogs application in Site Adminstration helps bloggers and blog
administrators manage blog entries. *Search* finds entries that match the
keywords you enter. The *Order by* selector enables you to organize entries by
*Title* or *Display Date*, in ascending or descending order. And the icons above
the listing of entries allow you to display the entries in different ways:
showing large cover images, listing single cell rows that show the author's image
and entry information, or displaying the entries in a table. Working with
existing blog entries has never been easier! 

You can manage entries individually or in a batch. Each entry has an *Actions*
icon (![Actions](../../../images/icon-actions.png)) to edit the entry, configure
its permissions, or move it to the Recycle Bin. You can select the *All*
checkbox to select all entries or select an entry's individual check box, and
click the trash icon to move them into the Recycle Bin. Under the *Images* tab
you can view individual images and delete them individually or in a batch. 

The *Options* icon (![Options](../../../images/icon-options.png)) at the top of
Blogs lets you configure permissions and notifications, or import/export the
blog. Here are the blog instance Configuration options:

**Email From:** defines the *From* field in the email messages that users
receive from Blogs.

**Entry Added Email:** defines a subject and body for the emails sent when
a new blog entry has been added.

**Entry Updated Email:** defines a subject and body for the emails sent 
when a new blog entry has been updated.

**RSS:** choose how blogs are displayed to RSS readers. 

- **Maximum Items to Display:** choose the total number of RSS feeds entries to
display on the initial page. You can choose up to one hundred to be displayed.

- **Display Style:** choose between *Full Content*, *Abstract*, and *Title*. These
options work just like the ones above for blog entries.

- **Format:** choose which format you want to deliver your blogs: Atom 1.0, RSS
1.0, or RSS 2.0.

You've learned how to create blog entries and manage them. It's time to consider
blog security. For example, who is allowed to view the different blog instances
and who is allowed to add blog entries?

If you have a personal blog, the default permissions should work well for you.
If you have a shared blog, you may want to modify the permissions on the blog.
The default settings make it so only the owner of the site where the application
was added can add entries. This, of course, is great if the Blogs app has been
added to a user's personal pages, but doesn't work so well for a shared
multi-author blog. But don't worry: it's easy to grant blogging permissions to
users. 

First, create a
[role](/discover/portal/-/knowledge_base/7-0/roles-and-permissions) for your
bloggers and add them to the role. Next, in Blogs, click *Options* &rarr;
*Permissions*. A list of both instance and site roles is displayed, and
currently only the owner is checked for the *Add Entry* permission. Mark any
other role or team that should be able to add blog entries and then click
*Save*. Once this is done, users in the roles or teams that you selected are
able to post to the shared blog. You can also grant roles and teams the ability
to subscribe to blog updates. 

From within the Control Panel, you can configure all the permissions for Blogs.
Permissions for the Blogs and Blogs Aggregator applications are covered next.

You've now created a blog entry and learned how to manage blog entries and
permissions. Next, you'll learn how to use the Blogs and Blogs Aggregator
applications to display blog entries the way you want them. 

## Displaying Blogs [](id=displaying-blogs)

You can add the Blogs application a page from the *Add* menu.
Since Blogs supports scopes, you can use it to create a multi-author blog to
build a site like [http://slashdot.org/](http://slashdot.org/) or to create
multiple personal blogs to build a site like
[http://blogger.com](http://blogger.com). What's the difference? Adding the
Blogs app to a site page creates a shared blog for members of the site. Adding
the app to a user's personal site (dashboard) creates a blog just for that user.
Blogs works the same way in both cases. And of course, you can scope a blog to a
page to produce a blog instance for just that page. 

Add the Blogs application to one of your site's pages. It lists abstracts of the
site's recently published blog entries. Notice that each entry's cover image
shows prominently in the listing. The figure below shows a blog entry abstract. 

![Figure 4: Here's a blog entry in abstract format.](../../../images/blogs-blog-entry-abstract-2.png)

You can see that in the summary view, you don't see the trackback/pingback link,
and you only see the number of comments that have been added. If you click the
entry's title, you'll see the whole article, all the comments in a threaded
view, and the trackback/pingback link which others can use to link back to your
blog entry.

The full view of the blog entry also contains links to share blog entries on
social networks, such as Twitter, Facebook, and Google+. This gives your readers
an easy way to share blog entries with friends, potentially driving further
traffic to your site. 

![Figure 5: Users can view your blog entry in all its glory. They can rate it, comment on it, and share it with other people.](../../../images/blogs-blog-entry-full-content.png)

By default, the Blogs application displays the abstract and image of the latest
entries. There are several display options that let you configure the listing to
look the way you want. To configure the application, click the *Options* icon in
the app's title bar and select *Configuration*. The *Display Settings* are in
the Setup tab. To choose the right settings, you should think about the best way
to display your entries as well as how you want users to interact with bloggers. 

Here are the Display Settings:

**Maximum Items to Display:** choose the total number of blog entries to display
on the initial page. You can select up to 75 to display at once.

**Display Template:** choose between *Abstract*, *Full Content*, *Title*, or
*Basic*. Setting this to Abstract shows the entry's cover image and first four
hundred characters of text. Users can click on the entry's title to see its full
content. You can click *Manage Display Templates for Liferay* to select an
existing Blogs application display template (ADT) or to create your own. To
learn how to customize your own display templates with
[Application Display Templates](/participate/liferaypedia/-/wiki/Main/Application+Display+Templates)
visit the *Using Application Display Templates* section of this guide.

**Enable Flags:** flag content as inappropriate and send an email to the
administrators.

**Enable Related Assets:** select related content from other applications to
pull into their blog entry for readers to view.

**Enable Ratings:** lets readers rate your blog entries from one to five stars.

**Enable Comments:** lets readers comment on your blog entries.

**Enable Comment Ratings:** lets readers rate the comments which are posted to
your blog entries.

**Enable Social Bookmarks:** lets users tweet, Facebook like, or +1 (Google+)
blog posts. You can edit which social bookmarks are available in the *Social
Bookmarks* section of the Configuration menu.

**Display Style:** select a menu, simple, vertical, or horizontal display style for
your blog posts.

**Display Position:** choose a top or bottom position for your blog posts.

**Social Bookmarks:** choose social bookmarks to enable for blog posts, which
includes Twitter, Facebook, and Google+.

Here are descriptions for the other Blogs Configuration tabs:

**Permissions:** shows Liferay's permissions dialog for the Blogs application.

**Communication:** lists public render parameters the application publishes to
other applications on the page. Other applications can read these and take
actions based on them. For each shared parameter, you can specify whether to
allow communication using the parameter and select which incoming parameter can
populate it. 

**Sharing:** lets you embed the application instance as a widget on on any
website, Facebook, or Netvibes, or as an OpenSocial Gadget.

**Scope:** lets you specify the blog instance the application displays: the
current site's blog (default), global blog, or page blog. If the page doesn't
already have a blog instance, you can select scope option *\[Page Name\]
\(Create New\)* to create a page-scoped blog instance for the Blogs application
to display. 

Liferay's Blogs application excels at aggregating information from multiple
places. The Blogs Aggregator application lets you "bubble up" blog entries from
multiple users and highlight them on your site. Let's look next at how that
works. 

## Aggregating Blogs [](id=aggregating-blogs)

You can set up a whole web site devoted just to blogging if you wish. The Blogs
Aggregator lets you publish entries from multiple bloggers on one page, giving
further visibility to blog entries. You can add it to a page from the
*Collaboration* category in the *Add*
(![Add](../../../images/icon-control-menu-add.png)) *&rarr; Applications* menu. 

If you click *Configuration* from the options button in the app's title bar, the
Blogs Aggregator's configuration page appears. From here, you can set its
options. 

**Selection Method:** select Users or Scope here. If you select Users, the Blogs
Aggregator aggregates the entries of every blogger on your system. If you want
to refine the aggregation, you can select an organization by which to filter the
users. If you select Scope, the Blogs Aggregator contains only entries of users
who are in the current scope. This limits the entries to members of the site
where the Blogs Aggregator resides.

**Organization:** select which organization's blogs you want to aggregate.

**Display Style:** select from several different styles for displaying blog
entries: *Body and Image*, *Body*, *Abstract*, *Abstract without Title*,
*Quote*, *Quote without Title*, and *Title*.

**Maximum Items to Display:** select the maximum number of entries the app
displays.

**Enable RSS Subscription:** creates an RSS feed out of the aggregated entries.
This lets users subscribe to an aggregate feed of all your bloggers. Below this
checkbox, you can configure how you want the RSS Feed displayed:

- **Maximum Items to Display:** select maximum number of RSS items to display.

- **Display Style:** select from several different styles for displaying RSS
feeds: *Abstract*, *Full Content*, and *Title*.

- **Format:** select which web feed language to use for your feed, which
includes *Atom 1.0*, *RSS 1.0*, or *RSS 2.0*. 

**Show Tags:** for each entry, displays all the tags associated with the blogs.

At the top of the Configuration screen, there's an option called
*Archive/Restore Setup*. It lets you store your current Setup configuration or
apply an existing archived Setup. This is especially helpful when you have
multiple configurations you like to use in Blogs Aggregator instances. 

Here are descriptions for the other Blogs Aggregator's Configuration screens:

**Permissions:** shows Liferay's permissions dialog. 

<!-- TODO - Revisit the permission Access in Control Panel. It's currently grayed out. - Jim --> 

**Communication:** lists public render parameters the application publishes to
other applications on the page. Other applications can read these and take
actions based on them. For each shared parameter, you can specify whether to
allow communication using the parameter and select which incoming parameter can
populate it. 

**Sharing:** lets you embed the application instance as a widget on on any
website, Facebook, or Netvibes, or as an OpenSocial Gadget.

**Scope:** lets you specify the blog instance the application displays: the
current site's blog (default), global blog, or the page's. If the page doesn't
already have a blog instance, you can select scope option *\[Page Name\]
\(Create New\)* to create a page-scoped blog instance and set the Blogs app to
display it.

When you've finished setting the options, click *Save*. Then close the dialog
box. You'll notice the Blogs Aggregator looks very much like the Blogs
application, except that it shows entries from multiple blogs. 

![Figure 6: The Blogs Aggregator lets you display blog entries authored by multiple authors from different sites.](../../../images/blogs-blog-aggregator.png)

## Summary [](id=summary)

Throughout this section, you've learned how to create blog posts in a powerful
no-nonsense editor, manage blog instances and blog entries, and display blogs
the way you like them using the the Blogs and Blogs Aggregator applications.
Now you can enjoy the benefits of participating in the world of blogging. 

Blogs are terrific for sharing information on a topic, posting important
announcements, and expressing yourself. And if comments are enabled, users can
have short exchanges about your blog entry. The best place to discuss things or
ask questions, however, is in a forum. Next, you'll learn how to create forums
using Message Boards. 
