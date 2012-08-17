package com.wsy.gwtp.web.client.core;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.wsy.gwtp.web.client.model.Stock;
import com.wsy.gwtp.web.client.place.NameTokens;
import com.wsy.gwtp.web.client.place.TabPriorities;
import com.wsy.gwtp.web.shared.LoadStockAction;
import com.wsy.gwtp.web.shared.LoadStockResult;

public class RemoteFilterGridPresenter
		extends
		Presenter<RemoteFilterGridPresenter.MyView, RemoteFilterGridPresenter.MyProxy> {

	public interface MyView extends View {

		void setLoader(
				PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Stock>> loader);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.remoteFilterGrid)
	@TabInfo(container = GXTTabPresenter.class, label = "Remote Filter Grid", priority = TabPriorities.remoteFilterGrid)
	public interface MyProxy extends
			TabContentProxyPlace<RemoteFilterGridPresenter> {
	}

	private final DispatchAsync dispatcher;

	@Inject
	public RemoteFilterGridPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy,
			final DispatchAsync dispatcher) {
		super(eventBus, view, proxy);
		this.dispatcher = dispatcher;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, GXTTabPresenter.TYPE_SetTabContent,
				this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		initLoader();
	}

	private void initLoader() {
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Stock>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Stock>>() {
			@Override
			public void load(final FilterPagingLoadConfig loadConfig,
					final AsyncCallback<PagingLoadResult<Stock>> callback) {
				dispatcher.execute(new LoadStockAction(loadConfig),
						new AsyncCallback<LoadStockResult>() {
							@Override
							public void onFailure(Throwable caught) {
								callback.onFailure(caught);
							}

							@Override
							public void onSuccess(LoadStockResult result) {
								callback.onSuccess(result.getResult());
							}
						});
			}

		};

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Stock>> remoteLoader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Stock>>(
				proxy) {
			@Override
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		remoteLoader.setRemoteSort(true);
		getView().setLoader(remoteLoader);
		remoteLoader.load();
	}
}
