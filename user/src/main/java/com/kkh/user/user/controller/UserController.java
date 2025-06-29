package com.kkh.user.user.controller;

import com.kkh.user.user.model.dto.request.UserRegisterRequest;
import com.kkh.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public Mono<ResponseEntity<String>> register(@RequestBody UserRegisterRequest request){
        return userService.register(request);
    }

}
