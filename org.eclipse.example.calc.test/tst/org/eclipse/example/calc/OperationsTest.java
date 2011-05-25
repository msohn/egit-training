/*******************************************************************************
 * Copyright (C) 2010, Matthias Sohn <matthias.sohn@sap.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.example.calc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.eclipse.example.calc.internal.operations.Equals;
import org.eclipse.example.calc.internal.operations.Minus;
import org.eclipse.example.calc.internal.operations.Plus;
import org.eclipse.example.calc.internal.operations.Square;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OperationsTest {

	private Square square;

	@Before
	public void setUp() throws Exception {
		new Plus();
		new Minus();
		new Equals();
		square = new Square();
	}

	@After
	public void tearDown() throws Exception {
		Operations.INSTANCE.reset();
	}

	@Test
	public void testRegister() {
		assertTrue(Operations.INSTANCE.getOperation(square.getName()) instanceof Square);
		try {
			new Square();
			fail("double registration of same operation must fail");
		} catch (AssertionError a) {
			// expected
		}
	}

	@Test
	public void testGetOperation() {
		assertTrue(Operations.INSTANCE.getOperation("+") instanceof Plus);
		assertTrue(Operations.INSTANCE.getOperation("-") instanceof Minus);
		assertTrue(Operations.INSTANCE.getOperation("=") instanceof Equals);
		assertTrue(Operations.INSTANCE.getOperation("^2") instanceof Square);
	}

	@Test
	public void testSize() {
		assertEquals(4, Operations.INSTANCE.size());
	}

	@Test
	public void testGetOperationName() {
		for (int i = 0; i < Operations.INSTANCE.size(); i++) {
			String name = Operations.INSTANCE.getOperationName(i);
			Operation op = Operations.INSTANCE.getOperation(name);
			assertEquals(name, op.getName());
		}
	}

}
