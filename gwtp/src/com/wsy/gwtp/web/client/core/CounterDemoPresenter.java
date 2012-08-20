/**
 * 
 */
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

/**
 * @author Blues
 *
 */
public class CounterDemoPresenter extends Presenter<CounterDemoPresenter.MyView, CounterDemoPresenter.MyProxy> {


	public interface MyView extends View {
		
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.counterDemo)
	@TabInfo(container = CssPresenter.class, label = "Counter Demo", priority = TabPriorities.counterDemo)
	public interface MyProxy extends TabContentProxyPlace<CounterDemoPresenter> {
		
	}
	
	@Inject
	public CounterDemoPresenter(EventBus eventBus,
			MyView view, MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, CssPresenter.TYPE_SetTabContent, this);
	}
}
