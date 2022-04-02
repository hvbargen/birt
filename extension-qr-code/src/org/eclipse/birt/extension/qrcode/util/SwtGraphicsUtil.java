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

package org.eclipse.birt.extension.qrcode.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * SwtGraphicsUtil
 */
public class SwtGraphicsUtil {

	public static Image createQRCodeImage(String text, int dotsWidth, int dotsHeight, String encoding) {
		try {
			if (text == null || text.trim().length() == 0) {
				return null;
			}

			Display display = Display.getCurrent();

			TextLayout tl = new TextLayout(display);

			return createQRImage(tl, dotsWidth, dotsHeight, encoding);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	private static Image createQRImage(Object src, int width, int height, String encoding) {

		return renderQRObject(src, width, height, encoding);
	}

	private static Image renderQRObject(Object src, int width, int height, String encoding) {
		Display display = Display.getCurrent();
		QRCodeWriter qrw = null;

		Image dest = null;
		GC gc = null;
		Transform tf = null;

		try {
			dest = new Image(display, width, height);
			gc = new GC(dest);

			gc.setAdvanced(true);
			gc.setAntialias(SWT.OFF);

			if (src instanceof TextLayout) {
				TextLayout tl = (TextLayout) src;

				qrw = new QRCodeWriter();
				HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
				hints.put(EncodeHintType.CHARACTER_SET, encoding != null ? encoding : "utf-8");
				hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
				hints.put(EncodeHintType.QR_VERSION, 2);
				BitMatrix bm = qrw.encode(tl.getText(), BarcodeFormat.QR_CODE, width, height, hints);
				BufferedImage im = MatrixToImageWriter.toBufferedImage(bm);

				java.awt.image.WritableRaster awtRaster = im.getRaster();
				java.awt.image.DataBufferByte awtData = (DataBufferByte) awtRaster.getDataBuffer();
				byte[] rawData = awtData.getData();

				org.eclipse.swt.graphics.PaletteData swtPalette = new PaletteData(0xff, 0xff00, 0xff0000);

				int depth = 24;
				org.eclipse.swt.graphics.ImageData swtImageData = new ImageData(width, height, depth, swtPalette, width,
						rawData);

				org.eclipse.swt.graphics.Image swtImage = new Image(Display.getDefault(), swtImageData);

				gc.drawImage(swtImage, 0, 0);

				tl.draw(gc, 0, 0);
			} else if (src instanceof Image) {
				gc.drawImage((Image) src, 0, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (gc != null && !gc.isDisposed()) {
				gc.dispose();
			}

			if (tf != null && !tf.isDisposed()) {
				tf.dispose();
			}
		}

		return dest;
	}
}
