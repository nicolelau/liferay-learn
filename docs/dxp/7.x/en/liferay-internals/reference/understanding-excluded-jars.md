# Understanding Excluded JARs

[Portal property `module.framework.web.generator.excluded.paths`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework) declares JARs that are stripped from all Liferay DXP [generated WABs](../../reference/deploying-wars-wab-generator.md). These JARs are excluded from web application bundles (WABs) because Liferay DXP provides them already. All JARs listed for this property are excluded from a WAB, even if the WAB lists the JAR in a `portal-dependency-jars` property in its [`liferay-plugin-package.properties`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/liferay-plugin-package_7_2_0.properties.html) file.

If your WAR requires different versions of the packages Liferay DXP exports, you must include them in JARs named differently from the ones `module.framework.web.generator.excluded.paths` excludes.

For example, Liferay DXP's [`system.packages.extra`](https://github.com/liferay/liferay-portal/blob/7.3.3-ga4/modules/core/portal-bootstrap/system.packages.extra.bnd) module exports Spring Framework version 4.1.9 packages:

```
Export-Package:\
    ...
    org.springframework.*;version='4.1.9',\
    ...
```

Liferay DXP uses the `module.framework.web.generator.excluded.paths` portal property to exclude their JARs.

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

To use a different Spring Framework version in your WAR, you must name the corresponding Spring Framework JARs differently from the glob-patterned JARs `module.framework.web.generator.excluded.paths` lists.

For example, to use Spring Framework version 3.0.7's Spring AOP JAR, include it in your plugin's `WEB-INF/lib` but name it something other than `spring-aop.jar`. Adding the version to the JAR name (i.e., `spring-aop-3.0.7.RELEASE.jar`) differentiates it from the excluded JAR and prevents it from being stripped from the WAB (the bundled WAR).

## Additional Information

* [Configuring Dependencies](./configuring-dependencies.md)
* [Deploying WARs \(WAB Generator\)](../../reference/deploying-wars-wab-generator.md)