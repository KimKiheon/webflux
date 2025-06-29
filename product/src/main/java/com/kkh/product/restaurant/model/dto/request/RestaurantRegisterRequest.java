package com.kkh.product.restaurant.model.dto.request;

import com.kkh.product.restaurant.model.entity.Restaurant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRegisterRequest {
    private String ownerId;
    private String name;
    private String description;
    private String category;
    private String start;
    private String end;

    public static Restaurant toEntity(RestaurantRegisterRequest request) {
        return Restaurant.builder()
                .ownerId(request.getOwnerId())
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .openHours(Restaurant.OpenHours.builder()
                        .start(request.start)
                        .end(request.end)
                        .build())
                .build();
    }
}
