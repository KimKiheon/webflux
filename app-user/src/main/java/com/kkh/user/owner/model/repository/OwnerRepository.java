package com.kkh.user.owner.model.repository;

import com.kkh.user.owner.model.entity.Owner;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends ReactiveMongoRepository<Owner, String > {
}
