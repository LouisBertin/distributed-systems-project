package uqac.distributedsystems.model;

import uqac.distributedsystems.astar.Astar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Device extends JComponent {
	private String name;
	private String technology;
	private Coordinate coords;

	private ArrayList<Device> neighbourhood = new ArrayList<>();

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

		//Pour supprimer le device quand il n'est pas a porté
		ArrayList<Device> removeDevice = new ArrayList<>();
		for (Device neighbour: neighbourhood) {
			if(getCoords().distance(neighbour.getCoords()) > Astar.calculateRange(getTechnology(), neighbour.getTechnology())) {
				removeDevice.add(neighbour);
			}
		}
		for (Device device : removeDevice) {
			neighbourhood.remove(device);
		}

		for (Device neighbour: neighbourhood) {
				g.setColor(Color.BLACK);
				g.drawLine(neighbour.getCoords().getX() + 4,neighbour.getCoords().getY() + 4, this.getCoords().getX() + 4, this.getCoords().getY() + 4);
		}
	}

	public void addNeighbour(Device d){
		neighbourhood.add(d);
	}

	public ArrayList<Device> getNeighbourhood(){
		return neighbourhood;
	}

	public void removeNeighbour(Device d){
		ArrayList<Device> removeDevice = new ArrayList<>();
		for (Device device : neighbourhood) {
			if(device.getName().equals(d.getName()))
				removeDevice.add(device);
		}
		for (Device device :removeDevice) {
			neighbourhood.remove(device);
		}
	}

	@Override
	public boolean equals(Object other){
		boolean result = false;
		if (other instanceof Device) {
			Device that = (Device) other;
			if(that.getName().equals(name) && that.getCoords().equals(coords) && that.getTechnology().equals(technology))
				result = true;
		}
		return result;
	}
}