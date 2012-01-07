package bench.json;

import org.codehaus.jackson.node.ObjectNode;

public interface NodeVisitor {

	String INCLUDE = "#include";
	String DEFINE = "#define";
	String OBJECT = "#object";

	// String PARENT = "#parent";

	void apply(ObjectNode root);

}
