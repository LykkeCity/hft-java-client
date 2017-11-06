package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.EncodingUtils;

import com.lykke.hft.model.Wallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-11-06T23:34:32.567+02:00")
public interface WalletsApi extends ApiClient.Api {


  /**
   * Get balance.
   * 
    * @param apiKey access token (required)
   * @return List&lt;Wallet&gt;
   */
  @RequestLine("GET /api/Wallets")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "api-key: {apiKey}"
  })
  List<Wallet> wallets(@Param("apiKey") String apiKey);
}
