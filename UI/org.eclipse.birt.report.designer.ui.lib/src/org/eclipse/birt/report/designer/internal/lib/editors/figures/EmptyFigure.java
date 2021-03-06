/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation .
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.designer.internal.lib.editors.figures;

import org.eclipse.birt.report.designer.internal.ui.editors.schematic.figures.LabelFigure;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * The figure show in the library editor when the seleection object form outline
 * is not a visual element.
 * 
 */
public class EmptyFigure extends LabelFigure {

	// private String text;

	public EmptyFigure() {
		setOpaque(true);
//		setBackgroundColor( ReportColorConstants.greyFillColor );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.report.designer.internal.ui.editors.schematic.figures.
	 * LabelFigure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		if (prefSize != null)
			return prefSize;
		return getSize();
	}
}
