import uqac.distributedsystems.gui.Frame;
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
        Frame f = new Frame();
        // get gateways
        //Parser.getGatewaysFromJson(Helper.getResourcesPath() + (Helper.getPropertyValue("input_file")));

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f.sendMessage();
    }
}
