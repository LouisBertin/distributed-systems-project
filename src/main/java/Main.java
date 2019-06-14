import uqac.distributedsystems.gui.Frame;
import uqac.distributedsystems.model.Room;

import java.util.ArrayList;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ArrayList<Room> rooms = Parser.getDataFromJson(Helper.getResourcesPath() + "/data/sample.json");
        Frame f = new Frame(rooms);
    }
}
