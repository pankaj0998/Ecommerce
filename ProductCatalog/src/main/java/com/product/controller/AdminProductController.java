package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@PostMapping(value = "/products")
	private ResponseEntity<Product> addProduct(@RequestBody Product product) {
		if (product != null) {
			try {
				productService.addProduct(product);
				return new ResponseEntity<Product>(product, HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/products/{id}")
	private ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			try {
				productService.deleteProduct(id);
				return new ResponseEntity<Void>(HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
