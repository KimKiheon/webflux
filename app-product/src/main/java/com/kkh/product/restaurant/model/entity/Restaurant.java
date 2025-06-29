package com.kkh.product.restaurant.model.entity;

import com.kkh.product.restaurant.model.dto.response.RestaurantDetailResponse;
import com.kkh.product.restaurant.model.dto.response.RestaurantListResponse;
import com.kkh.product._support.config.common.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "restaurants")
@Getter
@Builder
@CompoundIndexes({
        @CompoundIndex(name = "restaurantName_index", def = "{'name': 1}"),
        @CompoundIndex(name = "category_index", def = "{'category': 1}"),
        @CompoundIndex(name = "viewCount_index", def = "{'viewCount': 1}"),
        @CompoundIndex(name = "orderCount_index", def = "{'orderCount': 1}")
})
public class Restaurant extends AuditEntity {
    @Id
    private String id;
    private String ownerId;
    private String name;
    private String description;
    private String category;
    @Builder.Default
    private int viewCount = 0;
    @Builder.Default
    private int orderCount = 0;
    @Builder.Default
    private int score = 0;
    private OpenHours openHours;


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class OpenHours {
        private String start;
        private String end;
    }

    public static RestaurantDetailResponse toDetail(Restaurant restaurant) {
        return RestaurantDetailResponse.builder()
                .restaurantId(restaurant.getId())
                .ownerId(restaurant.getOwnerId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .category(restaurant.getCategory())
                .viewCount(restaurant.viewCount)
                .orderCount(restaurant.orderCount)
                .score(restaurant.getScore())
                .openHours(restaurant.getOpenHours())
                .build();

    }
    public static RestaurantListResponse toList(Restaurant restaurant){
        return RestaurantListResponse.builder()
                .restaurantId(restaurant.getId())
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .viewCount(restaurant.viewCount)
                .orderCount(restaurant.orderCount)
                .score(restaurant.getScore())
                .openHours(restaurant.getOpenHours())
                .build();
    }
}
