package uqac.distributedsystems.astar;

import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Room;

import java.util.ArrayList;

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

    /*public Device search(Device start, Device end){
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
    }*/
}