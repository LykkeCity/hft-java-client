package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.EncodingUtils;

import com.lykke.hft.model.LimitOrderRequest;
import com.lykke.hft.model.LimitOrderState;
import com.lykke.hft.model.MarketOrderRequest;
import com.lykke.hft.model.ResponseModel;
import com.lykke.hft.model.ResponseModelDouble;
import java.util.UUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-11-06T23:34:32.567+02:00")
public interface OrdersApi extends ApiClient.Api {


  /**
   * Cancel the limit order.
   * 
    * @param id Limit order id (Guid) (required)
    * @param apiKey access token (required)
   */
  @RequestLine("POST /api/Orders/{id}/Cancel")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "api-key: {apiKey}"
  })
  void cancelLimitOrder(@Param("id") UUID id, @Param("apiKey") String apiKey);

  /**
   * Get the order info.
   * 
    * @param id Limit order id (Guid) (required)
    * @param apiKey access token (required)
   * @return LimitOrderState
   */
  @RequestLine("GET /api/Orders/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "api-key: {apiKey}"
  })
  LimitOrderState getOrderInfo(@Param("id") UUID id, @Param("apiKey") String apiKey);

  /**
   * Get all client orders.
   * 
    * @param apiKey access token (required)
    * @param status  (optional)
   * @return List&lt;LimitOrderState&gt;
   */
  @RequestLine("GET /api/Orders?status={status}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "api-key: {apiKey}"
  })
  List<LimitOrderState> getOrders(@Param("apiKey") String apiKey, @Param("status") String status);

  /**
   * Get all client orders.
   * 
   * Note, this is equivalent to the other <code>getOrders</code> method,
   * but with the query parameters collected into a single Map parameter. This
   * is convenient for services with optional query parameters, especially when
   * used with the {@link GetOrdersQueryParams} class that allows for
   * building up this map in a fluent style.
   * @param apiKey access token (required)
   * @param queryParams Map of query parameters as name-value pairs
   *   <p>The following elements may be specified in the query map:</p>
   *   <ul>
   *   <li>status -  (optional)</li>
   *   </ul>
   * @return List&lt;LimitOrderState&gt;
   */
  @RequestLine("GET /api/Orders?status={status}")
  @Headers({
  "Content-Type: application/json",
  "Accept: application/json",
      "api-key: {apiKey}"
  })
  List<LimitOrderState> getOrders(@Param("apiKey") String apiKey, @QueryMap(encoded=true) Map<String, Object> queryParams);

  /**
   * A convenience class for generating query parameters for the
   * <code>getOrders</code> method in a fluent style.
   */
  public static class GetOrdersQueryParams extends HashMap<String, Object> {
    public GetOrdersQueryParams status(final String value) {
      put("status", EncodingUtils.encode(value));
      return this;
    }
  }

  /**
   * Place a limit order.
   * 
    * @param apiKey access token (required)
    * @param order  (optional)
   * @return UUID
   */
  @RequestLine("POST /api/Orders/limit")
  @Headers({
    "Content-Type: application/json-patch+json",
    "Accept: application/json",
    "api-key: {apiKey}"
  })
  UUID placeLimitOrder(@Param("apiKey") String apiKey, LimitOrderRequest order);

  /**
   * Place a market order.
   * 
    * @param apiKey access token (required)
    * @param order  (optional)
   * @return ResponseModelDouble
   */
  @RequestLine("POST /api/Orders/market")
  @Headers({
    "Content-Type: application/json-patch+json",
    "Accept: application/json",
    "api-key: {apiKey}"
  })
  ResponseModelDouble placeMarketOrder(@Param("apiKey") String apiKey, MarketOrderRequest order);
}
