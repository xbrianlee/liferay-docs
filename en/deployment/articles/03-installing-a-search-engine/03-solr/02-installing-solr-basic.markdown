---
header-id: installing-solr-basic-installation
---

# Installing Solr: Basic Installation

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/using-search/installing-and-upgrading-a-search-engine/solr/installing-solr.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

There are two ways to install the Liferay Connector to Solr 7:

1. Navigate to 
   [Liferay Marketplace](https://web.liferay.com/marketplace/)
   and download the app that corresponds to your portal.

<!-- Update links when available
   **Liferay Portal CE:** [Liferay CE Connector to Solr 7](https://web.liferay.com/marketplace/-/mp/application/118014614) 

   **Liferay DXP:** [Liferay Connector to Solr 7](https://web.liferay.com/marketplace/-/mp/application/117931595)
-->

   Once the app LPKG is downloaded, copy it to `Liferay_Home/osgi/marketplace`.

2. In your running portal instance, navigate to *Control Panel* &rarr; *Apps*
   &rarr; *Store*. Sign in using your credentials, search for Solr Search
   Engine, and purchase (it's free) the Liferay Connector to Solr 7 entry.

As you proceed, remember these terms: 

*Solr Home*: The center of the Solr system (pun intended). This directory is
`solr-[version]/server/solr`.

*Liferay Home*: The root folder of your @product@ installation. It contains
the `osgi`, `deploy`, `data`, and `license` folders, among others.

There are two installation steps:

1.  Installing and configuring Solr 7.

2.  Installing and configuring the Solr 7 connector for @product@.

Before configuring @product@ for Solr, install and set up Solr.

## Installing and Configuring Solr 7

To install and properly configure Solr for @product@:

1.  Download 
    [Solr](http://archive.apache.org/dist/lucene/solr/7.5.0/) 
    and unzip it.

2.  Navigate to `solr-[version]/server/solr`. This is Solr Home.

3.  Create a new folder called `liferay`.

4.  In the `liferay` folder, create two new folders: `conf` and `data`.

5.  Copy the contents of `Solr_Home/configsets/_default/conf`
    to `Solr_Home/liferay/conf`.

6.  Open the Liferay Connector to Solr 7's LPKG file with an archive manager.

    Open the `com.liferay.portal.search.solr7.impl.jar` file, and extract 

        META-INF/resources/solrconfig.xml

    and

        META-INF/resources/schema.xml

    to

        Solr_Home/liferay/conf

    This replaces the current `solrconfig.xml` and `schema.xml` files with ones
    that tell Solr how to index data coming from @product@.

7.  Create a `core.properties` file in `Solr_Home/liferay` and add this
    configuration:

    ```properties
    config=solrconfig.xml
    dataDir=data
    name=liferay
    schema=schema.xml
    ```

8.  Checkpoint: your `Solr_Home/liferay` folder now has this structure:

    ```sh
    liferay
    ├── conf
    │   ├── lang
    │   │   ├── contractions_ca.txt
    │   │   ├── ....txt
    │   ├── managed-schema
    │   ├── params.json
    │   ├── protwords.txt
    │   ├── schema.xml
    │   ├── solrconfig.xml
    │   ├── stopwords.txt
    │   └── synonyms.txt
    ├── core.properties
    └── data
    ```

8.  Start the Solr server by entering

        ./bin/solr start -f

    from the top-level folder of your Solr installation (`solr-[version]`).

9.  The Solr server listens on port `8983` by default. Navigate to
    `http://localhost:8983/solr/#/~cores` (assuming you're testing locally with
    `localhost` as your host), and confirm that the `liferay` core is available.

Solr is now installed. Next install and configure the Solr connector.

## Installing and Configuring the Liferay Solr Adapter

Since Elasticsearch is the default search engine, the Elasticsearch connector is
already installed and running. You must stop it before configuring the Solr
connector.

### Stopping the Elasticsearch Connector

Stop the Elasticsearch connector bundle using the App Manager, the Felix Gogo
shell, or the bundle blacklist. If you're a Liferay DXP customer, use the
blacklist feature as described below. The App Manager and Gogo shell rely on the
`osgi/state` folder to "remember" the state of the bundle. If you delete this
folder (recommended during patching) the Elasticsearch connector is reinstalled
and started automatically. 

Navigate to Control Panel &rarr; Apps &rarr; App Manager.

Once in the App Manager, search for *elasticsearch*. Find the Liferay 
Connector to Elasticsearch 6 module and click the Actions
((![Actions](../../../images/icon-actions.png))) button. Choose the Deactivate option.
This leaves the bundle installed, but stops it in the OSGi runtime.

Alternatively, use the 
[Felix Gogo shell](/developer/tutorials/-/knowledge_base/7-2/using-the-felix-gogo-shell) 
to stop the Elasticsearch connector. Enter

    lb elasticsearch

You'll see two active bundles for the Liferay Connector to Elasticsearch 6:
an API and an IMPL bundle. 

```sh
ID|State      |Level|Name
476|Active     |   10|Liferay (CE) Connector to Elasticsearch 6 - API (3.0.0)
478|Active     |   10|Liferay Portal Search Elasticsearch 6 API (3.0.4)
480|Active     |   10|Liferay Portal Search Elasticsearch 6 SPI (3.2.1)
706|Active     |   10|Liferay (CE) Connector to Elasticsearch 6 - Impl (3.0.0)
707|Active     |   10|Liferay Portal Search Elasticsearch 6 Implementation (3.0.15)

```

Stop the API bundle by entering 

    stop [bundle ID]

In the example above, the `[bundle ID]` is `476`. 

**Liferay DXP:** DXP customers should
[blacklist](/docs/7-2/user/-/knowledge_base/u/blacklisting-osgi-bundles-and-components)
the Elasticsearch, Shield, and Marvel plugins.

1.  Create a 

        com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config

    file with these contents:

    ```properties
    blacklistBundleSymbolicNames=["com.liferay.portal.search.elasticsearch6.spi","com.liferay.portal.search.elasticsearch6.api","com.liferay.portal.search.elasticsearch6.impl","Liferay Enterprise Search Monitoring ","Liferay Enterprise Search Security "]
    ```

    If the LES Security and Monitoring LPKG files are installed, you must blacklist these too.

2.  Place the file in `Liferay Home/osgi/configs`.

### Install and Configure the Solr Connector

Now you're ready to install the connector:

1.  Start @product@, then deploy the Solr connector by copying the LPKG you
    downloaded to `Liferay_Home/deploy`.

    You'll see a `STARTED` message in your @product@ log once the Solr connector is
    installed. Here's what the log message looks like:

    ```sh
    2018-11-06 19:59:49.396 INFO  [pipe-start 943 944][BundleStartStopLogger:39] STARTED com.liferay.portal.search.solr7.api_2.0.5 [943]
    2018-11-06 19:59:49.490 INFO  [pipe-start 943 944][BundleStartStopLogger:39] STARTED com.liferay.portal.search.solr7.impl_2.0.11 [944]
    ```

2.  To re-index against Solr, navigate to *Control Panel* &rarr; *Configuration*
    &rarr; *Search*, and click *Execute* next to the *Reindex all
    search indexes* option.

   ![Figure 1: Once the Solr connector is installed, you can re-index your @product@ data against your Solr server.](../../../images/solr-reindex.png)

In production deployments, specify your edits to the Solr connector's default
configurations using a configuration file deployed to the `Liferay_Home/osgi/configs`
folder. Name the file 

    com.liferay.portal.search.solr7.configuration.SolrConfiguration.config

During testing and development, use the Solr 7 System Settings entry Control
Panel &rarr; Configuration &rarr; System Settings for editing the default
configurations.

![Figure 2: You can configure Solr from @product@'s System Settings application. This is most useful during development and testing.](../../../images/solr-system-settings.png)

The next article covers clustering Solr with SolrCloud.
