package com.carrotgarden.settings;

import java.io.File;

import org.codehaus.jackson.node.ContainerNode;
import org.codehaus.jackson.node.JsonNodeFactory;

public class NodeLoaderFile implements NodeLoader {

	private final String base;

	NodeLoaderFile(final String base) {
		this.base = base;
	}

	@Override
	public ContainerNode load(final String path) {

		final File file = new File(base, path.replace("base:", ""));

		try {
			return Json.load(file);
		} catch (final Exception e) {
			e.printStackTrace();
			return JsonNodeFactory.instance.objectNode();
		}

	}

}
