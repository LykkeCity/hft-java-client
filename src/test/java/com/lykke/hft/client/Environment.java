package com.lykke.hft.client;

import com.lykke.hft.ApiClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {

    static Properties properties;
    public static void load() {


        String propertyFileName= "hft-client.properties";

        String propertyFilePath = System.getenv().get("HFT-CLIENT-PROPERTIES");

        try  {
            InputStream is;
            //Try to load properties from resources
            is = ApiClient.class.getClassLoader().getResourceAsStream(propertyFileName);
            if (is==null){
                //Try to load from a file which name is in system environment variable HFT-CLIENT-PROPERTIES
                is = new FileInputStream(propertyFilePath);
            }
            properties = new Properties();
            properties.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getVariable(String variable) {
        return properties.getProperty(variable);
    }
}