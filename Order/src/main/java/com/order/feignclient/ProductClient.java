package com.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.entites.Product;

@FeignClient(name = "ProductCatalog", url = "http://localhost:8082/")
public interface ProductClient {

	@GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId);

}
