/**
 * 
 */
package com.wsy.gwtp.web.client.solar;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;

/**
 * @author e518417
 * 
 */
public class PlanetaryBody {

	private String name;
	private String color;
	private SolarSystem solarSystem;
	private double bodySize;
	private double orbitSpeed;
	private double orbitRadius;
	private List<PlanetaryBody> planets;

	public PlanetaryBody(String name, String color, double orbitPeriod,
			double bodySize, double orbitRadius, SolarSystem solarSystem) {
		this.name = name;
		this.color = color;
		this.bodySize = bodySize;
		this.orbitRadius = orbitRadius;
		this.solarSystem = solarSystem;
		this.planets = new ArrayList<PlanetaryBody>();
		this.orbitSpeed = this.calculateSpeed(orbitPeriod);
	}

	public PlanetaryBody(SolarSystem solarSystem, String name,
			String color, double bodySize) {
		this.solarSystem = solarSystem;
		this.name = name;
		this.color = color;
		this.bodySize = bodySize;
	}

	public PlanetaryBody(SolarSystem solarSystem, String name,
			String color, double bodySize, double orbitRadius) {
		this.solarSystem = solarSystem;
		this.name = name;
		this.color = color;
		this.bodySize = bodySize;
		this.orbitRadius = orbitRadius;
	}
	public PlanetaryBody(SolarSystem solarSystem, String name,
			String color, double bodySize, double orbitRadius, double orbitPeriod) {
		this.solarSystem = solarSystem;
		this.name = name;
		this.color = color;
		this.bodySize = bodySize;
		this.orbitRadius = orbitRadius;
		this.orbitSpeed = this.calculateSpeed(orbitPeriod);
	}

	public void addPlanet(PlanetaryBody plant) {
		this.planets.add(plant);
	}

	public void draw(Context2d context, int x, int y) {
		drawSelf(context, x, y);
		drawChildren(context, x, y);
	}

	private void drawSelf(Context2d context, int x, int y) {
		context.save();

		try {
			context.setLineWidth(0.5);
			context.setFillStyle(color);
			context.setStrokeStyle(color);
			if (bodySize >= 2.0) {
				context.setShadowOffsetX(2);
				context.setShadowOffsetY(2);
				context.setShadowBlur(2);
				context.setShadowColor("#ddd");
			}

			context.beginPath();
			context.arc(x, y, bodySize, 0, Math.PI * 2, false);
			context.fill();
			context.closePath();
			context.stroke();
			context.setShadowOffsetX(0);
			context.setShadowOffsetY(0);
			context.setShadowBlur(0);

			context.beginPath();
			context.arc(x, y, bodySize, 0, Math.PI * 2, false);
			context.fill();
			context.closePath();
			context.stroke();
		} finally {
			context.restore();
		}
	}

	private void drawChildren(Context2d context, int x, int y) {
		for (PlanetaryBody planet : planets) {
			planet.draw(context, x, y);
		}
	}

	public double calculateSpeed(double period) {
		if (period == 0.0) {
			return 0.0;
		} else {
			return 1 / (60.0 * 24.0 * 2 * period);
		}
	}

	public Point calculatePos(int x, int y) {
		if (orbitSpeed == 0.0) {
			return new Point(x, y);
		} else {
			double angle = solarSystem.getRenderTime() * orbitSpeed;

			return new Point(orbitRadius * Math.cos(angle) + x, orbitRadius
					* Math.sin(angle) + y);
		}
	}
}
