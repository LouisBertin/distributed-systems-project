package uqac.distributedsystems.tools;

/**
 * The type uqac.distributedsystems.tools.Helper.
 */
public class Helper {
    /**
     * Gets project path.
     *
     * @return the project path
     */
    public static String getProjectPath() {
        return System.getProperty("user.dir") + "/src/main";
    }

    /**
     * Gets resources path.
     *
     * @return the resources path
     */
    public static String getResourcesPath() {
        return System.getProperty("user.dir") + "/src/main/resources";
    }
}