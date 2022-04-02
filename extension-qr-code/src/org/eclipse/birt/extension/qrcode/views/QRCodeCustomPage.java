/*******************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.extension.qrcode.views;

import org.eclipse.birt.extension.qrcode.QRCodeItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * QRCodeCustomPage
 */
public class QRCodeCustomPage extends QRCodeGeneralPage {

	private Label lbText, lbDotsWidth, lbEncoding;

	@Override
	public void buildUI(Composite parent) {
		if (toolkit == null) {
			toolkit = new FormToolkit(Display.getCurrent());
			toolkit.setBorderStyle(SWT.NULL);
		}

		Control[] children = parent.getChildren();

		if (children != null && children.length > 0) {
			contentpane = (Composite) children[children.length - 1];

			GridLayout layout = new GridLayout(2, false);
			layout.marginTop = 8;
			layout.marginLeft = 8;
			layout.verticalSpacing = 12;
			contentpane.setLayout(layout);

			toolkit.createLabel(contentpane, "Text Content:"); //$NON-NLS-1$
			lbText = toolkit.createLabel(contentpane, ""); //$NON-NLS-1$
			GridData gd = new GridData();
			gd.widthHint = 200;
			lbText.setLayoutData(gd);

			toolkit.createLabel(contentpane, "Width (dots):"); //$NON-NLS-1$
			lbDotsWidth = toolkit.createLabel(contentpane, ""); //$NON-NLS-1$
			gd = new GridData();
			gd.widthHint = 200;
			lbDotsWidth.setLayoutData(gd);

			toolkit.createLabel(contentpane, "Encoding:"); //$NON-NLS-1$
			lbEncoding = toolkit.createLabel(contentpane, ""); //$NON-NLS-1$
			gd = new GridData();
			gd.widthHint = 200;
			lbEncoding.setLayoutData(gd);

		}
	}

	@Override
	protected void updateUI() {
		QRCodeItem item = getItem();

		if (item != null) {
			String text = item.getText();
			lbText.setText(text == null ? "" : text); //$NON-NLS-1$
			lbDotsWidth.setText(String.valueOf(item.getDotsWidth()));
			String encoding = item.getEncoding();
			lbEncoding.setText(encoding == null ? "" : encoding);
		}
	}

}
