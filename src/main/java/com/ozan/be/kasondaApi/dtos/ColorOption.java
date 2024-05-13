package com.ozan.be.kasondaApi.dtos;

import java.util.List;
import lombok.Data;

@Data
public class ColorOption {
  public String id;
  public Product.Color color;
  public List<Product.SubCategory> subCategories;
  public List<Product.BlendColor> blendColors;
}
