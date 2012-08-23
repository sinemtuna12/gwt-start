/**
 * 
 */
package com.wsy.gwtp.web.client.solar;

import java.util.Date;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.user.client.Timer;

/**
 * @author e518417
 * 
 */
public class SolarSystem {

	Canvas canvas;

	private int width;
	private int height;

	private PlanetaryBody sun;

	private long renderTime;

	public SolarSystem(Canvas canvas) {
		this.canvas = canvas;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void start() {
		// Measure the canvas element.
		// canvas.parent.rect.then((ElementRect rect) {
		// _width = rect.client.width;
		// _height = rect.client.height;
		//
		// canvas.width = _width;
		//
		// // Initialize the planets and start the simulation.
		// _start();
		// });
		this.width = canvas.getOffsetWidth();
		this.height = canvas.getOffsetHeight();
		_start();
	}

	private void _start() {
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

		// Start the animation loop.
		// requestRedraw();
		new Timer() {

			@Override
			public void run() {
				draw();
			}
		}.scheduleRepeating(25);
	}

	public boolean draw() {
		if (renderTime != 0) {
			// showFps((1000 / (time - renderTime)));
		}

		renderTime = new Date().getTime();

		Context2d context = canvas.getContext2d();

		drawBackground(context);
		drawPlanets(context);

		// requestRedraw();
		return true;
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

}
