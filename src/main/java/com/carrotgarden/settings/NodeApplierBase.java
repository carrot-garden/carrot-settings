package com.carrotgarden.settings;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ContainerNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeApplierBase implements NodeApplier {

	//

	protected static Set<String> getFields(final ContainerNode root) {

		final Set<String> set = new HashSet<String>();

		if (root.isArray()) {
			final int size = root.size();
			for (int index = 0; index < size; index++) {
				set.add(Integer.toString(index));
			}
		}

		if (root.isObject()) {
			final Iterator<String> iter = root.getFieldNames();
			while (iter.hasNext()) {
				set.add(iter.next());
			}
		}

		return set;
	}

	protected static JsonNode get(final ContainerNode root, final String field) {
		if (root.isArray()) {
			return root.get(Integer.parseInt(field));
		} else {
			return root.get(field);
		}
	}

	protected static void put(final ContainerNode root, final String field,
			final JsonNode node) {
		if (root.isArray()) {
			((ArrayNode) root).set(Integer.parseInt(field), node);
		} else {
			((ObjectNode) root).put(field, node);
		}
	}

	protected static void assertTextNode(final JsonNode node) {
		if (!node.isTextual()) {
			throw new UnsupportedOperationException("expecting text node");
		}
	}

	//

	protected void apply(final ContainerNode root, final String field,
			final ValueNode node) {
	}

	protected void apply(final ContainerNode root, final String field,
			final ContainerNode node) {
	}

	//

	@Override
	public void apply(final ContainerNode root) {

		final Set<String> fieldSet = getFields(root);

		for (final String field : fieldSet) {

			final JsonNode node = get(root, field);

			if (node.isValueNode()) {
				apply(root, field, (ValueNode) node);
			} else {
				apply(root, field, (ContainerNode) node);
				apply((ContainerNode) node);
			}

		}

	}

}
