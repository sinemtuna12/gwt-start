package com.wsy.gwtp.web.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.wsy.gwtp.web.client.core.ClockDemoPresenter;
import com.wsy.gwtp.web.client.core.ClockDemoPresenter.MyView;

public class ClockDemoView extends ViewImpl implements
		ClockDemoPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, ClockDemoView> {
	}

	@Inject
	public ClockDemoView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
