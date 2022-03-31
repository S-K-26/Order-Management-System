package com.project.OMS.Service;

import java.util.List;


import com.project.OMS.Entity.Order;

public interface OrderService {
	
	List<Order> getAllOrders();

	Order saveOrder(Order order);
	
	String totalCost(String orderId);
	
	Order addItemsToOrder(Integer orderId, Long id);
	
	void deleteOrder(String orderId);
	

}
