/*******************************************************************************
 * Copyright (C) 2010, Stefan Lay <stefan.lay@sap.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.example.calc.internal;

/**
 * Display abstraction for {@link Calculator}
 */
public interface TextProvider {

	/**
	 * @param text the text to display on the {@link Calculator}
	 */
	public void setDisplayText(String text);

	/**
	 * @return the text on the display of the  {@link Calculator}
	 */
	public String getDisplayText();

}
