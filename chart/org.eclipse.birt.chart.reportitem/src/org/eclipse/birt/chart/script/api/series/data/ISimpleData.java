/*******************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.chart.script.api.series.data;

/**
 * Represents the data contained in the Series
 */

public interface ISimpleData extends ISeriesData {

	/**
	 * Gets the expression.
	 * 
	 * @return expression
	 */
	String getExpr();

	/**
	 * Sets the expression
	 * 
	 * @param expr expression
	 */
	void setExpr(String expr);
}
