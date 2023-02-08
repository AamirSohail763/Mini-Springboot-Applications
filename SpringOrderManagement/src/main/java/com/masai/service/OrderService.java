package com.masai.service;

import com.masai.exception.OrderException;
import com.masai.model.Order;

public interface OrderService {
	
	public Order addOrder(Order order)throws OrderException;
	
	public Order removeOrder(Integer orderId)throws OrderException;
	
	public Order viewOrder(Integer orderId)throws OrderException;

}
