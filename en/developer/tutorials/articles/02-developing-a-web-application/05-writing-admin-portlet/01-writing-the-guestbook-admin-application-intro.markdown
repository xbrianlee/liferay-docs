---
header-id: writing-an-administrative-portlet
---

# Writing an Administrative Portlet

[TOC levels=1-4]

The Guestbook application lets users add and view guestbook entries. The
application's back-end, however, is much more powerful. It can support many
guestbooks and their associated entries. But at this point, there's no UI to
support these added features. When you create this UI, you must also make sure
that only administrators can add guestbooks. 

To accomplish this, you'll create a Guestbook Admin portlet and place it in 
@product@'s administrative interface---specifically, within the Content menu. 
This way, the Guestbook Admin portlet is accessible only to Site Administrators, 
while users use the Guestbook portlet to create entries. 

In short, this is a simple application with a simple interface: 

![Figure 1: The Guestbook Admin portlet lets administrators manage Guestbooks.](../../../images/admin-app-start.png)

If you get stuck, [source code](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/guestbook/05-admin-portlet)
for this step is provided. 

Are you ready to begin? 

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/creating-the-classes">Let's Go!<span class="icon-circle-arrow-right"></span></a>
