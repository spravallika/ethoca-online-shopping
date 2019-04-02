package etc.qa.at.config;

import etc.qa.at.exceptions.EtcRuntimeException;
import etc.qa.at.exceptions.ExceptionCause;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigReader {
    private static final String propertyFilePath = java.nio.file.Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "cucumber.properties").toString();
    private static HashMap<String, String> configMap;

    /**
     * This method is to get the current working module name from the project structure
     * @return
     */
    public static String getModuleName() {
        String moduleName = System.getProperty("user.dir");
        int index = moduleName.lastIndexOf("-") + 1;
        moduleName = "../output/reports/" + moduleName.substring(index, moduleName.length());
        return moduleName;
    }

    static {
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(propertyFilePath);
            properties.load(inputStream);
        } catch (IOException e) {
            String className = new Object() {
            }.getClass().getName();
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            String message = "Error reading file in this path : " + propertyFilePath;
            throw new EtcRuntimeException(e, ExceptionCause.CONFIG_PROPERTIES, className + " : " + methodName + " : " + message);
        }
        configMap = (HashMap<String, String>) properties.entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString())
        );
    }

    public static String getProperty(String property) {
        return configMap.get(property);
    }
}
