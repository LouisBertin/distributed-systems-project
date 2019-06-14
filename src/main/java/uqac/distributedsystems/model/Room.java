package uqac.distributedsystems.model;

import java.awt.Color;
import java.util.ArrayList;

public class Room {
	private String label;
	private Coordinate coords;
	private int width;
	private int height;
	private Color background;
	private ArrayList<Device> devices;		
	
	public Room(String label, Coordinate coords, int width, int height, Color background) {
		this.label = label;
		this.coords = coords;
		this.width = width;
		this.height = height;
		this.background = background;
		devices = new ArrayList<>();
	}
	
	public void addDevice(Device device) {
		devices.add(device);
	}
	
	public void RemoveDevice(Device device) {
		devices.remove(device);
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Coordinate getCoords() {
		return coords;
	}
	public void setCoords(Coordinate coords) {
		this.coords = coords;
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
	public Color getBackground() {
		return background;
	}
	public void setBackground(Color background) {
		this.background = background;
	}
	public ArrayList<Device> getDevice() {
		return devices;
	}
	public void setDevice(ArrayList<Device> devices) {
		this.devices = devices;
	}
}
