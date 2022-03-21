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

package org.eclipse.birt.extension.qrcode;

import org.eclipse.birt.extension.qrcode.util.SwtGraphicsUtil;
import org.eclipse.birt.report.designer.ui.extensions.IReportItemImageProvider;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.swt.graphics.Image;

/**
 * RotatedTextImageUI
 */
public class QRCodeImageUI implements IReportItemImageProvider {

	public void disposeImage(ExtendedItemHandle handle, Image image) {
		if (image != null && !image.isDisposed()) {
			image.dispose();
		}

	}

	public Image getImage(ExtendedItemHandle handle) {
		try {
			IReportItem item = handle.getReportItem();

			if (item instanceof QRCodeItem) {
				int angle = ((QRCodeItem) item).getRotationAngle();
				String text = ((QRCodeItem) item).getText();

				return SwtGraphicsUtil.createQRCodeImage(text, angle, null);
			}
		} catch (ExtendedElementException e) {
			e.printStackTrace();
		}
		return null;
	}

}
