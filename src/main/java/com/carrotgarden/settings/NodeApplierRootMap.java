package com.carrotgarden.settings;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ContainerNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeApplierRootMap extends NodeApplierBase {

	private final NodeRootMap map;

	public NodeApplierRootMap(final NodeRootMap map) {
		this.map = map;
	}

	@Override
	public void apply(final ContainerNode root) {
		map.clear();
		super.apply(root);
	}

	//

	@Override
	protected void apply(final ArrayNode root, final int index,
			final ValueNode value) {
		map.put(value, root);
	}

	@Override
	protected void apply(final ArrayNode root, final int index,
			final ArrayNode array) {
		map.put(array, root);
		apply(array);
	}

	@Override
	protected void apply(final ArrayNode root, final int index,
			final ObjectNode object) {
		map.put(object, root);
		apply(object);
	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ValueNode value) {
		map.put(value, root);
	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ArrayNode array) {
		map.put(array, root);
		apply(array);
	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ObjectNode object) {
		map.put(object, root);
		apply(object);
	}

}
