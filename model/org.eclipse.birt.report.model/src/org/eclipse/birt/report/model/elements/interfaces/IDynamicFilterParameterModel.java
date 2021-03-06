/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.model.elements.interfaces;

/**
 * The interface for DynamicFilterParameter element to store the constants.
 */
public interface IDynamicFilterParameterModel {

	/**
	 * Name of the display type property.
	 */
	public static final String DSIPLAY_TYPE_PROP = "displayType"; //$NON-NLS-1$

	/**
	 * Name of the filterOperator property.
	 */
	public static final String FILTER_OPERATOR_PROP = "filterOperator"; //$NON-NLS-1$

	/**
	 * Name of the column property.
	 */
	public static final String COLUMN_PROP = "column"; //$NON-NLS-1$

	/**
	 * Name of the native data type property.
	 */
	public static final String NATIVE_DATA_TYPE_PROP = "nativeDataType"; //$NON-NLS-1$

}
