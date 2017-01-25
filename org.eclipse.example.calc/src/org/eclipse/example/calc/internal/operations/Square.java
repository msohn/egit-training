/*******************************************************************************
 * Copyright (C) 2010, Matthias Sohn <matthias.sohn@sap.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.example.calc.internal.operations;

import org.eclipse.example.calc.UnaryOperation;

/**
 * Square operation
 */
public class Square extends AbstractOperation implements UnaryOperation {

	@Override
	public String getName() {
		return "^2";
	}

	@Override
	public float perform(float arg1) {
		return arg1 * arg1;
	}

}
