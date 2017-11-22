package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.AssetPairModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * API tests for AssetPairsApi
 */
public class AssetPairsApiTest {

    private AssetPairsApi api;
    private String basePath;


    @Before
    public void setup() {
        Environment.load();
        basePath = Environment.getVariable("HFT_API_BASE_PATH");
        api = new ApiClient().setBasePath(basePath).buildClient(AssetPairsApi.class);

    }

    
    /**
     * Get all asset pairs.
     *
     * 
     */
    @Test
    public void assetPairsTest() {
        List<AssetPairModel> response = api.assetPairs();

        Assert.assertTrue("Size more than 1",response.size()>1);
    }

    
    /**
     * Get specified asset pair.
     *
     * 
     */
    @Test
    public void assetPairsidTest() {
        String id = null;
        AssetPairModel response = api.assetPairsid("BTCUSD");

        Assert.assertEquals("BTC/USD",response.getName());
    }

    
}
