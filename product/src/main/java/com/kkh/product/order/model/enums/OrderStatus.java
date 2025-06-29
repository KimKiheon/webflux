package com.kkh.product.order.model.enums;

public enum OrderStatus {
    ORDERED("주문 완료"),
    PREPARING("준비 중"),
    CANCELLED("주문 취소"),
    DELIVERED("배달 완료");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
