---
header-id: reading-unscoped-configuration-values-from-a-component
---

# Reading Unscoped Configuration Values from a Component

[TOC levels=1-4]

Follow these steps to read `SYSTEM` scoped or unscoped configuration values from
a Component that isn't part of a portlet:

1.  First set the `configurationPid` Component property as the fully qualified
    class name of the configuration class:

    ```java
    @Component(configurationPid = "com.liferay.dynamic.data.mapping.form.web.configuration.DDMFormWebConfiguration")
    ```

2.  Then provide an `activate` method, annotated with `@Activate` to ensure the
    method is invoked as soon as the Component is started, and `@Modified` so
    it's invoked whenever the configuration is modified.

    ```java
    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _formWebConfiguration = ConfigurableUtil.createConfigurable(
            DDMFormWebConfiguration.class, properties);
    }

    private volatile DDMFormWebConfiguration _formWebConfiguration;
    ```

    The `activate()` method calls the method
    `ConfigurableUtil.createConfigurable()` to convert a map of the
    configuration's properties to a typed class, which is easier to handle. The
    configuration is stored in a `volatile` field. Don't forget to make it
    `volatile` to prevent thread safety problems.

3.  Once the activate method is set up, retrieve particular properties from the
    configuration wherever they're needed:

    ```java 
    public void orderCar(String model) {
        order("car", model, _configuration.favoriteColor());
    }
    ```

    This is dummy code: don't try to find it in the Liferay source code. The
    String configuration value of `favoriteColor` is passed to the `order`
    method call, presumably so that whatever model car is ordered gets ordered
    in the configured favorite color.

| **Note:** The bnd library also provides a class called
| `aQute.bnd.annotation.metatype.Configurable` with a `createConfigurable()`
| method. You can use that instead of Liferay's
| `com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil` without
| any problems. Liferay's developers created the `ConfigurableUtil` class to
| improve the performance of bnd's implementation, and it's used in internal code.
| Feel free to use whichever method you prefer. 

With very few lines of code, you have a configurable application that
dynamically changes its configuration, has an auto-generated UI, and uses a
simple API to access the configuration.
