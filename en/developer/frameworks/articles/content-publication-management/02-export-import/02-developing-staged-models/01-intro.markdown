---
header-id: developing-staged-models
---

# Developing Staged Models

[TOC levels=1-4]

To track an entity of an application with the Export/Import framework, you must
implement the
[`StagedModel`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface in the app's model classes. It provides the behavior contract for 
entities during the Staging process. For example, the Bookmarks application
manages
[`BookmarksEntry`](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksEntry.html)s
and
[`BookmarksFolder`](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksFolder.html)s,
and both implement the `StagedModel` interface. Once you've configured your
staged models, you can create staged model data handlers, which supply
information about a staged model (entity) and its referenced content to the
Export/Import and Staging frameworks. See the
[Developing Data Handlers](/docs/7-2/frameworks/-/knowledge_base/f/developing-data-handlers)
section for more information.

There are two ways to create staged models for your application's entities:

- [Using Service Builder to generate the required Staging implementations](/docs/7-2/frameworks/-/knowledge_base/f/generating-staged-models-using-service-builder)
- [Implementing the required Staging interfaces manually](/docs/7-2/frameworks/-/knowledge_base/f/creating-staged-models-manually)

You can follow step-by-step procedures for creating staged models for your
entities by visiting their respective articles.

Continue on to learn more about Staged Models!

## Staged Model Interfaces

The
[`StagedModel`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface must be implemented by your app's model classes, but this is typically
done through inheritance by implementing one of the interfaces that extend the
base interface:

- [`StagedAuditedModel`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedAuditedModel.html)
- [`StagedGroupedModel`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedGroupedModel.html)

You must implement these when you want to use certain features of the Staging
framework like automatic group mapping or entity level *Last Publish Date*
handling. So how do you choose which is right for you?

The `StagedAuditedModel` interface provides all the audit fields to the model
that implements it. You can check the
[`AuditedModel`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/AuditedModel.html)
interface for the specific audit fields provided. The `StagedAuditedModel`
interface is for models that function independently from the group concept
(sometimes referred to as company models). If your model is a group model, you
should not implement the `StagedAuditedModel` interface.

The `StagedGroupedModel` interface must be implemented for group models. For
example, if your application requires the `groupId` column, your model is a
group model. If your model satisfies both the `StagedGroupModel` and
`StagedAuditedModel` requirements, it should implement `StagedGroupedModel`.
Your model should only implement the `StagedAuditedModel` if it doesn't fulfill
the grouped model needs, but does fulfill the audited model needs. If your model
does not fulfill either the `StagedAuditedModel` or `StagedGroupedModel`
requirements, you should implement the base `StagedModel` interface.

As an example for extending your model class, you can visit the
[`BookmarksEntryModel`](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksEntryModel.html)
class, which extends the `StagedGroupedModel` interface; this is done because
bookmark entries are group models.

```java
public interface BookmarksEntryModel extends BaseModel<BookmarksEntry>,
    ShardedModel, StagedGroupedModel, TrashedModel, WorkflowedModel {
```

Those are the differences between staged model interfaces. 

## Staged Model Attributes

One of the most important attributes used by the Staging framework is the UUID
(Universally Unique Identifier). This attribute must be set to `true` in your
`service.xml` file for Service Builder to recognize your model as an eligible
staged model. The UUID differentiates entities between environments. Because the
UUID always remains the same, it's unique across multiple systems. Why is this
so important?

Suppose you're using
[remote staging](/docs/7-2/user/-/knowledge_base/u/enabling-remote-live-staging)
and you create a new entity on your local staging site and publish it to your
remote live site. When you go back to modify the entity on your
local site and want to publish those changes, the UUID shows that the local and
remote entities are the same. The Staging framework can thus recognize the
original entity on the remote site and update it. The UUID provides that. 

In addition to the UUID, there are several columns that must be defined in your
`service.xml` file for Service Builder to define your model as a staged model:

- `companyId`
- `createDate`
- `modifiedDate`

If you want a staged grouped model, also include the `groupId` and
`lastPublishDate` columns. If you want a staged audited model, include the
`userId` and `userName` columns.

## Adapting Your Business Logic to Build Staged Models

What if you don't want to extend your model with special attributes your
business logic doesn't need (removing the ability to leverage Service Builder's
auto-generation of staged models)? In this case, you should adapt your
business logic to meet the Staging framework's needs. Liferay provides the
[`ModelAdapterBuilder`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html)
framework, which lets you adapt your model classes to staged models.

As an example, assume you have a completed app and you want it to work with
Staging. Your app, however, does not require a UUID for any of its entities, and
therefore, does not provide them. Instead of configuring your app to handle
UUIDs just for the sake of generating staged models, you can leverage the Model
Adapter Builder to build your staged models.

Another example for building staged models from scratch is for applications that
use REST services instead of the database to access their attributes. Since this
kind of app pulls its attributes from a remote system, it is more convenient to
build your staged models yourself instead of relying on Service Builder, which
is database driven.

To adapt your model classes to staged models, follow the steps outlined below:

1.  Create a `Staged[Entity]` interface that extends the model-specific
    interface (e.g., `[Entity]`) and the appropriate staged model interface
    (e.g., `StagedModel`). This class serves as the Staged Model Adapter.

2.  Create a `Staged[Entity]Impl` class that implements the `Staged[Entity]`
    interface and provides necessary logic for your entity model to be
    recognized as a staged model.

3.  Create a `Staged[Entity]ModelAdapterBuilder` class that implements
    `ModelAdapterBuilder<[Entity], Staged[Entity]>`. This class adapts the
    original model to the newly created Staged Model Adapter.

4.  Adapt your existing model and call one of the provided APIs to export or
    import the entity automatically.

![Figure 1: The Staged Model Adapter class extends your entity and staged model interfaces.](../../../../images/staged-model-adapter-diagram.png)

![Figure 2: The Model Adapter Builder gets an instance of the model and outputs a staged model.](../../../../images/model-adapter-builder-diagram.png)

To step through the process for leveraging the Model Adapter Builder for an
existing app, see 
[Creating Staged Models Manually](/docs/7-2/frameworks/-/knowledge_base/f/creating-staged-models-manually).
