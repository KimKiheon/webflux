package com.kkh.product.order.model.entity;

import com.kkh.product._support.common.AuditEntity;
import com.kkh.product.order.model.enums.OrderStatus;
import com.kkh.product.order.model.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "orderLogs")
public class Order extends AuditEntity {
    @Id
    private String id;
    private String userId;
    private String restaurantId;
    private int totalPrice;
    private String address;
    private OrderStatus orderStatus;
    private OrderType orderType;
    private List<OrderProduct> productList;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderProduct {
        private String productId;
        private String name;
        private int price;
        private int quantity;
        private List<OrderOption> options;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderOption {
        private String name;
        private List<String> selectedChoices;
    }


}
