package com.liferay.docs.j1h1.greeting;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface Greeting {

        public void greet(String name);

}