package com.carrotgarden.settings;

import org.codehaus.jackson.node.ContainerNode;

public interface NodeLoader {

	ContainerNode load(String path);

}
