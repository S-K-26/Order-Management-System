package com.project.OMS.Service;

import java.util.List;


import com.project.OMS.Entity.Order;

//Created Service Class to provide business logic to the application

public interface OrderService {
	
	// It will give list of all orders present
	List<Order> getAllOrders();
	
	// To get Order By Id
	Order getOrderById(Integer orderId);
	
	// To save the order.
	Order saveOrder(Order order);
	
	// To get the total cost of the order.
	String totalCost(String orderId);
	
	// To add items to the order.
	Order addItemsToOrder(Integer orderId, Integer id);
	
	// To delete the order.
	void deleteOrder(Integer orderId);
	
	// To delete items from order
	List<Order> deleteOrders(Integer orderId);	

}
