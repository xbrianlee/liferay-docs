---
header-id: installing-product-on-tomcat
---

# Installing @product@ on Tomcat

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-tomcat.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

@product-ver@ bundled with Tomcat 9 is available on the [Help
Center](https://customer.liferay.com/downloads) (DXP) or the [Community
Downloads page](https://www.liferay.com/downloads-community) (Portal CE). The
Tomcat bundle contains JARs, scripts, and configuration files required for
deploying @product-ver@. Copying these files from the @product@ Tomcat bundle
facilitates installing @product@ on an existing Tomcat application server.

Whether you copy bundle files (recommended) or download and create the files,
you must download these files for [DXP](https://customer.liferay.com/downloads)
or [Portal CE](https://www.liferay.com/downloads-community):

- @product@ WAR file
- Dependencies ZIP file
- OSGi Dependencies ZIP file

| **Important:**
| [Prepare for the install](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install)
| before continuing.

Here are the basic steps for installing @product@ on Tomcat:

- [Installing dependencies to your application server](#installing-dependencies)
- [Configuring your application server for @product@](#configuring-tomcat)
- [Deploying the @product@ WAR file to your application server](#deploying-product)

[*Liferay Home*](/docs/7-2/deploy/-/knowledge_base/d/liferay-home)
is Tomcat server's parent folder. `$TOMCAT_HOME` refers to your Tomcat server
folder. It is usually named `tomcat-[version]` or `apache-tomcat-[version]`.

## Installing Dependencies

@product@ depends on many JARs included by @product@ Tomcat bundle. Some of the
bundle's JARs are not strictly required but can still be useful. If you don't
have a bundle, you can download the Liferay JARs by downloading the
*Dependencies* archive and the *OSGi Dependencies* archive, and you can download
the third-party JARs as described below.

1.  Create the folder `$TOMCAT_HOME/lib/ext` if it doesn't exist and extract the
    JARs from the dependencies ZIP to it.

2.  Copy the following JARs from a @product@ Tomcat bundle (or download them) to
    the `$TOMCAT_HOME/lib/ext` folder:

    - [`activation.jar`](http://www.oracle.com/technetwork/java/javase/jaf-136260.html)
    - [`ccpp.jar`](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)
    - [`jms.jar`](http://www.oracle.com/technetwork/java/docs-136352.html)
    - [`jta.jar`](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
    - [`jutf7.jar`](http://mvnrepository.com/artifact/com.beetstra.jutf7/jutf7)
    - [`mail.jar`](http://www.oracle.com/technetwork/java/index-138643.html)
    - [`persistence.jar`](http://mvnrepository.com/artifact/org.eclipse.persistence/javax.persistence/2.1.1)
    - [`support-tomcat.jar`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.support.tomcat)

3.  Copy the JDBC driver for your database to the `$CATALINA_BASE/lib/ext`
    folder.

    | **Note:** The [Liferay DXP Compatibility Matrix](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?) specifies supported databases and environments.

4.  Create an `osgi` folder in your Liferay Home. Extract the folders (i.e.,
    `configs`, `core`, and more) from OSGi ZIP file to the `osgi` folder. The
    `osgi` folder provides the necessary modules for @product@'s OSGi runtime.

## Configuring Tomcat

Configuring Tomcat to run @product@ includes

- Setting environment variables
- Specifying a web application context for @product@
- Setting properties and descriptors

Optionally, if you're not using @product@'s built-in data source or mail
session, you can configure Tomcat to manage them:

- [Data source](#database-configuration)
- [Mail session](#mail-configuration)

Start with configuring Tomcat to run @product@.

1.  If you have a @product@ Tomcat bundle, copy the `setenv.bat`, `setenv.sh`,
    `startup.bat`, `startup.sh`, `shutdown.bat`, and `shutdown.sh` files from it
    to your `$CATALINA_BASE/bin` folder. If not, create the  `setenv.bat` and
    `setenv.sh`scripts.

    The scripts set JVM options for Catalina, which is Tomcat's servlet
    container. Among these options is the location of the Java runtime
    environment. If this environment is not available on your server globally,
    you must set its location in in these files so Tomcat can run. Do this by
    pointing the `JAVA_HOME` environment variable to a @product@-supported JRE:

    ```bash
    export JAVA_HOME=/usr/lib/jvm/java-8-jdk
    export PATH=$JAVA_HOME/bin:$PATH
    ```


    Then configure Catalina's JVM options to support @product@.

    Unix:

    ```bash
    CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=512m"
    ```

    Windows:

    ```bash
    set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxMetaspaceSize=512m"
    ```

    This sets the file encoding to UTF-8, prefers an IPv4 stack over IPv6,
    prevents Tomcat from working around garbage collection bugs relating to
    static or final fields (these bugs don't exist in @product@ and working
    around them causes problems with the logging system), sets the time zone to
    GMT, gives the JVM 2GB of RAM, and limits Metaspace to 512MB.

    | **Important:** @product@ requires that the application server JVM use the
    | GMT time zone and UTF-8 file encoding.

    | **Note:** On JDK 11, it's recommended to add this JVM argument to display
    | four-digit years.
    |
    | ```properties
    | -Djava.locale.providers=JRE,COMPAT,CLDR
    | ```

    After installation, tune your system (including these JVM options) for
    performance.

2.  If you have a @product@ Tomcat bundle, copy its
    `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` file to the corresponding
    location in your application server. Create the file path if it doesn't
    exist. If you don't have a @product@ Tomcat bundle, create a `ROOT.xml`
    file.

    The `ROOT.xml` file specifies a web application context for @product@.
    `ROOT.xml` looks like this:

    ```xml
    <Context crossContext="true" path="">

        <!-- JAAS -->

        <!--<Realm
            className="org.apache.catalina.realm.JAASRealm"
            appName="PortalRealm"
            userClassNames="com.liferay.portal.kernel.security.jaas.PortalPrincipal"
            roleClassNames="com.liferay.portal.kernel.security.jaas.PortalRole"
        />-->

        <!--
        Uncomment the following to disable persistent sessions across reboots.
        -->

        <!--<Manager pathname="" />-->

        <!--
        Uncomment the following to not use sessions. See the property
        "session.disabled" in portal.properties.
        -->

        <!--<Manager className="com.liferay.support.tomcat.session.SessionLessManagerBase" />-->

        <Resources>
            <PreResources
                base="${catalina.base}/lib/ext/portal"
                className="com.liferay.support.tomcat.webresources.ExtResourceSet"
                webAppMount="/WEB-INF/lib"
            />
        </Resources>
    </Context>
    ```

     Setting `crossContext="true"` lets multiple web applications use the same
     class loader. This configuration includes commented instructions and tags
     for configuring a JAAS realm, disabling persistent sessions, and disabling
     sessions entirely.

3.  Provide Catalina access to the JARs in `$CATALINA_BASE/lib/ext` by opening
    your `$CATALINA_BASE/conf/catalina.properties` file and appending this
    value to the `common.loader` property:

    ```properties
    ,"${catalina.home}/lib/ext/global","${catalina.home}/lib/ext/global/*.jar","${catalina.home}/lib/ext","${catalina.home}/lib/ext/*.jar"
    ```

4.  Make sure to use UTF-8 URI encoding consistently. If you have a @product@
    Tomcat bundle, copy the `$CATALINA_BASE/conf/server.xml` file to your
    server. If not, open your `$CATALINA_BASE/conf/server.xml` file and add the
    attribute `URIEncoding="UTF-8"` to HTTP and AJP connectors that use
    `redirectPort=8443`. Here are examples:

    Old:

    ```xml
    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />
    ```

    New:

    ```xml
    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />
    ```

    Old:

    ```xml
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
    ```

    New:

    ```xml
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" URIEncoding="UTF-8" />
    ```

5.  Refrain from writing access logs (optional) by commenting out the access
    log `Valve` element in `$CATALINA_BASE/conf/server.xml`. It's commented out
    here:

    ```xml
    <!-- <Valve className="org.apache.catalina.valves.AccessLogValve"
           directory="logs"
           prefix="localhost_access_log" suffix=".txt"
           pattern="%h %l %u %t &quot;%r&quot; %s %b" /> -->
    ```

6.  Optionally, set the following log levels in your
    `$CATALINA_HOME/conf/logging.properties` file:

    ```properties
    org.apache.catalina.startup.Catalina.level=INFO
    org.apache.catalina.startup.ClassLoaderFactory.level=SEVERE
    org.apache.catalina.startup.VersionLoggerListener.level=WARNING
    org.apache.level=WARNING
    ```

7.  In `$CATALINA_HOME/conf/web.xml`, set the JSP compiler to Java 8 and set
    @product@'s `TagHandlerPool` class to manage the JSP tag pool. Do this by
    adding the following elements above the `jsp` servlet element's
    `<load-on-startup>` element.

    ```xml
    <init-param>
        <param-name>compilerSourceVM</param-name>
        <param-value>1.8</param-value>
    </init-param>
    <init-param>
        <param-name>compilerTargetVM</param-name>
        <param-value>1.8</param-value>
    </init-param>
    <init-param>
        <param-name>tagpoolClassName</param-name>
        <param-value>com.liferay.support.tomcat.jasper.runtime.TagHandlerPool</param-value>
    </init-param>
    ```

8.  In `$CATALINA_HOME/conf/web.xml`, specify whether the application server
    should look for extra metadata, such as annotations in the application's
    JARs and classes. Setting `web-app` element's attribute
    `metadata-complete="true"` tells the application server there's no extra
    metadata. The application server starts up faster this way. The default is
    to check for extra metadata.

9.  If you're on Unix, Linux, or Mac OS, make the shell scripts in your
    `$CATALINA_HOME/bin` and `$CATALINA_BASE/bin` folders executable by running
    this command in each folder:

    ```bash
    chmod a+x *.sh
    ```

**Checkpoint:**

Your application server is configured to run @product@.

1.  The file encoding, user time-zone, and preferred protocol stack are set in
    your `setenv.sh`.

2.  The default memory available and Metaspace limit are set.

3.  `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` declares the web
    application context.

4.  The `common.loader` property in `$CATALINA_BASE/conf/catalina.properties`
    grants Catalina access to the JARs in `$CATALINA_BASE/lib/ext`.

5.  `$CATALINA_BASE/conf/server.xml` sets UTF-8 encoding.

6.  `$CATALINA_BASE/conf/server.xml` doesn't declare any valve for writing host
    access logs. (optional)

7.  `$CATALINA_HOME/conf/logging.properties` sets the desired log levels.

8.  `$CATALINA_HOME/conf/web.xml` sets the tag handler pool and sets Java 8 as
    the JSP compiler.

9.  `$CATALINA_HOME/conf/web.xml` specifies for the application server
    to refrain from looking for extra metadata. (optional)

10. The scripts in Tomcat's `bin` folders are executable.

### Database Configuration

The easiest way to handle your database configuration is to let @product@ manage
your data source. If you want to use the
[built-in data source (recommended)](/docs/7-2/deploy/-/knowledge_base/d/preparing-for-install#using-the-built-in-data-source),
skip this section.

If you want Tomcat to manage your data source, follow these steps:

1.  Make sure your database server is installed and working. If it's installed
    on a different machine, make sure your @product@ machine can access it.

2.  Open `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` and add your data
    source as a `Resource` in your web application `Context`:

    ```xml
    <Context...>
        ...
        <Resource
            name="jdbc/LiferayPool"
            auth="Container"
            type="javax.sql.DataSource"
            driverClassName="[place the database driver class here]"
            url="[place the URL to your database here]"
            username="[place your user name here]"
            password="[place your password here]"
            maxTotal="100"
            maxIdle="30"
            maxWaitMillis="10000"
        />
    </Context>
    ```

   Make sure to replace the database URL, user name, and password with the appropriate values. For example JDBC connection values, please see [Database Templates](/docs/7-2/deploy/-/knowledge_base/d/database-templates)

3.  In a `portal-ext.properties` file in your Liferay Home, specify your data
    source:

    ```properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    ```

You created a data source for Tomcat to manage and configured @product@ to use
it. Mail session configuration is next.

### Mail Configuration

As with database configuration, the easiest way to configure mail is to let
@product@ handle your mail session. If you want to use @product@'s
[built-in mail session](/docs/7-2/deploy/-/knowledge_base/d/configuring-mail),
skip this section.

If you want to manage your mail session with Tomcat, follow these steps:

1.  Open `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml` and add your mail
    session as a `Resource` in your web application `Context`. Make sure to
    replace the example mail session values with your own.

    ```xml
    <Context...>
        ...
        <Resource
            name="mail/MailSession"
            auth="Container"
            type="javax.mail.Session"
            mail.pop3.host="pop.gmail.com"
            mail.pop3.port="110"
            mail.smtp.host="smtp.gmail.com"
            mail.smtp.port="465"
            mail.smtp.user="user"
            mail.smtp.password="password"
            mail.smtp.auth="true"
            mail.smtp.starttls.enable="true"
            mail.smtp.socketFactory.class="javax.net.ssl.SSLSocketFactory"
            mail.imap.host="imap.gmail.com"
            mail.imap.port="993"
            mail.transport.protocol="smtp"
            mail.store.protocol="imap"
        />
    </Context>
    ```

2.  In your `portal-ext.properties` file in Liferay Home, reference your mail
    session:

    ```properties
    mail.session.jndi.name=mail/MailSession
    ```

You've created a mail session for Tomcat to manage and configured @product@ to
use it.

## Deploying @product@

Now you're ready to deploy @product@ using the @product@ WAR file.

1.  If you are manually installing @product@ on a clean Tomcat server, delete
    the contents of the `$CATALINA_BASE/webapps/ROOT` folder. This removes
    the default Tomcat home page.

2.  Extract the @product@ `.war` file contents to
    `$CATALINA_BASE/webapps/ROOT`.

    It's time to launch @product@ on Tomcat!

3.  Start Tomcat by navigating to `$CATALINA_HOME/bin` and executing
    `./startup.sh`. Alternatively, execute `./catalina.sh run` to tail
    @product@'s log file. The log audits startup activities and is useful for
    debugging deployment.

Congratulations on successfully installing and deploying @product@ on Tomcat!
