package uqac.distributedsystems.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame{
	public Frame(){                
	    this.setTitle("Project");
	    this.setSize(700, 700);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(new Panel(this.getSize()));	    

	    this.setVisible(true);
	  }    
}
