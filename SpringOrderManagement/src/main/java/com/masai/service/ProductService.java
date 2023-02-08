package com.masai.service;

import java.util.List;

import com.masai.exception.ProductException;
import com.masai.model.Product;

public interface ProductService {
	
	public Product addItem(Product item)throws ProductException;
	
	public Product updateItem(Product item)throws ProductException;
	
	public Product viewItem(Integer productId)throws ProductException;
	
	public Product removeItem(Integer productId)throws ProductException;
	
	public List<Product> viewAllItems()throws ProductException;

}
