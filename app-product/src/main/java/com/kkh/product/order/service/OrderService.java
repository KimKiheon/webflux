package com.kkh.product.order.service;

import com.kkh.product.order.model.dto.request.OrderRequest;
import com.kkh.product.order.model.dto.response.OrderDetailResponse;
import com.kkh.product.order.model.entity.Order;
import com.kkh.product.order.model.respsitory.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Flux<String> orderListByUserId(String userId){
        return orderRepository.findByUserId(userId)
                .map(Order::getId);
    }
    public Mono<Void> order(OrderRequest request) {
        return orderRepository.save(OrderRequest.toEntity(request))
                .then();
    }

    public Mono<OrderDetailResponse> detail(String orderId){
        return orderRepository.findById(orderId)
                .map(OrderDetailResponse::toResponse);
    }
}
