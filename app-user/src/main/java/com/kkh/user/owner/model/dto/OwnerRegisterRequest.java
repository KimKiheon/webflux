package com.kkh.user.owner.model.dto;

import com.kkh.user.owner.model.entity.Owner;
import com.kkh.user.common.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString
public class OwnerRegisterRequest {
    private String name;
    private String phoneNumber;
    private String email;

    public static Owner toEntity(OwnerRegisterRequest request) {
        log.info("request: {}", request.toString());
        return Owner.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .restaurantList(null)
                .email(request.getEmail())
                .settlement(0L)
                .role(Role.OWNER)
                .build();
    }
}
