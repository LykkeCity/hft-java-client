package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.AssetPairModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for AssetPairsApi
 */
public class AssetPairsApiTest {

    private AssetPairsApi api;

    @Before
    public void setup() {
        api = new ApiClient().buildClient(AssetPairsApi.class);
    }

    
    /**
     * Get all asset pairs.
     *
     * 
     */
    @Test
    public void assetPairsTest() {
        // List<AssetPairModel> response = api.assetPairs();

        // TODO: test validations
    }

    
    /**
     * Get specified asset pair.
     *
     * 
     */
    @Test
    public void assetPairsidTest() {
        String id = null;
        // AssetPairModel response = api.assetPairsid(id);

        // TODO: test validations
    }

    
}
