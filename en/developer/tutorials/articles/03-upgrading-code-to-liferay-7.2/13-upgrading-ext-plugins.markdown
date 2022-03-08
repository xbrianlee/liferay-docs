---
header-id: upgrading-ext-plugins
---

# Upgrading Ext Plugins

[TOC levels=1-4]

Ext plugins let you use internal APIs and even let you overwrite @product@ core
files. This puts your deployment at risk of being incompatible with security,
performance, or feature updates released by Liferay. When upgrading to a new
version of @product@, you must review all changes and manually modify your Ext
projects to merge your changes with @product@'s.

During your upgrade to @product-ver@, it's highly recommended to leverage an
extension point to customize @product@ instead of using you existing Ext plugin,
if possible. @product-ver@ provides many extension points that let you customize
almost every detail of @product@. If there's a way to customize what you want
with an extension point, do it that way instead. See
[Finding Extension Points](/docs/7-2/customization/-/knowledge_base/c/finding-extension-points)
for more details.

For more information on Ext projects, how to decide if you need one, and how to
manage them, see the
[Customization with Ext](/docs/7-2/customization/-/knowledge_base/c/customization-with-ext)
section.
