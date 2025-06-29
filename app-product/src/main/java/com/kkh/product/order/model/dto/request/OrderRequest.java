package com.kkh.product.order.model.dto.request;

import com.kkh.product.order.model.enums.OrderStatus;
import com.kkh.product.order.model.entity.Order;
import com.kkh.product.order.model.enums.OrderType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String userId;
    private String restaurantId;
    private int totalPrice;
    private String address;
    private OrderType orderType;
    private List<Order.OrderProduct> productList;
    public static Order toEntity(OrderRequest request){
        return Order.builder()
                .userId(request.getUserId())
                .restaurantId(request.getRestaurantId())
                .totalPrice(request.getTotalPrice())
                .orderStatus(OrderStatus.ORDERED)
                .orderType(request.getOrderType())
                .productList(request.getProductList())
                .build();
    }

}
