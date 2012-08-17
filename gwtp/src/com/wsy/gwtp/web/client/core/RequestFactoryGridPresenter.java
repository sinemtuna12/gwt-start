package com.wsy.gwtp.web.client.core;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.data.shared.loader.RequestFactoryProxy;
import com.wsy.gwtp.web.client.place.NameTokens;
import com.wsy.gwtp.web.client.place.TabPriorities;
import com.wsy.gwtp.web.shared.ExampleRequestFactory;
import com.wsy.gwtp.web.shared.PostProxy;
import com.wsy.gwtp.web.shared.PostRequest;

public class RequestFactoryGridPresenter
		extends
		Presenter<RequestFactoryGridPresenter.MyView, RequestFactoryGridPresenter.MyProxy> {

	public interface MyView extends View {

		void setLoader(
				PagingLoader<FilterPagingLoadConfig, PagingLoadResult<PostProxy>> loader);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.requestFactoryGrid)
	@TabInfo(container = GXTTabPresenter.class, label = "Request Factory Grid", priority = TabPriorities.requestFactoryGrid)
	public interface MyProxy extends
			TabContentProxyPlace<RequestFactoryGridPresenter> {
	}

	private final ExampleRequestFactory rf;

	@Inject
	public RequestFactoryGridPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy,
			final ExampleRequestFactory rf) {
		super(eventBus, view, proxy);
		this.rf = rf;
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
		this.rf.initialize(this.getEventBus());
		final RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<PostProxy>> proxy = new RequestFactoryProxy<FilterPagingLoadConfig, PagingLoadResult<PostProxy>>() {
			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					Receiver<? super PagingLoadResult<PostProxy>> receiver) {
				PostRequest req = rf.post();
				List<SortInfo> sortInfo = createRequestSortInfo(req,
						loadConfig.getSortInfo());
				List<FilterConfig> filterConfig = createRequestFilterConfig(
						req, loadConfig.getFilters());
				req.getPosts(loadConfig.getOffset(), loadConfig.getLimit(),
						sortInfo, filterConfig).to(receiver);

				req.fire();
			}
		};

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<PostProxy>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<PostProxy>>(
				proxy) {
			@Override
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		getView().setLoader(loader);
		loader.load();
	}
}
