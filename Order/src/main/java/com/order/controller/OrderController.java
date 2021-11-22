package com.order.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.order.entites.Item;
import com.order.entites.Order;
import com.order.entites.User;
import com.order.feignclient.UserClient;
import com.order.service.CartService;
import com.order.service.OrderService;
import com.order.utilities.OrderUtilities;

@RestController
public class OrderController {

	@Autowired
	private UserClient userClient;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartService cartService;

	@PostMapping(value = "/order/{userId}")
	public ResponseEntity<Order> saveOrder(@PathVariable("userId") Long userId,
			@RequestHeader(value = "Cookie") String cartId) {

		List<Item> cart = cartService.getAllItemsFromCart(cartId);
		User user = userClient.getUserById(userId);
		if (cart != null && user != null) {
			Order order = this.createOrder(cart, user);
			try {
				orderService.saveOrder(order);
				cartService.deleteCart(cartId);
				return new ResponseEntity<Order>(order, HttpStatus.CREATED);
			} catch (Exception ex) {
				ex.printStackTrace();
				return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
	}

	private Order createOrder(List<Item> cart, User user) {
		Order order = new Order();
		order.setItems(cart);
		order.setUser(user);
		order.setTotal(OrderUtilities.countTotalPrice(cart));
		order.setOrderedDate(LocalDate.now());
		order.setStatus("PAYMENT_EXPECTED");
		return order;
	}

}
