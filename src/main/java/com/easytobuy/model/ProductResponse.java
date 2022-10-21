package com.easytobuy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
  private String productId;
  private String productName;
  private String productDescription;
  private long price;
  private long quantity;
  private boolean inStock;
  private String imageUrl;
  private Integer categoryId;
}
