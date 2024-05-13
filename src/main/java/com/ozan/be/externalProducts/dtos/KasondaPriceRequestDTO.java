package com.ozan.be.externalProducts.dtos;

import lombok.Data;

@Data
public class KasondaPriceRequestDTO {
  private String category;
  private Integer subcategory;
  private String color;
  private Integer blendcolor;
  private Integer width;
  private Integer height;

  @Override
  public String toString() {
    return "KasondaPriceRequestDTO{"
        + "category='"
        + category
        + '\''
        + ", subcategory="
        + subcategory
        + ", color='"
        + color
        + '\''
        + ", blendcolor="
        + blendcolor
        + ", width="
        + width
        + ", height="
        + height
        + '}';
  }
}
