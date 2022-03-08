---
header-id: elasticsearch
---

# Elasticsearch

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/using-search/installing-and-upgrading-a-search-engine/elasticsearch.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Elasticsearch is an open source, highly scalable, full-text search and
analytics engine.

By default, Elasticsearch runs as an embedded search engine, but it's only
supported in production as a separate server or cluster. This guide walks you
through the process of configuring Elasticsearch in remote mode.

![Figure 1: To see information about the currently connected search engine, go to _Control Panel_ &rarr; _Configuration_ &rarr; _Search_.](../../../images/search-admin-engineinfo.png)

| **Note:** Although Elasticsearch 6.5 is shipped as the embedded Elasticsearch
| server version, Elasticsearch 7 is the most recent supported Elasticsearch
| version for @product-ver@. Installing Elasticsearch 7 requires that you are
| running Service Pack 1/Fix Pack 2 or later (GA2 or later for CE users).
| Elasticsearch 6.8.x is also supported. See the [compatibility matrix for exact versions](https://help.liferay.com/hc/en-us/articles/360016511651).

If you'd rather use Solr, it's also supported. See the documentation on
[Installing Solr](/docs/7-2/deploy/-/knowledge_base/d/installing-solr) 
if you're interested.

To get up and running quickly with Elasticsearch as a remote server, refer to
the 
[Installing Elasticsearch article](/docs/7-2/deploy/-/knowledge_base/d/installing-elasticsearch).
Included there are basic instructions for installing and configuring
Elasticsearch in a single server environment. Additional articles include more
details and information on configuring and tuning Elasticsearch. 

These terms are useful to understand as you read this guide:

-  *Elasticsearch Home* refers to the root folder of your unzipped Elasticsearch
   installation (for example, `elasticsearch-7.4.1`). 

-  [*Liferay Home*](/docs/7-2/deploy/-/knowledge_base/d/liferay-home)
   refers to the root folder of your @product@ installation. It contains the
   `osgi`, `deploy`, `data`, and `license` folders, among others.

## Embedded vs. Remote Operation Mode

When you start @product@, this message is displayed in the log: 

    2019-04-29 09:59:02.276 WARN  [Elasticsearch initialization thread][EmbeddedElasticsearchConnection:288] Liferay is configured to use embedded Elasticsearch as its search engine. Do NOT use embedded Elasticsearch in production. Embedded Elasticsearch is useful for development and demonstration purposes. Refer to the documentation for details on the limitations of embedded Elasticsearch. Remote Elasticsearch connections can be configured in the Control Panel.

When you start @product@, Elasticsearch is already running in embedded mode.
@product@ runs an Elasticsearch node in the same JVM so it's easy to test-drive
with minimal configuration. Running both servers in the same process has
drawbacks:

-  Elasticsearch must use the same JVM options as @product@.
-  @product@ and Elasticsearch compete for the same system resources. 

| **Note:** While it's not a supported production configuration, installing
| Kibana to monitor the embedded Elasticsearch server is useful during
| development and testing. Just be aware that you must install the
| [OSS only Kibana build](https://www.elastic.co/downloads/kibana-oss).

You wouldn't run an embedded database like HSQL in production, and you shouldn't
run Elasticsearch in embedded mode in production either. Instead, run
Elasticsearch in *remote operation mode*, as a standalone server or cluster of
server nodes.

## Troubleshooting Elasticsearch Integration

Sometimes things don't go as planned. If you've set up @product@ with
Elasticsearch in remote mode, but @product@ can't connect to Elasticsearch, check
these things:

**Cluster name:** 
: The value of the `cluster.name` property in `elasticsearch.yml` must match
the `clusterName` property configured in the @product@ Elasticsearch connector.

**Transport address:** 
: The value of the `transportAddresses` property in the Elasticsearch connector
configuration must contain at least one valid host and port where an
Elasticsearch node is running. If @product@ is running in embedded mode, and
you start a standalone Elasticsearch node or cluster, it detects that port
`9300` is taken and switches to port `9301`. If you then set Liferay's
Elasticsearch connector to remote mode, it continues to look for Elasticsearch
at the default port (`9300`).

The following articles cover the Liferay Connector to Elasticsearch's
configuration options in more detail.

**Cluster Sniffing (Additional Configurations):**
: Elasticsearch clusters can have multiple node 
[types](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-node.html#modules-node).
[Cluster sniffing](https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html), 
enabled by default in the @product@ connector, looks for `data` nodes
configured in the `transportAddresses` property. If none are available, the
connector may throw a `NoNodeAvailableException` in the console log. If cluster
sniffing is to remain enabled, be sure that your configuration allows for at
least one `data` node's transport address to be "sniffable" at all times to
avoid this error.

To disable cluster sniffing, add `clientTransportSniff=false` to the `.config`
file or un-check the Client Transport Sniff property in System Settings.
