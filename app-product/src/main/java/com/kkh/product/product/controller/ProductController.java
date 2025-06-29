package com.kkh.product.product.controller;

import com.kkh.product.product.model.dto.request.ProductRegisterRequest;
import com.kkh.product.product.model.dto.request.ProductUpdateRequest;
import com.kkh.product.product.model.dto.response.ProductDetailResponse;
import com.kkh.product.product.model.dto.response.ProductListResponse;
import com.kkh.product.product.service.ProductService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;
    @GetMapping("/{id}")
    public Mono<ProductDetailResponse> detail(@PathVariable("id") String id){
        return productService.detail(id);
    }

    @GetMapping("/list/{restaurantId}")
    public Mono<ProductListResponse> byRestaurant(@PathVariable("restaurantId") String id){
        return  productService.listByRestaurant(id);
    }

    @PostMapping
    public Mono<Void> register(@RequestBody ProductRegisterRequest request){
        return productService.register(request);
    }

    @PutMapping
    public Mono<Void> update(@RequestBody ProductUpdateRequest request){
        return productService.update(request);
    }

}
