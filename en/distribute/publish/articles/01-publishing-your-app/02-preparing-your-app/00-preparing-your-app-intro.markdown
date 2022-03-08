---
header-id: preparing-your-app
---

# Preparing Your App

[TOC levels=1-4]

As a Liferay developer, you're undoubtedly already familiar with the concept of
plugins (portlets, themes, etc.). If you're not familiar with Liferay
plugins, see 
[Introduction to Liferay Development](/docs/7-2/appdev).
A *Liferay App* (sometimes just called an *app*) is a collection of one or more
of these plugins, packaged together to represent the full functionality of an
application on the Liferay platform. In addition to the plugins contained within
an app, apps have metadata such as names, descriptions, versions, and other
information that describe and track the app throughout its life cycle. 

Much like standard Liferay plugins, Liferay apps are also auto-deployable.
Liferay Marketplace apps are distributed via a special file type with an `.lpkg`
extension. To deploy these files, drop them into a running Liferay instance's
auto-deploy folder (`[Liferay_Home]/deploy`), like any other plugin. 

As an app developer, you're not required to create the `.lpkg` files. Instead,
your app's individual plugins (WAR files for traditional plugins or JAR files
for OSGi modules) are uploaded as part of the publication process, along with
information (name, description, version, icon, etc.) that identifies the app.
The publication process is described in detail later.

At this point in preparing to publish your app, you've developed your app. But
before you start the formal publishing process, you must prepare your app's
files and app metadata. 
