package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProduct();
		if (!products.isEmpty()) {
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/products", params = "category")
	public ResponseEntity<List<Product>> getAllProductByCategory(@RequestParam("category") String category) {
		List<Product> products = productService.getAllProductByCategory(category);
		if (!products.isEmpty()) {
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product> getOneProductById(@PathVariable("id") long id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/products", params = "name")
	public ResponseEntity<List<Product>> getAllProductsByName(@RequestParam("name") String name) {
		List<Product> products = productService.getAllProductsByName(name);
		if (!products.isEmpty()) {
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
	}
}
