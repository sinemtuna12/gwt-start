package com.wsy.gwtp.web.client.core;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ChangeTabHandler;
import com.gwtplatform.mvp.client.RequestTabsHandler;
import com.gwtplatform.mvp.client.TabContainerPresenter;
import com.gwtplatform.mvp.client.TabView;
import com.gwtplatform.mvp.client.annotations.ChangeTab;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.proxy.AsyncCallFailEvent;
import com.gwtplatform.mvp.client.proxy.AsyncCallFailHandler;
import com.gwtplatform.mvp.client.proxy.AsyncCallStartEvent;
import com.gwtplatform.mvp.client.proxy.AsyncCallStartHandler;
import com.gwtplatform.mvp.client.proxy.AsyncCallSucceedEvent;
import com.gwtplatform.mvp.client.proxy.AsyncCallSucceedHandler;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MainPagePresenter
		extends
		TabContainerPresenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy>
		implements AsyncCallStartHandler, AsyncCallFailHandler,
		AsyncCallSucceedHandler {

	public interface MyView extends TabView {

		void setTopMessage(String string);
	}

	@ProxyStandard
	public interface MyProxy extends Proxy<MainPagePresenter> {
	}

	/**
	 * This will be the event sent to our "unknown" child presenters, in order
	 * for them to register their tabs.
	 */
	@RequestTabs
	public static final Type<RequestTabsHandler> TYPE_RequestTabs = new Type<RequestTabsHandler>();

	/**
	 * Fired by child proxie's when their tab content is changed.
	 */
	@ChangeTab
	public static final Type<ChangeTabHandler> TYPE_ChangeTab = new Type<ChangeTabHandler>();

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetTabContent = new Type<RevealContentHandler<?>>();

	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy, TYPE_SetTabContent, TYPE_RequestTabs,
				TYPE_ChangeTab);
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	@ProxyEvent
	public void onAsyncCallSucceed(AsyncCallSucceedEvent asyncCallSucceedEvent) {
		GWT.log("Loading done.");
		getView().setTopMessage(null);
	}

	@Override
	@ProxyEvent
	public void onAsyncCallFail(AsyncCallFailEvent asyncCallFailEvent) {
		getView().setTopMessage("Oops, something went wrong...");
	}

	@Override
	@ProxyEvent
	public void onAsyncCallStart(AsyncCallStartEvent asyncCallStartEvent) {
		GWT.log("Loading...");
		getView().setTopMessage("Loading...");
	}

}
