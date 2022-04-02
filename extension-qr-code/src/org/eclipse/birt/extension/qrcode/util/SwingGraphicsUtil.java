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
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * SwingGraphicsUtil
 */
public class SwingGraphicsUtil {

	public static BufferedImage createQRCodeImage(String text, int dotsWidth, int dotsHeight, String encoding) {
		QRCodeWriter qrw = null;
		try {
			if (text == null || text.trim().length() == 0) {
				return null;
			}

			qrw = new QRCodeWriter();
			HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			hints.put(EncodeHintType.QR_VERSION, 2);
			BitMatrix bm = qrw.encode(text, BarcodeFormat.QR_CODE, dotsWidth, dotsHeight, hints);
			BufferedImage im = MatrixToImageWriter.toBufferedImage(bm);
			return im;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static BufferedImage createQRImage(String text, int width, int height, String encoding) {
		return renderQRObject(text, width, height, encoding);
	}

	private static BufferedImage renderQRObject(String text, int width, int height, String encoding) {
		QRCodeWriter qrw = null;

			try {
				qrw = new QRCodeWriter();
				HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
				hints.put(EncodeHintType.CHARACTER_SET, (encoding != null ? encoding : "utf-8"));
				hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
				hints.put(EncodeHintType.QR_VERSION, 2);
				BitMatrix bm = qrw.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
				BufferedImage im = MatrixToImageWriter.toBufferedImage(bm);
				return im;
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
}
