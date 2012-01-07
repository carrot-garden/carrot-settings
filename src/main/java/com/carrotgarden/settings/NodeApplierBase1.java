package com.carrotgarden.settings;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ContainerNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeApplierBase1 implements NodeApplier {

	protected void apply(final ArrayNode root, final int index,
			final ValueNode node) {
	}

	protected void apply(final ArrayNode root, final int index,
			final ArrayNode node) {
	}

	protected void apply(final ArrayNode root, final int index,
			final ObjectNode node) {
	}

	protected void apply(final ObjectNode root, final String name,
			final ValueNode node) {
	}

	protected void apply(final ObjectNode root, final String name,
			final ArrayNode node) {
	}

	protected void apply(final ObjectNode root, final String name,
			final ObjectNode node) {
	}

	//

	protected static Set<String> getFieldNames(final ObjectNode root) {
		final Set<String> set = new HashSet<String>();
		final Iterator<String> iter = root.getFieldNames();
		while (iter.hasNext()) {
			set.add(iter.next());
		}
		return set;
	}

	protected static void assertTextNode(final JsonNode node) {
		if (!node.isTextual()) {
			throw new UnsupportedOperationException("expecting text node");
		}
	}

	//

	protected void apply(final ArrayNode root) {

		final int size = root.size();

		for (int index = 0; index < size; index++) {

			final JsonNode node = root.get(index);

			if (node.isValueNode()) {
				apply(root, index, (ValueNode) node);
			}

			if (node.isArray()) {
				apply(root, index, (ArrayNode) node);
			}

			if (node.isObject()) {
				apply(root, index, (ObjectNode) node);
			}

		}

	}

	protected void apply(final ObjectNode root) {

		final Set<String> fieldNames = getFieldNames(root);

		for (final String name : fieldNames) {

			final JsonNode node = root.get(name);

			if (node.isValueNode()) {
				apply(root, name, (ValueNode) node);
			}

			if (node.isArray()) {
				apply(root, name, (ArrayNode) node);
			}

			if (node.isObject()) {
				apply(root, name, (ObjectNode) node);
			}

		}

	}

	@Override
	public void apply(final ContainerNode root) {

		if (root.isArray()) {
			apply((ArrayNode) root);
		}

		if (root.isObject()) {
			apply((ObjectNode) root);
		}

	}

}
