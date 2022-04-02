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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
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

	public static BufferedImage createQRCodeImage(String text, int angle, Font ft) {
		QRCodeWriter qrw = null;
		Graphics2D g2d = null;
		try {
			if (text == null || text.trim().length() == 0) {
				return null;
			}

			BufferedImage stringImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

			g2d = (Graphics2D) stringImage.getGraphics();
			g2d.setFont(ft);

			FontMetrics fm = g2d.getFontMetrics();
			Rectangle2D bounds = fm.getStringBounds(text, g2d);

			TextLayout tl = new TextLayout(text, ft, g2d.getFontRenderContext());

			g2d.dispose();
			g2d = null;

			return createQRImage(tl, text, (int) bounds.getWidth(), (int) bounds.getHeight(), angle);
		} catch (Exception e) {
			e.printStackTrace();

			if (g2d != null) {
				g2d.dispose();
			}
		}

		return null;
	}

	private static BufferedImage createQRImage(Object src, String text, int width, int height, int angle) {
		angle = angle % 360;

		if (angle < 0) {
			angle += 360;
		}

		if (angle == 0) {
			return renderQRObject(src, text, 0, width, height, 0, 0);
		} else if (angle == 90) {
			return renderQRObject(src, text, -Math.PI / 2, height, width, -width, 0);
		} else if (angle == 180) {
			return renderQRObject(src, text, Math.PI, width, height, -width, -height);
		} else if (angle == 270) {
			return renderQRObject(src, text, Math.PI / 2, height, width, 0, -height);
		} else if (angle > 0 && angle < 90) {
			double angleInRadians = ((-angle * Math.PI) / 180.0);
			double cosTheta = Math.abs(Math.cos(angleInRadians));
			double sineTheta = Math.abs(Math.sin(angleInRadians));

			int dW = (int) (width * cosTheta + height * sineTheta);
			int dH = (int) (width * sineTheta + height * cosTheta);

			return renderQRObject(src, text, angleInRadians, dW, dH, -width * sineTheta * sineTheta,
					width * sineTheta * cosTheta);

		} else if (angle > 90 && angle < 180) {
			double angleInRadians = ((-angle * Math.PI) / 180.0);
			double cosTheta = Math.abs(Math.cos(angleInRadians));
			double sineTheta = Math.abs(Math.sin(angleInRadians));

			int dW = (int) (width * cosTheta + height * sineTheta);
			int dH = (int) (width * sineTheta + height * cosTheta);

			return renderQRObject(src, text, angleInRadians, dW, dH, -(width + height * sineTheta * cosTheta),
					-height / 2);

		} else if (angle > 180 && angle < 270) {
			double angleInRadians = ((-angle * Math.PI) / 180.0);
			double cosTheta = Math.abs(Math.cos(angleInRadians));
			double sineTheta = Math.abs(Math.sin(angleInRadians));

			int dW = (int) (width * cosTheta + height * sineTheta);
			int dH = (int) (width * sineTheta + height * cosTheta);

			return renderQRObject(src, text, angleInRadians, dW, dH, -(width * cosTheta * cosTheta),
					-(height + width * cosTheta * sineTheta));

		} else if (angle > 270 && angle < 360) {
			double angleInRadians = ((-angle * Math.PI) / 180.0);
			double cosTheta = Math.abs(Math.cos(angleInRadians));
			double sineTheta = Math.abs(Math.sin(angleInRadians));

			int dW = (int) (width * cosTheta + height * sineTheta);
			int dH = (int) (width * sineTheta + height * cosTheta);

			return renderQRObject(src, text, angleInRadians, dW, dH, (height * cosTheta * sineTheta),
					-(height * sineTheta * sineTheta));

		}

		return renderQRObject(src, text, 0, width, height, 0, 0);
	}

	private static BufferedImage renderQRObject(Object src, String text, double angle, int width, int height, double tx,
			double ty) {
		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		QRCodeWriter qrw = null;

		Graphics2D g2d = (Graphics2D) dest.getGraphics();
		g2d.setColor(Color.black);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		AffineTransform at = AffineTransform.getRotateInstance(angle);
		at.translate(tx, ty);
		g2d.setTransform(at);

		if (src instanceof TextLayout) {
			try {
				TextLayout tl = (TextLayout) src;

				qrw = new QRCodeWriter();
				HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
				hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
				hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
				hints.put(EncodeHintType.QR_VERSION, 2);
				BitMatrix bm = qrw.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
				BufferedImage im = MatrixToImageWriter.toBufferedImage(bm);
				g2d.drawImage(im, 0, 0, null);
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (src instanceof Image) {
			g2d.drawImage((Image) src, 0, 0, null);
		}
		g2d.dispose();

		return dest;
	}
}
