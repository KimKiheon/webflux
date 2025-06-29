package com.kkh.user.owner.model.dto.response;

import com.kkh.user.owner.model.entity.Owner;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerDetailResponse {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<String> restaurantList;
    private Long settlement;

    public static OwnerDetailResponse toResponse(Owner owner) {
        return OwnerDetailResponse.builder()
                .id(owner.getId())
                .name(owner.getName())
                .email(owner.getEmail())
                .phoneNumber(owner.getPhoneNumber())
                .restaurantList(owner.getRestaurantList())
                .settlement(owner.getSettlement())
                .build();
    }
}
