package com.easytobuy.service;

import com.easytobuy.entity.Product;
import com.easytobuy.exception.ProductCustomException;
import com.easytobuy.model.ProductRequest;
import com.easytobuy.model.ProductResponse;
import com.easytobuy.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
  @Autowired ProductRepository productRepository;

  @Override
  public ProductResponse saveProduct(ProductRequest productRequest) {
    Product product =
        Product.builder()
            .productDescription(productRequest.getProductDescription())
            .productName(productRequest.getProductName())
            .categoryId(productRequest.getCategoryId())
            .imageUrl(productRequest.getImageUrl())
            .inStock(productRequest.isInStock())
            .price(productRequest.getPrice())
            .quantity(productRequest.getQuantity())
            .build();
    productRepository.save(product);
    return ProductResponse.builder()
        .productId(productRequest.getProductId())
        .productName(productRequest.getProductName())
        .price(productRequest.getPrice())
        .inStock(productRequest.isInStock())
        .quantity(productRequest.getQuantity())
        .imageUrl(productRequest.getImageUrl())
        .quantity(productRequest.getQuantity())
        .build();
  }

  @Override
  public ProductResponse getProductByIdProductId(String productId) {
    log.info("ProductServiceImpl:getProductById::productId" + productId);
    Optional<Product> product =
        Optional.ofNullable(
            productRepository
                .getProductByIdProductId(productId)
                .orElseThrow(
                    () ->
                        new ProductCustomException(
                            "No Product found with given ProductId", "PRODUCT_NOT_FOUND")));
    return ProductResponse.builder().productId(product.get().getProductId()).productName(product.get().getProductName()).quantity(product.get().getQuantity()).inStock(product.get().isInStock()).price(product.get().getPrice()).imageUrl(product.get().getImageUrl()).categoryId(product.get().getCategoryId()).build();
  }

    @Override
    public List<ProductResponse> getProductByCategoryId(Integer categoryId) {
        log.info("ProductServiceImpl:getProductByCategoryId::categoryId" + categoryId);
        List<Product>getProductsByCategoryId=productRepository.getProductByCategoryId(categoryId);
        return getProductsByCategoryId.stream().map(product->
                ProductResponse.builder()
                .categoryId(product.getCategoryId())
                .productId(product.getProductId())
                .imageUrl(product.getImageUrl())
                .inStock(product.isInStock())
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productDescription(product.getProductDescription())
                .build()).collect(Collectors.toList());
    }

    @Override
  public List<ProductResponse> getAllProducts() {
      List<Product>getAllProducts=productRepository.findAll();
      return getAllProducts.stream().map(product->
              ProductResponse.builder()
                      .categoryId(product.getCategoryId())
                      .productId(product.getProductId())
                      .imageUrl(product.getImageUrl())
                      .inStock(product.isInStock())
                      .productName(product.getProductName())
                      .price(product.getPrice())
                      .quantity(product.getQuantity())
                      .productDescription(product.getProductDescription())
                      .build()).collect(Collectors.toList());
  }

    @Override
    public void reduceQuantity(String productId, long quantity) {
        Product product=productRepository.findById(productId).orElseThrow(()->new ProductCustomException("Product with given Id not Found","PRODUCT_NOT_FOUND"));
        if(product.getQuantity() < quantity){
          throw new ProductCustomException("Product does not have sufficient Quantity","INSUFFICIENT_QUANTITY");
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Product Quantity Updated Successfully");
    }
}
