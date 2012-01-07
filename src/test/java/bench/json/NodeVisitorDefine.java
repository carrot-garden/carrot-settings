package bench.json;

import java.util.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.ValueNode;

public class NodeVisitorDefine extends NodeVisitorBase {

	protected void define(final ObjectNode root, final String key) {

		final ObjectNode define = root.with(DEFINE);

		final JsonNode value = define.get(key);

	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ValueNode node) {

		final String text = node.asText();

		final ObjectNode define = root.with(DEFINE);

		final Iterator<String> iter = define.getFieldNames();

		while (iter.hasNext()) {

			final String key = iter.next();

			if (text.contains(key)) {

			}

		}

	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ArrayNode array) {

	}

	@Override
	protected void apply(final ObjectNode root, final String name,
			final ObjectNode node) {

	}

}
