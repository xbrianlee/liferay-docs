---
header-id: generating-portlet-urls
---

# Generating Portlet URLs

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 6 of 11</p>
</div>

Since users can place multiple portlets on a single page, you have no idea what
other portlets may share a page with yours. This means that you can't define
URLs for various functions in your application like you otherwise would.

For example, consider a Calendar application that a user puts on the same page 
as a Blog application. To implement the functionality for deleting calendar 
events and blog entries in the respective application, both application 
developers append the `del` parameter to the URL and give it a primary key 
value so the application can look up and delete the calendar event or blog 
entry. Since both applications read this parameter, their delete functionality 
clashes. 

System-generated URLs prevent this. If the system generates a unique URL for
each piece of functionality, multiple applications can coexist in perfect
harmony. 

In `view.jsp`, follow these steps to create system-generated URLs in your
portlet: 

1.  Add these tags below `<%@ include file="../init.jsp" %>`, but above the 
    `<aui:button-row>` tag: 

    ```java
    <portlet:renderURL var="addEntryURL">
        <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp"></portlet:param>
    </portlet:renderURL>
    ```

2.  Add this attribute to the `<aui:button>` tag, before `value="Add Entry"`:

    ```java
    onClick="<%= addEntryURL.toString() %>"
    ```

    Your `view.jsp` page should now look like this: 

    ```markup
    <%@ include file="/init.jsp" %>

    <portlet:renderURL var="addEntryURL">
        <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp"></portlet:param>
    </portlet:renderURL>

    <aui:button-row>
        <aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
    </aui:button-row>
    ```

The `<portlet:renderURL>` tag's `var` attribute creates the `addEntryURL` 
variable to hold the system-generated URL. The `<portlet:param>` tag defines a 
URL parameter to append to the URL. In this example, a URL parameter named 
`mvcPath` with a value of `/edit_entry.jsp` is appended to the URL. 

Note that your `GuestbookPortlet` class (located in your `guestbook-web` 
module's `com.liferay.docs.guestbook.portlet` package) extends Liferay's 
`MVCPortlet` class. In a 
[Liferay MVC portlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet), 
the `mvcPath` URL parameter indicates a page within your portlet. To navigate to
another page in your portlet, use a portal URL with the parameter `mvcPath` to
link to the specific page. 

In the example above, you created a `renderURL` that points to your 
application's `edit_entry.jsp` page, which you haven't yet created. Note that
using an AlloyUI button to follow the generated URL isn't required. You can use 
any HTML construct that contains a link. Users can click your button to access 
your application's `edit_entry.jsp` page. This currently produces an error since 
no `edit_entry.jsp` exists yet. Creating `edit_entry.jsp` is your next step. 
