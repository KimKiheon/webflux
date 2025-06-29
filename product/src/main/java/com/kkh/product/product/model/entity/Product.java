package com.kkh.product.product.model.entity;

import com.kkh.product.product.model.dto.request.ProductUpdateRequest;
import com.kkh.product.product.model.dto.response.ProductDetailResponse;
import com.kkh.product.product.model.dto.response.ProductListResponse;
import com.kkh.product._support.common.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "products")
@CompoundIndex(name = "restaurantId_index", def = "{'restaurantId': 1}")
@CompoundIndex(name = "name_index", def = "{'name': 1}")
public class Product extends AuditEntity {
    @Id
    private String id;
    private String restaurantId;
    private String name;
    private String description;
    private int price;
    private boolean isAvailable;
    private List<Option> optionList;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Option {
        private String name;
        private List<Choice> choiceList;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Choice {
        private String name;
        private int addPrice;
    }

    public static ProductDetailResponse toDetail(Product product) {
        return ProductDetailResponse.builder()
                .id(product.getId())
                .restaurantId(product.getRestaurantId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .isAvailable(product.isAvailable())
                .optionList(product.getOptionList())
                .build();
    }

    public static Mono<ProductListResponse> toList(Flux<Product> productFlux) {
        return productFlux.collectList() // Flux<Product> → List<Product>
                .map(products -> {
                    if (products.isEmpty()) {
                        throw new IllegalArgumentException("상품이 존재하지 않습니다.");
                    }

                    String restaurantId = products.get(0).getRestaurantId();

                    List<ProductListResponse.ProductData> dataList = products.stream()
                            .map(product -> ProductListResponse.ProductData.builder()
                                    .productId(product.getId())
                                    .name(product.getName())
                                    .description(product.getDescription())
                                    .price(product.getPrice())
                                    .isAvailable(product.isAvailable())
                                    .optionList(product.getOptionList())
                                    .build())
                            .toList();

                    return ProductListResponse.builder()
                            .restaurantId(restaurantId)
                            .dataList(dataList)
                            .build();
                });
    }

    public void updateFrom(ProductUpdateRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.price = request.getPrice();
        this.isAvailable = request.isAvailable();
        this.optionList = request.getOptionList();
    }

}

