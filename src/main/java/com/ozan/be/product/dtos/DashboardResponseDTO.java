package com.ozan.be.product.dtos;

import java.io.Serializable;
import lombok.Data;

@Data
public class DashboardResponseDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private int totalProductCount;
  private int totalStockCount;
  private int totalOutOfStockCount;
}
