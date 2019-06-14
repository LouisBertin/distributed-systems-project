package uqac.distributedsystems.model;

import javax.swing.*;
import java.awt.*;

public class Device extends JComponent {
	private String name;
	private String technology;
	private Coordinate coords;

	public Device(String name, String technology, Coordinate coords) {
		this.name = name;
		this.technology = technology;
		this.coords = coords;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public Coordinate getCoords() {
		return coords;
	}
	public void setCoords(Coordinate coords) {
		this.coords = coords;
	}

	/**
	 * paint Component
	 * @param g Graphics
	 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.getCoords().getX(), this.getCoords().getY(), 8, 8);
		g.drawString(this.getName(), this.getCoords().getX(), this.getCoords().getY());
	}

}
