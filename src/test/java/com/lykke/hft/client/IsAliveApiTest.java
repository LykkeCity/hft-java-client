package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.ErrorResponse;
import com.lykke.hft.model.IsAliveResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for IsAliveApi
 */
public class IsAliveApiTest {

    private IsAliveApi api;

    @Before
    public void setup() {
        api = new ApiClient().buildClient(IsAliveApi.class);
    }

    
    /**
     * Checks service is alive
     *
     * 
     */
    @Test
    public void isAliveTest() {
        // IsAliveResponse response = api.isAlive();

        // TODO: test validations
    }

    
}
