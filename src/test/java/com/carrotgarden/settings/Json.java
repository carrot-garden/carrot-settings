package com.carrotgarden.settings;

import java.io.File;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ContainerNode;

public class Json {

	private final static ObjectMapper mapper = new ObjectMapper();

	public static ContainerNode load(final File file) throws Exception {

		return mapper.readValue(file, ContainerNode.class);

	}

}
