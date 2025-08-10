package com.kkh.user.user.model.dto.request;

import com.kkh.user._support.enums.Role;
import com.kkh.user.user.model.entity.mongo.MongoUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public static MongoUser toEntity(UserRegisterRequest request) {
        return MongoUser.builder()
                .role(Role.USER)
                .email(request.getEmail())
                .address(request.getAddress())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }
}
