---
header-id: working-with-collections-of-data
---

# Working with Collections of Data

[TOC levels=1-4]

Collection resources are common in @product@ web APIs. If you followed along
with the previous examples that sent requests to the portal's `blog-postings`
resource URL, you've already seen collections in action: the `BlogPosting`
resource is a collection. 

Here, you'll learn more detailed information about working with collection
resources. But first, you should learn about collection pagination. 

## Pagination

A small collection can be transmitted in a single response without difficulty.
Transmitting a large collection all at once, however, can consume too much
bandwidth, time, and memory. It can also overwhelm the user with too much data. 

It's therefore best to get and display the elements of a large collection in
discrete chunks, or pages. 

@product@'s GraphQL APIs return paginated collections by default. The following
attributes in the responses also contain the information needed to navigate
between those pages: 

`totalCount`: The total number of this resource's items. 

`pageSize`: The number of this resource's items to be included in this response. 

`page`: The current page's number. 

`items`: The collection elements present on this page. Each element also
contains the data of the object it represents, so there's no need for additional
requests for individual elements. 

`id`: Each item's identifier. You can use this, if necessary, to get more
information on a specific item. 

The attributes `page` and `pageSize` allow client applications to navigate
through the results. For example, such a client could send a request for
a specific page. 

This example gets the second page (`page:2`) of blog postings that exist on the
content set with the ID `42345`: 

```
query {
  contentSetContentSetElements(contentSetId: 42345, page: 2) {
    items {
      id
      title
      content {
        ... on BlogPosting {
          headline
        }
      }
    }
    page
    pageSize
    totalCount
  }
}
```

