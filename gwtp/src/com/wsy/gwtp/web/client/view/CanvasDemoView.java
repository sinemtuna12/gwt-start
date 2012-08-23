package com.wsy.gwtp.web.client.view;

import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.wsy.gwtp.web.client.core.CanvasDemoPresenter;
import com.wsy.gwtp.web.client.solar.SolarSystem;

public class CanvasDemoView extends ViewImpl implements
		CanvasDemoPresenter.MyView {

	private Canvas canvas;

	private int width = 1200;
	private int height = 400;
	private SolarSystem system;

	private double avgFps;
	private VerticalPanel panel;
	private Label fpsLbl;
	@Inject
	public CanvasDemoView() {
		panel = new VerticalPanel();
		
		this.canvas = Canvas.createIfSupported();
		if(this.canvas != null) {
			this.canvas.setWidth(width + "px");
			this.canvas.setHeight(height + "px");
			canvas.setCoordinateSpaceWidth(width);
		    canvas.setCoordinateSpaceHeight(height);
		    this.system = new SolarSystem(this, this.canvas, width, height);
		    fpsLbl = new Label("");
		    panel.add(this.canvas);
		    panel.add(fpsLbl);
		} else {
			panel.add(new Label("Canvas not supported"));
		}
		
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public void start() {
		this.system.start();
	}

	@Override
	public void stop() {
		this.system.stop();
	}

	public void showFps(double fps) {
		GWT.log("fps got is [" + fps + "]");
		
		if (avgFps == 0) {
			avgFps = fps;
		}

		avgFps = fps * 0.05 + avgFps * 0.95;
		GWT.log("avgFps is [" + avgFps + "]");
		fpsLbl.setText(avgFps + " fps");
	}
}
