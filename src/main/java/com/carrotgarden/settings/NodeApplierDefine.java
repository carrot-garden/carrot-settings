package com.carrotgarden.settings;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeApplierDefine extends NodeApplierBase {

	private final NodeRootMap map;

	public NodeApplierDefine(final NodeRootMap map) {
		this.map = map;
	}

}
