package com.carrotgarden.settings;

import static org.junit.Assert.*;

import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ContainerNode;
import org.junit.Test;

public class TestApplyDefine {

	static void print(final NodeRootMap rootmap) {

		for (final Map.Entry<JsonNode, JsonNode> entry : rootmap.entrySet()) {
			System.out.println("===========");
			System.out.println("@@@ source = " + entry.getKey());
			System.out.println("@@@ target = " + entry.getValue());
		}

	}

	public void testCase(final String base) {

		System.out.println("#######");

		final NodeLoader loader = new NodeLoaderFile(base);

		final NodeRootMap rootmap = new NodeRootMap();

		final NodeApplierRootMap mapper = new NodeApplierRootMap(rootmap);

		final NodeApplierDefine definer = new NodeApplierDefine(rootmap);

		final NodeApplierClean cleaner = new NodeApplierClean();

		final ContainerNode source = loader.load("source.json");
		System.out.println("source = " + source);

		mapper.apply(source);

		definer.apply(source);

		cleaner.apply(source);

		final ContainerNode target = loader.load("target.json");
		System.out.println("target = " + target);

		assertEquals(source, target);

	}

	// @Test
	public void testCase1() {
		final String base = "./src/test/resources/define/case1";
		testCase(base);
	}

	// @Test
	public void testCase2() {
		final String base = "./src/test/resources/define/case2";
		testCase(base);
	}

	@Test
	public void testCase3() {
		final String base = "./src/test/resources/define/case3";
		testCase(base);
	}

}
