package com.kkh.product.product.service;

import com.kkh.product.product.model.dto.request.ProductRegisterRequest;
import com.kkh.product.product.model.dto.request.ProductUpdateRequest;
import com.kkh.product.product.model.dto.response.ProductDetailResponse;
import com.kkh.product.product.model.dto.response.ProductListResponse;
import com.kkh.product.product.model.entity.Product;
import com.kkh.product.product.model.repository.ProductRepository;
import com.kkh.product.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final RestaurantService restaurantService;

    @Transactional(readOnly = true)
    public Mono<ProductDetailResponse> detail(String productId) {
        return productRepository.findById(productId)
                .map(Product::toDetail);
    }

    @Transactional(readOnly = true)
    public Mono<ProductListResponse> listByRestaurant(String restaurantId) {
        return restaurantService.findById(restaurantId) // Mono<RestaurantDetailResponse>
                .switchIfEmpty(Mono.error(new IllegalStateException("식당이 존재하지 않습니다.")))
                .thenMany(productRepository.findByRestaurantId(restaurantId))
                .as(Product::toList);
    }

    @Transactional
    public Mono<Void> register(ProductRegisterRequest request) {
        Product product = ProductRegisterRequest.toEntity(request);
        return restaurantService.findById(request.getRestaurantId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("식당이 존재하지 않습니다.")))
                .flatMap(restaurant -> productRepository.save(product))
                .then();
    }

    @Transactional
    public Mono<Void> update(ProductUpdateRequest request) {
        return restaurantService.findById(request.getRestaurantId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("레스토랑이 존재하지 않습니다")))
                .flatMap(restaurant -> productRepository.findById(request.getProductId()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("상품이 존재하지 않습니다")))
                .flatMap(product -> {
                    product.updateFrom(request); // 수정 메서드
                    return productRepository.save(product);
                })
                .then();
    }
}
