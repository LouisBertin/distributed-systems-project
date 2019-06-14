package uqac.distributedsystems.model;

public class Device {
	private String name;
	private String technology;
	private Coordinate coords;	
	
	public Device(String name, String technology, Coordinate coords) {
		super();
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
}
