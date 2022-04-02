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
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
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

	public static Image createQRCodeImage(String text, int angle, Font ft) {
		GC gc = null;
		try {
			if (text == null || text.trim().length() == 0) {
				return null;
			}

			Display display = Display.getCurrent();

			gc = new GC(display);
			if (ft != null) {
				gc.setFont(ft);
			}

			Point pt = gc.textExtent(text);

			gc.dispose();

			TextLayout tl = new TextLayout(display);
			if (ft != null) {
				tl.setFont(ft);
			}
			tl.setText(text);

			return createQRImage(tl, pt.x, pt.y, angle);
		} catch (Exception e) {
			e.printStackTrace();

			if (gc != null && !gc.isDisposed()) {
				gc.dispose();
			}
		}

		return null;
	}

	/**
	 * @return Returns as [rotatedWidth, rotatedHeight, xOffset, yOffset]
	 */
	public static double[] computedRotatedInfo(int width, int height, int angle) {
		angle = angle % 360;

		if (angle < 0) {
			angle += 360;
		}

		if (angle == 0) {
			return new double[] { width, height, 0, 0 };
		} else if (angle == 90) {
			return new double[] { height, width, -width, 0 };
		} else if (angle == 180) {
			return new double[] { width, height, -width, -height };
		} else if (angle == 270) {
			return new double[] { height, width, 0, -height };
		} else if (angle > 0 && angle < 90) {
			double angleInRadians = ((-angle * Math.PI) / 180.0);
			double cosTheta = Math.abs(Math.cos(angleInRadians));
			double sineTheta = Math.abs(Math.sin(angleInRadians));

			int dW = (int) (width * cosTheta + height * sineTheta);
			int dH = (int) (width * sineTheta + height * cosTheta);

			return new double[] { dW, dH, -width * sineTheta * sineTheta, width * sineTheta * cosTheta };

		} else if (angle > 90 && angle < 180) {
			double angleInRadians = ((-angle * Math.PI) / 180.0);
			double cosTheta = Math.abs(Math.cos(angleInRadians));
			double sineTheta = Math.abs(Math.sin(angleInRadians));

			int dW = (int) (width * cosTheta + height * sineTheta);
			int dH = (int) (width * sineTheta + height * cosTheta);

			return new double[] { dW, dH, -(width + height * sineTheta * cosTheta), -height / 2 };

		} else if (angle > 180 && angle < 270) {
			double angleInRadians = ((-angle * Math.PI) / 180.0);
			double cosTheta = Math.abs(Math.cos(angleInRadians));
			double sineTheta = Math.abs(Math.sin(angleInRadians));

			int dW = (int) (width * cosTheta + height * sineTheta);
			int dH = (int) (width * sineTheta + height * cosTheta);

			return new double[] { dW, dH, -(width * cosTheta * cosTheta), -(height + width * cosTheta * sineTheta) };

		} else if (angle > 270 && angle < 360) {
			double angleInRadians = ((-angle * Math.PI) / 180.0);
			double cosTheta = Math.abs(Math.cos(angleInRadians));
			double sineTheta = Math.abs(Math.sin(angleInRadians));

			int dW = (int) (width * cosTheta + height * sineTheta);
			int dH = (int) (width * sineTheta + height * cosTheta);

			return new double[] { dW, dH, (height * cosTheta * sineTheta), -(height * sineTheta * sineTheta) };

		}

		return new double[] { width, height, 0, 0 };
	}

	private static Image createQRImage(Object src, int width, int height, int angle) {
		angle = angle % 360;

		if (angle < 0) {
			angle += 360;
		}

		double[] info = computedRotatedInfo(width, height, angle);

		return renderQRObject(src, -angle, (int) info[0], (int) info[1], info[2], info[3]);
	}

	private static Image renderQRObject(Object src, double angle, int width, int height, double tx, double ty) {
		Display display = Display.getCurrent();
		QRCodeWriter qrw = null;

		Image dest = null;
		GC gc = null;
		Transform tf = null;

		try {
			dest = new Image(Display.getCurrent(), width, height);
			gc = new GC(dest);

			gc.setAdvanced(true);
			gc.setAntialias(SWT.OFF);
			gc.setTextAntialias(SWT.ON);

			tf = new Transform(display);
			tf.rotate((float) angle);
			tf.translate((float) tx, (float) ty);

			gc.setTransform(tf);

			if (src instanceof TextLayout) {
				TextLayout tl = (TextLayout) src;

				qrw = new QRCodeWriter();
				HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
				hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
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
