/**
 * 
 */
package com.wsy.gwtp.web.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.wsy.gwtp.web.client.core.CounterDemoPresenter;

/**
 * @author Blues
 *
 */
public class CounterDemoView extends ViewImpl implements CounterDemoPresenter.MyView {

	public interface CounterDemoViewUiBinder extends UiBinder<Widget, CounterDemoView> {
	}

	private final Widget widget;
	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	@Inject
	public CounterDemoView(final  CounterDemoViewUiBinder uiBinder) {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

}
