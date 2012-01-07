package bench.json;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeVisitorRootMap extends NodeVisitorBase {

	private NodeRootMap map;

	public void apply(final ObjectNode root, final NodeRootMap map) {
		this.map = map;
		apply(root);
		this.map = null;
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
		for (final JsonNode node : array) {
			map.put(node, array);
		}
	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ObjectNode object) {
		map.put(object, root);
		apply(object);
	}

}
