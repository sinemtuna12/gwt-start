/**
 * 
 */
package com.wsy.gwtp.web.client.solar;

import java.util.Date;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.wsy.gwtp.web.client.view.CanvasDemoView;

/**
 * @author e518417
 * 
 */
public class SolarSystem {

	private Canvas canvas;
	private Canvas buffer;
	private CanvasDemoView view;
	private int width;
	private int height;

	private PlanetaryBody sun;

	private long renderTime;

	private Timer timer;
	
	public SolarSystem(CanvasDemoView view, Canvas canvas, int width, int height) {
		this.view = view;
		this.canvas = canvas;
		this.width = width;
		this.height = height;
		this.buffer = Canvas.createIfSupported();
//		this.buffer.setWidth(width + "px");
//		this.buffer.setHeight(height + "px");
		this.buffer.setCoordinateSpaceWidth(width);
		this.buffer.setCoordinateSpaceHeight(height);
		init();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void init() {
		// Create the Sun.
		sun = new PlanetaryBody(this, "Sun", "#ff2", 14.0);

		// Add planets.
		sun.addPlanet(new PlanetaryBody(this, "Mercury", "orange", 0.382,
				0.387, 0.241));
		sun.addPlanet(new PlanetaryBody(this, "Venus", "green", 0.949, 0.723,
				0.615));

		PlanetaryBody earth = new PlanetaryBody(this, "Earth", "#33f", 1.0,
				1.0, 1.0);
		sun.addPlanet(earth);
		earth.addPlanet(new PlanetaryBody(this, "Moon", "gray", 0.2, 0.14,
				0.075));

		sun.addPlanet(new PlanetaryBody(this, "Mars", "red", 0.532, 1.524, 1.88));

		addAsteroidBelt(sun, 150);

		double f = 0.1;
		double h = 1 / 1500.0;
		double g = 1 / 72.0;

		PlanetaryBody jupiter = new PlanetaryBody(this, "Jupiter", "gray", 4.0,
				5.203, 11.86);
		sun.addPlanet(jupiter);
		jupiter.addPlanet(new PlanetaryBody(this, "Io", "gray", 3.6 * f,
				421 * h, 1.769 * g));
		jupiter.addPlanet(new PlanetaryBody(this, "Europa", "gray", 3.1 * f,
				671 * h, 3.551 * g));
		jupiter.addPlanet(new PlanetaryBody(this, "Ganymede", "gray", 5.3 * f,
				1070 * h, 7.154 * g));
		jupiter.addPlanet(new PlanetaryBody(this, "Callisto", "gray", 4.8 * f,
				1882 * h, 16.689 * g));
	}
	
	public void start() {
		timer = new Timer() {

			@Override
			public void run() {
				draw();
			}
		};
		timer.scheduleRepeating(1);
	}

	public void draw() {
		long time = new Date().getTime();
		if (renderTime != 0) {
			view.showFps((1000.0 / (time - renderTime)));
		}

		renderTime = time;

		Context2d context = buffer.getContext2d();

		drawBackground(context);
		drawPlanets(context);
		
		Context2d front = this.canvas.getContext2d();
		
		front.drawImage(context.getCanvas(), 0, 0);
	}

	private void drawBackground(Context2d context) {
		// context.fillStyle = "white";
		context.setFillStyle("white");
		context.rect(0, 0, width, height);
		context.fill();
	}

	void drawPlanets(Context2d context) {
		sun.draw(context, width / 2, height / 2);
	}

	//
	// void requestRedraw() {
	// .requestAnimationFrame(draw);
	// }

	private void addAsteroidBelt(PlanetaryBody body, int count) {
		// Asteroids are generally between 2.06 and 3.27 AUs.
		for (int i = 0; i < count; i++) {
			double radius = 2.06 + Math.random() * (3.27 - 2.06);

			body.addPlanet(new PlanetaryBody(this, "asteroid", "#777",
					0.1 * Math.random(), radius, radius * 2));
		}
	}

	public double normalizeOrbitRadius(double r) {
		return r * (width / 10.0);
	}

	public double normalizePlanetSize(double r) {
		return Math.log(r + 1) * (width / 100.0);
	}

	public double getRenderTime() {
		return this.renderTime;
	}

	public void stop() {
		this.timer.cancel();
	}

}
