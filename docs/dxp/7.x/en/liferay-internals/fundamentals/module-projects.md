# Module Projects

Customizations and new Liferay applications are written using [OSGi modules](https://www.osgi.org/developer/what-is-osgi/). OSGi modules are `.jar` files containing some extra configuration for publishing and consuming APIs.

A module project comprises three things:

1. **Code:** Java classes and required resources, such as images, templates, and additional descriptors. Packages are private by default. Packages for other modules to use (such as API packages) are [specified for export](./exporting-packages.md) in the `bnd.bnd` file.

1. **Build Scripts:** [Gradle](https://gradle.org/) files for building and deploying the module.

1. **Metadata:** A [Bnd](https://bnd.bndtools.org/) file defines the module artifact and specifies capabilities, including Java packages, that the module defines, provides, and requires.

Here's the project structure:

```
[project root]
 └── [module project 1]
 │    ├── bnd.bnd // Defines the module artifact, provided/required capabilities
 │    ├── build.gradle // Declares dependencies
 │    └── src
 │          └── main
 │              ├── java
 │              │   └── [Java packages]
 │              └── resources
 │                  └── [any image files, templates, descriptors, etc.]
 │
 └── [module project 2]
 │
 └── [module project n]
 │
 ├── gradle
 │    └── [Gradle wrapper files]
 ├── gradlew // Invokes the Gradle wrapper to execute tasks
 ├── gradlew.bat
 ├── gradle.properties // Specifies the Liferay product version
 └── settings.gradle // Applies Gradle plugins
```

By convention, module projects use a parent folder that provides [Liferay Workspace](../../developing-applications/tooling/liferay-workspace.md) and a Gradle build infrastructure.

Here you'll examine an example module project, build it, and deploy it. The module provides an API that generates a greeting.

## Install the Example Module Project

Start using the example module.

1. Create and navigate to a folder for the example project

1. Download the example.

    ```bash
    curl https://learn.liferay.com/dxp-7.x/liferay-internals/fundamentals/liferay-k8s2.zip -O
    ```

1. Unzip the example.

    ```bash
    unzip liferay-k8s2.zip
    ```

## Examine the Project

The example includes the project root files and a module folder called `k8s2-api`. The module defines an API--a contract that a provider implements and a consumer uses. Here is the project's structure:

```
[project root]
 └── k8s2-api
 │   ├── bnd.bnd
 │   ├── build.gradle
 │   └── src
 │       └── main
 │           └── java
 │               └── com/acme/k8s2/greeting
 │                   └── Greeting.java
 │
 ├── gradle
 │    └── [Gradle wrapper files]
 ├── gradlew
 ├── gradlew.bat
 ├── gradle.properties
 └── settings.gradle
```

The Gradle files provide [Workspace](../../developing-applications/tooling/liferay-workspace.md) to the module and any sibling modules added to the project root. Explore the module next.

### Code

The example module has only one Java class: an interface called `Greeting`.

```java
package com.acme.k8s2.greeting;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface Greeting {

	public void greet(String name);

}
```

[`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) annotation tells the service registry that anything implementing the interface provides it (i.e., a `Greeting`).

The interface's one method called `greet` asks for a `String` and doesn't return anything.

Beyond the Java source file, there are only two other files: a configuration file called `bnd.bnd` and a Gradle build script.

### Metadata

The `bnd.bnd` file describes and configures the module.

```properties
Bundle-Name: ACME K8S2 Greeting API
Bundle-SymbolicName: com.liferay.learn.k8s2.api
Bundle-Version: 1.0.0
Export-Package: com.acme.k8s2.greeting
```

The module's name is *ACME K8S2 Greeting API*. Its symbolic name---a name that ensures uniqueness---is `com.liferay.learn.k8s2.api`. Its [semantic version](./semantic-versioning.md) is declared next. Lastly the module [*exports*](./exporting-packages.md) the package `com.acme.k8s2.greeting`, which means the package is available to other modules. This package is just an API other modules can implement.

### Build Script

A module's `build.gradle` file specifies its dependencies. Liferay's fat JAR TODO provides many Liferay, Bnd, and OSGi artifacts.

```groovy
dependencies {
	compileOnly group: "com.liferay.portal", name: "release.portal.api"
}
```

This module depends on one artifact: the Portal API for the Liferay release. It's a large JAR packed with Liferay, Bnd, and OSGi artifacts associated with the Liferay release.

The `compileOnly` configuration scope means that the dependencies are needed locally at compile time only. The Portal API artifacts are available already at runtime.

Lastly, notice that no verions are specified for the dependency. That's because Workspace applies the artifact versions associated with the Liferay product specified in the `[project root]/gradle.properties` file. Here's the project version used when this article was written:

```properties
liferay.workspace.product=portal-7.3-ga3
```

```note::
   Please see `Configuring Dependencies <./configuring-dependencies/configuring-dependencies.md>`_ for more information.
```

That's it! As you can see, creating modules is the same as creating other Java projects, with some added configuration.

## Build the Module

When you build a module JAR, Bnd propagates configuration metadata from the module's `bnd.bnd` file to the JAR file's `META-INF/MANIFEST.MF`. At runtime, the OSGi framework uses the metadata to register the module's provided capabilites and to wire required capabilities.

1. Build the module JAR.

    ```bash
    ./gradlew jar
    ```

    The JAR file is generated to the module's `build/libs` folder.

    ```
    k8s2-api/build/libs/com.liferay.learn.k8s2.api-1.0.0.jar
    ```

1. Examine the JAR's `META-INF/MANIFEST.MF` file:

    ```properties
    Manifest-Version: 1.0
    Bnd-LastModified: 1598968383025
    Bundle-ManifestVersion: 2
    Bundle-Name: ACME K8S2 Greeting API
    Bundle-SymbolicName: com.liferay.learn.k8s2.api
    Bundle-Version: 1.0.0
    Created-By: 1.8.0_252 (Oracle Corporation)
    Export-Package: com.acme.k8s2.greeting;version="1.0.0"
    Javac-Debug: on
    Javac-Deprecation: off
    Javac-Encoding: UTF-8
    Require-Capability: osgi.ee;filter:="(&(osgi.ee=JavaSE)(version=1.8))"
    Tool: Bnd-4.3.0.201909301554
    ```

    Bnd generated the manifest based on the `bnd.bnd` file and adds more details. For example, the manifest exports the `com.acme.k8s2.greeting` package and assigns the default package version `1.0.0`.

## Deploy the Module

It's time to install the module JAR on Liferay.

1. Start a [Liferay Docker container](../../installation-and-upgrades/installing-liferay/using-liferay-dxp-docker-images/dxp-docker-container-basics.md).

    ```bash
    docker run -it -p 8080:8080 liferay/portal:7.3.2-ga3
    ```

1. Deploy the module JAR.

    ```bash
    docker cp k8s2-api/build/libs/com.liferay.learn.k8s2.api-1.0.0.jar $(docker ps -lq):/opt/liferay/deploy
    ```

    Console messages reflect Liferay processing the JAR and starting the module.

    ```
    INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:271] Processing com.liferay.learn.k8s2.api-1.0.0.jar
    INFO  [fileinstall-[Liferay Home]/osgi/modules][BundleStartStopLogger:46] STARTED com.liferay.learn.k8s2.api_1.0.0 [1192]
    ```

## Examine the Deployed Module

1. Go to `http://localhost:8080` and sign in using the default credentials:
   **User Name:** `test@liferay.com`
   **Password:** `test`

1. Open the Gogo Shell by clicking the Applications Menu (![Global Menu icon](./module-projects/images/01.png)), selecting the *Control Panel* tab, and selecting *Gogo Shell* in the System category.

1. In the command field, use the `lb` command to list the bundles (modules).

    ```bash
    lb | grep K8S2
    ```

    Output:

    ```
    1193|Active     |   15|ACME K8S2 Greeting API (1.0.0)|1.0.0
    ```

    The module's ID is `1193`.

1. Print the module's runtime information.

    ```bash
    b 1193
    ```

    Output:

    ```
    com.liferay.learn.k8s2.api_1.0.0 [1193]
    Id=1193, Status=ACTIVE      Data Root=/home/jhinkey/portals/liferay-ce-portal-7.3.3-ga4/osgi/state/org.eclipse.osgi/1193/data
      "No registered services."
      No services in use.
      Exported packages
        com.acme.k8s2.greeting; version="1.0.0"[exported]
      No imported packages
      No fragment bundles
      No required bundles
    ```

    The output confirms the exported package `com.acme.k8s2.greeting`.

Now you know what module projects look like, how to build and deploy a module, and how to inspect a module at runtime.

## What's Next

APIs aren't exciting by themselves. [Module Collaboration](./apis-as-osgi-services.md) demonstrates using modules that implement and consume APIs.

## Additional Information

* [Configure Dependencies](./configuring-dependencies/configuring-dependencies.md)
* [Importing Packages](./importing-packages.md)
* [Exporting Packages](./exporting-packages.md)
* [Semantic Versioning](./semantic-versioning.md)