package com.kkh.product.product.model.dto.response;

import com.kkh.product.product.model.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse {
    private String id;
    private String restaurantId;
    private String name;
    private String description;
    private int price;
    private boolean isAvailable;
    private List<Product.Option> optionList;

}
