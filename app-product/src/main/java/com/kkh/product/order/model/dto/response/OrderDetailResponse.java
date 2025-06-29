package com.kkh.product.order.model.dto.response;

import com.kkh.product.order.model.enums.OrderStatus;
import com.kkh.product.order.model.entity.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private String orderId;
    private String userId;
    private String restaurantId;
    private int totalPrice;
    private OrderStatus orderStatus;
    private List<Order.OrderProduct> productList;

    public static OrderDetailResponse toResponse(Order order){
        return OrderDetailResponse
                .builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .productList(order.getProductList())
                .build();
    }
}
