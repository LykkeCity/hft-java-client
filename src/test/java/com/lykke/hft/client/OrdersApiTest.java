package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.model.LimitOrderRequest;
import com.lykke.hft.model.LimitOrderState;
import com.lykke.hft.model.MarketOrderRequest;
import com.lykke.hft.model.ResponseModelDouble;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.UUID;

/**
 * API tests for OrdersApi
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class OrdersApiTest {

    private OrdersApi api;

    private String basePath;
    private String apiKey;
    private String walletKey;

    private UUID limitOrderResponse;


    @Before
    public void setup() {
        Environment.load();
        basePath = Environment.getVariable("HFT_API_BASE_PATH");
        apiKey = Environment.getVariable("HFT_KEY");
        walletKey = Environment.getVariable("WALLET_KEY");
        api = new ApiClient().setBasePath(basePath).buildClient(OrdersApi.class);
    }

    /**
     * Place a market order.
     */
    @Test
    public void _1placeMarketOrderTest() {
        MarketOrderRequest order = new MarketOrderRequest();
        order.assetPairId("BTCUSD");
        order.setVolume(new Double(0.00001));
        order.setAsset("BTC");
        order.setOrderAction(MarketOrderRequest.OrderActionEnum.BUY);
        ResponseModelDouble response = api.placeMarketOrder(apiKey, order);


    }


    /**
     * Place a limit order.
     */
    @Test
    public void _2placeLimitOrderTest() {
        LimitOrderRequest order = new LimitOrderRequest();
        order.assetPairId("BTCUSD");
        order.setPrice(new Double(8182.74));
        order.setOrderAction(LimitOrderRequest.OrderActionEnum.BUY);
        order.setVolume(new Double(0.00001));

        UUID limitOrderResponse = api.placeLimitOrder(apiKey, order);
        LimitOrderState limitOrderState = api.getOrderInfo(limitOrderResponse, apiKey);

        List<LimitOrderState> limitOrderStateList = api.getOrders(apiKey, limitOrderState.getStatus().toString());


    }


    /**
     * Place a limit order.
     */
    @Test
    public void _3placeLimitOrderTest() {
        LimitOrderRequest order = new LimitOrderRequest();
        order.assetPairId("BTCUSD");
        order.setPrice(new Double(8182.75));
        order.setOrderAction(LimitOrderRequest.OrderActionEnum.SELL);
        order.setVolume(new Double(0.00001));

        UUID limitOrderResponse = api.placeLimitOrder(apiKey, order);
        LimitOrderState limitOrderState = api.getOrderInfo(limitOrderResponse, apiKey);

        List<LimitOrderState> limitOrderStateList = api.getOrders(apiKey, limitOrderState.getStatus().toString());



    }


}




    


    


    


    

