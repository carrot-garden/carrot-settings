package com.carrotgarden.settings;

import static org.junit.Assert.*;

import org.codehaus.jackson.node.ContainerNode;
import org.junit.Test;

public class TestApplyInclude {

	public void testCase(final String base) {

		System.out.println("#######");

		final NodeLoader loader = new NodeLoaderFile(base);

		final NodeApplierInclude includer = new NodeApplierInclude(loader);

		final ContainerNode source = loader.load("source.json");
		System.out.println("source = " + source);

		includer.apply(source);

		final ContainerNode target = loader.load("target.json");
		System.out.println("target = " + target);

		assertEquals(source, target);

	}

	@Test
	public void testCase1() {
		final String base = "./src/test/resources/include/case1";
		testCase(base);
	}

	@Test
	public void testCase2() {
		final String base = "./src/test/resources/include/case2";
		testCase(base);
	}

	@Test
	public void testCase3() {
		final String base = "./src/test/resources/include/case3";
		testCase(base);
	}

	@Test
	public void testCase4() {
		final String base = "./src/test/resources/include/case4";
		testCase(base);
	}

	@Test
	public void testCase5() {
		final String base = "./src/test/resources/include/case5";
		testCase(base);
	}

	@Test
	public void testCase6() {
		final String base = "./src/test/resources/include/case6";
		testCase(base);
	}

}
