package com.wsy.gwtp.web.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.wsy.gwtp.web.client.core.GridSystemPresenter;

public class GridSystemView extends ViewImpl implements
		GridSystemPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, GridSystemView> {
	}

	@Inject
	public GridSystemView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
