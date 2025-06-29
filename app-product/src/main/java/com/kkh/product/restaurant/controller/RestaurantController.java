package com.kkh.product.restaurant.controller;

import com.kkh.product.restaurant.model.dto.request.RestaurantRegisterRequest;
import com.kkh.product.restaurant.model.dto.response.RestaurantDetailResponse;
import com.kkh.product.restaurant.model.dto.response.RestaurantListResponse;
import com.kkh.product.restaurant.model.entity.Restaurant;
import com.kkh.product.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/{id}")
    public Mono<RestaurantDetailResponse> detail(@PathVariable("id") String id) {
        return restaurantService.findById(id);
    }

    @GetMapping("/list")
    public Flux<RestaurantListResponse> findAll() {
        return restaurantService.findAll();
    }

    @PostMapping
    public Mono<Void> register(RestaurantRegisterRequest request) {
        return restaurantService.register(request);
    }

    @DeleteMapping
    public Mono<Void> delete(String id) {
        return restaurantService.delete(id);
    }
}
