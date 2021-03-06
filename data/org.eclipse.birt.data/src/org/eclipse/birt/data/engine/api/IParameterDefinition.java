/*
 *************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */
package org.eclipse.birt.data.engine.api;

/**
 * Describes the metadata of a data set parameter. The definition is used to
 * provide a parameter's metadata when such information cannot be dynamically
 * obtained from the underlying data source.
 */
public interface IParameterDefinition {
	/**
	 * Returns the parameter name.
	 * 
	 * @return the name of the parameter. Null if parameter is identified by index.
	 */
	public abstract String getName();

	/**
	 * Returns the native name of the parameter as known to the underlying data
	 * source.
	 * 
	 * @return the parameter native name, or null if the name is not available or
	 *         this parameter is not named.
	 */
	public abstract String getNativeName();

	/**
	 * Returns the parameter position. Parameter positions start from 1.
	 * 
	 * @return the parameter position. -1 if parameter is identified by name.
	 */
	public abstract int getPosition();

	/**
	 * Returns the parameter data type. See the
	 * <code>org.eclipse.birt.core.data.DataType</code> class for return value
	 * constants.
	 * 
	 * @return the parameter data type
	 */
	public abstract int getType();

	/**
	 * Returns the parameter's native data type as defined by the underlying data
	 * source. The native data type code value is implementation-specific. Default
	 * value is 0 for none or unknown value.
	 * 
	 * @return the native data type code of this parameter
	 */
	public abstract int getNativeType();

	/**
	 * Returns whether this parameter is an input parameter. A parameter can be of
	 * both input and output modes.
	 * 
	 * @return true if this parameter is of input mode, false otherwise.
	 */
	public abstract boolean isInputMode();

	/**
	 * Returns whether this parameter is an output parameter. A parameter can be of
	 * both input and output modes.
	 * 
	 * @return true if this parameter is of output mode, false otherwise.
	 */
	public abstract boolean isOutputMode();

	/**
	 * Specifies whether this parameter is optional. Applies to the parameter only
	 * if it is of input mode.
	 * 
	 * @return true if this parameter is optional, false if this parameter is
	 *         required.
	 */
	public abstract boolean isInputOptional();

	/**
	 * Returns the default input value of this parameter.
	 * 
	 * @return the default value, or null if the default value is not specified or
	 *         if this is an output only parameter.
	 */
	public abstract String getDefaultInputValue();

	/**
	 * Specifies whether null values are allowed for this parameter.
	 * 
	 * @return true if this parameter value can be null, false otherwise.
	 */
	public abstract boolean isNullable();

}