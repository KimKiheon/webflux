package com.kkh.user.owner.service;

import com.kkh.user.owner.model.dto.request.OwnerRegisterRequest;
import com.kkh.user.owner.model.dto.response.OwnerDetailResponse;
import com.kkh.user.owner.model.entity.Owner;
import com.kkh.user.owner.model.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public Mono<ResponseEntity<String>> register(OwnerRegisterRequest request) {
        Owner owner = OwnerRegisterRequest.toEntity(request);

        return ownerRepository.save(owner)
                .map(saved -> ResponseEntity.ok("등록 성공: " + saved.getId()));
    }

    public Mono<ResponseEntity<OwnerDetailResponse>> detail(String email) {
        return ownerRepository.findByEmail(email)
                .map(OwnerDetailResponse::toResponse)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
