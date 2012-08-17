package com.wsy.gwtp.web.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.wsy.gwtp.web.client.core.AboutUSPresenter;

public class AboutUSView extends ViewImpl implements AboutUSPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, AboutUSView> {
	}
	private static final Binder binder = GWT.create(Binder.class);
	public AboutUSView() {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
