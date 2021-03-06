/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.birt.report.item.crosstab.core.re;

import org.eclipse.birt.report.engine.extension.IExtendedItem;
import org.eclipse.birt.report.engine.extension.IReportEventHandler;
import org.eclipse.birt.report.engine.extension.IReportItemExecutor;
import org.eclipse.birt.report.engine.extension.IReportItemPreparation;
import org.eclipse.birt.report.engine.extension.IReportItemPresentation;
import org.eclipse.birt.report.engine.extension.IReportItemQuery;
import org.eclipse.birt.report.item.crosstab.core.script.internal.handler.CrosstabHandlerCache;

/**
 * CrosstabCellExtendedItem
 */
public class CrosstabCellExtendedItem implements IExtendedItem {

	private CrosstabHandlerCache handlerCache;

	CrosstabCellExtendedItem(CrosstabHandlerCache handlerCache) {
		this.handlerCache = handlerCache;
	}

	public IReportEventHandler createEventHandler() {
		return new CrosstabCellReportEventHandler(handlerCache);
	}

	public IReportItemExecutor createExecutor() {
		return null;
	}

	public IReportItemPreparation createPreparation() {
		return null;
	}

	public IReportItemPresentation createPresentation() {
		return null;
	}

	public IReportItemQuery createQuery() {
		return null;
	}

	public void release() {
		handlerCache = null;
	}

}
