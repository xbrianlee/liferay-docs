---
header-id: examining-an-osgi-service-to-override
---

# Examining an OSGi Service to Override

[TOC levels=1-4]

Creating and injecting a custom service in place of an existing service requires 
three things: 

- Understanding the service interface 
- The existing service 
- The references to the service 

Your custom service must implement the service interface, match 
references you want, and might need to invoke the existing service. 

Getting components to adopt your custom service immediately can require 
reconfiguring their references to the service. Here you'll flesh out service 
details to make these decisions. 

## Gathering Information on a Service

1.  Since component service references are extension points, start with 
    determining the service you want to override and components that use that 
    service. 
    
    <!--replace step 1 text with text below once article is available
    Since component service references are extension points, start with 
    following the steps provided in 
    Finding Extension Points 
    to determine the service you want to override and components that use that 
    service.
    -->

2.  Once you know the service and components that use it, use Gogo Shell's 
    Service Component Runtime (SCR) to inspect the components and get the 
    service and reference details. The 
    [Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell) 
    command `scr:info [componentName]` lists the component's attributes and 
    service references. 

Here's an example `scr:info` command and results (abbreviated with `...`) that
describe component `override.my.service.reference.OverrideMyServiceReference`
(from sample module
[override-my-service-reference](https://portal.liferay.dev/documents/113763090/114000186/override-my-service-reference.zip))
and its reference to a service of type
`override.my.service.reference.service.api.SomeService`: 

    > scr:info override.my.service.reference.OverrideMyServiceReference 

    ...
    Component Description:
        Name: override.my.service.reference.portlet.OverrideMyServiceReferencePortlet
    ...
    Reference: _someService
        Interface Name: override.my.service.reference.service.api.SomeService
        Cardinality: 1..1
        Policy: static
        Policy option: reluctant
        Reference Scope: bundle
    ...
    Component Configuration:
    ComponentId: 2399
    State: active
    SatisfiedReference: _someService
      Target: null
      Bound to:        6840
          Properties:
            component.id = 2400
            component.name = override.my.service.reference.service.impl.SomeServiceImpl
            objectClass = [override.my.service.reference.service.api.SomeService]
            service.bundleid = 524
            service.id = 6840
            service.scope = bundle
    ...
 
The `scr:info` results, like the ones above, contain information relevant to 
injecting a custom service. Here's what you'll do with the information: 

1.  [Copy the service interface name](#step-1-copy-the-service-interface-name) 

2.  [Copy the existing service name](#step-2-copy-the-existing-service-name) 

3.  [Gather reference configuration details (if reconfiguration is necessary)](#step-3-gather-reference-configuration-details-if-reconfiguration-is-needed)

Start with the service interface. 

## Step 1: Copy the Service Interface Name

The reference's *Interface Name* is the service interface's fully qualified 
name.

    ...
    Reference: _someService
        Interface Name: override.my.service.reference.service.api.SomeService
        ...

**Copy and save the interface name**, because it's the type your custom service 
must implement. 

| Javadocs for @product@ service interfaces are at these locations:
| 
| - [@product@ core Javadocs](@platform-ref@/7.2-latest/javadocs/)
| - [@product@ app Javadocs](@app-ref@)
| - [MVNRepository](https://mvnrepository.com/)
|   and
|   [Maven Central](https://search.maven.org/)
|   (for Liferay and non-Liferay artifact Javadocs).

## Step 2: Copy the Existing Service Name

If you want to invoke the existing service along with your custom service, get 
the existing service name. 

The `src:info` result's Component Configuration section lists the existing 
service's fully qualified name. For example, the 
`OverrideMyServiceReferencePortlet` component's references `_someService` is 
bound to a service component whose fully qualified name is 
`override.my.service.reference.service.impl.SomeServiceImpl`. 

    Component Configuration:
    ...
    SatisfiedReference: _someService
      ...
      Bound to:        6840
          Properties:
            ...
            component.name = override.my.service.reference.service.impl.SomeServiceImpl

**Copy the `component.name`** so you can reference the service in your 
[custom service](/docs/7-2/customization/-/knowledge_base/c/creating-a-custom-osgi-service).

Here's an example of referencing the service above. 

```java
@Reference  (
    target = "(component.name=override.my.service.reference.service.impl.SomeServiceImpl)"
)
private SomeService _defaultService;
```

## Step 3: Gather Reference Configuration Details (if reconfiguration is needed)

The service reference's policy and policy option determine a component's 
conditions for adopting a particular service. 

- If the reference's policy option is `greedy`, it binds to the matching, 
  highest ranking service right away. The reference need not be reconfigured to 
  adopt your service. 

- If policy is `static` and its policy option is `reluctant`, however, the 
  component requires one of the following conditions to switch from using the 
  existing service it's referencing to using the matching, highest ranking 
  service (i.e., you'll rank your custom service highest):

   1. The component is reactivated
   2. The component's existing referenced service is unavailable
   3. The component's reference is modified so that it does not match the
      existing service but matches your service

[Reconfiguring the reference](/docs/7-2/customization/-/knowledge_base/c/reconfiguring-components-to-use-your-service) 
can be the quickest way for the component to adopt a new service. 

**Gather these details:** 

- *Component name:* Find this at *Component Description* &rarr; *Name*. For example,

        Component Description:
            Name: override.my.service.reference.portlet.OverrideMyServiceReferencePortlet
            ...

- *Reference name:* The *Reference* value (e.g., `Reference: _someService`).

- *Cardinality:* Number of service instances the reference can bind to. 

| **Note**: Declarative Services makes all components configurable through OSGi 
| Configuration Admin. Each `@Reference` annotation in the source code has a name 
| property, either *explicitly* set in the annotation or *implicitly* derived from 
| the name of the member on which the annotation is used. 
| 
| -   If no reference name property is used and the `@Reference` is on a field, 
|     then the reference name is the field name. If `@Reference` is on a field 
|     called `_someService`, for example, then the reference name is 
|     `_someService`. 
| -   If the `@Reference` is on a method, then heuristics derive the reference 
|     name. Method name suffix is used and prefixes such as `set`, `add`, and 
|     `put` are ignored. If `@Reference` is on a method called 
|     `setSearchEngine(SearchEngine se)`, for example, then the reference name is 
|     `SearchEngine`. 

After [creating your custom service](/docs/7-2/customization/-/knowledge_base/c/creating-a-custom-osgi-service) 
(next), you'll use the details you collected here to [configure the component 
to use your custom service](/docs/7-2/customization/-/knowledge_base/c/reconfiguring-components-to-use-your-service). 

Congratulations on getting the details required for overriding the OSGi service! 

## Related Topics

- [OSGi Services and Dependency Injection with Declarative Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)
- [Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell)
