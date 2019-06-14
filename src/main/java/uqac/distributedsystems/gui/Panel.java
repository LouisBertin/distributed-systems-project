package uqac.distributedsystems.gui;

import uqac.distributedsystems.astar.Astar;
import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Room;
import uqac.distributedsystems.tools.Helper;
import uqac.distributedsystems.tools.Parser;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * The type Panel.
 */
public class Panel extends JPanel {

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
				device.paintComponent(g);
			}
		}
	}

}