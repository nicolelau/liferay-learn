# Exporting Packages

An OSGi bundle's Java packages are private by default. To expose a package, you must explicitly export it. This way you share only the classes you want to share. Exporting a package in your OSGi bundle (bundle) manifest makes all the package's classes available for other bundles to [import](./importing-packages.md).

To export a package, add it to your module's or plugin's `Export-Package` OSGi header. A header exporting `com.liferay.petra.io` and `com.liferay.petra.io.unsync` would look like this:

```properties
Export-Package:\ com.liferay.petra.io,\ com.liferay.petra.io.unsync
```

The correct location for the header depends on your project's type:

| Project Type | `Export-Package` header location |
| :----------- | :------------------------------- |
| Module JAR (uses Bnd)     | `[project]/bnd.bnd` |
| Module JAR (doesn't use Bnd) | `[module JAR]/META-INF/MANIFEST.MF` |
| Plugin WAR | `WEB-INF/liferay-plugin-package.properties` |

Module projects created using [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md) or [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md) use [Bnd](http://bnd.bndtools.org/). On building such a project's module JAR, Bnd propagates the OSGi headers from the project's `bnd.bnd` file to the JAR's `META-INF/MANIFEST.MF`.

In module projects that don't use Bnd, you must manually add package exports to an `Export-Package` header in the module JAR's `META-INF/MANIFEST.MF`.

In plugin WAR projects, you must add package exports to an `Export-Package` header in the project's `WEB-INF/liferay-plugin-package.properties`. On copying the WAR into the `[Liferay Home]/deploy` folder, the [WAB Generator](../reference/deploying-wars-wab-generator.md) propagates the OSGi headers from the WAR's `liferay-plugin-package.properties` file to the `META-INF/MANIFEST.MF` file in the generated Web Application Bundle (WAB).

```note::
   Bnd makes a module's exported packages *substitutable*. That is, the OSGi framework can substitute your module's exported package with a compatible package of the same name, but potentially different version, that's exported from a different OSGi bundle. Bnd enables this for your module by automatically making your module import every package it exports. In this way, your module can work on its own, but can also work in conjunction with bundles that provide a different (compatible) version, or even the same version, of the package. A package from another bundle might provide better "wiring" opportunities with other bundles. `Peter Kriens' blog post <http://blog.osgi.org/2007/04/importance-of-exporting-nd-importing.html>`_ provides more details on how substitutable exports works.
```

```important::
   Don't export the same package from different JARs. Multiple exports of the same package leads to "split package" issues, whose side affects differ from case to case.
```

Now you can share your module's or plugin's terrific [EDITOR: or terrible!] packages with other OSGi bundles!

## Additional Information

* [Configuring Dependencies](./configuring-dependencies/configuring-dependencies.md)

* [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)

* [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)

* [Deploying WARs \(WAB Generator\)](../reference/deploying-wars-wab-generator.md)

* [Semantic Versioning](./semantic-versioning.md)