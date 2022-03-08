---
header-id: calendar-resources-and-porting
---

# Calendar Resources and Porting

[TOC levels=1-4]

With calendar resources, you can invite entities other than people to your
events. This is beneficial for finding the availability of important resources
your event requires, like a conference room, laptop, or, at The Lunar Resort,
the Sasquatch Space Suit used to scare guests out on Lunar hikes. 

Another important topic is porting your calendar's data from one installation of
@product@ to another.

## Calendar Resources

Follow these steps to add a new calendar resource:

1.  Click on the *Resources* tab and click the ![Add](../../images/icon-add.png) 
    button to add a new resource.

    ![Figure 1: Resources are accessed from the tab menu at the top of the widget.](../../images/calendar-resources.png)

2.  Fill in the *New Resource* form. Enter a name, give it a description, and
    choose whether to set it as active. You can also tag it, assign it to
    categories, and configure its permissions. Click *Save*.

The resource has its own calendar that was generated automatically (this is how
users can check its availability when creating events). Just as with Users,
however, resources can have more than one calendar. Follow these steps to assign
a new calendar to the resource:

1.  Go to the widget's *Resources* tab, click the
    ![Options](../../images/icon-actions.png) button next to the resource, and
    select *View Calendars*.

2.  Click *Add Calendar* and continue just as if you were creating a calendar
    for a user or a Site.

Once a resource is created, invite it to your events just as you would an
attendee.

## Exporting and Importing Calendar Data

Like other Liferay Applications, the calendar allows data to be exported or
imported as [LAR files](/docs/7-2/user/-/knowledge_base/u/exporting-importing-widget-data). 
As with all LAR files, data can only be ported between installations of the same 
version.

1.  From the calendar widget, click the
    ![Options](../../images/icon-app-options.png) button in the widget header and select
    *Export/Import*.

2.  Enter a name for the LAR file (or use the default).

    Under *Application*, choose whether to include the widget's configuration
    in the LAR.

    Under *Content*, choose how much historical data to export and select the
    content types (calendars, resources, and events) to include. You can
    also choose whether to include comments and ratings.

    Check the appropriate boxes to select whether to include deletions and
    permissions in the LAR.

3.  Click *Export*. When a success message displays (this may take a few
    moments) you can click on the LAR's filename to download it.

    ![Figure 2: This LAR is ready to be downloaded.](../../images/calendar-lar.png)

Follow these steps to import a LAR:

1.  From the calendar widget, click the
    ![Options](../../images/icon-app-options.png) button in the widget header and select
    *Export/Import*.

2.  Click the *Import* tab.

3.  Click *Choose File* or else drag-and-drop a LAR into the area surrounded by
    a dotted line. Click *Continue*.

4.  Decide how much data you want to import:

    Under *Application*, check the box to import the configuration stored in the
    LAR or leave in unchecked to keep your current configuration.

    Under *Content*, decide which content types (calendars, resources, and
    events) to import, and whether to include comments and ratings.

    Choose whether to import permissions and deletions, and decide whether to
    delete your widget's existing data before the import.

5.  In the collapsible *Update Data* section, choose how data will be updated.

    **Mirror:** The data will be imported along with a reference to its
    source. This allows data to be updated rather than duplicated if the same
    LAR is imported more than once.

    **Copy as New:** All data is imported as new entries. Repeat imports will
    produce duplicates.

6.  In the *Authorship of the Content* section, choose whether to keep the
    original author of the imported content (where available) or to list the
    current user as the author.

7. Click *Import*.

Your calendar is set up and ready to go! Better check it to see what's next on
the agenda.
