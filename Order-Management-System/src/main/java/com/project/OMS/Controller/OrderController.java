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
/*
 * Created Controller Class to handle request for retrieving data for orders.
  
 * @Controller - It indicates that the annotated class is controller 
 * @Autowired - Used to inject the object dependency.
 * @GetMapping - Used to handle the HTTP GET requests matched with given expression.
 * @PostMapping - Used to annotated classes handle the HTTP POST requests matched with given expression.
 * @PathVariable -It is used to handle template variables in the request URI mapping, and set them as 
 				  method parameters.
 * @ModelAttribute - Used to bind the object "item" to item entity . Used to retrieve the argument from the model. 				  
  
 * Since we have used thymleaf for our forntend part We have returned views below such as items,edit 
   etc. which are shown in the src/main/resources/templates. 
   
 */
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
		orderService.deleteOrder(Integer.parseInt(orderId));
		System.out.println(orderId);
		return "redirect:/orders";
	}
	
	@GetMapping("/orders/cost/{orderId}")
	public String totalCostOfOrder(@PathVariable String orderId) {
		return orderService.totalCost(orderId);
	}


}
