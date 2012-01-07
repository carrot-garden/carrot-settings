package com.carrotgarden.settings;

import org.codehaus.jackson.node.ContainerNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeApplierInclude extends NodeApplierBase {

	private final NodeLoader loader;

	public NodeApplierInclude(final NodeLoader loader) {
		this.loader = loader;
	}

	protected void include(final ObjectNode source, final ObjectNode target,
			final boolean isOverride) {

		if (!isOverride) {
			source.remove(getFields(target));
		}

		target.putAll(source);

	}

	// @Override
	protected void apply(final ObjectNode root, final String name,
			final ValueNode node) {

		if (!INCLUDE.equals(name)) {
			return;
		}

		root.remove(INCLUDE);

		final ContainerNode container = loader.load(node.asText());

		if (container.isObject()) {
			include((ObjectNode) container, root, false);
			return;
		}

		new Exception("unexpected container").printStackTrace();

	}

	@Override
	protected void apply(final ContainerNode root, final String field,
			final ValueNode node) {

		if (!INCLUDE.equals(field)) {
			return;
		}

		final ContainerNode container = loader.load(node.asText());

		apply(container);

	}

	@Override
	protected void apply(final ContainerNode root, final String field,
			final ContainerNode node) {

		if (!INCLUDE.equals(field)) {
			return;
		}

		new Exception("unexpected").printStackTrace();

	}

}
