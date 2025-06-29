package com.kkh.user.user.service;

import com.kkh.user.user.model.dto.request.UserRegisterRequest;
import com.kkh.user.user.model.entity.User;
import com.kkh.user.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Mono<ResponseEntity<String>> register(UserRegisterRequest request) {
        User user = UserRegisterRequest.toEntity(request);

        return userRepository.save(user)
                .map(saved -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("등록 성공: " + saved.getId()));
    }
}
