package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.EncodingUtils;

import com.lykke.hft.model.ErrorResponse;
import com.lykke.hft.model.IsAliveResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-11-06T23:34:32.567+02:00")
public interface IsAliveApi extends ApiClient.Api {


  /**
   * Checks service is alive
   * 
   * @return IsAliveResponse
   */
  @RequestLine("GET /api/IsAlive")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  IsAliveResponse isAlive();
}
