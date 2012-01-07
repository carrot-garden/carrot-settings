package bench.json;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public abstract class NodeVisitorBase implements NodeVisitor {

	protected abstract void apply(ObjectNode root, String name, ValueNode node);

	protected abstract void apply(ObjectNode root, String name, ArrayNode node);

	protected abstract void apply(ObjectNode root, String name, ObjectNode node);

	protected static Set<String> getFieldNames(final ObjectNode root) {
		final Set<String> fieldNames = new HashSet<String>();
		final Iterator<String> iter = root.getFieldNames();
		while (iter.hasNext()) {
			fieldNames.add(iter.next());
		}
		return fieldNames;
	}

	@Override
	public void apply(final ObjectNode root) {

		final Set<String> fieldNames = getFieldNames(root);

		for (final String name : fieldNames) {

			final JsonNode node = root.get(name);

			if (node.isValueNode()) {
				final ValueNode value = (ValueNode) node;
				apply(root, name, value);
			}

			if (node.isArray()) {
				final ArrayNode array = (ArrayNode) node;
				apply(root, name, array);
			}

			if (node.isObject()) {
				final ObjectNode object = (ObjectNode) node;
				apply(root, name, object);
			}

		}

	}

}
