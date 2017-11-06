package com.lykke.hft.client;

import com.lykke.hft.ApiClient;
import com.lykke.hft.EncodingUtils;

import com.lykke.hft.model.AssetPairModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-11-06T23:34:32.567+02:00")
public interface AssetPairsApi extends ApiClient.Api {


  /**
   * Get all asset pairs.
   * 
   * @return List&lt;AssetPairModel&gt;
   */
  @RequestLine("GET /api/AssetPairs")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  List<AssetPairModel> assetPairs();

  /**
   * Get specified asset pair.
   * 
    * @param id Asset pair ID. Example: AUDUSD (required)
   * @return AssetPairModel
   */
  @RequestLine("GET /api/AssetPairs/{id}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  AssetPairModel assetPairsid(@Param("id") String id);
}
