/*******************************************************************************
 * Copyright (c) 2005 Actuate Corporation.
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

package org.eclipse.birt.extension.qrcode;

import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.extension.IMessages;
import org.eclipse.birt.report.model.api.extension.IReportItem;
import org.eclipse.birt.report.model.api.extension.ReportItemFactory;

/**
 * QRCodeItemFactory
 */
public class QRCodeItemFactory extends ReportItemFactory {

	@Override
	public IReportItem newReportItem(DesignElementHandle modelHandle) {
		if (modelHandle instanceof ExtendedItemHandle
				&& QRCodeItem.EXTENSION_NAME.equals(((ExtendedItemHandle) modelHandle).getExtensionName())) {
			return new QRCodeItem((ExtendedItemHandle) modelHandle);
		}
		return null;
	}

	@Override
	public IMessages getMessages() {
		return null;
	}

}
