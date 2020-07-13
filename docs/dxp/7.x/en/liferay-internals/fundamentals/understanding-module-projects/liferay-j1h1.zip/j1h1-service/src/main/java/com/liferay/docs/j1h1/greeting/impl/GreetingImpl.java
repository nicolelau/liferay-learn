package com.liferay.docs.j1h1.greeting.impl;

import com.liferay.docs.j1h1.greeting.Greeting;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = Greeting.class
)
public class GreetingImpl implements Greeting {

    @Override
    public void greet(String name) {

        System.out.println("Hello " + name + "!");
    }

}