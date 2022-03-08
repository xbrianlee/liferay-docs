---
header-id: enabling-search-and-indexing-for-guestbooks
---

# Enabling Search and Indexing for Guestbooks

[TOC levels=1-4]

In this section, you'll create the classes that control these aspects of the
search functionality:

- Registration:

    - `GuestbookSearchRegistrar` registers the search services to the search
    framework for the Guestbook entity.

- Indexing:

    - `GuestbookModelDocumentContributor` controls which Guestbook fields are
      indexed in the search engine.

    - `GuestbookModelIndexerWriterContributor` configures the re-indexing and
      batch re-indexing behavior for Guestbooks.

- Querying:

    - `GuestbookKeywordQueryContributor` contributes clauses to the ongoing
      search query.

    - `GuestbookModelPreFilterContributor` controls how search results are filtered
      before they're returned from the search engine.

- Generating Result Summaries:

    - `GuestbookModelSummaryContributor` constructs the result summary for
      Guestbooks, including specifying which fields to use.

After creating the search classes, you'll modify the service layer to update the
search index when a guestbook is persisted. Specifically,
`GuestbookLocalServiceImpl`'s `addGuestbook`, `updateGuestbook`, and
`deleteGuestbook` methods are updated to invoke the guestbook indexer.

In prior versions of @product@, search and indexing was accomplished with one
`*Indexer` class that extended `BaseIndexer`. In @product-ver@ is a new pattern
that relies on 
[composition instead of inheritance](https://stackoverflow.com/questions/2399544/difference-between-inheritance-and-composition).
If you want to use the old approach, feel free to extend `BaseIndexer`. It's
still supported. 

Since there's no reason to search for guestbooks in the UI, only back-end work
is necessary. 

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/understanding-search-and-indexing">Let's Go!<span class="icon-circle-arrow-right"></span></a>
