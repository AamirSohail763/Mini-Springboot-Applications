package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.OrderException;
import com.masai.model.Order;
import com.masai.repository.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDAO dao;

	@Override
	public Order addOrder(Order order) throws OrderException {
		Optional<Order> opt = dao.findById(order.getOrderId());
		if(opt.isPresent()) {
			throw new OrderException("Order already exists..");
		}else {
			return dao.save(order);
		}
	}

	@Override
	public Order removeOrder(Integer orderId) throws OrderException {
		Optional<Order> opt = dao.findById(orderId);
		if(opt.isPresent()) {
			Order order = opt.get();
			dao.delete(order);
			return order;
		}else {
			throw new OrderException("No Order found with ID: "+orderId);
		}
	}

	@Override
	public Order viewOrder(Integer orderId) throws OrderException {
		Optional<Order> opt = dao.findById(orderId);
		if(opt.isPresent()) {
			Order order = opt.get();
			return order;
		}else {
			throw new OrderException("No Order found with ID: "+orderId);
		}
	}
	
	

}
