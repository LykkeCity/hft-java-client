package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.LimitOrderRequest;
import com.lykke.hft.model.LimitOrderState;
import com.lykke.hft.model.MarketOrderRequest;
import com.lykke.hft.model.ResponseModel;
import com.lykke.hft.model.ResponseModelDouble;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for OrdersApi
 */
public class OrdersApiTest {

    private OrdersApi api;

    @Before
    public void setup() {
        api = new ApiClient().buildClient(OrdersApi.class);
    }

    
    /**
     * Cancel the limit order.
     *
     * 
     */
    @Test
    public void cancelLimitOrderTest() {
        UUID id = null;
        String apiKey = null;
        // api.cancelLimitOrder(id, apiKey);

        // TODO: test validations
    }

    
    /**
     * Get the order info.
     *
     * 
     */
    @Test
    public void getOrderInfoTest() {
        UUID id = null;
        String apiKey = null;
        // LimitOrderState response = api.getOrderInfo(id, apiKey);

        // TODO: test validations
    }

    
    /**
     * Get all client orders.
     *
     * 
     */
    @Test
    public void getOrdersTest() {
        String apiKey = null;
        String status = null;
        // List<LimitOrderState> response = api.getOrders(apiKey, status);

        // TODO: test validations
    }

    /**
     * Get all client orders.
     *
     * 
     *
     * This tests the overload of the method that uses a Map for query parameters instead of
     * listing them out individually.
     */
    @Test
    public void getOrdersTestQueryMap() {
        String apiKey = null;
        OrdersApi.GetOrdersQueryParams queryParams = new OrdersApi.GetOrdersQueryParams()
            .status(null);
        // List<LimitOrderState> response = api.getOrders(apiKey, queryParams);

    // TODO: test validations
    }
    
    /**
     * Place a limit order.
     *
     * 
     */
    @Test
    public void placeLimitOrderTest() {
        String apiKey = null;
        LimitOrderRequest order = null;
        // UUID response = api.placeLimitOrder(apiKey, order);

        // TODO: test validations
    }

    
    /**
     * Place a market order.
     *
     * 
     */
    @Test
    public void placeMarketOrderTest() {
        String apiKey = null;
        MarketOrderRequest order = null;
        // ResponseModelDouble response = api.placeMarketOrder(apiKey, order);

        // TODO: test validations
    }

    
}
