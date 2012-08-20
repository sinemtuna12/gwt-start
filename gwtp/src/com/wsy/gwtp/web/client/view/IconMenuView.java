package com.wsy.gwtp.web.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.wsy.gwtp.web.client.core.IconMenuPresenter;
import com.wsy.gwtp.web.client.resources.ImageResources;

public class IconMenuView extends ViewImpl implements IconMenuPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, IconMenuView> {
	}

	@UiField(provided = true) ImageResources imgs = GWT.create(ImageResources.class);
	
	@Inject
	public IconMenuView(final Binder binder) {
		imgs.res().ensureInjected();
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
