# APIs as OSGi Services

Liferay's runtime environment supports deploying multiple API implementations and dynamically binding them to classes that use them. This is done using OSGi Services. It's a way of developing and working with APIs that allows objects to collaborate dynamically.

Here are the service class roles:

**API** defines a service. The [`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) annotation makes the class a service type that a component class (component) can implement or use.

**Provider** is a component that implements a service. A [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) annotation specifies the service type it provides.

**Consumer** is a component that uses one or more APIs. At run time, the framework injects a matching provider into each of the consumer class fields that specify the [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html). If no matching provider exists, the consumer component is unsatisfied and inactive. Note, each consumer class must also be a component (i.e., must have a [`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) annotation).

```note::
   Liferay uses the `Declarative Services (DS) <https://enroute.osgi.org/FAQ/300-declarative-services.html)>`_ service component model on top of OSGi Services. DS service components are marked with the `@Component` annotation and implement or extend a service class. Service components can reference (`@Reference`) and use each other's services.
```

The Service Component Runtime (SCR) registers service provider components and binds them to other components that reference them.

Here's how the "magic" happens:

1. **Service registration:** On installing a module that contains a service component (class with an `@Component` annotation), the SCR creates a component configuration that associates the component with its specified service type, and stores the configuration in the service registry.

1. **Service reference handling:** On installing a module whose service component references another service type (API), the SCR searches the registry for a component configuration that matches the service type and on finding a match binds an instance of that service to the referring component.

It's publish, find, and bind at its best!

The Greeting Command example modules demonstrate API, provider, and consumer.

## Deploying the Example

TODO write new example.

Deploy the Greeting Command example modules per the instructions in [Understanding Module Projects](./understanding-module-projects.md). After all three modules are deployed, continue with the following sections to examine the API, provider, and consumer.

## Examine the API

An API specifies an object type and its operations. Here's an API for greetings:

```java
package com.liferay.docs.j1h1.greeting;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface Greeting {

    public void greet(String name);

}
```

The interface's [`@ProviderType`](https://docs.osgi.org/javadoc/osgi.annotation/7.0.0/org/osgi/annotation/versioning/ProviderType.html) annotation tells the SCR that anything implementing the interface is a provider. This API's `greet` method takes a name `String` as input.

## Examine the Provider

A provider implements an API. The `GreetingImpl` class below implements the `Greeting` API:

```java
package com.liferay.docs.j1h1.greeting.impl;

import com.liferay.docs.j1h1.greeting.Greeting;

import org.osgi.service.component.annotations.Component;

@Component(
    service = Greeting.class
)
public class GreetingImpl implements Greeting {

    @Override
    public void greet(String name) {

        System.out.println("Hello " + name + "!");
    }

}
```

The `@Component` annotation and its  `service = Greeting.class` attribute make the `GreetingImpl` class a `Greeting` service provider. On deploying the `GreetingImpl` class in a module, the SCR registers the `GreetingImpl` component as providing the `Greeting` service.

Use the [Gogo Shell](./using-the-gogo-shell/using-the-gogo-shell.md) to inspect the module's service capabilities.

1. [Go to the Gogo Shell](./using-the-gogo-shell/using-the-gogo-shell.md).

2. Execute the following command, replacing `[Bundle_ID]` with the service module's ID. Note: the ID is printed in the console when the module is deployed and is also printed next to the module's bundle symbolic name in the bundle listing printed by executing the `lb` Gogo shell command.

    ```bash
    g! inspect capability service [Bundle_ID]
    ```

    Here's the greeting service module inspection.

    ```bash
    g! inspect capability service  1178
    ```

    Output:

    ```
    com.liferay.docs.j1h1.greeting.service_1.0.0 [1178] provides:
    -------------------------------------------------------------
    service; com.liferay.docs.j1h1.greeting.Greeting with properties:
       service.id = 12780
       service.bundleid = 1178
       service.scope = bundle
       component.name = com.liferay.docs.j1h1.greeting.impl.GreetingImpl
       component.id = 6020
       Used by:
          com.liferay.docs.j1h1.greeting.command_1.0.0 [1179]
      ```

The command lists the services that the module provides via its components. The `component.name = com.liferay.docs.j1h1.greeting.impl.GreetingImpl` property shows that `GreetingImpl` provides the `com.liferay.docs.j1h1.greeting.Greeting` service. Lastly, the service component is used by (consumed by) by the `com.liferay.docs.j1h1.greeting.command_1.0.0` module (its bundle ID is `1179`). The consumer is up next.

## Examine the Consumer

Components (classes annotated with `@Component`) may access other service components. Adding the [`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html) annotation to a component field specifies that the field is to be injected an implementation of that field type. Here's a component that consumes a `Greeting` service.

```java
package com.liferay.docs.j1h1.greeting.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.j1h1.greeting.Greeting;

@Component(
    immediate = true,
    property = {
        "osgi.command.scope=greet",
        "osgi.command.function=greet"
    },
    service = Object.class
)
public class GreetingCommand {

    public void greet(String name) {

        Greeting greeting = _greeting;

        greeting.greet(name);
    }

    @Reference
    private Greeting _greeting;

}
```

The `@Component` annotation and `service = Object.class` attribute make the `GreetingCommand` class a component of type `Object`. Providing an implementation of `Object` is ordinary, but being a Declarative Services component allows `GreetingCommand` to access other components via the `@Reference` annotation.

The `@Reference` annotation on the `private Greeting _greeting` field marks that field to be injected with a `Greeting` service provider at run time.

You can use the `scr:info [component name]` [Gogo Shell command](./using-the-gogo-shell/gogo-shell-commands.md) to inspect the consumer service component. Replace `[component name]` with your component's name (e.g., the consumer's fully qualified class name).

Here's information on the `GreetingCommand` component.

```bash
g! scr:info com.liferay.docs.j1h1.greeting.command.GreetingCommand
```

Output:

```
Component Description: com.liferay.docs.j1h1.greeting.command.GreetingCommand
=============================================================================
Class:         com.liferay.docs.j1h1.greeting.command.GreetingCommand
Bundle:        1179 (com.liferay.docs.j1h1.greeting.command:1.0.0)
Enabled:       true
Immediate:     true
Services:      [java.lang.Object]
Scope:         singleton
Config PID(s): [com.liferay.docs.j1h1.greeting.command.GreetingCommand], Policy: optional
Base Props:    (2 entries)
  osgi.command.function = greet
  osgi.command.scope = greet

Component Configuration Id: 6021
--------------------------------
State:        ACTIVE
Service:      12781 [java.lang.Object]
Config Props: (4 entries)
  component.id = 6021
  component.name = com.liferay.docs.j1h1.greeting.command.GreetingCommand
  osgi.command.function = greet
  osgi.command.scope = greet
References:   (total 1)
  - _greeting: com.liferay.docs.j1h1.greeting.Greeting SATISFIED 1..1 static
    target=(*) scope=bundle (1 binding):
    * Bound to [12780] from bundle 1178 (com.liferay.docs.j1h1.greeting.service:1.0.0)
```

The Component Configuration's `References:` section shows that the consumer component's `_greeting` field of service type `com.liferay.docs.j1h1.greeting.Greeting` is satisfied and that the field is bound to the service component `12780` from bundle `1178 (com.liferay.docs.j1h1.greeting.service:1.0.0)`. Note, the `Greeting` provider inspection showed the `Greeting` service's `service.id` value was `12780`.

Congratulations! The API, provider, and consumer are working in concert.

## What's Next

Here are some ways to start using OSGi Services:

* Extending Liferay (Coming soon)
* Creating Services with Service Builder (Coming soon)
* [Create a Module Project](../fundamentals/understanding-module-projects.md)

## Additional Information

* [Getting started with OSGi at OSGi EnRoute](https://enroute.osgi.org/)
* [Declarative Services](https://enroute.osgi.org/FAQ/300-declarative-services.html)
* [OSGi Alliance](https://www.osgi.org/)
* [Gogo Shell Commands](./using-the-gogo-shell/gogo-shell-commands.md)