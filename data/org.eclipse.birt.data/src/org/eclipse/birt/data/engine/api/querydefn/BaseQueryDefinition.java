/*
 *************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */ 
package org.eclipse.birt.data.engine.api.querydefn;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.data.engine.api.IBaseQueryDefinition;


/**
 * Default implementation of the IBaseQueryDefn interface, which
 * represents attributes common to a report query and a subquery.
 *
 */

abstract public class BaseQueryDefinition extends BaseTransform implements IBaseQueryDefinition
{
	protected List 		        groups = new ArrayList();
	protected boolean 			hasDetail = true;
	protected BaseQueryDefinition 	parentQuery;
	protected int				maxRowCount = 0;
	
	BaseQueryDefinition(BaseQueryDefinition parent)
	{
		parentQuery = parent;
	}
	
	/**
	 * Returns the group definitions as an ordered collection of GroupDefn
	 * objects. Groups are organizations within the data that support
	 * aggregation, filtering and sorting. Reports use groups to trigger
	 * level breaks.
	 * 
	 * @return the list of groups. If no group is defined, null is returned.
	 */
	
	public List getGroups( )
	{
		return groups;
	}

	/**
	 * Appends a group definition to the group list.
	 * @param group Group definition to add
	 */
	public void addGroup( GroupDefinition group )
	{
	    groups.add( group );
	}
	
	/**
	 * Indicates if the report will use the detail rows. Allows the data
	 * transform engine to optimize the query if the details are not used.
	 * 
	 * @return true if the detail rows are used, false if not used
	 */
	public boolean usesDetails( )
	{
		return hasDetail;
	}
	
	
	/**
	 * @param usesDetail Whether detail rows are used in this query
	 */
	public void setUsesDetails(boolean usesDetails) 
	{
		this.hasDetail = usesDetails;
	}

	/**
	 * Returns the parent query. The parent query is the outer query which encloses
	 * this query
	 */
	public IBaseQueryDefinition getParentQuery() 
	{
		return parentQuery;
	}
	
	/**
	 * Gets the maximum number of detail rows that can be retrieved by this report query
	 * @return Maximum number of rows. If 0, there is no limit on how many rows this query can retrieve.
	 */
	public int getMaxRows( ) 
	{
		return maxRowCount;
	}
	
	/**
	 * Sets the maximum number of detail rows that can be retrieved by this report query
	 * 
	 */
	public void setMaxRows( int maxRows ) 
	{
	    maxRowCount = maxRows;
	}
}
