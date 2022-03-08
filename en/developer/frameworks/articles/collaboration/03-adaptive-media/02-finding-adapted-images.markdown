---
header-id: finding-adapted-images
---

# Finding Adapted Images

[TOC levels=1-4]

If you need more control than the 
[Adaptive Media taglib](/docs/7-2/frameworks/-/knowledge_base/f/displaying-adapted-images-in-your-app) 
offers for finding adapted images to display in your app, you can query Adaptive 
Media's finder API directly. The steps here show you how for these scenarios: 

-   [Getting Adapted Images for File Versions](#getting-adapted-images-for-file-versions)
-   [Getting the Adapted Images for a Specific Image Resolution](#getting-the-adapted-images-for-a-specific-image-resolution)
-   [Getting Adapted Images in a Specific Order](#getting-adapted-images-in-a-specific-order)
-   [Using Approximate Attributes](#using-approximate-attributes)
-   [Using the Adaptive Media Stream](#using-the-adaptive-media-stream)

For background information on these topics, see 
[Adaptive Media's Finder API](/docs/7-2/frameworks/-/knowledge_base/f/adaptive-media#adaptive-medias-finder-api). 

## Getting Adapted Images for File Versions

Follow these steps to get adapted images for file versions. Note that the method 
calls here only return adapted images for 
[enabled image resolutions](/docs/7-2/user/-/knowledge_base/u/managing-image-resolutions): 

1.  Get an `AMImageFinder` reference: 

    ```java
    @Reference
    private AMImageFinder _amImageFinder;
    ```

2.  To get adapted images for a specific file version, call the 
    [`AMImageQueryBuilder`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/image/finder/AMImageQueryBuilder.html) 
    method `forFileVersion` with a 
    [`FileVersion`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/model/FileVersion.html) 
    object as an argument: 

    ```java
    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion).done());
    ```

3.  To get the adapted images for the latest approved file version, use the 
    `forFileEntry` method with a 
    [`FileEntry`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/model/FileEntry.html) 
    object: 

    ```java
    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileEntry(fileEntry).done());
    ```

To get adapted images regardless of status (enabled/disabled image resolutions), 
invoke the `withConfigurationStatus` method with the constant 
`AMImageQueryBuilder.ConfigurationStatus.ANY`: 

```java
Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
    _amImageFinder.getAdaptiveMediaStream(
        amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
            .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY).done());
```

Use the constant `AMImageQueryBuilder.ConfigurationStatus.DISABLED` to get 
adapted images for only disabled image resolutions. 

## Getting the Adapted Images for a Specific Image Resolution

By providing an image resolution's UUID to `AMImageFinder`, you can get that 
resolution's adapted images. This UUID is defined when 
[adding the resolution](/docs/7-2/user/-/knowledge_base/u/adding-image-resolutions) 
in the Adaptive Media app. To get a resolution's adapted images, you must pass 
that resolution's UUID to the `forConfiguration` method. 

Follow these steps to get adapted images for an image resolution: 

1.  Get an `AMImageFinder` reference: 

    ```java
    @Reference
    private AMImageFinder _amImageFinder;
    ```

2.  Call the 
    [`AMImageQueryBuilder.ConfigurationStep`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/image/finder/AMImageQueryBuilder.ConfigurationStep.html) 
    method `forConfiguration` with the image resolution's UUID. For example, 
    this code gets the adapted images that match a file version, and belong to 
    an image resolution with the UUID `hd-resolution`. It returns the adapted 
    images regardless of whether the resolution is enabled or disabled: 

    ```java
    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .forConfiguration("hd-resolution").done());
    ```

## Getting Adapted Images in a Specific Order

It's also possible to define the order in which `getAdaptiveMediaStream` returns 
adapted images. Follow these steps to do so: 

1.  Get an `AMImageFinder` reference: 

    ```java
    @Reference
    private AMImageFinder _amImageFinder;
    ```

2.  Call the `orderBy` method with your sort criteria just before calling the 
    `done()` method. The `orderBy` method takes two arguments: the first 
    specifies the image attribute to sort by (e.g., width/height), while the 
    second specifies the sort order (e.g., ascending/descending). The Adaptive 
    Media API provides 
    [constants](/docs/7-2/frameworks/-/knowledge_base/f/adaptive-media#adaptive-media-api-constants) 
    that you can use for these arguments. 

    For example, this code gets all the adapted images regardless of whether the 
    image resolution is enabled, and puts them in ascending order by image 
    width: 

    ```java
    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY)
                .orderBy(AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH, AMImageQueryBuilder.SortOrder.ASC)
                .done());
    ```

## Using Approximate Attributes

You can use the API to get adapted images that match approximate attribute 
values. Follow these steps to do so: 

1.  Get an `AMImageFinder` reference: 

    ```java
    @Reference
    private AMImageFinder _amImageFinder;
    ```

2.  Call the `with` method with your search criteria just before calling the 
    `done()` method. The `with` method takes two arguments: the image attribute 
    and that attribute's approximate value. For example, this code gets adapted 
    images whose height is approximately 400px: 

    ```java
    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                .with(AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT, 400).done());
    ```

## Using the Adaptive Media Stream

The Adaptive Media stream flows like a babbling brook through the sands of time. 
Just kidding; it's not like that at all. Once you have the 
[`AdaptiveMedia`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/AdaptiveMedia.html) 
stream, you can get the information you need from it. For example, this code 
prints the URI for each adapted image: 

```java
adaptiveMediaStream.forEach(
    adaptiveMedia -> {
        System.out.println(adaptiveMedia.getURI());
    }
);
```

You can also get other values and attributes from the `AdaptiveMedia` stream. 
Here are a few examples: 

```java
// Get the InputStream 
adaptiveMedia.getInputStream()

// Get the content length
adaptiveMedia.getValueOptional(AMAttribute.getContentLengthAMAttribute())

// Get the image height
adaptiveMedia.getValueOptional(AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT)
```

## Related Topics

[Adaptive Media](/docs/7-2/frameworks/-/knowledge_base/f/adaptive-media)

[Displaying Adapted Images in Your App](/docs/7-2/frameworks/-/knowledge_base/f/displaying-adapted-images-in-your-app)

[Creating an Image Scaler](/docs/7-2/frameworks/-/knowledge_base/f/creating-an-image-scaler)
