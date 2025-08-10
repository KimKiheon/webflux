package com.kkh.user.user.model.repository;

import com.kkh.user.user.model.entity.mongo.MongoUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<MongoUser, String> {
}
