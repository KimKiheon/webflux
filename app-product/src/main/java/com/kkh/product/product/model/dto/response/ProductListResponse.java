package com.kkh.product.product.model.dto.response;

import com.kkh.product.product.model.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponse {
    private String restaurantId;
    private List<ProductData> dataList;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductData{
        private String productId;
        private String name;
        private String description;
        private int price;
        private boolean isAvailable;
        private List<Product.Option> optionList;
    }

}
