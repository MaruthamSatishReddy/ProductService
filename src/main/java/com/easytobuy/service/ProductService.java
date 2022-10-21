package com.easytobuy.service;

import com.easytobuy.entity.Product;
import com.easytobuy.model.ProductRequest;
import com.easytobuy.model.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
  ProductResponse saveProduct(ProductRequest productRequest);
  ProductResponse getProductByIdProductId(String productId);
  List<ProductResponse> getProductByCategoryId(Integer categoryId);
  List<ProductResponse> getAllProducts();
  void reduceQuantity(String productId, long quantity);
}
