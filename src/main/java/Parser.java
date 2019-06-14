import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import uqac.distributedsystems.model.Coordinate;
import uqac.distributedsystems.model.Device;
import uqac.distributedsystems.model.Room;

import java.awt.*;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Set;

/**
 * The type Parser.
 */
class Parser {
    /**
     * Gets data from json.
     *
     * @return the data from json
     */
    static ArrayList<Room> getDataFromJson(String JsonFilePath) {
        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(JsonFilePath));
            JSONObject jsonObject = (JSONObject) obj;
            return getObjects(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ArrayList<Room> getObjects(JSONObject object){
        ArrayList<Room> listRooms = new ArrayList<>();

        Set<String> rooms = object.keySet();
        for (String room: rooms) {
            JSONObject s = (JSONObject) object.get(room);
            String bg = (String) s.get("background");
            Color color;
            try{
                Field field = Class.forName("java.awt.Color").getField(bg);
                color = (Color)field.get(null);
            }catch (Exception e){
                color = null;
            }

            String lb = (String) s.get("label");
            JSONArray coordRoom =  (JSONArray) s.get("coords");
            Long x = (Long) coordRoom.get(0);
            Long y = (Long) coordRoom.get(1);

            Room r = new Room(lb, new Coordinate(x, y),0,0, color );

            JSONObject listDevice = (JSONObject) s.get("objects");
            Set<String>  devices = listDevice.keySet();
            for(String device : devices){
                JSONObject d = (JSONObject) listDevice.get(device);
                String tech = (String) d.get("technology");
                JSONArray coordDevice =  (JSONArray) d.get("coords");
                Long xD = (Long) coordDevice.get(0);
                Long yD = (Long) coordDevice.get(1);
                r.addDevice(new Device(device,  tech, new Coordinate(xD, yD)));
            }

            listRooms.add(r);
        }
        return listRooms;
    }
}
