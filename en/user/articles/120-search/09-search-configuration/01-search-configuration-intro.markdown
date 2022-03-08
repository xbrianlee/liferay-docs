---
header-id: configuring-search
---

# Configuring Search

[TOC levels=1-4]

_Configuring Search_ could mean lots of different things: 

- System scoped search configuration
- Reindexing to make sure the search indexes are current with the database
- Tweaking the search widgets added to pages
- Creating new Search Pages
- Configuring the connectors that let @product@ and the search engine
  communicate

In fact, _Configuring Search_ means all those things. This is a high level
overview of what search behavior is configurable out of the box, and
importantly, _where_ to find search configuration options.

## System Scoped Search Configuration

System scoped search configurations are primarily found in [System
Settings](/docs/7-2/user/-/knowledge_base/u/system-settings). 

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.

2.  Click the *Search* category under the Platform section.

    Alternatively, search for *Search*.

![Figure 1: There are numerous system scoped entries for search in System Settings.](../../../images/search-category-system-settings.png)

These system scoped configurations are available in System Settings:

### Default Keyword Query

The Default Keyword Query entry contains one setting:

`disabledEntryClassNames`: The `DefaultKeywordQueryContributor` code
automatically adds `description`, `userName`, and `title` fields to the
keyword search query. Specify the entry class names
`DefaultKeywordQueryContributor` should ignore.

### Default Search Result Permission Filter

The Default Search Result Permission Filter entry allows configuration of
*post-filtering permission checking* (database permission checking that occurs
after the results are returned from the search index). Read
[here](/docs/7-2/user/-/knowledge_base/u/search-results-behavior#final-permissions-checking)
for more information on these settings:

- `permissionFilteredSearchResultAccurateCountThreshold` 

- `searchQueryResultWindowLimit`

### Index Status Manager

The Index Status Manager entry has one setting:

`indexReadOnly`: Suspends all indexing operations and writes to the
search engine. Searches return only the documents already indexed. This is
useful for speeding up large data imports, but it should be disabled and a full
re-index executed once the import is finished.

### Indexer Writer Helper

The Index Writer Helper entry contains only one valid entry. The second,
`indexReadOnly`, is deprecated and unused, so setting it has no effect. Use
`indexReadOnly` from the [Index Status Manager](#index-status-manager) instead.

`indexCommitImmediately`: When *true* (the default), each write request
forces the search engine to refresh the index reader, potentially flushing
transactions to disk. This may negatively impact search engine performance.
The default behavior is to commit immediately for index writing on
individual assets (e.g. add blog, update blog) but delay commits for bulk
index writing operations (e.g.  index all users, index all form entries)
until all entries have been sent to the search engine. Setting this to false
changes the behavior for individual index operations, and may cause
applications like Asset Publisher to exhibit a delayed response when showing
newly added content. See the 
[Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/current/near-real-time.html)
for more information.

### Index Registry

Configure the buffering of index requests:

`buffered`: Disable or configure the buffering of indexing requests. To stop
the buffering of index requests, choose *Disabled*. 

`bufferedExecutionMode`: Allows administrators to select a different
`IndexerRequestBufferExecutor`, used to execute a `IndexerRequest`. One
implementation of the executor is provided out of the box (_DEFAULT_). When a
developer creates and deploys a new `IndexerRequestBufferExecutor`
implementation, one of the properties they provided is a
`buffered.execution.mode` which makes the implementation selectable from System
Settings. 

`maximumBufferSize`: If buffering is enabled, set the Maximum Buffer Size so
that any additional indexing requests are executed immediately. 

`minimumBufferAvailabilityPercentage`: When the capacity of the buffer has
only the specified percent of space left, the existing requests in the
buffer are executed in one batch and removed from the buffer.

### Index Query Preprocessor

This entry has one repeatable property (use array syntax if you're defining via 
[OSGi configuration file](/docs/7-2/user/-/knowledge_base/u/creating-configuration-files)):

`fieldNamePatterns`: Fields with names matching the patterns set here are
treated as non-analyzed keyword fields. Instead of scored full text queries,
matching is performed by non-scored wildcard queries. This is a resource
intensive operation that degrades search engine performance as indexes grow
larger. For substring matching, relying on the 
[ngram tokenizer](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/analysis-ngram-tokenizer.html)
usually performs better.

### Reindex

This entry contains only one property:

`indexingBatchSizes`: Set the number of documents indexed
per batch for model types that support batch indexing. Defaults to 10000. For
models with large documents, decreasing this value may improve stability when
executing a full re-index.

### Engine Helper

This entry has one repeatable property (use array syntax if you're defining via 
[OSGi configuration file](/docs/7-2/user/-/knowledge_base/u/creating-configuration-files)):

`excludedEntryClassNames`: Exclude an asset type from being
searched in the catchall query constructed for the Search application. For
example, fields of the Organization asset must be indexed to be searchable
from the Users and Organizations application, but should not be searched in
the Search application. Thus, Organizations are added to
`excludedEntryClassNames`.

### Permission Checker

Configure *pre-filtering permission checking* (permission checking on the search
index) behavior. See
[here](/docs/7-2/user/-/knowledge_base/u/search-results-behavior#initial-permissions-checking)
for more information on these properties:

- `includeInheritedPermission`

- `permissionTermsLimit`

### Title Field Query Builder

Configure how search responds to matches on the Title field of a document.

**Exact Match boost:** Give an additional boost when searched keywords exactly
match the `title` field of a document.

**Maximum Expansions:** Limit the number of documents to return when matching
searched keywords to the `title` field as a phrase prefix. See Elasticsearch's
[Match Phrase Query
documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-match-query-phrase.html)
for more information.

### Elasticsearch 6

Configure the connection between @product@ and Elasticsearch 6. See
[here](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-liferay-elasticsearch-connector)
for more information on these properties:

- `clusterName`
- `operationMode`
- `indexNamePrefix`
- `numberOfIndexReplicas`
- `numberOfIndexShards`
- `bootstrapMlocakAll`
- `logExceptionsOnly`
- `retryOnConflict`
- `zenDiscoveryUnicastHostsPort`
- `networkHost`
- `networkBindHost`
- `networkPublishHost`
- `transportTcpPort`
- `transportAddresses`
- `clientTransportSniff`
- `clientTransprtIgnoreClusterName`
- `clientTransportPingTimeout`
- `clientTransportNodesSamplerInterval`
- `HttpEnabled`
- `HttpCrsEnabled`
- `HttpCorsAllowOrigin`
- `HttpCorsConfigurations`
- `additionalConfigurations`
- `additionalIndexConfigurations`
- `overrideTypeMappings`
- `synchronizedSearch`

### Search Web

This entry contains one property: `classicSearchPortletInFrontPage`: Revert the
default search experience from using the new Search Widgets to the classic
Search Portlet that was standard in past releases. See
[here](/docs/7-2/user/-/knowledge_base/u/configuring-search-pages#legacy-search-experience)
for more information.

### Search Administration

In *Control Panel* &rarr; *Configuration* &rarr; *Search* there are two
administrative UIs: Index Actions and Field Mappings

#### Index Actions

In Index Actions, re-index one of these:

    - All indexable assets
    - An individual indexable asset
    - All spell check indexes

#### Field Mappings

The Field Mappings tab shows you all field mappings that are effective in the
system, by index. Currently, you can view the mappings, copy them, zoom in or
out, and view them with a dark theme. Look for added functionality to this UI in
future versions.

### Portal Properties

Portal properties are system scoped configurations as well. The 
[Lucene Search](https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#Lucene%20Search)
portal properties configure low level search behavior. Review the properties and
their descriptions and determine if they apply to your search requirements.

## Site Scoped Search Configuration

Search isn't configurable at the Site Scope by the strict definition of [Site
Scoped Configuration](/docs/7-2/user/-/knowledge_base/u/setting-up#configuration-scope).
However, [Search
Pages](/docs/7-2/user/-/knowledge_base/u/configuring-search-pages) influence
site-specific search behavior. Commonly, Search Pages contain search widgets
configured to search for all content within a particular Site.

In addition, the Header Search (the Search Bar embedded in every Site page by
default), whether populated by the new Search Bar widget or the legacy Search
portlet, is Site scoped. Only one instance of the Header Search application
exists per Site, and configuring it in one page context configures it for the
entire Site. 

Because of the modularity of Search, there are some important configuration
nuances to be aware of when using the new Search widgets:

- If the Header Search uses the Search Bar widget, its configuration
  always requires a _destination page_ to be set, where Users are redirected to
  complete their search activity, interacting with the other Search widgets
  (Results, Facets, Suggestions etc.). [Search destination
  pages](/docs/7-2/user/-/knowledge_base/u/configuring-search-pages) are
  ordinary pages holding the Search widgets. You can have as many pages with
  Search widgets across the Site as you want.

- Unlike the legacy Search portlet, the new Search Bar widget is instanceable,
  so one page can contain multiple Search Bar widgets configured differently.
  All Search Bar instances must point to a Search Page to be effective.

- **Important**: if the destination Search Page has a Search Bar widget
  instance besides the embedded Header Search, the configurations of the
  Header Search take precedence over the page's widget instance.

  Conversely, searching from a Search Bar widget instance on other pages honors
  their configurations, even if they differ from the Header Search
  configuration. 

See the documentation on [configuring of a Search
Bar](/docs/7-2/user/-/knowledge_base/u/searching-for-assets#configuring-the-search-bar)
for more information.

## Widget Scoped Search Configuration

Several search widgets are available, and each one has its own configuration
options:

**Search Results**
:  Configure how search results are displayed. Read 
[here](/docs/7-2/user/-/knowledge_base/u/search-results) for more
information.

**Search Bar**
: Configure the behavior of how search keywords are processed. See
[here](/docs/7-2/user/-/knowledge_base/u/searching-for-assets#configuring-the-search-bar) 
for more information.

**Search Facets**
: Configure each facet's behavior and URL parameters. See
[here](/docs/7-2/user/-/knowledge_base/u/facets) for more information.

**Search Options**
: This is a special case, where configuring this widget defines page scoped
behavior. Add the Search Option widget to a page and define two booleans for
the Search Page: 

- Allow Empty Searches: By default, failure to enter a keyword returns no
  results. Enabling this ensure that _all_ results are returned when no
  keyword is entered in the Search Bar.

- Basic Facet Selection: By default, facet counts are recalculated after each
  facet selection. Enable this to turn off facet recounting.

**Search Suggestions**
: Suggest better queries and spell check queries. See
[here](/docs/7-2/user/-/knowledge_base/u/searching-for-assets#search-suggestions) 
for more information.

**Search Insights**
: Add this to the Search Page to inspect the full query string that's
constructed by the back-end search code when the User enters a keyword. Only
useful for testing and development.

**Custom Filter**
: Add a widget to the page for each of the filters you'd like applied to the
search results. Let search page users see and manipulate the filters or make
them invisible and/or immutable.

**Sort**
: Let Users reorder the search results based on the value of certain `keyword`
fields in the index. For example, show results in alphabetic order of the Title
field. The default order is determined by the search engine's _Relevance_
calculation.

