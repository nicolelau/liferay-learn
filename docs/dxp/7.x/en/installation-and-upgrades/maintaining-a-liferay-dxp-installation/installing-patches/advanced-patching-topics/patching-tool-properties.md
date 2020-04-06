# Patching Tool Properties

The Patching Tool is configured by properties from a properties file: a profile properties file or a default properties file.

Examples: 

* `./patching-tool.sh my-profile install` looks for properties in a file called `my-profile.properties`.

* `./patching-tool.sh install` looks for properties in a default file called `default.properties`.

There are two groups of settings:

* [General Settings](#general-settings)
* [Proxy Settings](#proxy-settings)

## General Settings

**`patching.mode`:** Patches contain updated binary and source files. The mode determines which file type to apply.

* `binary` (the default): For updating your DXP installation.
* `source`: For updating source trees that extend DXP.

**`patches.folder`:** Specifies where to store patches. The default location is `./patches`.

**`war.path`:** Specifies the path to your DXP web application (the path to its exploded folder structure or to its `.war` file).

**`global.lib.path`** (`binary` mode only): Specifies the location for storing `.jar` files on the global classpath. Hint: `portal-kernel.jar` is on the global classpath. 

**`liferay.home`:** Specifies the parent folder of the `data`, `osgi`, and `tools` folders.

**`source.path`:** (`source` mode only) Specifies the DXP source tree location. 

## Proxy Settings

Service Pack detection is available behind a proxy server. To configure your proxy, use on of the following setting groups and replace all of the values, including `[PROXY_IP_ADDRESS]`:

```properties
### Proxy settings

# HTTP Proxy

#proxy.http.host=[PROXY_IP_ADDRESS]
#proxy.http.port=80
#proxy.http.user=user
#proxy.http.password=password

# HTTPS Proxy

proxy.https.host=[PROXY_IP_ADDRESS]
proxy.https.port=80
proxy.https.user=user
proxy.https.password=password

# SOCKS Proxy

#proxy.socks.host=[PROXY_IP_ADDRESS]
#proxy.socks.port=1080
#proxy.socks.user=user
#proxy.socks.password=password
```

## Additional Information

[Configuring the Patching Tool](./automatic-patching-tool-configuration) demonstrates using the configuration properties.
