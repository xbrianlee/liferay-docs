---
header-id: using-osgi-services-in-a-bean
---

# Using OSGi Services in a Bean

[TOC levels=1-4]

Any bean can use the `@org.osgi.service.cdi.annotations.Reference` annotation to
inject OSGi services. It's the easiest way for a bean to access an OSGi service.
Here's how:

1.  Add a project dependency on the OSGi CDI Integration artifact. For example,
    here's the dependency to use in a Maven `pom.xml` file:

    ```xml
    <dependency>
        <groupId>org.osgi</groupId>
        <artifactId>org.osgi.service.cdi</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

2.  Obtain and inject the OSGi service by using the
    `@org.osgi.service.cdi.annotations.Reference` and `@javax.inject.Inject`
    annotations respectively. Here's an example of injecting a service of type
    `ProductStore`. 

    ```java
    import javax.inject.Inject;

    import org.osgi.service.cdi.annotations.Reference;

    import package.path.ProductStore;

    public class MyBean {

        @Inject
        @Reference
        ProductStore productStore;

        ...
    }
    ```

3.  Deploy your bean project to @product@. 

Congratulations on injecting an OSGi service into your bean! Now your bean uses
the OSGi service you injected.
