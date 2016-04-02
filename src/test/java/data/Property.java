package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by QZen on 02/04/16.
 */
public class Property {

    public static final String BASE_URL;
    public static final String VIDEO_NAME;

    private static final String TEST_PROPERTIES_FILE = "/test.properties";
    private static final java.util.Properties TEST_PROPERTIES = new java.util.Properties();

    static {
        try {
            InputStream propertiesFile = Property.class.getResourceAsStream(TEST_PROPERTIES_FILE);
            if (propertiesFile != null) {
                TEST_PROPERTIES.load(propertiesFile);
                propertiesFile.close();
            } else {
                throw new FileNotFoundException(TEST_PROPERTIES_FILE);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }

        BASE_URL = get("BaseUrl");
        VIDEO_NAME = get("VideoName");
    }

    private Property() {
    }

    protected static String get(String propertyKey) {
        String value = TEST_PROPERTIES.getProperty(propertyKey);
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Property '" + propertyKey + "' not found");
        } else {
            return value;
        }
    }
}
