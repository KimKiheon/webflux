package com.kkh.user.owner.controller;

import com.kkh.user.owner.model.dto.request.OwnerRegisterRequest;
import com.kkh.user.owner.model.dto.response.OwnerDetailResponse;
import com.kkh.user.owner.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("/{email}")
    public Mono<ResponseEntity<OwnerDetailResponse>> detail(@PathVariable("email") String email){
        return ownerService.detail(email);
    }
    @PostMapping
    public Mono<ResponseEntity<String>> register(@RequestBody OwnerRegisterRequest request) {
        return ownerService.register(request);
    }
}
