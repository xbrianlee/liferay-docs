---
header-id: adding-a-search-bar-to-the-guestbook-portlet
---

# Adding a Search Bar to the Guestbook Portlet

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Updating Your UI for Search</p><p>Step 1 of 2</p>
</div>

Create the search bar UI for the Guestbook portlet:

1.  In `guestbook-web`, open the file 
    `src/main/resources/META-INF/resources/guestbook/view.jsp`. Add a 
    render URL near the top of the file, just after the scriptlet that gets the 
    `guestbookId` from the request:

    ```markup
    <portlet:renderURL var="searchURL">
        <portlet:param name="mvcPath" 
        value="/guestbook/view_search.jsp" />
    </portlet:renderURL>
    ```

    The render URL points to `/guestbook/view_search.jsp` (created in
    the next step). You construct the URL first to specify what
    happens when the user submits a search query. 

2.  Right after the render URL, create an AUI form that adds an input field for
    search keywords and a *Submit* button that executes the form action, which
    is mapped to the `searchURL`.

    ```markup
    <aui:form action="${searchURL}" name="fm">

        <div class="row">
            <div class="col-md-8">
                <aui:input inlineLabel="left" label="" name="keywords" placeholder="search-entries" size="256" />
            </div>

            <div class="col-md-4">
                <aui:button type="submit" value="search" />
            </div>
        </div>

     </aui:form>
     ```

The body of the search form consists of a `<div>` with one row containing
two fields: an input field, named `keywords` and a _Submit_ button. Its
`name="keywords"` attribute specifies the name of the URL parameter that
contains the search query. The `<aui:button>` tag defines the search button.
The `type="submit"` attribute specifies that when the button is clicked (or
the *Enter* key is pressed), the AUI form is submitted. The `value="search"`
attribute specifies the name that appears on the button. 

That's all there is to the search form! When the form is submitted, the
`mvcPath` parameter pointing to the `view_search.jsp` is included in the URL
along with the `keywords` parameter containing the search query. Next you'll
create the `view_search.jsp` file to display the search results. 
