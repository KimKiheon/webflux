package com.kkh.product.restaurant.model.dto.response;

import com.kkh.product.restaurant.model.entity.Restaurant;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDetailResponse {
    private String restaurantId;
    private String ownerId;
    private String name;
    private String description;
    private String category;
    private int viewCount;
    private int orderCount;
    private int score;
    private Restaurant.OpenHours openHours;
    public static RestaurantDetailResponse toDetail(Restaurant restaurant) {
        return RestaurantDetailResponse.builder()
                .restaurantId(restaurant.getId())
                .ownerId(restaurant.getOwnerId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .category(restaurant.getCategory())
                .viewCount(restaurant.getViewCount())
                .orderCount(restaurant.getOrderCount())
                .score(restaurant.getScore())
                .openHours(restaurant.getOpenHours())
                .build();

    }
}
