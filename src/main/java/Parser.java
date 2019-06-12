import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * The type Parser.
 */
public class Parser {
    /**
     * Gets data from json.
     *
     * @return the data from json
     */
    public static JSONObject getDataFromJson(String JsonFilePath) {
        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(JsonFilePath));

            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
