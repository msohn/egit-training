/*******************************************************************************
 * Copyright (C) 2010, Matthias Sohn <matthias.sohn@sap.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.example.calc.internal.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.eclipse.example.calc.BinaryOperation;
import org.eclipse.example.calc.Operation;
import org.eclipse.example.calc.Operations;
import org.eclipse.example.calc.UnaryOperation;
import org.eclipse.example.calc.internal.operations.Equals;
import org.eclipse.example.calc.internal.operations.Minus;
import org.eclipse.example.calc.internal.operations.Plus;
import org.eclipse.example.calc.internal.operations.Square;

/*
 * A simple calculator featuring a Swing UI.
 */
public class Calculator extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private String cmd;

	private boolean clearDisplay;

	private float value;

	private JTextField display;

	private JPanel buttonsPanel;

	private JPanel numberButtonsPanel;

	private JPanel cmdButtonsPanel;

	private JButton numberButtons[];

	private JButton cmdButtons[];

	public static void main(String args[]) {
		new Calculator().setVisible(true);
	}

	public Calculator() {
		setupOperations();
		setupGUI();
	}

	private void setupOperations() {
		new Equals();
		new Minus();
		new Plus();
		new Square();
	}

	private void setupGUI() {
		setTitle("Simple Calculator");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		setLocationByPlatform(true);

		setupDisplay(c);
		setupButtonsPanel(c);
		setupNumberButtons();
		setupCommandButtons();

		pack();
	}

	private void setupDisplay(Container c) {
		display = new JTextField("0");
		display.setHorizontalAlignment(JTextField.TRAILING);
		c.add(display, BorderLayout.NORTH);
		// initially clear the display
		clearDisplay = true;
	}

	private void setupButtonsPanel(Container c) {
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 1));
		c.add(buttonsPanel);
	}

	private void setupNumberButtons() {
		numberButtonsPanel = new JPanel();
		numberButtonsPanel.setLayout(new GridLayout(3, 4));
		buttonsPanel.add(numberButtonsPanel, BorderLayout.CENTER);
		numberButtons = new JButton[11];

		for (int i = 0; i < numberButtons.length - 1; i++) {
			addNumberButton(i, Integer.valueOf(i).toString());
		}
		addNumberButton(10, ".");
	}

	private void addNumberButton(int i, String name) {
		numberButtons[i] = new JButton();
		numberButtons[i].setText(name);
		numberButtons[i].addActionListener(this);
		numberButtonsPanel.add(numberButtons[i]);
	}

	private void setupCommandButtons() {
		// command buttons
		cmdButtonsPanel = new JPanel();
		cmdButtonsPanel.setLayout(new GridLayout(1, 0));
		buttonsPanel.add(cmdButtonsPanel, BorderLayout.CENTER);
		TitledBorder title = BorderFactory.createTitledBorder("Operations");
		cmdButtonsPanel.setBorder(title);
		cmdButtons = new JButton[Operations.INSTANCE.size()];

		// make the buttons, set ActionListener and add to panel
		for (int i = 0; i < cmdButtons.length; i++) {
			addCommandButton(i);
		}
	}

	private void addCommandButton(int i) {
		cmdButtons[i] = new JButton();
		cmdButtons[i].setText(Operations.INSTANCE.getOperationName(i));
		cmdButtons[i].addActionListener(this);
		cmdButtonsPanel.add(cmdButtons[i]);
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (isCommand(str)) {
			calculate(str);
		} else {
			char digit = (str.toCharArray())[0];
			if (Character.isDigit(digit) || digit == '.') {
				if (clearDisplay) {
					// save current value and clear the display
					value = Float.parseFloat(display.getText());
					display.setText("");
					clearDisplay = false;
				}

				// add new digit to display
				display.setText(display.getText() + digit);
			}
		}
	}

	private boolean isCommand(String name) {
		return (Operations.INSTANCE.getOperation(name) != null);
	}

	private void calculate(String cmdName) {
		float curValue;
		float newValue = 0;

		// get current value of display
		curValue = Float.parseFloat(display.getText());

		Operation currentOp = Operations.INSTANCE.getOperation(cmdName);
		if ((currentOp instanceof BinaryOperation) && (cmd == null)) {
			// if last clicked operation was binary and there is no saved
			// operation, store it
			cmd = cmdName;
			clearDisplay = true;
		} else {
			// if saved command is binary perform it
			Operation savedOp = Operations.INSTANCE.getOperation(cmd);
			if (savedOp instanceof BinaryOperation) {
				BinaryOperation bop = (BinaryOperation) savedOp;
				newValue = bop.perform(value, curValue);
			} // if current operation is unary perform it
			else if (currentOp instanceof UnaryOperation) {
				UnaryOperation uop = (UnaryOperation) currentOp;
				newValue = uop.perform(curValue);
			}

			// display the result and prepare clear on next button
			display.setText("" + newValue);
			clearDisplay = true;
			if (currentOp instanceof Equals) {
				// do not save "=" command
				cmd = null;
			} else if (currentOp instanceof BinaryOperation) {
				// save binary commands as they are executed on next operation
				cmd = cmdName;
			} else {
				// clear saved command
				cmd = null;
			}
		}

	}
}
