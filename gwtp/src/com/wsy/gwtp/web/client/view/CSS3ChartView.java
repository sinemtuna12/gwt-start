package com.wsy.gwtp.web.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.wsy.gwtp.web.client.core.CSS3ChartPresenter;
import com.wsy.gwtp.web.client.core.CSS3ChartPresenter.MyView;

public class CSS3ChartView extends ViewImpl implements
		CSS3ChartPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, CSS3ChartView> {
	}

	@Inject
	public CSS3ChartView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
