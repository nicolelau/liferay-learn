# Exporting Packages

An OSGi bundle's Java packages are private by default. To expose a package, you must explicitly export it. This way you share only the classes you want to share. Exporting a package in your OSGi bundle (bundle) manifest makes all the package's classes available for other bundles to [import](./importing-packages.md).

To export a package, add it to your module's `Export-Package` OSGi header in your `bnd.bnd` file. A header exporting `com.liferay.petra.io` and `com.liferay.petra.io.unsync` would look like this:

```groovy
Export-Package: com.liferay.petra.io, com.liferay.petra.io.unsync
```

[Workspace](../../developing-applications/tooling/liferay-workspace.md)-based projects created using [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md) or [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md) use [Bnd](http://bnd.bndtools.org/). On building such a project's module JAR, Bnd propagates the OSGi headers from the project's `bnd.bnd` file to the JAR's `META-INF/MANIFEST.MF`.

```important::
   Don't export the same package from different JARs. Multiple exports of the same package leads to "split package" issues, whose side affects differ from case to case.
```

```note::
   Bnd makes a module's exported packages *substitutable*. That is, the OSGi framework can substitute your module's exported package with a compatible package of the same name, but potentially different version, that's exported from a different OSGi bundle. Bnd enables this for your module by automatically making your module import every package it exports. In this way, your module can work on its own, but can also work in conjunction with bundles that provide a different (compatible) version, or even the same version, of the package. A package from another bundle might provide better "wiring" opportunities with other bundles. `Peter Kriens' blog post <http://blog.osgi.org/2007/04/importance-of-exporting-nd-importing.html>`_ provides more details on how substitutable exports works.
```

Now you can share your module's terrific packages with other OSGi bundles!

## Additional Information

* [Configuring Dependencies](./configuring-dependencies/configuring-dependencies.md)

* [Semantic Versioning](./semantic-versioning.md)

* [Liferay Workspace](../../developing-applications/tooling/liferay-workspace.md)

* [Blade CLI](../../../developing-applications/tooling/blade-cli/generating-projects-with-blade-cli.md)

* [Liferay Developer Studio](../../developing-applications/tooling/developer-studio.md)

* [Exporting Packages Outside of Workspace](../reference/exporting-packages-outside-of-workspace.md)