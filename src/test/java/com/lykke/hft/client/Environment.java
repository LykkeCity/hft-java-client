package com.lykke.hft.client;

import com.lykke.hft.ApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {

    static Properties properties;
    public static void load() {
        try (InputStream is = ApiClient.class.getResourceAsStream("/" + "hft-client.properties")) {
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