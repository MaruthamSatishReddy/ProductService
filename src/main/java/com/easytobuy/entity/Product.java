package com.easytobuy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document(collection = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  private String productId;
  private String productName;
  private String productDescription;
  private long price;
  private long quantity;
  private boolean inStock;
  private String imageUrl;
  private Integer categoryId;
}
