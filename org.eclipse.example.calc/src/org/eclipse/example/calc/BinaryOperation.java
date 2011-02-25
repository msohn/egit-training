/*******************************************************************************
 * Copyright (C) 2010, Matthias Sohn <matthias.sohn@sap.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.example.calc;

/**
 * Binary operation interface
 */
public interface BinaryOperation extends Operation {
	/**
	 * @param arg1 first parameter
	 * @param arg2 second parameter
	 * @return result of binary operation
	 */
	public float perform(float arg1, float arg2);
}
