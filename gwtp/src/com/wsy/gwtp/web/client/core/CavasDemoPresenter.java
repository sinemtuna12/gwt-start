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

public class CavasDemoPresenter extends
		Presenter<CavasDemoPresenter.MyView, CavasDemoPresenter.MyProxy> {

	public interface MyView extends View {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.canvasDemo)
	@TabInfo(container = GWTPresenter.class, label = "Cavas Demo", priority = TabPriorities.cavasDemo)
	public interface MyProxy extends TabContentProxyPlace<CavasDemoPresenter> {
	}

	@Inject
	public CavasDemoPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, GWTPresenter.TYPE_SetTabContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}
}
