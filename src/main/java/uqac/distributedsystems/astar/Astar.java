package uqac.distributedsystems.astar;

import uqac.distributedsystems.model.Coordinate;
import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Room;

import java.util.ArrayList;

public class Astar {
    private Coordinate but;
    private Node nearestNode;
    private double min = 1000;

    //Cette fonction remplie une liste de voisins connectés qui sont accessibles entre eux
    public static void network(ArrayList<Room> rooms) {
        ArrayList<Device> devices = new ArrayList<>();
        for (Room room : rooms) {
            devices.addAll(room.getDevices());
        }

        for (Device d1 : devices) {
            for (Device d2 : devices) {
                if (d1.getCoords().distance(d2.getCoords()) <= calculateRange(d1.getTechnology(), d2.getTechnology())) {
                    if(!d1.getName().equals(d2.getName()))
                        d1.addNeighbour(d2);
                }
            }
        }
    }

    //Cette fonction calcule la portée de la technologie
    public static double calculateRange(String techno1, String techno2){
        return getRange(techno1) + getRange(techno2);
    }

    //Cette fonction retourne la portée de la techno
    public static double getRange(String techno){
        switch (techno) {
            case "wifi":
                return 1.1 * 100;
            case "lora":
                return 1.7 * 100;
        }
        return 0;
    }

    /**
     * recherche A* standard, à partir d'un point passé en paramètre
     *
     * @param node (Node)
     * @return null si pas de chemin, sinon le point d'arrivé ("but")
     */
    private Node search(Node node) {
        ArrayList<Node> openList = new ArrayList<>();
        openList.add(node);

        while (!openList.isEmpty()) {
            Node curNode = selectNode(openList);

            openList.remove(curNode);
            if (curNode.getPos().equals(but)) {
                return curNode;
            }else if(curNode.getPos().distance(but)<min){
                nearestNode = curNode;
                min = curNode.getPos().distance(but);
            }else if (curNode.deep() > 80) {
                return null;
            }

            for (Node nNode : curNode.nextNode()) {

                if (!nNode.isIn(openList)) {
                    openList.add(nNode);
                } else if (nNode.heuristic(but) < curNode.heuristic(but)) {
                    curNode = nNode;
                }
            }
        }
        return null;
    }

    /**
     * Execute une recherche  A* du point u vers le point but
     *
     * @param u point A
     * @param but point B
     * @return  le chemin pour aller de A vers B, null si pas de chemin possible
     */
    public ArrayList<Device> execute(Device u, Device but) {
        min = 1000;
        nearestNode = null;
        this.but = but.getCoords();
        Node node = new Node(u, null);
        Node finish = search(node);
        if (finish != null)
            return finish.toTrail();
        else
            return null;
    }

    /**
     * Sélectionne le point
     *
     * @param list (ArrayList<Node>)
     * @return node (Node)
     */
    private Node selectNode(ArrayList<Node> list) {
        Node curNode = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (curNode.heuristic(but) > list.get(i).heuristic(but)) {
                curNode = list.get(i);
            }
        }
        return curNode;
    }

    /**
     * retourne le point le plus proche du node sur lequel on appelle la methode
     * @return nearestNode (Node)
     */
    public Node getNearestNode() {
        return nearestNode;
    }
}