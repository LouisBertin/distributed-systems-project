package uqac.distributedsystems.tools;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import uqac.distributedsystems.model.Coordinate;
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
    public static ArrayList<Room> getFormattedDataFromJson(String JsonFilePath) {
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
                arrayList.add(room);
            }
        }

        return arrayList;
    }
}
