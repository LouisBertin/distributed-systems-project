package uqac.distributedsystems.astar;

import uqac.distributedsystems.model.Coordinate;
import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Room;

import java.util.ArrayList;

/*import java.util.ArrayList;

public class Astar {
    Device nearestDevice;
    private double min = 1000;

    public static void network(ArrayList<Room> rooms){
        ArrayList<Device> devices = new ArrayList<>();
        for (Room room : rooms) {
            ArrayList<Device> ds = room.getDevices();
            for (Device d:ds) {
                devices.add(d);
            }
        }

        for (Device d1:devices) {
            for (Device d2:devices) {
                double distance = d1.getCoords().distance(d2.getCoords());
                String techno1 = d1.getTechnology();
                String techno2 = d2.getTechnology();
                double range1 = 0;
                double range2 = 0;

                switch (techno1){
                    case "wifi" :
                        range1 = 1.1 * 100;
                        break;
                    case "lora" :
                        range1 = 1.7 * 100;
                        break;
                }
                switch (techno2){
                    case "wifi" :
                        range2 = 1.1 * 100;
                        break;
                    case "lora" :
                        range2 = 1.7 * 100;
                        break;
                }
                if(distance <= range1 +range2){
                    d1.addNeighbour(d2);
                }
            }
        }

    }

    public Device search(Device start, Device end){
        ArrayList<Device> deviceArrayList = new ArrayList<>();
        deviceArrayList.add(start);

        while(!deviceArrayList.isEmpty()){
            Device device = selectionDevice(deviceArrayList, end);
            //Sélection de l'appareil
            deviceArrayList.remove(device);

            if (device.getCoords().equals(end.getCoords())) {
                return device;
            }else if(device.getCoords().distance(end.getCoords())<min){
                nearestDevice = device;
                min = device.getCoords().distance(end.getCoords());
            }else if (device.deep() > 80) {
                return null;
            }

            for (Device d : device.nextNode()) {

                if (!d.isIn(deviceArrayList)) {
                    deviceArrayList.add(d);
                } else if (d.heuristic(end) < device.heuristic(end)) {
                    device = d;
                }
            }
        }
        return null;
    }


    private Device selectionDevice(ArrayList<Device> list, Device end){
        Device device = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (device.heuristic(end) > list.get(i).heuristic(end)) {
                device = list.get(i);
            }
        }

        return device;
    }
}*/

public class Astar {
    private Coordinate but;
    private Node nearestNode;
    private double min = 1000;

    public static void network(ArrayList<Room> rooms) {
        ArrayList<Device> devices = new ArrayList<>();
        for (Room room : rooms) {
            ArrayList<Device> ds = room.getDevices();
            for (Device d : ds) {
                devices.add(d);
            }
        }

        for (Device d1 : devices) {
            for (Device d2 : devices) {
                double distance = d1.getCoords().distance(d2.getCoords());
                String techno1 = d1.getTechnology();
                String techno2 = d2.getTechnology();
                double range1 = 0;
                double range2 = 0;

                switch (techno1) {
                    case "wifi":
                        range1 = 1.1 * 100;
                        break;
                    case "lora":
                        range1 = 1.7 * 100;
                        break;
                }
                switch (techno2) {
                    case "wifi":
                        range2 = 1.1 * 100;
                        break;
                    case "lora":
                        range2 = 1.7 * 100;
                        break;
                }
                if (distance <= range1 + range2) {
                    if(!d1.getName().equals(d2.getName()))
                        d1.addNeighbour(d2);
                }
            }
        }
    }

    /**
     * recherche A* standard, à partir d'un point passé en paramètre
     *
     * @param node
     * @return null si pas de chemin, sinon le point d'arrivé ("but")
     */
    private Node search(Node node,boolean b) {
        ArrayList<Node> closedList = new ArrayList<>();
        ArrayList<Node> openList = new ArrayList<>();
        openList.add(node);

        while (!openList.isEmpty()) {
            Node curNode = selectNode(openList);

            openList.remove(curNode);
            closedList.add(curNode);
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
        Node finish = search(node,false);
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
}