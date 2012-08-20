package com.wsy.gwtp.web.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.RequestTabsHandler;
import com.gwtplatform.mvp.client.TabContainerPresenter;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.TabView;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.NonLeafTabContentProxy;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.wsy.gwtp.web.client.TabDataExt;
import com.wsy.gwtp.web.client.gin.ClientGinjector;
import com.wsy.gwtp.web.client.place.NameTokens;
import com.wsy.gwtp.web.client.place.TabPriorities;

public class CssPresenter extends
		TabContainerPresenter<CssPresenter.MyView, CssPresenter.MyProxy> {

	public interface MyView extends TabView {
	}

	@ProxyCodeSplit
	public interface MyProxy extends NonLeafTabContentProxy<CssPresenter> {
	}

	@TabInfo(container = MainPagePresenter.class, nameToken = NameTokens.cherryBlossom)
	public static TabData getTabData(ClientGinjector injector) {
//		@TabInfo(container = MainPagePresenter.class, label = "CSS", priority = TabPriorities.css, nameToken = NameTokens.cherryBlossom)
		return new TabDataExt("CSS", TabPriorities.css, null);
	}
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetTabContent = new Type<RevealContentHandler<?>>();

	@RequestTabs
	public static final Type<RequestTabsHandler> TYPE_RequestTabs = new Type<RequestTabsHandler>();

	private final PlaceManager placeManager;

	@Inject
	public CssPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy, TYPE_SetTabContent, TYPE_RequestTabs);
		this.placeManager = placeManager;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetTabContent,
				this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReset() {
		super.onReset();
		GWT.log("CssPresenter.onReset() called.");
		MyProxy proxy = getProxy();
		String nameToken = placeManager.getCurrentPlaceRequest().getNameToken();
		GWT.log("go to [" + nameToken + "]");
		proxy.changeTab(proxy.getTabData(), nameToken);
	}
}
