package com.easytobuy.api;

import com.easytobuy.entity.Product;
import com.easytobuy.model.ProductRequest;
import com.easytobuy.model.ProductResponse;
import com.easytobuy.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping("/api/v1/products")
@CrossOrigin
@AllArgsConstructor
public class ProductController {
  @Autowired
  ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponse> saveProducty(@RequestBody ProductRequest productRequest) {
    productRequest.setProductId(UUID.randomUUID().toString().split("-")[0]);
    return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.CREATED);
  }

  @GetMapping("/{productId}")
  @CrossOrigin
  public ResponseEntity<ProductResponse> getProductById(@PathVariable String productId) {
    ProductResponse productResponse = productService.getProductByIdProductId(productId);
    return new ResponseEntity<>(productResponse, HttpStatus.OK);
  }
  @GetMapping("/category/{categoryId}")
  @CrossOrigin
  public ResponseEntity<List<ProductResponse>> getProductByCategoryId(@PathVariable Integer categoryId) {
    return new ResponseEntity<>(productService.getProductByCategoryId(categoryId),HttpStatus.OK);
  }

  @GetMapping
  @CrossOrigin
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
  }
  @PutMapping("/reduceQuantity/{productId}")
  public ResponseEntity<Void> reduceQuantity(@PathVariable("productId") String productId, @RequestParam long quantity)  {

    productService.reduceQuantity(productId,quantity);
  return new ResponseEntity<>(HttpStatus.OK);
  }
}
