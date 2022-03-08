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

package com.liferay.docs.guestbook.web.internal.asset;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;


@Component(immediate = true, 
  property = {"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK}, 
  service = AssetRendererFactory.class
  )
public class GuestbookAssetRendererFactory extends 
  BaseAssetRendererFactory<Guestbook> {

  public GuestbookAssetRendererFactory() {
	setClassName(CLASS_NAME);
	setLinkable(_LINKABLE);
	setPortletId(GuestbookPortletKeys.GUESTBOOK); setSearchable(true);
	setSelectable(true); 
  }         
	
    @Override
    public AssetRenderer<Guestbook> getAssetRenderer(long classPK, int type) 
    throws PortalException {
      
      Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);

      GuestbookAssetRenderer guestbookAssetRenderer = 
      new GuestbookAssetRenderer(guestbook, _guestbookModelResourcePermission);

      guestbookAssetRenderer.setAssetRendererType(type);
      guestbookAssetRenderer.setServletContext(_servletContext);

      return guestbookAssetRenderer;
    }

    @Override
    public String getClassName() {
    	return CLASS_NAME;
    }

    @Override
    public String getType() {
    	return TYPE;
    }

    @Override
    public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
      LiferayPortletResponse liferayPortletResponse, long classTypeId) {
        PortletURL portletURL = null;

        try {
          ThemeDisplay themeDisplay = (ThemeDisplay) 
          liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

          portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),
              GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
          portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_guestbook");
          portletURL.setParameter("showback", Boolean.FALSE.toString());

          } catch (PortalException e) {
          
                logger.log(Level.SEVERE, e.getMessage()); 
                
          }

        return portletURL;
    }

    @Override
    public boolean isLinkable() {
        return _LINKABLE;
    }

    @Override
    public String getIconCssClass() {
      return "bookmarks";
    }

    @Reference(target = "(osgi.web.symbolicname=com.liferay.docs.guestbook.portlet)",
        unbind = "-")
    public void setServletContext(ServletContext servletContext) {
        _servletContext = servletContext;
    }

    @Reference(unbind = "-")
    protected void setGuestbookLocalService(GuestbookLocalService guestbookLocalService) {
        _guestbookLocalService = guestbookLocalService; 
    }

  private ServletContext _servletContext;
  private GuestbookLocalService _guestbookLocalService;
  private static final boolean _LINKABLE = true;
  public static final String CLASS_NAME = Guestbook.class.getName();
  public static final String TYPE = "guestbook";
  private Logger logger = Logger.getLogger(this.getClass().getName());
  private ModelResourcePermission<Guestbook> _guestbookModelResourcePermission;
}