package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.Wallet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for WalletsApi
 */
public class WalletsApiTest {

    private WalletsApi api;

    @Before
    public void setup() {
        api = new ApiClient().buildClient(WalletsApi.class);
    }

    
    /**
     * Get balance.
     *
     * 
     */
    @Test
    public void walletsTest() {
        String apiKey = null;
        // List<Wallet> response = api.wallets(apiKey);

        // TODO: test validations
    }

    
}
