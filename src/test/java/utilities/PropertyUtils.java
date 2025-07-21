package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils{

    private static final String PROPERTIES_FILE = "C:\\...\\...\\";
    private static Properties properties;

    static {
        try{
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch (IOException e){
            throw new RuntimeException("Error al cargar el archivo application.properties", e);
        }
    }

    private PropertyUtils(){
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}