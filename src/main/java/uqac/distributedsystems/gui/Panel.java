package uqac.distributedsystems.gui;

import uqac.distributedsystems.astar.Astar;
import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Room;
import uqac.distributedsystems.tools.Helper;
import uqac.distributedsystems.tools.Parser;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * The type Panel.
 */
public class Panel extends JPanel {
	private ArrayList<Device> devices = new ArrayList<>();

	/**
	 * Instantiates a new Panel.
	 *
	 * @param d the d
	 */
	Panel(Dimension d){
		// default 700x700
		this.setSize(d);
	}

	public void paintComponent(Graphics g){
		ArrayList<Room> rooms = Parser.getFormattedDataFromJson(Helper.getResourcesPath() + "/data/sample.json");
		Astar.network(rooms);
		// paint rooms
		for (Room room: rooms) {
			room.paintComponent(g);
			for (Device device: room.getDevices()) {
				this.devices.add(device);
				device.paintComponent(g);
			}
		}

	}

	public void sendMessage(){
		Astar a = new Astar();

		Random rand = new Random();
		int n1 = rand.nextInt(this.devices.size());
		int n2 = rand.nextInt(this.devices.size());
		while (n1 == n2){
			n2 = rand.nextInt(devices.size());
		}
		ArrayList<Device> de = a.execute(devices.get(n1), devices.get(n2));
		System.out.println("N1 : " +devices.get(n1).getName() );
		System.out.println("N2 : "+devices.get(n2).getName());
		if(de != null){
			for (Device d:de) {
				System.out.println(d.toString());
			}
		}
	}

}