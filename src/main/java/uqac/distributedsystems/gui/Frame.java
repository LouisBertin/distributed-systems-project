package uqac.distributedsystems.gui;

import uqac.distributedsystems.model.Room;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Frame extends JFrame{

	public Frame(ArrayList<Room> rooms){
	    this.setTitle("Project");
	    this.setSize(700, 700);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setContentPane(new Panel(this.getSize(), rooms));

	    this.setVisible(true);
	  }    
}
