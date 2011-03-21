/*******************************************************************************
 * Copyright (C) 2010, Stefan Lay <stefan.lay@sap.com>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.example.calc.internal.ui.swt;

import org.eclipse.example.calc.Operations;
import org.eclipse.example.calc.internal.Calculator;
import org.eclipse.example.calc.internal.TextProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/*
 * A simple calculator featuring a SWT
 *  UI.
 */
public class CalculatorUI implements TextProvider, SelectionListener {

	private static final long serialVersionUID = 1L;

	private Calculator calculator;

	private Shell shell;

	private Text display;

	private Button[] numberButtons;

	private Button[] cmdButtons;

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new CalculatorUI().open(display);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private Shell open(Display display) {
		shell = new Shell(display);
		setupGUI();

		shell.pack();
		shell.open();
		return shell;
	}

	public CalculatorUI() {
		calculator = new Calculator(this);
	}

	private void setupGUI() {
		shell.setText(Calculator.NAME);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		shell.setLayout(gridLayout);

		setupDisplay();
		setupNumberButtons();
		setupCommandButtons();

	}

	private void setupDisplay() {
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		display = new Text(shell, SWT.BORDER_SOLID | SWT.RIGHT);
		display.setLayoutData(gridData);
		display.setText("0");
		display.setEditable(false);
		display.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		calculator.setClearText(true);
	}

	private void setupNumberButtons() {
		Composite numberButtonsPanel = new Composite(shell, SWT.NONE);
		numberButtonsPanel.setLayout(new GridLayout(3, true));
		numberButtons = new Button[11];

		for (int i = 7; i < numberButtons.length - 1; i++) {
			addNumberButton(numberButtonsPanel, i, Integer.valueOf(i)
					.toString());
		}
		for (int i = 4; i < 7; i++) {
			addNumberButton(numberButtonsPanel, i, Integer.valueOf(i)
					.toString());
		}
		for (int i = 1; i < 4; i++) {
			addNumberButton(numberButtonsPanel, i, Integer.valueOf(i)
					.toString());
		}
		addNumberButton(numberButtonsPanel, 0, "0");
		addNumberButton(numberButtonsPanel, 10, ".");
	}

	private void addNumberButton(Composite parent, int i, String name) {
		numberButtons[i] = new Button(parent, SWT.PUSH);
		numberButtons[i].setText(name);
		numberButtons[i].addSelectionListener(this);
	}

	private void setupCommandButtons() {
		// command buttons
		Group cmdButtonsPanel = new Group(shell, SWT.NONE);
		cmdButtonsPanel.setText("Operations");
		cmdButtonsPanel.setLayout(new GridLayout(4, true));

		cmdButtons = new Button[Operations.INSTANCE.size()];

		// make the buttons, set ActionListener and add to panel
		for (int i = 0; i < cmdButtons.length; i++) {
			addCommandButton(cmdButtonsPanel, i);
		}
	}

	private void addCommandButton(Composite parent, int i) {
		cmdButtons[i] = new Button(parent, SWT.NONE);
		cmdButtons[i].setText(Operations.INSTANCE.getOperationName(i));
		cmdButtons[i].addSelectionListener(this);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// empty
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		String str = ((Button) event.getSource()).getText();
		calculator.handleButtonClick(str);
	}

	@Override
	public void setDisplayText(String text) {
		display.setText(text);
	}

	@Override
	public String getDisplayText() {
		return display.getText();
	}

}
