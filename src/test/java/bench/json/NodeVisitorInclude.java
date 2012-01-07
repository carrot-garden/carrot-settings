package bench.json;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeVisitorInclude extends NodeVisitorBase {

	protected ObjectNode load(final String path) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void assertTextNode(final JsonNode node) {
		if (!node.isTextual()) {
			throw new UnsupportedOperationException(
					"include from non-text is not permitted");
		}
	}

	protected void include(final JsonNode loader, final ObjectNode target,
			final boolean isOverride) {

		assertTextNode(loader);

		final String path = loader.getTextValue();

		final ObjectNode source = load(path);

		include(source, target, isOverride);

	}

	protected void include(final ObjectNode source, final ObjectNode target,
			final boolean isOverride) {

		if (!isOverride) {
			source.remove(getFieldNames(target));
		}

		target.putAll(source);

	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ValueNode node) {

		if (!INCLUDE.equals(name)) {
			return;
		}

		root.remove(INCLUDE);

		include(node, root, false);

	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ArrayNode array) {

		if (!INCLUDE.equals(name)) {
			return;
		}

		root.remove(INCLUDE);

		final ObjectNode temp = JsonNodeFactory.instance.objectNode();

		for (final JsonNode node : array) {

			include(node, temp, true);

		}

		include(temp, root, false);

	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ObjectNode node) {

		if (!INCLUDE.equals(name)) {
			return;
		}

		throw new UnsupportedOperationException(
				"include from object is not permitted");

	}

}
