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

package org.eclipse.birt.data.engine.impl.document;

import java.io.OutputStream;

/**
 * 
 */
public class StreamWrapper
{
	private OutputStream streamForResultClass;
	private OutputStream streamForDataSet;
	private OutputStream streamForGroupInfo;
	private OutputStream streamForRowIndexInfo;
	private OutputStream streamForParentIndex;

	/**
	 * @param streamForResultClass
	 * @param streamForDataSet
	 * @param streamForGroupInfo
	 * @param streamForRowIndexInfo
	 */
	public StreamWrapper( OutputStream streamForDataSet,
			OutputStream streamForResultClass, OutputStream streamForGroupInfo,
			OutputStream streamForRowIndexInfo,
			OutputStream streamForParentIndex )
	{
		this.streamForResultClass = streamForResultClass;
		this.streamForDataSet = streamForDataSet;
		this.streamForGroupInfo = streamForGroupInfo;
		this.streamForRowIndexInfo = streamForRowIndexInfo;
		this.streamForParentIndex = streamForParentIndex;
	}

	/**
	 * @return
	 */
	public OutputStream getStreamForResultClass( )
	{
		return this.streamForResultClass;
	}

	/**
	 * @return
	 */
	public OutputStream getStreamForDataSet( )
	{
		return this.streamForDataSet;
	}

	/**
	 * @return
	 */
	public OutputStream getStreamForGroupInfo( )
	{
		return this.streamForGroupInfo;
	}

	/**
	 * @return
	 */
	public OutputStream getStreamForRowIndexInfo( )
	{
		return streamForRowIndexInfo;
	}
	
	/**
	 * @return
	 */
	public OutputStream getStreamForParentIndex( )
	{
		return streamForParentIndex;
	}

}
