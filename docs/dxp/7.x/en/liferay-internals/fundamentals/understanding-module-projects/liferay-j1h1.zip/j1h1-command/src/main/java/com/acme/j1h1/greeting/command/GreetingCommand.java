package com.acme.j1h1.greeting.command;

import com.acme.j1h1.greeting.Greeting;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {"osgi.command.scope=greet", "osgi.command.function=greet"},
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