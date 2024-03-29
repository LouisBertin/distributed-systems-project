package uqac.distributedsystems.gui;

import uqac.distributedsystems.astar.Astar;
import uqac.distributedsystems.model.Coordinate;
import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Gateway;
import uqac.distributedsystems.model.Room;
import uqac.distributedsystems.tools.Helper;
import uqac.distributedsystems.tools.Parser;

import java.awt.*;
import java.util.*;

import javax.swing.JPanel;

/**
 * The type Panel.
 */
public class Panel extends JPanel {
	private ArrayList<Device> devices = new ArrayList<>();
	private Device drone;
	private ArrayList<Gateway> gateways;
	private Coordinate initialDronePosition;

	/**
	 * Instantiates a new Panel.
	 *
	 * @param d the d
	 */
	Panel(Dimension d){
		// default 700x700
		this.setSize(d);
		drone = Parser.getDrone(Helper.getResourcesPath() + (Helper.getPropertyValue("input_file")));
		gateways = Parser.getGatewaysFromJson(Helper.getResourcesPath() + (Helper.getPropertyValue("input_file")));
		if(drone != null)
			initialDronePosition = drone.getCoords();
	}

	/**
	 * Affiche toutes les salles et tous les devices
	 *
	 * @param g (Graphics)
	 */
	public void paintComponent(Graphics g){
		ArrayList<Room> rooms = Parser.getRoomsFromJson(Helper.getResourcesPath() + (Helper.getPropertyValue("input_file")));
		Astar.network(rooms);
		// paint rooms
		for (Room room: rooms) {
			room.paintComponent(g);
			for (Device device :room.getDevices()) {
				if(!devices.contains(device)){
					devices.add(device);
				}
			}
		}
		Set<Device> set = new HashSet<>(devices) ;
		devices = new ArrayList<>(set) ;

		//Pour éviter tout problème de d'affichage
		for (Device device : devices) {
			device.paintComponent(g);
		}

		if(drone != null){
			drone.paintComponent(g);
		}
		if(gateways != null){
			for (Gateway gateway:gateways) {
				gateway.paintComponent(g);
			}
		}
	}

	/**
	 * Affiche le chemin en vert
	 *
	 * @param chemin (ArrayList<Device>)
	 */
	private void affichage(ArrayList<Device> chemin){
		Graphics g = getGraphics();
		g.setColor(Color.GREEN);
		for(int i = 0; i <= chemin.size()-2; i++){
			Device d1 = chemin.get(i);
			Device d2 = chemin.get(i+1);
			g.drawLine(d1.getCoords().getX() + 4,d1.getCoords().getY() + 4,
					d2.getCoords().getX() + 4, d2.getCoords().getY() + 4);
		}
	}

	/**
	 * Pour envoyer un message
	 */
	void sendMessage(){
		Astar a = new Astar();

		//Sélection des points de départ et d'arrivé
		System.out.println("Point de départ");
		int n1 = selectionPoint(devices);
		System.out.println("Point d'arrivé");
		int n2 = selectionPoint(devices);
		while(n1 == n2){
			System.out.println("Point d'arrivé");
			n2 = selectionPoint(devices);
		}
		devices.get(n1).setSelection(true);
		devices.get(n2).setSelection(true);
		paintComponent(getGraphics());

		//Regarde si un chemin existe
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
			//cela veut dire que l'on est dans la configuration 2
			if(drone != null){
				drone.paintComponent(getGraphics());//Affichage du drone
				a.execute(devices.get(n1), drone);
				Device nearest = a.getNearestNode().getDevice();
				affichage(a.execute(devices.get(n1), nearest));
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
				moveDrone(nearest);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Le drone vient de recuperer le message, il va devoir se préparer à l'envoyer a l'autre réseau.
				a.execute(devices.get(n2), drone);

				moveDrone(a.getNearestNode().getDevice());
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Message transmis entre " + devices.get(n1).getName() + " et " + devices.get(n2).getName());
				affichage(a.execute(a.getNearestNode().getDevice(), devices.get(n2)));
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Le drone retourne a sa position initiale
				while(!drone.getCoords().equals(initialDronePosition)){
					int x = drone.getCoords().getX();
					int y = drone.getCoords().getY();

					if(x < initialDronePosition.getX()){
						x += 10;
					}
					if(x > initialDronePosition.getX()){
						x -= 10;
					}
					if(y < initialDronePosition.getY()){
						y += 10;
					}
					if(y > initialDronePosition.getY()){
						y -= 10;
					}
					drone.setCoords(new Coordinate(x, y));
					paintComponent(getGraphics());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			//Cela veut dire que l'on est dans la configuration 3
			else{
				boolean b = true;
				while(b){
					paintComponent(getGraphics());
					for (Gateway gateway:gateways) {
						Device temp = new Device(gateway.getName(), gateway.getTechnology(), gateway.getCurrentCoords());
						gateway.setCurrentCoords(move(temp, gateway.getDestination()));
						if(gateway.getCurrentCoords().equals(gateway.getDestination())){
							gateway.changeDestination();
						}
						for (Device d:devices) {
							d.removeNeighbour(temp);
						}
						temp.paintComponent(getGraphics());
						ArrayList<Device> trail;
						if (!gateway.isHasMessage()) {
							trail = a.execute(temp, devices.get(n1));
						}else{
							trail = a.execute(temp, devices.get(n2));
						}
						if(trail != null){
							gateway.setHasMessage();
							if(gateway.isHasMessage()){
                                affichage(trail);
                                System.out.println(gateway.getName() + " a récupéré le message");
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
							else{
							    affichage(trail);
								System.out.println(gateway.getName() + " a transmis le message");
								b = false;
							}

						}
					}
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Sélection des points
	 *
	 * @param d (ArrayList<Device> )
	 * @return int
	 */
	private int selectionPoint(ArrayList<Device> d){
		System.out.println("Choisissez parmis ces points : ");
		for (int i = 0; i < d.size(); i++) {
			System.out.println(i + ". " + d.get(i).getName());
		}
		Scanner scan = new Scanner(System.in);
		String r = scan.nextLine();
		try{
			return Integer.parseInt(r);
		}catch (Exception e){
			return selectionPoint(d);
		}
	}

	/**
	 * Fait bouger la passerelle
	 *
	 * @param gateway (Device)
	 * @param destination (Coordinate)
	 * @return (Coordinate)
	 */
	private Coordinate move(Device gateway, Coordinate destination){
		int x = gateway.getCoords().getX();
		int y = gateway.getCoords().getY();
		int xGoal = destination.getX();
		int yGoal = destination.getY();

		if(x < xGoal){
			x += 10;
		}else if(x > xGoal){
			x -= 10;
		}
		if(y < yGoal){
			y += 10;
		}else if(y > yGoal){
			y -= 10;
		}
		for (Device device: devices) {
			double range = Astar.calculateRange(gateway.getTechnology(), device.getTechnology());
			if (gateway.getCoords().distance(device.getCoords()) <= range) {
				device.addNeighbour(gateway);
				gateway.addNeighbour(device);
			}
		}
		return new Coordinate(x, y);
	}

	/**
	 * Fait bouger le drone
	 *
	 * @param goal (Coordinate)
	 */
	private void moveDrone(Device goal){
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
        affichage(cheminDrone);
	}

}