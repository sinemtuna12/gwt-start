package com.wsy.gwtp.web.client.view;

import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.wsy.gwtp.web.client.core.CavasDemoPresenter;
import com.wsy.gwtp.web.client.solar.SolarSystem;

public class CavasDemoView extends ViewImpl implements
		CavasDemoPresenter.MyView {

	private Canvas canvas;
	
	@Inject
	public CavasDemoView() {
		this.canvas = Canvas.createIfSupported();
		if(this.canvas != null) {
			this.canvas.setWidth(600 + "px");
			this.canvas.setHeight(400 + "px");
		}
	}

	@Override
	public Widget asWidget() {
		if(canvas != null) {
			this.canvas.getContext2d().restore();
			new SolarSystem(this.canvas).start();
			return canvas;
		} else {
			return new Label("Canvas not supported");
		}
	}
}
