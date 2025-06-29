package com.kkh.user.owner.controller;

import com.kkh.user.owner.model.dto.OwnerRegisterRequest;
import com.kkh.user.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping
    public Mono<ResponseEntity<String>> register(@RequestBody OwnerRegisterRequest request) {
        return ownerService.register(request);
    }
}
