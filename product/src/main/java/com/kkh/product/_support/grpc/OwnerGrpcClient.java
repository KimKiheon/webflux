package com.kkh.product._support.grpc;

import com.kkh.grpc.AddRestaurantRequest;
import com.kkh.grpc.AddRestaurantResponse;
import com.kkh.grpc.OwnerServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OwnerGrpcClient {
    private final OwnerServiceGrpc.OwnerServiceStub ownerStub;

    public Mono<Void> addRestaurantToOwner(String ownerId, String restaurantId) {
        AddRestaurantRequest request = AddRestaurantRequest.newBuilder()
                .setOwnerId(ownerId)
                .setRestaurantId(restaurantId)
                .build();

        return Mono.create(sink -> {
            ownerStub.addRestaurantToOwner(request, new StreamObserver<>() {
                public void onNext(AddRestaurantResponse response) {
                    sink.success();
                }

                public void onError(Throwable t) {
                    sink.error(t);
                }

                public void onCompleted() {}
            });
        });
    }
}