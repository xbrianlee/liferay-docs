---
header-id: defining-the-component-metadata-properties
---

# Defining the Component Metadata Properties

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End</p><p>Step 2 of 11</p>
</div>

When users add applications to a page, they pick them from a list of *display
categories*. 

![Figure 1: Users choose applications from a list of display categories.](../../../images/display-categories.png)

A portlet's display category is defined in its component class as a metadata 
property. Since the Guestbook portlet lets users communicate with each other, 
you'll add it to the Social category. Only one Guestbook portlet should be added 
to a page, so you'll also define it as a *non-instanceable* portlet. Such a 
portlet can appear only once on a page or Site, depending on its scope. 

1.  Open the `GuestbookPortlet` class and update the component class metadata 
    properties to match this configuration: 

    ```java
    @Component(
        immediate = true,
        property = {
          "com.liferay.portlet.display-category=category.social",
          "com.liferay.portlet.instanceable=false",
          "com.liferay.portlet.scopeable=true",
          "javax.portlet.display-name=Guestbook",
          "javax.portlet.expiration-cache=0",
          "javax.portlet.init-param.template-path=/",
          "javax.portlet.init-param.view-template=/guestbook/view.jsp",
          "javax.portlet.resource-bundle=content.Language",
          "javax.portlet.security-role-ref=power-user,user",
          "javax.portlet.supports.mime-type=text/html"
        },
        service = Portlet.class
    )
    ```

The `com.liferay.portlet.display-category=category.social` property sets the 
Guestbook portlet's display category to *Social*. The 
`com.liferay.portlet.instanceable=false` property specifies that the Guestbook 
portlet is non-instanceable, so only one instance of the portlet can be added 
to a page. In the property `javax.portlet.init-param.view-template`, you also 
update the location of the main `view.jsp` to a folder in
`src/main/resources/META-INF/resources` called `/guestbook`. You'll wind up
creating two folders there for the two different portlets you'll create:
`guestbook` and `guestbook-admin`. For now, just create the `guestbook` folder: 

1.  Open `src/main/resources`, then open `META-INF`. Right-click on the
    `resources` folder and select *New* &rarr; *Folder*. 

2.  Name the folder *guestbook* and hit *Enter* (or click OK). 

3.  Drag `view.jsp` and drop it onto the `guestbook` folder to move it there. 

4.  Open `view.jsp` and modify the path to `init.jsp` to include it from the
    parent folder: 

    ```markup
    <%@ include file="../init.jsp" %>
    ```

Since you edited the portlet's metadata, you must remove and re-add the portlet 
to the page before continuing: 

1.  Go to `localhost:8080` in your web browser.

2.  Sign in to your administrative account.

3.  The Guestbook portlet now shows an error on the page. Click its portlet menu 
    (at the top-right of the portlet), then select *Remove* and click *OK* to 
    confirm.

4.  Open the *Add* menu and select *Widgets*.

5.  Open the *Social* category and drag and drop the *Guestbook* widget
    onto the page.

Great! Now the Guestbook portlet appears in an appropriate category. Though you 
were able to add it to the page before, the user experience is better. 
