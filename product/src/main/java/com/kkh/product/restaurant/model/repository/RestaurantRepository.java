package com.kkh.product.restaurant.model.repository;

import com.kkh.product.restaurant.model.entity.Restaurant;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository

public interface RestaurantRepository extends ReactiveMongoRepository<Restaurant, String> {
    Flux<Restaurant> findByCategoryOrderByOrderCountDesc(String category);

    Flux<Restaurant> findByCategoryOrderByViewCountDesc(String category);

    Flux<Restaurant> findByNameContainingIgnoreCase(String name);
    Flux<Restaurant> findAll();

    Mono<Restaurant> findById(String id);
    Mono<Restaurant> findByOwnerIdAndName(String id, String name);


}
