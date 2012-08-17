package com.wsy.gwtp.web.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.wsy.gwtp.web.client.core.CherryBlossomPresenter;
import com.wsy.gwtp.web.client.core.CherryBlossomPresenter.MyView;

public class CherryBlossomView extends ViewImpl implements
		CherryBlossomPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, CherryBlossomView> {
	}

	@Inject
	public CherryBlossomView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
