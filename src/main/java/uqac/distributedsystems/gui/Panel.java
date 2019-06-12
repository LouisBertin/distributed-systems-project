package uqac.distributedsystems.gui;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {
	 
  public void paintComponent(Graphics g){
    //x1, y1, width, height
    g.drawRect(10, 10, 50, 60);
  }               
}
