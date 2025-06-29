package com.kkh.product.restaurant.model.dto.response;

import com.kkh.product.restaurant.model.entity.Restaurant;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantListResponse {
    private String restaurantId;
    private String name;
    private String category;
    private int viewCount;
    private int orderCount;
    private int score;
    private Restaurant.OpenHours openHours;
    public static RestaurantListResponse toList(Restaurant restaurant){
        return RestaurantListResponse.builder()
                .restaurantId(restaurant.getId())
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .viewCount(restaurant.getViewCount())
                .orderCount(restaurant.getOrderCount())
                .score(restaurant.getScore())
                .openHours(restaurant.getOpenHours())
                .build();
    }

}
