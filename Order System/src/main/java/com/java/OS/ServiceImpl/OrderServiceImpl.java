package com.java.OS.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.OS.Entity.Order;
import com.java.OS.Repository.ItemRepository;
import com.java.OS.Repository.OrderRepository;
import com.java.OS.Service.OrderService;
import com.java.OS.Exception.ResourceNotFoundException;
import com.java.OS.Entity.Item;

/**
 * ServiceImpl class to implement methods from service class.
  
 * @Autowired - Used to inject the object dependency.
 * @Service - The Annotation is used to mark the class as service provider.@Service annotation is 
   used with classes that provide some business functionalities. Spring context will auto detect 
   these classes when annotation-based configuration and class path scanning is used.
 */
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
	}
	

	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	@Override
	public Order getOrderById(Integer orderId) {
		orderRepository.findById(orderId).orElseThrow(() ->
		new ResourceNotFoundException("Order Not Found"));
		return orderRepository.getById(orderId);
	}

	@Override
	public Order addItemsToOrder(Integer orderId, Integer itemId) {
		Order order = orderRepository.getById(orderId);
		Item item = itemRepository.getById(itemId);
		item.addOrders(order);
		order.addItems(item);
		return orderRepository.save(order);
	}

	@Override
	public Double totalCostOfOrder(Integer orderId) {
		Order order = orderRepository.getById(orderId);
		Double cost = 0d;
		for (Item item : order.getItems()) {
			cost = cost + item.getCost();
		}
		return cost;
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deleteOrder(Integer orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(()->
			new ResourceNotFoundException("Order Not Found"));
		orderRepository.delete(order);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@Override
	public Order deleteItemsFromOrder(Integer orderId, Integer itemId) {
		Order order = orderRepository.getById(orderId);
		Item item = itemRepository.getById(itemId);
		item.removeOrders(order);
		order.removeItems(item);
		return orderRepository.save(order);
	}
	

}
