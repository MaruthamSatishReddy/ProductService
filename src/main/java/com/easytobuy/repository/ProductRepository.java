package com.easytobuy.repository;

import com.easytobuy.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ productId: ?0 }")
    Optional<Product> getProductByIdProductId(String productId);
    @Query("{ categoryId: ?0 }")
    List<Product> getProductByCategoryId(Integer categoryId);
}
