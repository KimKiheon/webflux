package com.kkh.user.user.model.repository;

import com.kkh.user.user.model.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
