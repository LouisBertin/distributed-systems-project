package uqac.distributedsystems.astar;
import uqac.distributedsystems.model.Coordinate;
import uqac.distributedsystems.model.Device;

import java.util.ArrayList;

/**
 * Classe pour représenter un utilisateur/point
 */
public class Node {

    private Coordinate pos;
    private Node father;
    private Device device;
    private double distanceToHere;
    private int deep;

    public Node getFather() {
        return father;
    }


    public double heuristic(Coordinate but) {
        return distanceToHere + pos.distance(but);
    }

    public Coordinate getPos() {
        return pos;
    }

    public Device getDevice() {
        return device;
    }

    public Node(Device device, Node father) {
        this.father = father;
        this.pos = device.getCoords();
        this.device = device;
        if (father == null) {
            distanceToHere = 0;
            deep = 0;
        }
        else{
            distanceToHere = father.distanceToHere + father.getPos().distance(pos);
            deep = father.deep +1;
        }

    }


    public ArrayList<Node> nextNode() {
        boolean b = true;
        ArrayList<Node> list = new ArrayList<>();
        ArrayList<Device> toAvoid = new ArrayList<>();
        for (Device d :this.toTrail())
            toAvoid.add(d);
        for (Device d : device.getNeighbourhood()) {
            b = true;
            for (Device avoid : toAvoid) {
                if ((!avoid.equals(d)) && b) {
                    list.add(new Node(d, this));
                    b = false;
                }

            }
        }

        return list;
    }

    public ArrayList<Device> toTrail() {

        ArrayList<Device> result = new ArrayList<>();
        Node finish = this;
        if (finish!=null) {
            while(finish !=null){
                result.add(finish.getDevice());
                finish = finish.getFather();
            }
            return result;
        }
        return null;
    }

    @Override
    public String toString(){
        return ""+pos.toString();
    }

    public int deep() {
        return deep;
    }

    public boolean isIn(ArrayList<Node> openList) {
        for(Node n :openList){
            if(device.equals(n.getDevice())){
                return true;
            }
        }
        return false;
    }
}