package com.kkh.product.product.model.dto.request;

import com.kkh.product.product.model.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductRegisterRequest {
    private String restaurantId;
    private String productId;
    private String name;
    private String description;
    private int price;
    private boolean isAvailable;
    private List<Product.Option> optionList;

    public static Product toEntity(ProductRegisterRequest request) {
        return Product.builder()
                .restaurantId(request.getRestaurantId())
                .name(request.getName())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .optionList(request.getOptionList())
                .build();
    }
}
