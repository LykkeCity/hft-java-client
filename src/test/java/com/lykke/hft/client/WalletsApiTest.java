package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.Wallet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * API tests for WalletsApi
 */
public class WalletsApiTest {

    private WalletsApi api;
    private String apiKey;
    private String basePath;


    @Before
    public void setup() {
        Environment.load();
         apiKey = Environment.getVariable("HFT_KEY");
         basePath = Environment.getVariable("HFT_API_BASE_PATH");


        api = new ApiClient().setBasePath(basePath).buildClient(WalletsApi.class);
    }

    
    /**
     * Get balance.
     *
     * 
     */
    @Test
    public void walletsTest() {
        List<Wallet> response = api.wallets(apiKey);

        Assert.assertEquals(response.size(),2);

    }

    
}
