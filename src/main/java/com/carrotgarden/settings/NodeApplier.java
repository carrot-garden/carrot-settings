package com.carrotgarden.settings;

import org.codehaus.jackson.node.ContainerNode;

public interface NodeApplier {

	String INCLUDE = "#include";
	String DEFINE = "#define";
	String OBJECT = "#object";

	void apply(ContainerNode root);

}
