---
header-id: copying-an-existing-themes-files
---

# Copying an Existing Theme's Files

[TOC levels=1-4]

Follow these steps to copy an existing theme's files with the Kickstart task. 
Unlike extending a base theme, which is a dynamic inheritance that applies your 
`src` files on top of the base theme on every build, the Kickstart task is a one 
time inheritance. 

| **Warning:** The gulp kickstart task copies an existing theme's files into 
| your own, which can potentially overwrite files with the same name. 
| Proceed with caution. 

Note that this task only works for themes that use the 
[liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the 
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme). 

| **Note:** Gulp is included as a local dependency in generated themes, so you 
| are not required to install it. It can be accessed by running 
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's 
| root folder.

1.  Navigate to your theme's root folder and run `gulp kickstart`.

    ![Figure 1: Run the `gulp kickstart` task to copy a theme's files into your own theme.](../../../../images/theme-ext-kickstarting-themes-gulp-kickstart.png)

2.  Select where to search for the theme to copy. You can copy files from 
    globally installed themes or themes published on the npm registry.
    
    | **Note:** **You can't kickstart the Classic Theme.**
    
    | **Note:** To globally install a theme, run the `npm link` command from the 
    | theme's root folder.

    ![Figure 2: You can copy files from  globally installed themes.](../../../../images/theme-ext-kickstarting-themes-global-theme.png)

3.  The theme's files are copied into your own theme, jump starting development. 
    Add your changes on top of these files.
    
Congrats! Now you have a head start to developing your theme. 
 
## Related Topics

- [Building Your Theme's files](/docs/7-2/frameworks/-/knowledge_base/f/building-your-themes-files)
- [Generating Themelets with the Theme Generator](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator)
- [Deploying and Applying Themes](/docs/7-2/frameworks/-/knowledge_base/f/deploying-and-applying-themes)
