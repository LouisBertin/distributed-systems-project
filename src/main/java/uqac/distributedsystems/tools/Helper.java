package uqac.distributedsystems.tools;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

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

    /**
     * Get property value
     * @return String property value
     */
    public static String getPropertyValue(String propertyName) {
        String result = "";
        InputStream inputStream;

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = Helper.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            result = prop.getProperty(propertyName);
            return result;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return result;
    }
}