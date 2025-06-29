package com.kkh.user.owner.model.repository;

import com.kkh.user.owner.model.entity.Owner;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OwnerRepository extends ReactiveMongoRepository<Owner, String > {
    Mono<Owner> findByEmail(String email);
}
