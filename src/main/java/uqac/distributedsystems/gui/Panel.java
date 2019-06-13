package uqac.distributedsystems.gui;

import uqac.distributedsystems.model.Coordinate;
import uqac.distributedsystems.model.Room;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

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
		System.out.println(d);
		this.setSize(d);
	}

	public void paintComponent(Graphics g){
		//x1, y1, width, height
		Room room = new Room("kitchen", new Coordinate(0, 0), 200, 200, Color.BLUE);
		room.paintComponent(g);

		//object dot :
/*		g.setColor(Color.RED);
		g.fillOval(20, 20, 8, 8);
		g.drawString("Wi-Fi Direct", 35, 35);*/
	}

}
