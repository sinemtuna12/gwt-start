package com.wsy.gwtp.web.client.core;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;
import com.wsy.gwtp.web.client.place.NameTokens;
import com.wsy.gwtp.web.client.place.TabPriorities;

public class CherryBlossomPresenter
		extends
		Presenter<CherryBlossomPresenter.MyView, CherryBlossomPresenter.MyProxy> {

	public interface MyView extends View {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.cherryBlossom)
	@TabInfo(container = CssPresenter.class, label = "Cherry Blossom", priority = TabPriorities.cherryBlossom)
	public interface MyProxy extends TabContentProxyPlace<CherryBlossomPresenter> {
	}

	@Inject
	public CherryBlossomPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, CssPresenter.TYPE_SetTabContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
}
