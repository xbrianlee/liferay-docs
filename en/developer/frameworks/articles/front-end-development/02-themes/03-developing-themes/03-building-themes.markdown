---
header-id: building-your-themes-files
---

# Building Your Theme's Files

[TOC levels=1-4]

Follow these steps to build your theme's files with the Build task. Note that 
this task only works for themes that use the 
[liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the 
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme).

| **Note:** Gulp is included as a local dependency in generated themes, so you 
| are not required to install it. It can be accessed by running 
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's 
| root folder.

1.  Navigate to your theme's root folder and run `gulp build`.

2.  The `gulp build` task generates the base theme files (in the `build` folder), 
    compiles Sass into CSS, and compresses all theme files into a `.war` file 
    (in the `dist` folder), that you can deploy to your server. Copy any of 
    these files and folders to your theme's `src` folder to modify them. 

3.  [Deploy](/docs/7-2/frameworks/-/knowledge_base/f/deploying-and-applying-themes) 
    the `war` file to your app server to make it available.
    
![Figure 1: Run the `gulp build` task to build your theme's files.](../../../../images/theme-dev-building-themes-gulp-build.png)

## Related Topics

- [Automatically Deploying Theme Changes](/docs/7-2/frameworks/-/knowledge_base/f/automatically-deploying-theme-changes)
- [Copying an Existing Theme's Files](/docs/7-2/frameworks/-/knowledge_base/f/copying-an-existing-themes-files)
- [Deploying and Applying Themes](/docs/7-2/frameworks/-/knowledge_base/f/deploying-and-applying-themes)
