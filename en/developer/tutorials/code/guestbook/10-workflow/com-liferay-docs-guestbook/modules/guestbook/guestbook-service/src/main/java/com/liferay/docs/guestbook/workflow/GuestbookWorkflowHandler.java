/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.guestbook.workflow;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;

@Component(immediate = true, service = WorkflowHandler.class)
public class GuestbookWorkflowHandler extends BaseWorkflowHandler<Guestbook> {

    @Override
    public String getClassName() {
        return Guestbook.class.getName();
    }

    @Override
    public String getType(Locale locale) {
        return _resourceActions.getModelResource(locale, getClassName());
    }

    @Override
    public Guestbook updateStatus(
            int status, Map<String, Serializable> workflowContext)
        throws PortalException {

        long userId = GetterUtil.getLong(
            (String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
        long resourcePrimKey = GetterUtil.getLong(
            (String)workflowContext.get(
                WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

        ServiceContext serviceContext = (ServiceContext)workflowContext.get(
            "serviceContext");

        return _guestbookLocalService.updateStatus(
            userId, resourcePrimKey, status, serviceContext);
    }

    @Reference(unbind = "-")
    protected void setResourceActions(ResourceActions resourceActions) {

        _resourceActions = resourceActions;
    }

    private ResourceActions _resourceActions;

	@Reference(unbind = "-")
	protected void setGuestbookLocalService(
		GuestbookLocalService guestbookLocalService) {

		_guestbookLocalService = guestbookLocalService;
	}

	private GuestbookLocalService _guestbookLocalService;

}