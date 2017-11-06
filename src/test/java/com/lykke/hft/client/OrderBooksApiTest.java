package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.OrderBook;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for OrderBooksApi
 */
public class OrderBooksApiTest {

    private OrderBooksApi api;

    @Before
    public void setup() {
        api = new ApiClient().buildClient(OrderBooksApi.class);
    }

    
    /**
     * Get all order books.
     *
     * 
     */
    @Test
    public void orderBooksTest() {
        // List<OrderBook> response = api.orderBooks();

        // TODO: test validations
    }

    
    /**
     * Get order books for a specified asster pair.
     *
     * 
     */
    @Test
    public void orderBooksIdTest() {
        String assetPairId = null;
        // List<OrderBook> response = api.orderBooksId(assetPairId);

        // TODO: test validations
    }

    
}
