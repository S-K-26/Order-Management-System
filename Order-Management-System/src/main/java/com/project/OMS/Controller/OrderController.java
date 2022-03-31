package com.project.OMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.OMS.Entity.Order;
import com.project.OMS.Service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public String getAllOrders(Model model) {
		model.addAttribute("orders",orderService.getAllOrders());
		return "orders";
	}
	
	@GetMapping("/orders/new")
	public String createNewOrder(Model model) {
		Order order =  new Order();
		model.addAttribute("order", order);
		return "New-Order";
	}
	
	@PostMapping("/orders")
	public String saveOrder(@ModelAttribute("order") Order order) {
		orderService.saveOrder(order);
		return "redirect:/items";
	}
	
	@GetMapping("/orders/{orderId}")
	public String deleteOrder(@PathVariable String orderId) {
		orderService.deleteOrder(orderId);
		return "redirect:/orders";
	}
	
	@GetMapping("/orders/cost/{orderId}")
	public String totalCostOfOrder(@PathVariable String orderId) {
		return orderService.totalCost(orderId);
	}


}
