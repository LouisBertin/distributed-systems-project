package uqac.distributedsystems.tools;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import uqac.distributedsystems.model.Coordinate;
import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Gateway;
import uqac.distributedsystems.model.Room;

/**
 * The type Parser.
 */
public class Parser {

    /**
     * Gets data from json.
     *
     * @param JsonFilePath the json file path
     * @return the data from json
     */
    public static JSONObject getDataFromJson(String JsonFilePath) {
        try {
            File file = new File(JsonFilePath);
            String content = FileUtils.readFileToString(file, "utf-8");

            // Convert JSON string to JSONObject
            return new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets formatted data from json.
     *
     * @param JsonFilePath the json file path
     * @return the formatted data from json
     */
    public static ArrayList<Room> getRoomsFromJson(String JsonFilePath) {
        JSONObject jsonObject = getDataFromJson(JsonFilePath);

        JSONArray rooms = null;
        if (jsonObject != null) {
            rooms = jsonObject.getJSONArray("rooms");
        }
        ArrayList<Room> arrayList = new ArrayList<>();
        // build Room array
        if (rooms != null) {
            for (int i = 0; i < rooms.length(); i++) {
                String label = rooms.getJSONObject(i).getString("label");
                String background = rooms.getJSONObject(i).getString("background");
                JSONArray coords = rooms.getJSONObject(i).getJSONArray("coords");
                Coordinate coordinate = new Coordinate(coords.getInt(0), coords.getInt(1));
                Room room = new Room(label, coordinate, coords.getInt(2), coords.getInt(3), Color.decode(background));
                // handle devices
                JSONArray devices = rooms.getJSONObject(i).getJSONArray("devices");
                for (int j = 0; j < devices.length(); j++) {
                    String name = devices.getJSONObject(j).getString("name");
                    String technology = devices.getJSONObject(j).getString("technology");
                    JSONArray coordinates = devices.getJSONObject(j).getJSONArray("coords");
                    Device device = new Device(name, technology, new Coordinate(coordinates.getInt(0), coordinates.getInt(1)));
                    room.addDevice(device);
                }
                arrayList.add(room);
            }
        }

        return arrayList;
    }

    /**
     * Gets gateways from json.
     *
     * @param JsonFilePath the json file path
     * @return the gateways from json
     */
    public static ArrayList<Gateway> getGatewaysFromJson(String JsonFilePath) {
        JSONObject jsonObject = getDataFromJson(JsonFilePath);

        JSONArray gateways = null;
        if (jsonObject != null) {
            gateways = jsonObject.getJSONArray("gateways");
        }
        ArrayList<Gateway> arrayList = new ArrayList<>();
        // build gateway array
        if (gateways != null) {
            for (int i = 0; i < gateways.length(); i++) {
                String label = gateways.getJSONObject(i).getString("label");
                String technology = gateways.getJSONObject(i).getString("technology");
                Coordinate departure = new Coordinate(gateways.getJSONObject(i).getJSONArray("coords").getJSONArray(0).getInt(0), gateways.getJSONObject(i).getJSONArray("coords").getJSONArray(0).getInt(1));
                Coordinate arrival = new Coordinate(gateways.getJSONObject(i).getJSONArray("coords").getJSONArray(1).getInt(0), gateways.getJSONObject(i).getJSONArray("coords").getJSONArray(1).getInt(1));
                Coordinate[] coordinates = new Coordinate[2];
                coordinates[0] = departure;
                coordinates[1] = arrival;
                Gateway gateway = new Gateway(label, technology, coordinates);
                // add gateway
                arrayList.add(gateway);
            }
        }

        return arrayList;
    }
}