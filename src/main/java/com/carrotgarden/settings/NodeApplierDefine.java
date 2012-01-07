package com.carrotgarden.settings;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeApplierDefine extends NodeApplierBase {

	private final NodeRootMap map;

	public NodeApplierDefine(final NodeRootMap map) {
		this.map = map;
	}

	protected ObjectNode findParentObject(final JsonNode past) {

		final JsonNode next = map.get(past);

		// System.out.println(">>> past = " + past);
		// System.out.println(">>> next = " + next);

		if (next == null) {
			return null;
		}

		if (next.isObject()) {
			return (ObjectNode) next;
		} else {
			return findParentObject(next);
		}

	}

	protected JsonNode resolve(final ObjectNode past, final String prop,
			final JsonNode propOld) {

		// System.out.println("# past = " + past);

		final ObjectNode define = (ObjectNode) past.get(DEFINE);

		final JsonNode propNew;

		if (define == null) {
			propNew = propOld;
		} else {
			final JsonNode propNode = define.get(prop);
			if (propNode == null) {
				propNew = propOld;
			} else {
				propNew = propNode;
			}
		}

		final ObjectNode next = findParentObject(past);

		// System.out.println("# next = " + next);

		if (next == null) {
			return propNew;
		} else {
			return resolve(next, prop, propNew);
		}

	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ValueNode value) {

		// System.out.println("# root = " + root);
		// System.out.println("# name = " + name);

		final ObjectNode define = (ObjectNode) root.get(DEFINE);

		if (define == null) {
			return;
		}

		final String text = value.asText();

		for (final String prop : getFieldNames(define)) {
			if (text.equals(prop)) {
				final JsonNode node = resolve(root, prop, null);
				// map.put(node, root);
				root.put(name, node);
				return;
			}
		}

	}

}
