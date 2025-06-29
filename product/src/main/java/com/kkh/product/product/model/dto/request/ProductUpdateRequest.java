package com.kkh.product.product.model.dto.request;

import com.kkh.product.product.model.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductUpdateRequest {
    private String productId;
    private String restaurantId;
    private String name;
    private String description;
    private int price;
    private boolean isAvailable;
    private List<Product.Option> optionList;
}
