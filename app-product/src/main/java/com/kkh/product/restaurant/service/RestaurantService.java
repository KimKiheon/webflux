package com.kkh.product.restaurant.service;

import com.kkh.product.restaurant.model.dto.request.RestaurantRegisterRequest;
import com.kkh.product.restaurant.model.dto.response.RestaurantDetailResponse;
import com.kkh.product.restaurant.model.dto.response.RestaurantListResponse;
import com.kkh.product.restaurant.model.entity.Restaurant;
import com.kkh.product.restaurant.model.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public Mono<RestaurantDetailResponse> findById(String id) {
        return restaurantRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("식당을 찾을 수 없습니다.")))
                .map(Restaurant::toDetail);
    }

    @Transactional(readOnly = true)
    public Flux<RestaurantListResponse> findAll() {
        return restaurantRepository.findAll()
                .map(Restaurant::toList);
    }

    @Transactional
    public Mono<Void> register(RestaurantRegisterRequest request) {
        Restaurant entity = RestaurantRegisterRequest.toEntity(request);
        return restaurantRepository.findById(entity.getOwnerId())
                .flatMap(exist -> Mono.error(new IllegalStateException("이미 존재하는 식당입니다")))
                .switchIfEmpty(restaurantRepository.save(entity)).then();
    }
    @Transactional
    public Mono<Void> delete(String id) {
        return restaurantRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("삭제할 식당이 존재하지 않습니다.")))
                .flatMap(restaurant -> restaurantRepository.deleteById(id));
    }

}
