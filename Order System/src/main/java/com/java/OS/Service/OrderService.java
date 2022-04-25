package com.java.OS.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.java.OS.Entity.Order;

/**
 * Created Service @Interface to provide business logic to the application
 */
public interface OrderService {
	
	/**
	 * Get List of orders From database.
	 * @return List of Orders.
	 */
	List<Order> getAllOrders();
	
	/**
	 * To save Order.
	 * @param order 
	 * @return saved order.
	 */
	Order saveOrder (Order order);
	
	/**
	 * Gets Order Of Given Id.
	 * @param orderId of the Order.
	 * @return Order of Given orderId.
	 */
	Order getOrderById (Integer orderId);
	
	/**
	 * To add Items in the order by using itemId & orderId.
	 * @param orderId of order.
	 * @param itemId of item.
	 * @return order of provided orderId.
	 */
	Order addItemsToOrder(Integer orderId,Integer itemId);
	
	/**
	 * 
	 * @param orderId of order.
	 * @return total cost of given order & items present in it.
	 */
	Double totalCostOfOrder(Integer orderId);
	
	/**
	 * To delete order using orderId. 
	 * @param orderId of order.
	 * @return Boolean response(True/False) if order is deleted.
	 */
	ResponseEntity<Map<String, Boolean>> deleteOrder(Integer orderId);
	
	/**
	 * TO delete the items from the order.
	 * @param orderId of order.
	 * @param itemId of item.
	 * @return order with deleted items.
	 */
	Order deleteItemsFromOrder(Integer orderId, Integer itemId);
	
}
