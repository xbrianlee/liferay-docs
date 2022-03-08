---
header-id: greedy-policy-option-portlet
---

# Greedy Policy Option Application

[TOC levels=1-4]

The Greedy Policy Option sample provides two portlets that can be added to a
@product@ page: Greedy Portlet and Reluctant Portlet.

![Figure 1: The Greedy Policy Option app provides two portlets that only print text. You'll dive deeper later to discover their interesting capabilities involving services.](../../../images/greedy-policy-app.png)

These two portlets do not provide anything useful out-of-the-box. They are,
however, very effective at demonstrating the ability to reference services using
greedy and reluctant policy options. You'll learn how to do this later.

## What API(s) and/or code components does this sample highlight?

This sample provides two modules referencing services using greedy and reluctant
policy options.

- `service-reference`: Provides an OSGi service interface called
  `SomeService`, a default implementation of it, and portlets that refer to
  service instances. One portlet refers to new higher ranked instances of the
  service automatically. The other portlet is reluctant to use new higher
  ranked instances and continues to use its bound service. The reluctant
  portlet can, however, be configured dynamically to use other service
  instances.

-  `higher-ranked-service`: Has a higher ranked `SomeService` implementation.

Here are each module's file structures:

- `service-reference/`
    - `bnd.bnd`
    - `configs/`
        - `com.liferay.blade.reluctant.vs.greedy.portlet.portlet.ReluctantPortlet.config` &rarr; `ReluctantPortlet` configuration file
    - `src/main/java/com/liferay/blade/reluctant/vs/greedy/portlet/`
        - `api/`
            - `SomeService.java` &rarr; Service interface
        - `constants/`
            - `ReluctantPortletVsGreedyPortletKeys.java` &rarr; Portlet constants
        - `portlet/`
            - `DefaultSomeService.java` &rarr; Zero ranked service implementation
            - `GreedyPortlet.java` &rarr; Refers to `SomeService` using a greedy service policy option
            - `ReluctantPortletPortlet.java` &rarr; Refers to `SomeService` using a reluctant service policy option by default.

- `higher-ranked-service/`
    - `bnd.bnd`
    - `src/main/java/com/liferay/blade/reluctant/vs/greedy/svc/HigherRankedService.java` &rarr; Service implementation with service ranking value of `100`

## How does this sample leverage the API(s) and/or code component?

Here are the things you can learn using the sample modules:

1.  [Binding a component's service reference to the highest ranked service instance that's available initially.](#binding-a-newly-deployed-components-service-reference-to-the-highest-ranking-service-instance-thats-available-initially)

2.  [Deploying a module with a higher ranked service instance for binding to greedy references immediately.](#deploying-a-module-with-a-higher-ranked-service-instance-for-binding-to-greedy-references-immediately)

3.  [Configuring a component to reference a different service instance dynamically.](#configuring-a-component-to-reference-a-different-service-instance-dynamically)

Let's walk through the demonstration.

### Binding a newly deployed component's service reference to the highest ranking service instance that's available initially

On deploying a component that references a service, it binds to the highest
ranking service instance that matches its target filter (if specified).

The portlet classes refer to instances of interface `SomeService`. The
`doSomething` method returns a `String`.

```java
public interface SomeService {

    public String doSomething();

}
```

Class `DefaultSomeService` implements `SomeService`. Its `doSomething` method
returns the `String` "I am Default!".

```java
@Component
public class DefaultSomeService implements SomeService {

    @Override
    public String doSomething() {
        return "I am Default!";
    }

}
```

When module's portlets refer to `DefaultSomeService`, they display the `String`
"I am Default!".

The `ReluctantPortlet` class's `SomeService` reference's policy option is the
default: static and reluctant. This policy option keeps the reference bound to
its current service instance unless that instance stops or the reference is
reconfigured to refer to a different service instance.

```java
@Component(
   immediate = true,
   property = {
       "com.liferay.portlet.display-category=category.sample",
       "com.liferay.portlet.instanceable=true",
       "javax.portlet.display-name=Reluctant Portlet",
       "javax.portlet.init-param.template-path=/",
       "javax.portlet.init-param.view-template=/view.jsp",
       "javax.portlet.name=" + ReluctantVsGreedyPortletKeys.Reluctant,
       "javax.portlet.resource-bundle=content.Language",
       "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
  )
public class ReluctantPortlet extends MVCPortlet {

   @Override
   public void doView(
           RenderRequest renderRequest, RenderResponse renderResponse)
       throws IOException, PortletException {

       renderRequest.setAttribute("doSomething", _someService.doSomething());

       super.doView(renderRequest, renderResponse);
   }

    @Reference
    private SomeService _someService;

}
```

The `ReluctantPortlet`'s method `doView` sets render request attribute
`doSomething` to the value returned from the `SomeService` instance's
`doSomething` method (e.g., `DefaultService` returns "I am default!").

The `GreedyPortlet` class is similar to `ReluctantPortlet`, except its
`SomeService` reference's policy option is static and greedy (i.e.,
`ReferencePolicyOption.GREEDY`).

```java
public class GreedyPortlet extends MVCPortlet {

  @Override
	public void doView(
  		RenderRequest renderRequest, RenderResponse renderResponse)
  	throws IOException, PortletException {

  	renderRequest.setAttribute("doSomething", _someService.doSomething());

  	super.doView(renderRequest, renderResponse);
	}

	@Reference (policyOption = ReferencePolicyOption.GREEDY)
	private SomeService _someService;

}
```

The greedy policy option lets the component switch to using a higher ranked
`SomeService` instance if one becomes active in the system. The section
[*Deploying a module with a higher ranked service instance for binding to greedy references immediately*](#deploying-a-module-with-a-higher-ranked-service-instance-for-binding-to-greedy-references-immediately)
demonstrates this portlet switching to a higher ranked service.

It's time to see this module's portlets and service in action.

1.  Stop module `higher-ranked-service` if it's active.
2.  Deploy the `service-reference` module.
3.  Add the *Reluctant Portlet* from the *Add* &rarr; *Widgets* &rarr;
    *Sample* category to a site page.

    The portlet displays the message "SomeService says I am Default!"--whose
    latter part comes from the render request attribute set by the
    `DefaultService` instance.

    ![Figure 2: *Reluctant Portlet* displays the message "SomeService says I am Default!"](../../../images/reluctant-portlet-using-default.png)

4.  Add the *Greedy Portlet* from the *Add* &rarr; *Widgets* &rarr;
    *Sample* category to a site page.

    The portlet displays the message "SomeService says I am better, use me!".
    Both portlets are referencing a `DefaultService` instance.

    ![Figure 3: *Greedy Portlet* displays the message "SomeService says I am better, use me!"](../../../images/greedy-portlet-using-default.png)

Since `DefaultService` is the only active `SomeService` instance in the system,
the portlets refer to it for their `SomeService` fields.

The `DefaultService` and portlets *Reluctant Portlet* and *Greedy Portlet* are
active. Let's activate a higher ranked `SomeService` instance and see how the
portlets react to it.

### Deploying a module with a higher ranked service instance for binding to greedy references immediately

Module `higher-ranked-service` provides a `SomeService` implementation called
`HigherRankedService`. `HigherRankedService`'s service ranking is `100`--that's
`100` more than `DefaultService`'s ranking `0`. Its `doSomething` method returns
the `String` "I am better, use me!".

1.  Deploy the `higher-ranked-service` module.
2.  Refresh your page that has the portlets *Reluctant Portlet* and *Greedy Portlet*.

*Reluctant Portlet* continues displaying message "SomeService says I am better,
use me!". It's "reluctant" to unbind from the `DefaultService` instance and
bind to the newly activated `HigherRankedService` service.

*Greedy Portlet* displays a new message "SomeService says I am better, use me!".
The part of the message "I am better, use me!" comes from the
`HigherRankedService` instance to which it refers.

![Figure 4: The *Greedy Portlet* is using a `HigherRankedService` instance](../../../images/greedy-portlet-using-higher-ranked-service.png)

Next, learn how to bind the *Reluctant Portlet* to a `HigherRankedService`
instance.

### Configuring a component to reference a different service instance dynamically

The *Reluctant Portlet* is currently bound to a `DefaultService` instance. It's
"reluctant" to unbind from it and bind to a different service. OSGi
Configuration Administration lets you reconfigure service references to filter
on and bind to different service instances.

The `service-reference` module's configuration files and
`com.liferay.blade.reluctant.vs.greedy.portlet.portlet.ReluctantPortlet.config`
and `com.liferay.blade.reluctant.vs.greedy.portlet.portlet.ReluctantPortlet.cfg`
configure the `ReluctantPortlet` component to use a `HigherRankedService`
instance.

```
_someService.target=(component.name=com.liferay.blade.reluctant.vs.greedy.service.HigherRankedService)
```

The service configuration filters on a service whose `component.name` is
`com.liferay.blade.reluctant.vs.greedy.service.HigherRankedService`.

Note: For deploying to @product-ver@, use file with suffix `.config`. For
earlier versions (i.e., Liferay DXP 7.0 Fix Packs earlier than Fix Pack 8 and
Liferay CE Portal 7.0 GA3 or earlier), use the file with suffix `.cfg`.

Here are the steps to reconfigure `ReluctantPortlet` to use
`HigherRankedService`:

1.  Copy the configuration file to `[Liferay-Home]/osgi/configs`.
2.  Refresh your browser.

*Reluctant Portlet* displays a new message "SomeService says I am better, use
me!".

![Figure 5: *Reluctant Portlet* is using the `HigherRankedService` instance instead of a `DefaultService` instance.](../../../images/reluctant-portlet-using-higher-ranked-service.png)

*Reluctant Portlet* is using `HigherRankedService` instance instead of a
`DefaultService` instance. You've configured *Reluctant Portlet* to use a
`HigherRankedService` instance!

## Where Is This Sample?

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.2/gradle/apps/greedy-policy-option-portlet)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace/apps/greedy-policy-option-portlet)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.2/maven/apps/greedy-policy-option-portlet)
