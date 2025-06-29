package com.kkh.user.owner.service;

import com.kkh.user.owner.model.dto.OwnerRegisterRequest;
import com.kkh.user.owner.model.entity.Owner;
import com.kkh.user.owner.model.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
                .map(saved -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("등록 성공: " + saved.getId()));
    }
}
