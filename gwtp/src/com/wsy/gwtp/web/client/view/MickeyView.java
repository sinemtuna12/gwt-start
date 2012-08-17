package com.wsy.gwtp.web.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.wsy.gwtp.web.client.core.MickeyPresenter;
import com.wsy.gwtp.web.client.core.MickeyPresenter.MyView;

public class MickeyView extends ViewImpl implements MickeyPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, MickeyView> {
	}

	@Inject
	public MickeyView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
