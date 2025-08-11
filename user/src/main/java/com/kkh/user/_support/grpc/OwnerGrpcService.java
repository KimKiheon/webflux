package com.kkh.user._support.grpc;

import com.kkh.grpc.AddRestaurantRequest;
import com.kkh.grpc.AddRestaurantResponse;
import com.kkh.grpc.OwnerServiceGrpc;
import com.kkh.user.owner.model.repository.mongo.MongoOwnerRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerGrpcService extends OwnerServiceGrpc.OwnerServiceImplBase {

    private final MongoOwnerRepository mongoOwnerRepository;

    @Override
    public void addRestaurantToOwner(AddRestaurantRequest request, StreamObserver<AddRestaurantResponse> responseObserver) {
        mongoOwnerRepository.findById(request.getOwnerId())
                .flatMap(owner -> {
                    List<String> list = Optional.ofNullable(owner.getRestaurantList()).orElse(new ArrayList<>());
                    if (!list.contains(request.getRestaurantId())) {
                        list.add(request.getRestaurantId());
                        owner.setRestaurantList(list);
                    }
                    return mongoOwnerRepository.save(owner);
                })
                .doOnNext(saved -> {
                    responseObserver.onNext(AddRestaurantResponse.newBuilder()
                            .setMessage("추가 완료")
                            .build());
                    responseObserver.onCompleted();
                })
                .doOnError(responseObserver::onError)
                .subscribe();
    }
}
