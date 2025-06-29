package com.kkh.product._support.grpc;

import com.kkh.grpc.OwnerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcChannelConfig {
    @Bean
    public ManagedChannel ownerChannel(@Value("${grpc.owner.host}") String host,
                                       @Value("${grpc.owner.port}") int port) {
        return ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
    }

    @Bean
    public OwnerServiceGrpc.OwnerServiceStub ownerStub(ManagedChannel ownerChannel) {
        return OwnerServiceGrpc.newStub(ownerChannel);
    }
}
