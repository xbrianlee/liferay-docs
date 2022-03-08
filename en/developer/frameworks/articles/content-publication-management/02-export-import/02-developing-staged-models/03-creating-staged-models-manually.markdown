---
header-id: creating-staged-models-manually
---

# Creating Staged Models Manually

[TOC levels=1-4]

There are times when using
[Service Builder to generate your staged models](/docs/7-2/frameworks/-/knowledge_base/f/generating-staged-models-using-service-builder)
is not practical. In these cases, you should create your staged models manually.
Make sure to read the
[Adapting Your Business Logic to Build Staged Models](/docs/7-2/frameworks/-/knowledge_base/f/developing-staged-models#adapting-your-business-logic-to-build-staged-models)
section to determine if creating staged models manually is beneficial for your
use case.

In this tutorial, you'll explore how the Asset Link framework (a @product@
framework used for
[relating assets](/docs/7-2/user/-/knowledge_base/u/defining-content-relationships))
manually creates staged models. This framework is separate from Export/Import
and is referenced solely as an example for how to leverage the
[ModelAdapterBuilder](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html)
framework, which lets you adapt your model classes to staged models.

Follow the steps below to leverage the Model Adapter Builder in your app.

1.  Create a new interface that extends one of the
    [staged model interfaces](/docs/7-2/frameworks/-/knowledge_base/f/developing-staged-models#staged-model-interfaces)
    and your model specific interface. For example,

    ```java
    public interface StagedAssetLink extends AssetLink, StagedModel {

    }
    ```

    | **Note:** Staged model interfaces typically follow the `Staged[Entity]`
    | naming convention. The Asset Link framework uses a generic entity called
    | `AssetLink`.

2.  Define methods required for your model to qualify as a staged model. For
    asset links, methods for retrieving entry UUIDs (among others) are defined:

    ```java
    public String getEntry1Uuid();

    public String getEntry2Uuid();
    ```

    | **Note:** Asset links do not provide UUIDs by default; however, they still
    | need to be tracked in the Staging and Export/Import frameworks. Therefore,
    | they require staged models. Since they don't provide a UUID, Service
    | Builder cannot generate staged models for asset links. The Asset Link
    | framework has to create staged models differently using the Model Adapter
    | Builder.

    These will be implemented by a new implementation class later. 

2.  Create an implementation class that implements your new `Staged[Entity]`.
    For example, the Asset Link framework does this:

    ```java
    public class StagedAssetLinkImpl implements StagedAssetLink {

    }
    ```

    This class provides necessary logic for your entity model to be recognized
    as a staged model. Below is a subset of logic in the example
    `StagedAssetLinkImpl` class used to populate UUIDs for asset link entries:

    ```java
    public StagedAssetLinkImpl(AssetLink assetLink) {
         _assetLink = assetLink;

        ...

         populateUuid();
    }

    @Override
    public String getEntry1Uuid() {
        if (Validator.isNotNull(_entry1Uuid)) {
            return _entry1Uuid;
        }

        populateEntry1Attributes();

        return _entry1Uuid;
    }

    @Override
    public String getEntry2Uuid() {
        if (Validator.isNotNull(_entry2Uuid)) {
                return _entry2Uuid;
        }

        populateEntry2Attributes();

        return _entry2Uuid;
    }

    protected void populateEntry1Attributes() {

        ...

        AssetEntry entry1 = AssetEntryLocalServiceUtil.fetchAssetEntry(
            _assetLink.getEntryId1());

        ...

        _entry1Uuid = entry1.getClassUuid();
    }

    protected void populateEntry2Attributes() {

        ...

        AssetEntry entry2 = AssetEntryLocalServiceUtil.fetchAssetEntry(
            _assetLink.getEntryId2());

        ...

        _entry2Uuid = entry2.getClassUuid();
    }

    protected void populateUuid() {

        ...

        String entry1Uuid = getEntry1Uuid();
        String entry2Uuid = getEntry2Uuid();

        ...

        _uuid = entry1Uuid + StringPool.POUND + entry2Uuid;
            }
    }

    private AssetLink _assetLink;
    private String _entry1Uuid;
    private String _entry2Uuid;
    private String _uuid;
    ```

    This logic retrieves asset link entries and populates UUIDs for them usable
    by the Staging and Export/Import frameworks. With the newly generated UUIDs,
    asset link model classes can be converted to staged models.

3.  Create a Model Adapter Builder class and implement the
    [ModelAdapterBuilder](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html)
    interface. You should define the entity type and your Staged Model Adapter
    class when implementing the interface:

    ```java
    public class StagedAssetLinkModelAdapterBuilder
        implements ModelAdapterBuilder<AssetLink, StagedAssetLink> {

        @Override
        public StagedAssetLink build(AssetLink assetLink) {
            return new StagedAssetLinkImpl(assetLink);
        }

    }
    ```

    For the `StagedAssetLinkModelAdapterBuilder`, the entity type is `AssetLink`
    and the Staged Model Adapter is `StagedAssetLink`. Your app should follow a
    similar design. The Model Adapter Builder outputs a new instance of the
    `Staged[Entity]Impl` object.

4.  Now you need to adapt your existing business logic to call the provided
    APIs. You can call the
    [ModelAdapterUtil](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/ModelAdapterUtil.html)
    class to create an instance of your Staged Model Adapter:

    ```java
    StagedAssetLink stagedAssetLink = ModelAdapterUtil.adapt(
        assetLink, AssetLink.class, StagedAssetLink.class);
    ```

    Once you've created
    [Staged Model Data Handlers](/docs/7-2/frameworks/-/knowledge_base/f/creating-staged-model-data-handlers),
    you can begin exporting/importing your now Staging-compatible entities:

    ```java
    StagedModelDataHandlerUtil.exportStagedModel(
        portletDataContext, stagedAssetLink);
    ```

Awesome! You've successfully adapted your business logic to build staged models!
