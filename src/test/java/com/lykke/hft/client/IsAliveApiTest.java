package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.IsAliveResponse;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;
/**
 * API tests for IsAliveApi
 */
public class IsAliveApiTest {

    private IsAliveApi api;
    private String basePath;
    @Before
    public void setup() {
        Environment.load();
         basePath = Environment.getVariable("HFT_API_BASE_PATH");
         api = new ApiClient().setBasePath(basePath).buildClient(IsAliveApi.class);


    }

    
    /**
     * Checks service is alive
     *
     * 
     */
    @Test
    public void isAliveTest() {
         IsAliveResponse response = api.isAlive();

        Assert.assertEquals("1.0.14.0", response.getVersion());

    }

    
}
