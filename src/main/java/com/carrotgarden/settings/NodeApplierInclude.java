package com.carrotgarden.settings;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ContainerNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeApplierInclude extends NodeApplierBase {

	private final NodeLoader loader;

	public NodeApplierInclude(final NodeLoader loader) {
		this.loader = loader;
	}

	protected void include(final ObjectNode source, final ObjectNode target,
			final boolean isOverride) {

		final ObjectNode temp = JsonNodeFactory.instance.objectNode();

		if (isOverride) {
			temp.putAll(target);
			temp.putAll(source);
		} else {
			temp.putAll(source);
			temp.putAll(target);
		}

		target.removeAll();
		target.putAll(temp);

	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ValueNode value) {

		if (!INCLUDE.equals(name)) {
			return;
		}

		root.remove(INCLUDE);

		final ContainerNode container = loader.load(value.asText());

		if (container.isObject()) {
			final ObjectNode object = (ObjectNode) container;
			apply(object);
			include(object, root, false);
			return;
		}

		new Exception("unexpected container").printStackTrace();

	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ArrayNode array) {

		if (!INCLUDE.equals(name)) {
			apply(array);
			return;
		}

		root.remove(INCLUDE);

		final ObjectNode temp = JsonNodeFactory.instance.objectNode();

		for (final JsonNode value : array) {

			if (value.isValueNode()) {

				final ContainerNode container = loader.load(value.asText());

				if (container.isObject()) {
					final ObjectNode object = (ObjectNode) container;
					apply(object);
					include(object, temp, true);
				} else {
					new Exception("unexpected container").printStackTrace();
				}

			} else {

				new Exception("unexpected container").printStackTrace();

			}

		}

		include(temp, root, false);

	}

}
