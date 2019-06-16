package uqac.distributedsystems.gui;

import uqac.distributedsystems.astar.Astar;
import uqac.distributedsystems.model.Coordinate;
import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Room;
import uqac.distributedsystems.tools.Helper;
import uqac.distributedsystems.tools.Parser;

import java.awt.*;
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
		ArrayList<Room> rooms = Parser.getFormattedDataFromJson(Helper.getResourcesPath() + "/data/sample2.json");
		Astar.network(rooms);
		// paint rooms
		for (Room room: rooms) {
			room.paintComponent(g);
			devices.addAll(room.getDevices());
		}
		//Pour éviter tout problème de d'affichage
		for (Device device : devices) {
			device.paintComponent(g);
		}
	}

	private void affichage(ArrayList<Device> chemin){
		Graphics g = getGraphics();
		g.setColor(Color.GREEN);
		for(int i = 0; i <= chemin.size()-2; i++){
			Device d1 = chemin.get(i);
			Device d2 = chemin.get(i+1);
			System.out.println(d1.getName() + " " + d2.getName());
			g.drawLine(d1.getCoords().getX() + 4,d1.getCoords().getY() + 4,
					d2.getCoords().getX() + 4, d2.getCoords().getY() + 4);
		}
	}

	void sendMessage(){
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
		//Chemin trouver directement
		if(de != null){
			for (Device d:de) {
				System.out.println(d.getName());
			}
			affichage(de);
		}
		//Cela veut dire que le chemin n'existe pas, il faut donc faire appel au drone
		else{
			Device drone = new Device("drone","wifi", new Coordinate(10, 350));
			drone.paintComponent(getGraphics());//Affichage du drone
			Device nearest = a.getNearestNode().getDevice();

			moveDrone(drone, nearest);
			//Le drone vient de recupperer le message, il va devoir se préparer à l'envoyer a l'autre réseau.
			moveDrone(drone, devices.get(n2));

			System.out.println("Message transmis entre " + devices.get(n1).getName() + " et " + devices.get(n2).getName());

			while(!drone.getCoords().equals(new Coordinate(10, 350))){
				int x = drone.getCoords().getX();
				int y = drone.getCoords().getY();

				if(x < 10){
					drone.setCoords(new Coordinate(x+10, y));
				}
				if(x > 10){
					drone.setCoords(new Coordinate(x-10, y));
				}
				if(y < 350){
					drone.setCoords(new Coordinate(x, y+10));
				}
				if(y > 350){
					drone.setCoords(new Coordinate(x, y-10));
				}
				paintComponent(getGraphics());
				drone.paintComponent(getGraphics());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void moveDrone(Device drone, Device goal){
		Astar a = new Astar();
		ArrayList<Device> cheminDrone = a.execute(goal, drone);//On va transmettre le message au drone
		System.out.println(goal.getName());
		while(cheminDrone == null){
			int xGoal = goal.getCoords().getX();
			int yGoal = goal.getCoords().getY();
			int xDrone = drone.getCoords().getX();
			int yDrone = drone.getCoords().getY();

			if(xDrone < xGoal){
				xDrone += 10;
			}else if(xDrone > xGoal){
				xDrone -= 10;
			}
			if(yDrone < yGoal){
				yDrone += 10;
			}else if(yDrone > yGoal){
				yDrone -= 10;
			}
			drone.setCoords(new Coordinate(xDrone, yDrone));
			paintComponent(getGraphics());
			drone.paintComponent(getGraphics());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			double range = Astar.calculateRange(goal.getTechnology(), drone.getTechnology());
			if (goal.getCoords().distance(drone.getCoords()) <= range) {
				goal.addNeighbour(drone);
			}
			cheminDrone = a.execute(goal, drone);
		}
	}

}