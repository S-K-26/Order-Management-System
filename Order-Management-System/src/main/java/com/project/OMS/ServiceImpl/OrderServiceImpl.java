package com.project.OMS.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.OMS.Entity.Order;
import com.project.OMS.Repositpry.ItemRepository;
import com.project.OMS.Repositpry.OrderRepository;
import com.project.OMS.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(String orderId) {
		Order order = orderRepository.getById(Integer.parseInt(orderId));
		orderRepository.delete(order);
	
	}

	@Override
	public Order addItemsToOrder(Integer orderId, Long id) {
		Order order = orderRepository.getById(orderId);
		 com.project.OMS.Entity.Item item = itemRepository.getById(id);
		item.addOrders(order);
		order.addItems(item);
		return orderRepository.save(order);
	}

	@Override
	public String totalCost(String orderId) {
			Order order = orderRepository.getById(Integer.parseInt(orderId));
			Double cost = 0d;
			for (com.project.OMS.Entity.Item item : order.getItems()) {
				cost = cost + item.getCost();
			}
			return String.valueOf(cost);
		}

		/*
		 * @Override public List<Order> deleteOrders(String orderId) { Order order =
		 * orderRepository.getById(Integer.parseInt(orderId));
		 * List<com.project.OMS.Entity.Item> items = order.getItems(); if (items ==
		 * null) { orderRepository.delete(order); } else { for
		 * (com.project.OMS.Entity.Item item : items) { item.removeOrders(order); }
		 * orderRepository.delete(order); }
		 * 
		 * return getAllOrders(); }
		 */
}


