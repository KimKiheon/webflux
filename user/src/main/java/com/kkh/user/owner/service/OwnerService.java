package com.kkh.user.owner.service;

import com.kkh.user.owner.model.dto.request.OwnerRegisterRequest;
import com.kkh.user.owner.model.dto.response.OwnerDetailResponse;
import com.kkh.user.owner.model.entity.mongo.MongoOwner;
import com.kkh.user.owner.model.repository.mongo.MongoOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final MongoOwnerRepository mongoOwnerRepository;

    public Mono<ResponseEntity<String>> register(OwnerRegisterRequest request) {
        MongoOwner owner = OwnerRegisterRequest.toEntity(request);

        return mongoOwnerRepository.save(owner)
                .map(saved -> ResponseEntity.ok("등록 성공: " + saved.getId()));
    }

    public Mono<ResponseEntity<OwnerDetailResponse>> detail(String email) {
        return mongoOwnerRepository.findByEmail(email)
                .map(OwnerDetailResponse::toResponse)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
