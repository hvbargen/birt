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

package org.eclipse.birt.report.designer.internal.ui.views.attributes.page;

import org.eclipse.birt.report.designer.internal.ui.views.attributes.provider.TextPropertyDescriptorProvider;
import org.eclipse.birt.report.designer.internal.ui.views.attributes.section.TextSection;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.elements.ReportDesignConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * The comments attribute page of Report element.
 */
public class DescriptionPage extends AttributePage {
	public void buildUI(Composite parent) {
		super.buildUI(parent);
		container.setLayout(WidgetUtil.createGridLayout(2, 15));

		TextPropertyDescriptorProvider descriptorProvider = new TextPropertyDescriptorProvider(
				ReportDesignHandle.DESCRIPTION_PROP, ReportDesignConstants.REPORT_DESIGN_ELEMENT);
		TextSection discriptorSection = new TextSection(descriptorProvider.getDisplayName(), container, true);
		discriptorSection.setStyle(SWT.MULTI | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL);
		discriptorSection.setProvider(descriptorProvider);
		discriptorSection.setWidth(500);
		discriptorSection.setHeight(200);
		discriptorSection.setFillText(true);
		addSection(PageSectionId.DISCRIPTOR_DISCRIPTOR, discriptorSection);

		createSections();
		layoutSections();

	}
}