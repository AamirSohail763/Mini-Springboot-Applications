package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.ProductException;
import com.masai.model.Product;
import com.masai.repository.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO dao;

	@Override
	public Product addItem(Product item) throws ProductException {
		Optional<Product> opt = dao.findById(item.getProductId());
		if(opt.isPresent()) {
			throw new ProductException("Product already exists..");
		}else {
			return dao.save(item);
		}
	}

	@Override
	public Product updateItem(Product item) throws ProductException {
		Optional<Product> opt = dao.findById(item.getProductId());
		if(opt.isPresent()) {
			return dao.save(item);
		}else {
			throw new ProductException("No such product found..");
		}
	}

	@Override
	public Product viewItem(Integer productId) throws ProductException {
		Optional<Product> opt = dao.findById(productId);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new ProductException("No Product found with ID: "+productId);
		}
	}

	@Override
	public Product removeItem(Integer productId) throws ProductException {
		Optional<Product> opt = dao.findById(productId);
		if(opt.isPresent()) {
			Product item = opt.get();
			dao.delete(item);
			return item;
		}else {
			throw new ProductException("No Product found with ID: "+productId);
		}
	}

	@Override
	public List<Product> viewAllItems() throws ProductException {
		List<Product> items = dao.findAll();
		if(items.size() > 0) {
			return items;
		}else {
			throw new ProductException("No Product exists..");
		}
	}

}
