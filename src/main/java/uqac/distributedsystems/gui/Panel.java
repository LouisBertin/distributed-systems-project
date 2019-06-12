package uqac.distributedsystems.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {

	Panel(Dimension d){
		System.out.println(d);
		this.setSize(d);
	}

	public void paintComponent(Graphics g){
		g.drawString("Kitchen", 10, 12);
		//x1, y1, width, height
		g.setColor(Color.BLUE);
		g.fillRect(10, 15, 100, 100);

		//object dot :
		g.setColor(Color.DARK_GRAY);
		g.fillOval(20, 20, 8, 8);
		g.drawString("Wi-Fi Direct", 35, 35);
	}

}
