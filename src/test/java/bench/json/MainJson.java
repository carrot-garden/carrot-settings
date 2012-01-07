package bench.json;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainJson {

	static final Logger logger = LoggerFactory.getLogger(MainJson.class);

	static final String REF = "#ref";
	static final String OBJ = "#obj";
	static final String VARIABLE_PREFIX = "$";

	public static void main(final String[] args) throws Exception {

		logger.debug("init");

		final ObjectMapper mapper = new ObjectMapper();

		// mapper.setVisibilityChecker(mapper.getVisibilityChecker().with(
		// JsonAutoDetect.Visibility.NONE));

		final File file = new File("src/test/resources/main.json");

		final String src = FileUtils.readFileToString(file);

		// final Map<String, Object> root = mapper.readValue(src, Map.class);
		// final ObjectNode root = mapper.readValue(src, ObjectNode.class);
		final JsonNode root = mapper.readValue(src, JsonNode.class);

		if (!root.isObject()) {
			throw new Exception("room must be a json object");
		}

		logger.debug("root : " + root);

		final NodeMap vars = extractVars((ObjectNode) root);

		logger.debug("vars : " + vars);

		logger.debug("root : " + root);

		logger.debug("done");

	}

	static void applyVisitor(final NodeVisitor visitor, final JsonNode node) {

		final Iterator<String> iter = node.getFieldNames();

		while (iter.hasNext()) {

			final String name = iter.next();

			// visitor.visit(node.get(name));

		}

	}

	static void applyVars(final NodeMap vars, final ObjectNode node) {

		final Iterator<String> iter = node.getFieldNames();

		while (iter.hasNext()) {

			final String name = iter.next();

		}

	}

	static NodeMap extractVars(final ObjectNode node) {

		final NodeMap vars = new NodeMap();

		final Iterator<String> iter = node.getFieldNames();

		while (iter.hasNext()) {

			final String name = iter.next();

			if (!name.startsWith(VARIABLE_PREFIX)) {
				continue;
			}

			vars.put(name, node.get(name));

		}

		final Set<String> keys = vars.keySet();

		node.remove(keys);

		return vars;

	}

}
