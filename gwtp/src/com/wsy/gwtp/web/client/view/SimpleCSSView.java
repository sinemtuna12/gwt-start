package com.wsy.gwtp.web.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.wsy.gwtp.web.client.core.SimpleCSSPresenter;
import com.wsy.gwtp.web.client.core.SimpleCSSPresenter.MyView;

public class SimpleCSSView extends ViewImpl implements
		SimpleCSSPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, SimpleCSSView> {
	}

	@Inject
	public SimpleCSSView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
