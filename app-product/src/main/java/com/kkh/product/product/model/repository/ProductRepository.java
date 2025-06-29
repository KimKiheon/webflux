package com.kkh.product.product.model.repository;

import com.kkh.product.product.model.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<Product> findByRestaurantId(String restaurantId);

}
