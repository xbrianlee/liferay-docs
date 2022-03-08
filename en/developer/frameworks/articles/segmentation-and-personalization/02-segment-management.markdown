---
header-id: segment-management
---

# Segment Management

[TOC levels=1-4]

There are a broad array of uses for Segments and ways that you can integrate 
them with your application. You'll learn more about how to manage segments next.

## Defining a Segment

This snippet defines a segment by retrieving `@Reference` objects from
`SegmentsCriteriaContributor` and instantiating a new `Criteria` object. It then
adds user criteria using the `segmentsEntryService`:

- the user's `jobTitle` is `Developer` **AND**
- the user belongs to an Organization with a name that contains `America`

```java
private void addSegmentWithCriteria() {
    Criteria criteria = new Criteria();

    _userSegmentsCriteriaContributor.contribute(
        criteria, "(jobTitle eq 'Developer')", Criteria.Conjunction.AND);
    _organizationCriteriaContributor.contribute(
        criteria, "contains(name,'America')", Criteria.Conjunction.OR);

    segmentsEntryService.addSegmentsEntry(
        "segment-key", nameMap, descriptionMap, true, CriteriaSerializer.serialize(criteria),
				    User.class.getName(), serviceContext);
}

@Reference(target = "(segments.criteria.contributor.key=organization)")
private SegmentsCriteriaContributor _organizationSegmentsCriteriaContributor;

@Reference(target = "(segments.criteria.contributor.key=user)")
private SegmentsCriteriaContributor _userSegmentsCriteriaContributor;
```

### Manual Segment Member Assignments

To define manual user-segment member assignments with the
`SegmentsEntryRelService`, use a snippet like this:

```java
segmentsEntryRelService.addSegmentsEntryRel(
    segmentsEntryId, _portal.getClassNameId(User.class), userId, serviceContext)
```

This assigns a user identified by a `userId` to a segment identified by a
`segmentsEntryId`:

## Retrieving Segments

Segments and Segment Members can be retrieved programmatically. The code snippet
below retrieves an ordered range of active segments for the `User`, within a
site identified by a `groupId`.

```java
List<SegmentsEntry> segmentsEntries = segmentsEntryService.getSegmentsEntries(groupId, true, User.class.getName(), 0, 10, orderByComparator);
```

## Retrieving segment members

The local API to query computed segment-member associations is available in the
`com.liferay.segments.api module`. The `SegmentsEntryProvider` service provides
methods to obtain the entities associated to a segment, and the segments
associated to an entity.

This snippet retrieves a range of primary keys of the entities associated to a
segment identified by a `segmentsEntryId`:

```java
long[] segmentsEntryClassPKs = segmentsEntryProvider.getSegmentsEntryClassPKs(segmentsEntryId, 0, 10);
```

To obtain the total count of entities associated to a segment, use the 
`getSegmentsEntryClassPKsCount` method, as shown in the following snippet:

```java
int segmentsEntryClassPksCount =
    segmentsEntryProvider.getSegmentsEntryClassPKsCount(segmentsEntryId);
```

The method `getSegmentsEntryIds` obtains the reverse association --- the
segments associated to a specific entity. For example, this snippet returns the
segments associated to a user identified by a `userId`:

```java
int segmentsEntryClassPksCount =
    segmentsEntryProvider.getSegmentsEntryIds(User.class.getName(), userId);
```     

Great! You now know how to manage segments!
