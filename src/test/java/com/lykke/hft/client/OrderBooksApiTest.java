package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.OrderBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * API tests for OrderBooksApi
 */
public class OrderBooksApiTest {

    private OrderBooksApi api;
    private String basePath;
    private String apiKey;
    private String walletKey;


    @Before
    public void setup() {
        Environment.load();

        basePath = Environment.getVariable("HFT_API_BASE_PATH");
        apiKey = Environment.getVariable("HFT_KEY");
        walletKey= Environment.getVariable("WALLET_KEY");

        api = new ApiClient().setBasePath(basePath).buildClient(OrderBooksApi.class);
    }

    
    /**
     * Get all order books.
     *
     * 
     */
    @Test
    public void orderBooksTest() {
        List<OrderBook> response = api.orderBooks();

        Assert.assertEquals(response.get(1).getAssetPair(),"AUDCHF");

    }

    
    /**
     * Get order books for a specified asster pair.
     *
     * 
     */
    @Test
    public void orderBooksIdTest() {
        String assetPairId = "AUDCHF";

         List<OrderBook> response = api.orderBooksId(assetPairId);

         Assert.assertEquals(response.get(1).getAssetPair(),"AUDCHF");


    }

    
}
