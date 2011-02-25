/*******************************************************************************
 * Copyright (C) 2010, Matthias Sohn <matthias.sohn@sap.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.example.calc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to register operations
 */
public class Operations {
	public static final Operations INSTANCE = new Operations();

	private final Map<String, Operation> commands = new HashMap<String, Operation>();

	public void register(Operation op) {
		assert (commands.get(op.getName()) == null);
		commands.put(op.getName(), op);
	}

	public void reset() {
		commands.clear();
	}

	public Operation getOperation(String name) {
		return commands.get(name);
	}

	public int size() {
		return commands.size();
	}

	public String getOperationName(int i) {
		String keys[] = new String[commands.keySet().size()];
		(commands.keySet()).toArray(keys);
		Arrays.sort(keys);
		return keys[i];
	}
}
