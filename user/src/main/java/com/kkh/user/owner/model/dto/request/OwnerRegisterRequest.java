package com.kkh.user.owner.model.dto.request;

import com.kkh.user.owner.model.entity.Owner;
import com.kkh.user._support.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
                .restaurantList(List.of())
                .email(request.getEmail())
                .settlement(0L)
                .role(Role.OWNER)
                .build();
    }
}
