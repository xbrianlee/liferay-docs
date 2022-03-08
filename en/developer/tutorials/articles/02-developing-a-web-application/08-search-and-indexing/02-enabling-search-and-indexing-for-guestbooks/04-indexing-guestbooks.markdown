---
header-id: indexing-guestbooks
---

# Indexing Guestbooks

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Enabling Search and Indexing for Guestbooks</p><p>Step 3 of 6</p>
</div>

To control how Guestbook objects are translated into search engine documents,
create two classes in the new search package:

1.  Implement a `ModelDocumentContributor` that "contributes" fields to a search
    document indexed by the search engine. The main searchable field for
    guestbooks is the guestbook name. 

2.  `ModelIndexerWriterContributor` configures the batch indexing behavior for
    Guestbooks. This code is executed when Guestbooks are re-indexed from the
    Search administration section of the Control Panel.

## Implementing `ModelDocumentContributor`

Create `GuestbookModelDocumentContributor` and populate it with this:

```java
@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
        service = ModelDocumentContributor.class
)
public class GuestbookModelDocumentContributor
    implements ModelDocumentContributor<Guestbook> {

    @Override
    public void contribute(Document document, Guestbook guestbook) {
        try {
            document.addDate(Field.MODIFIED_DATE, guestbook.getModifiedDate());

            Locale defaultLocale = PortalUtil.getSiteDefaultLocale(
    guestbook.getGroupId());

            String localizedTitle = LocalizationUtil.getLocalizedName(
    Field.TITLE, defaultLocale.toString());

            document.addText(localizedTitle, guestbook.getName());
        } catch (PortalException pe) {
            if (_log.isWarnEnabled()) {
                _log.warn(
    "Unable to index guestbook " + guestbook.getGuestbookId(), pe);
            }
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(
    GuestbookModelDocumentContributor.class);

}
```

Because @product@ supports localization, you should too. The above code gets 
the default locale from the Site by passing the `Guestbook`'s group ID to 
the `getSiteDefaultLocale` method, then using it to get the localized name
of the Guestbook's title field. The retrieved Site locale is appended to the
field (e.g., `title_en_US`), so the field gets passed to the search engine
and goes through the right analysis and
[tokenization](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/analysis-tokenizers.html). 

Use Ctrl-Shift-O to add these imports, and then save the file: 

- `com.liferay.portal.kernel.search.Field` 
- `com.liferay.portal.kernel.search.Document` 

## Implementing `ModelIndexerWriterContributor`

Create `GuestbookModelIndexerWriterContributor` and populate it with this:

```java
@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
        service = ModelIndexerWriterContributor.class
)
public class GuestbookModelIndexerWriterContributor
    implements ModelIndexerWriterContributor<Guestbook> {

    @Override
    public void customize(
        BatchIndexingActionable batchIndexingActionable,
        ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod((Guestbook guestbook) -> {
            Document document = modelIndexerWriterDocumentHelper.getDocument(
    guestbook);

            batchIndexingActionable.addDocuments(document);
        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
    guestbookLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Guestbook guestbook) {
        return guestbook.getCompanyId();
    }

    @Override
    public void modelIndexed(Guestbook guestbook) {
        guestbookEntryBatchReindexer.reindex(
    guestbook.getGuestbookId(), guestbook.getCompanyId());
    }

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
    dynamicQueryBatchIndexingActionableFactory;

    @Reference
    protected GuestbookEntryBatchReindexer guestbookEntryBatchReindexer;

    @Reference
    protected GuestbookLocalService guestbookLocalService;

}
```

First look at the `customize` method. Configure the batch indexing behavior for
the entity's documents by calling `BatchIndexingActionable` methods. This code
uses the Guestbook's actionable dynamic query helper method to retrieve all
Guestbooks in the virtual instance (identified by the Company ID). Service
Builder generated this query method for you when you built the services. Each
Guestbook's document is then retrieved and added to a collection.

When you write the indexing classes for Guestbook entries, you'll add the
Guestbook title to the `GuestbookEntry` document. Thus, you must provide a way to update
the indexed `GuestbookEntry` documents if a Guestbook title is changed.  The
`modelIndexed` method calls a `reindex` method from an interface that will be
created later for `GuestbookEntry`s. For now, ignore the error in the
`modelIndexed` method. 

Use Ctrl-Shift-O to add this import, and save the file: 

- `com.liferay.portal.kernel.search.Document`

Once the re-indexing behavior is in place, you can move on to controlling how
Guestbook documents are queried from the search engine.
