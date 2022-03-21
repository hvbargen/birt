/*******************************************************************************
 * Copyright (c) 2005 Actuate Corporation.
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
 *  IBM Corporation  - Bidi direction of text
 *******************************************************************************/

package org.eclipse.birt.extension.qrcode;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.extension.qrcode.util.SwingGraphicsUtil;
import org.eclipse.birt.report.engine.extension.IBaseResultSet;
import org.eclipse.birt.report.engine.extension.IQueryResultSet;
import org.eclipse.birt.report.engine.extension.ReportItemPresentationBase;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;

/**
 * RotatedTextPresentationImpl
 */
public class QRCodePresentationImpl extends ReportItemPresentationBase {

	private QRCodeItem textItem;

	@Override
	public void setModelObject(ExtendedItemHandle modelHandle) {
		try {
			textItem = (QRCodeItem) modelHandle.getReportItem();
		} catch (ExtendedElementException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getOutputType() {
		return OUTPUT_AS_IMAGE;
	}

	@Override
	public Object onRowSets(IBaseResultSet[] results) throws BirtException {
		if (textItem == null) {
			return null;
		}

		int angle = textItem.getRotationAngle();
		String text = textItem.getText();

		// XXX Uncomment this block for expression support
		// /*
		if (results != null && results.length > 0) {
			if (results[0] instanceof IQueryResultSet && ((IQueryResultSet) results[0]).isBeforeFirst()) {
				((IQueryResultSet) results[0]).next();
			}

			text = String.valueOf(results[0].evaluate(text));
		} else {
			text = String.valueOf(context.evaluate(text));
		}
		// */

		BufferedImage rotatedImage = SwingGraphicsUtil.createQRCodeImage(text, angle, new Font("Default", 0, 12)); //$NON-NLS-1$

		ByteArrayInputStream bis = null;

		try {
			ImageIO.setUseCache(false);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			ImageOutputStream ios = ImageIO.createImageOutputStream(baos);

			ImageIO.write(rotatedImage, "png", ios); //$NON-NLS-1$
			ios.flush();
			ios.close();

			bis = new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bis;

	}

}
