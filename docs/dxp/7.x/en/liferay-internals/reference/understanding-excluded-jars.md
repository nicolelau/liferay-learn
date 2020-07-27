# Understanding Excluded JARs

Liferay strips the JARs listed in the [`module.framework.web.generator.excluded.paths`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework) [portal property](../../../installation-and-upgrades/reference/portal-properties.md) from all [Liferay-generated web application bundles \(WABs\)](../../reference/deploying-wars-wab-generator.md). Liferay excludes these JARs from WABs because it provides the packages from these JARs already.

```note::
    Liferay excludes these JARs from WABs, even if the WABs list the JARs in a ``portal-dependency-jars`` property in their `liferay-plugin-package.properties <https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/liferay-plugin-package_7_2_0.properties.html>`_ file.
```

If your WAR requires different versions of the packages Liferay exports, include the packages in JARs named differently from the JARs `module.framework.web.generator.excluded.paths` excludes.

For example, here's how to use different Spring Framework package versions than the ones Liferay provides:

1. Examine the [`system.packages.extra`](https://github.com/liferay/liferay-portal/blob/7.3.3-ga4/modules/core/portal-bootstrap/system.packages.extra.bnd) module package exports to determine the package version that Liferay provides. For example, the `system.packages.extra` module exports Spring Framework version 4.1.9 packages:

    ```
    Export-Package:\
        ...
        org.springframework.*;version='4.1.9',\
        ...
    ```

1. If you require a different package version, [find an artifact](../fundamentals/configuring-dependencies/finding-artifacts.md) that provides it.

1. Check if Liferay's [`module.framework.web.generator.excluded.paths`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework) excludes that artifact. For example, The `module.framework.web.generator.excluded.paths` portal property strips these Spring Framework JARs from WABs:

    ```properties
    module.framework.web.generator.excluded.paths=\
        ...
        WEB-INF/lib/spring-aop.jar,\
        WEB-INF/lib/spring-aspects.jar,\
        WEB-INF/lib/spring-beans.jar,\
        WEB-INF/lib/spring-context.jar,\
        WEB-INF/lib/spring-context-support.jar,\
        WEB-INF/lib/spring-core.jar,\
        WEB-INF/lib/spring-expression.jar,\
        WEB-INF/lib/spring-jdbc.jar,\
        WEB-INF/lib/spring-jms.jar,\
        WEB-INF/lib/spring-orm.jar,\
        WEB-INF/lib/spring-oxm.jar,\
        WEB-INF/lib/spring-tx.jar,\
        WEB-INF/lib/spring-web.jar,\
        WEB-INF/lib/spring-webmvc.jar,\
        WEB-INF/lib/spring-webmvc-portlet.jar,\
        ...
    ```

1. If the artifact you need is named the same as an artifact that Liferay excludes (see the previous step), rename the JAR differently from the glob-patterned JAR `module.framework.web.generator.excluded.paths` lists. For example, to use Spring Framework version 3.0.7's Spring AOP JAR, include it in your plugin's `WEB-INF/lib` folder but rename it something other than `spring-aop.jar` (the JAR listed in the `module.framework.web.generator.excluded.paths` property).

    ```tip::
       Adding the version to a JAR name (i.e., `spring-aop-3.0.7.RELEASE.jar`) differentiates it from the excluded JAR and prevents it from being stripped from the WAB (the bundled WAR).
    ```

You are now including the version of the JAR your WAB requires.

## Additional Information

* [Configuring Dependencies](../fundamentals/configuring-dependencies.md)
* [Deploying WARs \(WAB Generator\)](./deploying-wars-wab-generator.md)