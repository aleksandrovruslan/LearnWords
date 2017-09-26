package com.aleksandrov.Model;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Settings {
    private static final Logger log = LogManager.getLogger(Settings.class.getName());
    private static final Settings SETTINGS_LOAD = new Settings();
    private HashMap<String, String> properties = new HashMap<>();

    /**
     * upload properties to the HasMap
     */
    private Settings(){
        Properties property = new Properties();
        try{
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            for(String key:property.stringPropertyNames()) {
                properties.put(key, property.getProperty(key));
            }
            log.debug("properties uploaded");
        }catch (IOException e){
            log.error("properties not uploaded");
        }
    }

    public static Settings getSettings(){
        return SETTINGS_LOAD;
    }

    public HashMap getProperties(){
        return properties;
    }
}
