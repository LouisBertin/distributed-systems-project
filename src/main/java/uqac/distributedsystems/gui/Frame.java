package uqac.distributedsystems.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame{
	public Frame(){                
	    this.setTitle("Project");
	    this.setSize(500, 500);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(new Panel());	    

	    this.setVisible(true);
	  }    
}
