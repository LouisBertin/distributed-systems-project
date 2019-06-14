package uqac.distributedsystems.gui;

import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Room;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private ArrayList<Room> rooms;

	Panel(Dimension d, ArrayList<Room> rooms){
		System.out.println(d);
		this.rooms = rooms;
		this.setSize(d);
	}

	public void paintComponent(Graphics g){
		/*g.drawString("Kitchen", 10, 12);
		//x1, y1, width, height
		g.setColor(Color.BLUE);
		g.fillRect(10, 15, 100, 100);

		//object dot :
		g.setColor(Color.DARK_GRAY);
		g.fillOval(20, 20, 8, 8);
		g.drawString("Wi-Fi Direct", 35, 35);*/
		for (Room room:rooms) {
			g.drawString(room.getLabel(),  (int) room.getCoords().getX(), (int) room.getCoords().getY());
			g.setColor(room.getBackground());
			g.fillRect((int) room.getCoords().getX(), (int) room.getCoords().getY(), 100, 100);

			for (Device device : room.getDevice()) {
				g.setColor(Color.DARK_GRAY);
				g.fillOval((int) device.getCoords().getX(), (int) device.getCoords().getY(), 8, 8);
				g.drawString(device.getName(),
						(int) device.getCoords().getX(), (int) device.getCoords().getY());
			}

		}
	}

}
