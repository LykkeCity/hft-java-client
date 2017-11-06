package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.EncodingUtils;

import com.lykke.hft.model.OrderBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-11-06T23:34:32.567+02:00")
public interface OrderBooksApi extends ApiClient.Api {


  /**
   * Get all order books.
   * 
   * @return List&lt;OrderBook&gt;
   */
  @RequestLine("GET /api/OrderBooks")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  List<OrderBook> orderBooks();

  /**
   * Get order books for a specified asster pair.
   * 
    * @param assetPairId Asset pair ID. Example: AUDUSD (required)
   * @return List&lt;OrderBook&gt;
   */
  @RequestLine("GET /api/OrderBooks/{assetPairId}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  List<OrderBook> orderBooksId(@Param("assetPairId") String assetPairId);
}
