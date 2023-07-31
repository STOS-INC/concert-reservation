package com.stos.concert;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;

public class FixtureMonkeySupport {
	public static FixtureMonkey CONSTRUCTOR_FIXTURE = FixtureMonkey.builder()
		.objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
		.build();
	public static FixtureMonkey REFLECTION_FIXTURE = FixtureMonkey.builder()
		.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
		.build();

}
