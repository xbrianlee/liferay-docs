---
header-id: portletmvc4spring
---

# PortletMVC4Spring

[TOC levels=1-4]

PortletMVC4Spring is a way to develop portlets using the Spring Framework and
the Model View Controller (MVC) pattern. While the Spring Framework supports
developing *servlet-based* web applications using
[Spring Web MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html),
PortletMVC4Spring supports developing *portlet-based* applications using MVC.
You can build 
[Spring Framework](https://spring.io/projects/spring-framework)
@product@ portlets with features like these:

-   Inversion of control (IoC) / dependency injection (DI)
-   Annotations
-   Security
-   Binding and validation
-   Multi-part file upload
-   ... and more

You'll learn these things about PortletMVC4Spring: 

-   **Developing a Portlet Using PortletMVC4Spring:** Demonstrates creating and 
    deploying a portlet using PortletMVC4Spring. 

-   **Annotation-based Controller Development:** Shows how to implement 
    controllers using plain old Java objects (POJOs) and annotations. 

| **Background:** The PortletMVC4Spring project began as Spring Portlet MVC and 
| was part of the
| [Spring Framework](https://spring.io/projects/spring-framework).
| When the project was pruned from version 5.0.x of the Spring Framework under
| [SPR-14129](https://github.com/spring-projects/spring-framework/issues/18701),
| it became necessary to fork and rename the project. This made it possible to
| improve and maintain the project for compatibility with the latest versions of
| the Spring Framework and the Portlet API.
|
| [Liferay](http://www.liferay.com)
| adopted Spring Portlet MVC in March of 2019 and the project was renamed to
| **PortletMVC4Spring**.

If you're familiar with Spring Web MVC, it's helpful to compare it with
PortletMVC4Spring. Portlet workflow differs from servlet workflow because
a request to the portlet can have two distinct phases: the `ACTION_PHASE` and
the `RENDER_PHASE`. The `ACTION_PHASE` is executed only once and is where any
back-end changes or actions occur, such as making changes in a database. The
`RENDER_PHASE` presents the portlet's content to the user each time the display
is refreshed. Thus for a single request, the `ACTION_PHASE` is executed only
once, but the `RENDER_PHASE` may be executed multiple times. This provides (and
requires) a clean separation between the activities that modify the system's
persistent state and the activities that generate content. The Portlet 2.0
Specification added two more phases: The event phase and the resource phase,
both of which are supported by annotation-driven dispatching. 

PortletMVC4Spring provides annotations that support requests from the render,
action, event, and resource serving portlet phases; Spring Web MVC provides only
a `@RequestMapping` annotation. Where a Spring Web MVC controller might have
a single handler method annotated with `@RequestMapping`, an equivalent
PortletMVC4Spring controller might have multiple handler methods, each using
one of the phase annotations: `@ActionMapping`, `@EventMapping`,
`@RenderMapping`, or `@ResourceMapping`.

The PortletMVC4Spring framework uses a `DispatcherPortlet` that dispatches
requests to handlers, with configurable handler mappings and view resolution,
just as the `DispatcherServlet` in the web framework does. 

| **Note:** For more information on portlets, portlet specifications, and how 
| portlets differ from servlets, see
| [Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets). 

Liferay also provides full-featured sample portlets that demonstrate using JSP
and
[Thymeleaf](https://www.thymeleaf.org)
view templates. They exercise many features that form-based
portlet applications typically require. 

![Figure 1: This PortletMVC4Spring portlet enables users to enter job applications. It uses the Spring features mentioned above and handles requests from multiple portlet phases.](../../../images/portletmvc4spring-applicant-jsp-app.png)

The samples are available here: 

| Source Code   | Maven Central |
| ------------- | ------------- |
| [applicant-jsp-portlet](https://github.com/liferay/portletmvc4spring/tree/master/demo/applicant-jsp-portlet)  |  [com.liferay.portletmvc4spring.demo.applicant.jsp.portlet.war](https://search.maven.org/search?q=a:com.liferay.portletmvc4spring.demo.applicant.jsp.portlet) |
| [applicant-thymeleaf-portlet](https://github.com/liferay/portletmvc4spring/tree/master/demo/applicant-thymeleaf-portlet)  |  [com.liferay.portletmvc4spring.demo.applicant.thymeleaf.portlet.war](https://search.maven.org/search?q=a:com.liferay.portletmvc4spring.demo.applicant.thymeleaf.portlet) | 

Now that you have a basic understanding of PortletMVC4Spring portlets and how
they compare to Spring Web MVC applications, it's time to develop a
PortletMVC4Spring portlet. 
