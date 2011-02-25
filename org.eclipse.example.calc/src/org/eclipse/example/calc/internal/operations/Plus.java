/*******************************************************************************
 * Copyright (C) 2010, Matthias Sohn <matthias.sohn@sap.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.example.calc.internal.operations;

import org.eclipse.example.calc.BinaryOperation;

/**
 * Binary plus operation
 */
public class Plus extends AbstractOperation implements BinaryOperation {

	@Override
	public float perform(float arg1, float arg2) {
		return arg1 + arg2;
	}

	@Override
	public String getName() {
		return "+";
	}

}
