package com.java.OS.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.OS.Entity.Order;
import com.java.OS.Service.OrderService;

/**
 * Created Controller Class to handle request for retrieving data.
  
 * @RestController - It indicates that the annotated class is controller.
 * @CrossOrigin - Since we have used Angular for our front end, we have used @CrossOrigin for resource sharing. 
 * @Autowired - Used to inject the object dependency.
 * @PathVariable -It is used to handle template variables in the request URI mapping, and set them as 
 				  method parameters. 
   @RequestBody -Annotation maps the HttpRequest body to a transfer or domain object.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	/**
	 * @GetMapping - Used to handle the HTTP GET requests matched with given expression.
	 * @return List of All Orders present in Database in Json format.
	 */
	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}
	
	/**
	 * @PostMapping - Used to annotated classes handle the HTTP POST requests matched with given expression.
	 * @return order in json.
	 */
	@PostMapping("/orders")
	public Order saveOrders(@RequestBody Order order) {
		return orderService.saveOrder(order);
	}
	
	/**
	 * @PutMapping - Used to update/modify the source so @PutMapping annotation is used for 
	 				 mapping HTTP PUT requests onto specific handler methods.
	 * @param itemId of item
	 * @return order of added items.
	 */
	@PutMapping("/orders/{orderId}/{itemId}")
	public Order addItemsToOrder(@PathVariable Integer orderId, @PathVariable Integer itemId) {
		return orderService.addItemsToOrder(orderId, itemId);
	}
	
	/**
	 * @param orderId
	 * @return total cost of order in json.
	 */
	@GetMapping("/orders/{orderId}")
	public Double totalCostOfOrder(@PathVariable Integer orderId) {
		return orderService.totalCostOfOrder(orderId);
	}
	
	/**
	 * @DeleteMapping -Annotation maps HTTP DELETE requests onto specific handler methods.
	 * @param oredrId of order.
	 * @return Boolean response(True/False) if item is deleted.
	 */
	@DeleteMapping("/orders/{orderId}")
	public  ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Integer orderId){
		return orderService.deleteOrder(orderId);
	}
	
	/**
	 * @param oredrId of order.
	 * @return order with deleted item.
	 */
	@DeleteMapping("/orders/{orderId}/{itemId}")
	public Order deleteItemsFromOrder(@PathVariable Integer orderId,@PathVariable Integer itemId) {
		return orderService.deleteItemsFromOrder(orderId, itemId);
	}
}
