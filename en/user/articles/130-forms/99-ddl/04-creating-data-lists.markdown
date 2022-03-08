---
header-id: creating-data-lists
---

# Creating Data Lists

[TOC levels=1-4]

There are two places to create dynamic data lists: 

1.  **Site Administration:** Open the Menu and expand your Site's menu (the
    Site Administration menu). Then select *Content & Data* &rarr; *Dynamic
    Data Lists*. This opens the Dynamic Data Lists screen. A table contains any
    existing lists. Click the *Add* button
    (![Add](../../../images/icon-add.png)) to open *New List* form. 

    To add Dynamic Data Lists, you must have permission to access the Dynamic
    Data Lists app in Site Administration. 

2.  **Dynamic Data Lists Display widget:** Navigate to the Site page where you
    want this widget and add it to the page from *Add*
    (![Add](../../../images/icon-add-app.png)) &rarr; *Widgets* &rarr;
    *Collaboration* &rarr; *Dynamic Data Lists Display*. Then click the widget's
    *Add List* button. This opens the *New List* form. 

    To do this, you must have permission to create a new list in the widget. 

Either option leads to the New List form: 

1.  Give the list a name and a description. 

2.  Select the list's data definition: click *Select* under the *Data
    Definition* field, then click the definition you want to use. 

3.  To use a workflow with this list, select it from the *Workflow* field. 

4.  To change the list's default permissions, expand the form's *Permissions* 
    section and make your selections. 

5.  Click *Save* to finish creating the list. Your new list appears in the table. 

    ![Figure 1: The New List form.](../../../images/ddl-add-list.png)

## Creating List Records

By default, only administrators have permission to create list records. Follow 
these steps to give other users this permission: 

1.  Navigate to *Content & Data* &rarr; *Dynamic Data Lists* in Site Administration. 

2.  Click *Actions* 
    (![Actions](../../../images/icon-actions.png)) &rarr; *Permissions* for the
    list getting the new permissions.

3.  Select *Add Record* for the Roles that should have that permission, then
    click *Save*. Allow unauthenticated Users to add records by giving Guest the
    Add Record permission.

Create new records in a list from the same places you can create the lists
themselves: 

1.  **Site Administration:** In Site Administration, navigate to *Content & Data* 
    &rarr; *Dynamic Data Lists*. Click a list in the table to view any existing 
    records, then click the *Add* button 
    (![Add](../../../images/icon-add.png)). 
    This opens a form based on the list's data definition, which you can then 
    fill out and submit to create a new record. To do this, you must have 
    permission to access the Dynamic Data Lists app in Site Administration. 

2.  **Dynamic Data Lists Display widget:** See above for instructions on adding 
    this widget to a page. You must then configure the widget to display a 
    list's records. 

    To configure the widget to display a list's records: 

    -   Click the widget's *Select List* button. 
    -   In the dialog that appears, select a list, click *Save*, then close the 
        dialog. The widget then displays the list's existing records. 

    To add a record: 

    -   Click the widget's *Add* button 
        (![Add](../../../images/icon-add.png)). 
    -   Fill out the form that appears and click *Publish*. 

See the section below for more information on configuring the widget. 

![Figure 2: Dynamic Data Lists Display widget.](../../../images/ddl-widget.png)

## Configuring the Dynamic Data Lists Display Widget

The widget's default display template isn't exciting, but it shows the list's
contents, and with permission, add and/or edit list items. To configure the
widget, click its *Options* menu
(![Options](../../../images/icon-app-options.png)) and select *Configuration*.
This opens the Configuration dialog, with the *Setup* tab selected by default.
The Setup tab contains two other tabs: 

**Lists:** Select the list that the widget displays. The currently 
displayed list appears at the top of the tab, while the available lists 
appear in a table. To change the widget's list, select the list from the 
table and click *Save*. 

**Optional Configuration:** 

**Display Template:** Select the display template for the list.

**Form Template:** Select the form template for the list.

**Editable:** Whether users can add records to the widget's list. 

**Form View:** Whether to display the Add Record form by default, instead of
the List View. Note that even without this selected, users can still add
records via the widget's *Add* button
(![Add](../../../images/icon-add.png)). 

**Spreadsheet View:** Whether the List View displays each record in a row, with
columns for the record attributes. 

When finished, click *Save* and close the Configuration dialog. 

![Figure 3: The Dynamic Data Lists Display widget's optional configuration.](../../../images/ddl-widget-options.png)
