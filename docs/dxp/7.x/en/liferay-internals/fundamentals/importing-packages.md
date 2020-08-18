# Importing Packages

Modules often depend on functionality from other modules. Modules in the OSGi framework must [export](./exporting-packages.md) a package for your plugin to import it.

When an OSGi module imports packages, the OSGi framework finds other registered bundles (modules) that export the needed packages and wires them to the importing bundle. At run time, the importing bundle gets the class from the wired bundle that exports the package.

For this to happen, a bundle's `META-INF/MANIFEST.MF` file must specify the `Import-Package` OSGi manifest header with a comma-separated list of the Java packages it needs. For example, if a bundle needs classes from the `javax.portlet` and `com.liferay.portal.kernel.util` packages, it must specify them like so:

```properties
Import-Package: javax.portlet,com.liferay.portal.kernel.util
```

Import packages must sometimes be specified manually, but not always. Conveniently, DXP project templates and [tools](../../developing-applications/tooling/developer-tools-overview.md) automatically detect the packages a bundle uses and add them to the package imports in the bundle's manifest. Here are the different package import scenarios:

* [Automatic Package Import Generation](#automatic-package-import-generation)

* [Manually Adding Package Imports](#manually-adding-package-imports)

Read below to explore how package imports are specified in these scenarios.

## Automatic Package Import Generation

Workspace-based projects created using [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md) or [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md) use [Bnd](http://bnd.bndtools.org/). On building such a project's module JAR, Bnd detects the packages the module uses and generates a `META-INF/MANIFEST.MF` file whose `Import-Package` header specifies the packages.

```note::
   Liferay's project templates use `a third-party Gradle plugin <https://github.com/TomDmitriev/gradle-bundle-plugin>`_ to invoke Bnd.
```

For example, suppose you're developing a Liferay module. In most cases, you specify your module's dependencies in your `build.gradle` file. At build time, the Gradle module plugin reads your `build.gradle` file and Bnd adds the required `Import-Package` headers to your module JAR's `META-INF/MANIFEST.MF`.

Here's an example dependencies section from the [Gogo Command Sample](https://github.com/liferay/liferay-blade-samples/tree/7.3/liferay-workspace/extensions/gogo)'s `build.gradle` file:

```groovy
dependencies {
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
    compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"
}
```

And here's the `Import-Package` header that's generated in the module JAR's `META-INF/MANIFEST.MF` file:

```properties
Import-Package: com.liferay.portal.kernel.service;version="[4.3,5)"
```

Note that your build file need only specify artifact dependencies. Bnd examines your module's class path to determine which packages from those artifacts contain classes your application uses and imports the packages. The examination includes all classes found in the class path--even those from embedded [third party library JARs](./configuring-dependencies/resolving-third-party-library-package-dependencies.md).

Regarding classes used by a plugin WAR, Liferay's [WAB Generator](../reference/deploying-wars-wab-generator.md) detects their use in the WAR's JSPs, descriptor files, and classes (in `WEB-INF/classes` and embedded JARs). The WAB Generator searches the `web.xml`, `liferay-web.xml`, `portlet.xml`, `liferay-portlet.xml`, and `liferay-hook.xml` descriptor files. It adds package imports for classes that are neither found in the plugin's `WEB-INF/classes` folder nor in its embedded JARs.

### Java API Packages

Packages for Java APIs, such as Java Portlet, aren't [semantically versioned](./semantic-versioning.md) but have Portable Java Contracts. Each API's contract specifies the JSR it satisfies. Bundles that use these APIs must specify requirements on the API contracts. The contract requirement specifies your bundle's relationship with the imported API packages. If the system you're running does *not* provide the exact contract, your bundle does not resolve. Resolving the missing package is better than handling an incompatibility failure during execution.

* **Blade CLI and Liferay Developer Studio module projects** specify Portable Java Contracts automatically! For example, if your Blade CLI or Liferay Developer Studio module uses the Java Portlet API and you compile against the Java Portlet 2.0 artifact, a contract requirement for the package is added to your module's manifest.

* **Module projects that use Bnd but are not created using Blade CLI or Liferay Developer Studio** must specify contracts in their `bnd.bnd` file. For example, here are contract instructions for Java Portlet and Java Servlet APIs:

    ```
    -contract: JavaPortlet,JavaServlet
    ```

    At build time, Bnd adds the contract instructions to your module's manifest. It adds a requirement for the first version of the API found in your classpath and *removes* version range information from ``Import-Package`` entries for corresponding API packages---the package version information isn't needed.

* **Projects that don't use Bnd** must specify contracts in their OSGi bundle manifest. For example, here's the specified contract for `JavaPortlet` 2.0, which goes in your `META-INF/MANIFEST.MF` file:

    ```
    Import-Package: javax.portlet Require-Capability: osgi.contract;filter:=(&(osgi.contract=JavaPortlet)(version=2.0))
    ```

For Portable Java Contract details, see [Portable Java Contract Definitions](https://www.osgi.org/portable-java-contract-definitions/).

## Manually Adding Package Imports

Bnd and the [WAB Generator](../reference/deploying-wars-wab-generator.md) don't add package imports for classes referenced in these places:

* Unrecognized descriptor file
* Custom or unrecognized descriptor element or attribute
* Reflection code
* Class loader code

In such cases, you must manually determine the packages required and add them to an `Import-Package` OSGi header in the location appropriate to your project type:

| Project type | `Import-Package` header location |
| :----------- | :------------------------------- |
| Module JAR (uses Bnd)     | `[project]/bnd.bnd` |
| Module JAR (doesn't use Bnd) | `[module JAR]/META-INF/MANIFEST.MF` |
| Plugin WAR | `WEB-INF/liferay-plugin-package.properties` |

```note::
   The WAB Generator refrains from adding WAR project embedded third-party JARs to a WAB if `Liferay already exports the JAR's packages <../../developing-applications/reference/jars-excluded-from-wabs.md>`_.

   If your WAR requires a different version of a third-party package that DXP exports, specify that package in your ``Import-Package:`` list. Then if the package provider is an OSGi module, publish its exported packages by deploying the module. If the package provider is not an OSGi module, follow the instructions for `adding third-party libraries <./configuring-dependencies/resolving-third-party-library-package-dependencies.md>`_.
```

Congratulations! Now you can import all kinds of packages for your modules and plugins to use.

## Additional Information

* [Configuring Dependencies](./configuring-dependencies/configuring-dependencies.md)

* [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)

* [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)

* [Deploying WARs \(WAB Generator\)](../reference/deploying-wars-wab-generator.md)

* [Semantic Versioning](./semantic-versioning.md)
