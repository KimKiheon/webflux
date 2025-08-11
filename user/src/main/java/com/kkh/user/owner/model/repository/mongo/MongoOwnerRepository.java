package com.kkh.user.owner.model.repository.mongo;

import com.kkh.user.owner.model.entity.mongo.MongoOwner;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MongoOwnerRepository extends ReactiveMongoRepository<MongoOwner, String > {
    Mono<MongoOwner> findByEmail(String email);
}
