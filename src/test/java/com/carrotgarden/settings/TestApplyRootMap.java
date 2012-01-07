package com.carrotgarden.settings;

import static org.junit.Assert.*;

import org.codehaus.jackson.node.ContainerNode;
import org.junit.Test;

public class TestApplyRootMap {

	public void testCase(final String base, final int size, final int count) {

		System.out.println("#######");

		final NodeLoader loader = new NodeLoaderFile(base);

		final NodeRootMap rootmap = new NodeRootMap();

		final NodeApplierRootMap mapper = new NodeApplierRootMap(rootmap);

		final ContainerNode source = loader.load("source.json");
		System.out.println("source = " + source);

		mapper.apply(source);
		System.out.println("rootmap = " + rootmap);

		assertEquals(source.size(), size);

		assertEquals(rootmap.size(), count);

	}

	@Test
	public void testCase1() {
		final String base = "./src/test/resources/rootmap/case1";
		testCase(base, 1, 1);
	}

	@Test
	public void testCase2() {
		final String base = "./src/test/resources/rootmap/case2";
		testCase(base, 2, 3);
	}

}
