package com.ozan.be.externalProducts.dtos;

import lombok.Data;

@Data
public class KasondaPriceResponseDTO {
  private Data data;
  private String status;

  @lombok.Data
  public static class Data {
    private Boolean valid;
    private String selectedAttributes;
    private String sku;
    private Price price;
  }

  @lombok.Data
  public static class Price {
    private String currency;
    private String salePrice;
  }
}
