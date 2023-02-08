package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Product;
import com.masai.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService service;

	
	@PostMapping("/add")
	public ResponseEntity<Product> addItem(@RequestBody Product item){
		Product newItem = service.addItem(item);
		return new ResponseEntity<Product>(newItem, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Product> updateItem(@RequestBody Product item){
		Product updatedProduct = service.updateItem(item);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}
	
	
	@GetMapping("/view/{productId}")
	public ResponseEntity<Product> getItem(@PathVariable("productId") Integer productId){
		Product item = service.viewItem(productId);
		return new ResponseEntity<Product>(item, HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/remove/{productId}")
	public ResponseEntity<Product> removeItem(@PathVariable("productId") Integer productId){
		Product removedItem = service.removeItem(productId);
		return new ResponseEntity<Product>(removedItem, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/viewall")
	public ResponseEntity<List<Product>> getAllItems(){
		List<Product> items = service.viewAllItems();
		return new ResponseEntity<List<Product>>(items, HttpStatus.OK);
		
	}
	
}
