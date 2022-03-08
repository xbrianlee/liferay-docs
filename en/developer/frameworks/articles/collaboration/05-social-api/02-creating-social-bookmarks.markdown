---
header-id: creating-social-bookmarks
---

# Creating Social Bookmarks

[TOC levels=1-4]

By default, @product@ contains social bookmarks for Twitter, Facebook, and 
LinkedIn. You can also create your own social bookmark by registering a 
component that implements the 
[`SocialBookmark`](@app-ref@/social/latest/javadocs/com/liferay/social/bookmarks/SocialBookmark.html) 
interface from the module 
`com.liferay.social.bookmarks.api`. The steps here show you how to do this. 

## Implementing the SocialBookmark Interface

Follow these steps to implement the `SocialBookmark` interface: 

1.  Create your `*SocialBookmark` class and register a component that defines 
    the `social.bookmarks.type` property. This property's value is what you 
    enter for the `liferay-social-bookmarks:bookmarks` tag's `type` attribute 
    when you use your social bookmark. 

    For example, here's the definition for a Twitter social bookmark class: 

    ```java
    @Component(immediate = true, property = "social.bookmarks.type=twitter")
    public class TwitterSocialBookmark implements SocialBookmark {...
    ```

2.  Create a 
    [`ResourceBundleLoader`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html) 
    reference to help localize the social bookmark's name. 

    ```java
    @Reference(
            target = "(bundle.symbolic.name=com.liferay.social.bookmark.twitter)"
    )
    private ResourceBundleLoader _resourceBundleLoader;
    ```

3.  Implement the `getName` method to return the social bookmark's name as a 
    string. This method takes a 
    [`Locale`](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html) 
    object that you can use for localization via 
    [`LanguageUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/language/LanguageUtil.html) 
    and 
    [`ResourceBundle`](https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html): 

    ```java
    @Override
    public String getName(Locale locale) {
        ResourceBundle resourceBundle = _resourceBundleLoader.loadResourceBundle(locale);

        return LanguageUtil.get(resourceBundle, "twitter");
    }
    ```

4.  Implement the `getPostURL` method to return the share URL. This method 
    constructs the share URL from a title and URL, and uses 
    [`URLCodec`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/URLCodec.html) 
    to encode the title in the URL: 

    ```java
    @Override
    public String getPostURL(String title, String url) {
        return String.format(
            "https://twitter.com/intent/tweet?text=%s&tw_p=tweetbutton&url=%s", 
            URLCodec.encodeURL(title), url);
    }
    ```

5.  Create a `ServletContext` reference: 

    ```java
    @Reference(
            target = "(osgi.web.symbolicname=com.liferay.social.bookmark.twitter)"
    )
    private ServletContext _servletContext;
    ```

6.  Implement the `render` method, which is called when the inline display style 
    is selected. Typically, this method renders a link to the share URL (e.g., a 
    share button), but you can use it for whatever you need. To keep a 
    consistent look and feel with the default social bookmarks, you can use a 
    [Clay icon](/docs/7-2/reference/-/knowledge_base/r/clay-icons). 

    This example gets a `RequestDispatcher` for the JSP that contains a Clay 
    icon (`page.jsp`), and then includes that JSP in the response: 

    ```java
    @Override
    public void render(
                    String target, String title, String url, HttpServletRequest request,
                    HttpServletResponse response)
            throws IOException, ServletException {

            RequestDispatcher requestDispatcher =
                    _servletContext.getRequestDispatcher("/page.jsp");

            requestDispatcher.include(request, response);
    }
    ```

## Creating Your JSP

The `page.jsp` file referenced in the above `SocialBookmark` implementation uses 
[a Clay link](/docs/7-2/reference/-/knowledge_base/r/clay-labels-and-links) 
(`clay:link`) to specify and style the Twitter icon included with Clay. Follow 
these steps to create a JSP for your own social bookmark: 

1.  Add the `clay` and `liferay-theme` taglib declarations: 

    ```markup
    <%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
    ```

2.  Import 
    [`GetterUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/GetterUtil.html) 
    and `SocialBookmark`: 

    ```markup
    <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
    <%@ page import="com.liferay.social.bookmarks.SocialBookmark" %>
    ```

3.  From the request, get a `SocialBookmark` instance and the social bookmark's 
    title and URL: 

    ```java
    <%
    SocialBookmark socialBookmark = (SocialBookmark)request.getAttribute("liferay-social-bookmarks:bookmark:socialBookmark");
    String title = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmark:title"));
    String url = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmark:url"));
    %>
    ```

    The title and URL are set via the `liferay-social-bookmarks` taglib when 
    [applying the social bookmark](/docs/7-2/frameworks/-/knowledge_base/f/applying-social-bookmarks). 

4.  Add the Clay link. See the `clay:link` 
    [documentation](https://clayui.com/docs/components/link.html) 
    for a full description of its attributes. 

    ```markup
    <clay:link
            buttonStyle="secondary"
            elementClasses="btn-outline-borderless btn-sm lfr-portal-tooltip"
            href="<%= socialBookmark.getPostURL(title, url) %>"
            icon="twitter"
            title="<%= socialBookmark.getName(locale) %>"
    />
    ```

    This example sets the following `clay:link` attributes: 

    `buttonStyle`: This example renders the 
    [button's type](/docs/7-2/reference/-/knowledge_base/r/clay-buttons) 
    as a secondary button. 

    `elementClasses`: The custom CSS to use for styling the button (optional). 

    `href`: The button's URL. You should specify this by calling your 
    `SocialBookmark` instance's `getPostURL` method. 

    `icon`: The button's icon. This example specifies the Twitter icon included 
    in Clay (`twitter`). 

    `title`: The button's title. This example uses the `SocialBookmark` 
    instance's `getName` method. 

To see a complete, real-world example of a social bookmark implementation, see 
[Liferay's Twitter social bookmark code](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/social/social-bookmark-twitter). 

## Related Topics

[Applying Social Bookmarks](/docs/7-2/frameworks/-/knowledge_base/f/applying-social-bookmarks)

[Using the Clay Taglib in Your Portlets](/docs/7-2/reference/-/knowledge_base/r/using-the-clay-taglib-in-your-portlets)
