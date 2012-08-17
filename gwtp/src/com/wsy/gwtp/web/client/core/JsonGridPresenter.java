package com.wsy.gwtp.web.client.core;

import com.google.gwt.http.client.RequestBuilder;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;
import com.sencha.gxt.data.client.loader.HttpProxy;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.wsy.gwtp.web.client.model.Email;
import com.wsy.gwtp.web.client.model.Email.EmailRecordJsonReader;
import com.wsy.gwtp.web.client.place.NameTokens;
import com.wsy.gwtp.web.client.place.TabPriorities;

public class JsonGridPresenter extends
		Presenter<JsonGridPresenter.MyView, JsonGridPresenter.MyProxy> {

	public interface MyView extends View {
		void setPresenter(JsonGridPresenter presenter);

		void setLoader(ListLoader<ListLoadConfig, ListLoadResult<Email>> loader);
	}

	private ListLoader<ListLoadConfig, ListLoadResult<Email>> loader;

	@ProxyCodeSplit
	@NameToken(NameTokens.jsonGrid)
	@TabInfo(container = GXTTabPresenter.class, label = "Json Grid", priority = TabPriorities.jsonGrid)
	public interface MyProxy extends TabContentProxyPlace<JsonGridPresenter> {
	}

	@Inject
	public JsonGridPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
		view.setPresenter(this);
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

	public void loadJson() {
		this.loader.load();
	}

	private void initLoader() {
		EmailRecordJsonReader reader = new EmailRecordJsonReader();
		String path = "data/data.json";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, path);
		HttpProxy<ListLoadConfig> proxy = new HttpProxy<ListLoadConfig>(builder);
		loader = new ListLoader<ListLoadConfig, ListLoadResult<Email>>(proxy,
				reader);
		loader.useLoadConfig(Email.EmailAutoBeanFactory.instance.loadConfig()
				.as());
		this.getView().setLoader(loader);
	}
}
