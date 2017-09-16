package app.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {
    private static final String PROPERTIES_FILE_NAME = "app.properties";
    private static Properties properties;

    public static void load() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File propertiesFile = new File(classLoader.getResource(PROPERTIES_FILE_NAME).getFile());
        try (FileInputStream in = new FileInputStream(propertiesFile)) {
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (properties == null) load();
        return properties.getProperty(key);
    }
}
