package com.carrotgarden.settings;

import org.codehaus.jackson.node.ObjectNode;

public class NodeApplierClean extends NodeApplierBase {

	public NodeApplierClean() {
	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ObjectNode object) {

		root.remove(DEFINE);

		apply(object);

	}

}
