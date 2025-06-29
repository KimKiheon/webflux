package com.kkh.product.restaurant.service;

import com.kkh.product._support.grpc.OwnerGrpcClient;
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
    private final OwnerGrpcClient ownerGrpcClient;


    @Transactional(readOnly = true)
    public Mono<RestaurantDetailResponse> findById(String id) {
        return restaurantRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("식당을 찾을 수 없습니다.")))
                .map(RestaurantDetailResponse::toDetail);
    }

    @Transactional(readOnly = true)
    public Flux<RestaurantListResponse> findAll() {
        return restaurantRepository.findAll()
                .map(RestaurantListResponse::toList);
    }

    public Mono<Object> register(RestaurantRegisterRequest request) {
        Restaurant entity = RestaurantRegisterRequest.toEntity(request);

        return restaurantRepository.findByOwnerIdAndName(entity.getOwnerId(), entity.getName())
                .flatMap(existing -> Mono.error(new IllegalStateException("이미 존재하는 식당입니다")))
                .switchIfEmpty(Mono.defer(() ->
                        restaurantRepository.save(entity)
                                .flatMap(saved ->
                                        ownerGrpcClient.addRestaurantToOwner(entity.getOwnerId(), saved.getId())
                                                .thenReturn(saved.getId())
                                )
                ));
    }
    @Transactional
    public Mono<Void> delete(String id) {
        return restaurantRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("삭제할 식당이 존재하지 않습니다.")))
                .flatMap(restaurant -> restaurantRepository.deleteById(id));
    }

}
