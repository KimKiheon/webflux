package com.kkh.product.order.model.enums;

public enum OrderType {
    DELIVERY("배달"),
    TAKEOUT("포장");
    private final String description;

    OrderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
