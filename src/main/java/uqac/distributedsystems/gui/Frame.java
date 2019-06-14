package uqac.distributedsystems.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame{
	private Panel panel;
	public Frame(){
		this.setTitle("Project");
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new Panel(this.getSize());
		this.setContentPane(panel);

		this.setVisible(true);
	}

	public void sendMessage(){
		panel.sendMessage();
	}
}