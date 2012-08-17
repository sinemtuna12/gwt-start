package com.wsy.gwtp.web.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.RequestTabsHandler;
import com.gwtplatform.mvp.client.TabContainerPresenter;
import com.gwtplatform.mvp.client.TabView;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.NonLeafTabContentProxy;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.wsy.gwtp.web.client.place.NameTokens;
import com.wsy.gwtp.web.client.place.TabPriorities;

public class BoostrapPresenter
		extends
		TabContainerPresenter<BoostrapPresenter.MyView, BoostrapPresenter.MyProxy> {

	public interface MyView extends TabView {
	}

	@ProxyCodeSplit
	@TabInfo(container = MainPagePresenter.class, label = "Boostrap", priority = TabPriorities.boostrap, nameToken = NameTokens.gridSystem)
	public interface MyProxy extends NonLeafTabContentProxy<BoostrapPresenter> {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetTabContent = new Type<RevealContentHandler<?>>();

	@RequestTabs
	public static final Type<RequestTabsHandler> TYPE_RequestTabs = new Type<RequestTabsHandler>();

	private final PlaceManager placeManager;

	@Inject
	public BoostrapPresenter(final EventBus eventBus, final MyView view,
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
		GWT.log("BoostrapPresenter.onReset() called.");
		MyProxy proxy = getProxy();
		String nameToken = placeManager.getCurrentPlaceRequest().getNameToken();
		GWT.log("go to [" + nameToken + "]");
		proxy.changeTab(proxy.getTabData(), nameToken);
	}
}
