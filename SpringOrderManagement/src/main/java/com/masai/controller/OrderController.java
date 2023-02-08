package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.OrderException;
import com.masai.model.Order;
import com.masai.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService service;
	
	
	@PostMapping("/save")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) throws OrderException{
 
    	return new ResponseEntity<Order>(service.addOrder(order), HttpStatus.CREATED);
    }
	
	
	@DeleteMapping("/remove/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") Integer orderId ) throws OrderException{  	
    	
    	return new ResponseEntity<Order>(service.removeOrder(orderId), HttpStatus.ACCEPTED);

    }
	
	
	@GetMapping("/view/{orderId}")
    public ResponseEntity<Order> viewOrder(@PathVariable("orderId") Integer orderId) throws OrderException{
    	
    	return  new ResponseEntity<Order>(service.viewOrder(orderId),HttpStatus.FOUND);
    }

}
