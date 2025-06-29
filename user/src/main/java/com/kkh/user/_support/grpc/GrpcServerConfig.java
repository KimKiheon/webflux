package com.kkh.user._support.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfig {

    @Bean
    public Server grpcServer(OwnerGrpcService service) throws Exception {
        return ServerBuilder
                .forPort(6565)
                .addService(service)
                .build()
                .start();
    }

    @Bean
    public SmartLifecycle grpcLifecycle(Server server) {
        return new SmartLifecycle() {
            private boolean running = false;

            @Override
            public void start() {
                running = true;
            }

            @Override
            public void stop() {
                server.shutdown();
                running = false;
            }

            @Override
            public boolean isRunning() {
                return running;
            }
        };
    }
}