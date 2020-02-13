# DXP and Elasticsearch

Re-enable search indexing by removing the following property from `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`:

```properties
indexReadOnly=true
```

```note::
   The `indexReadOnly ` value defaults to `false`, which tells Liferay to update the search indices.
```

```important::
   If you have not already done so, you must also [install and configure a standalone Elasticsearch](https://help.liferay.com/hc/en-us/articles/360028711132-Installing-Elasticsearch) or [Solr](placeholder) instance to run in production. By default, Liferay DXP ships with an embedded configuration for Elasticsearch for demo purposes. This configuration is not supported in production.
```

Once indexing is enabled and your search engine is configured, re-index Liferay DXP's search indices. Since DXP 7.2, you can do this in the UI after starting up Liferay DXP by navigating to _Control Panel_ → _Configuration_ → _Search_ and then clicking _Reindex all search indexes_.

![Reindex from the Search configuration page in the Control Panel.](./post-upgrade-tasks/images/01.png)
