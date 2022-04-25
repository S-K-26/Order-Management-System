package com.java.OS.Servise;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.java.OS.Entity.Order;
import com.java.OS.Exception.ResourceNotFoundException;
import com.java.OS.Service.OrderService;

@SpringBootTest
@Transactional
class OrderServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private OrderService orderService;
		
	@Test
	@DirtiesContext
	void getAllOrders_Test() {
		assertEquals(2, orderService.getAllOrders().size());
	}

	@Test
	@DirtiesContext
	void addItemToOrder_Test() {
		Order order = orderService.addItemsToOrder(1, 2);
		logger.info("Ordered Items-> {}", order.getItems());
		assertEquals(1,order.getOrderId());
		assertEquals(1, order.getItems().size());
	}
	
	@Test
	@DirtiesContext
	void getOrderByOrderId_Test() {
		Order order = orderService.getOrderById(1);
		assertEquals(1, order.getOrderId());
		assertEquals(50001, order.getCustomerId());
		assertEquals("Mumbai", order.getAdressOfDelivery());
	}
	
	@Test
	@DirtiesContext
	void orderNotFoundException_Test() {
		ResourceNotFoundException throwsException = 
		 assertThrows(ResourceNotFoundException.class, ()->{orderService.getOrderById(12);});
			
			assertEquals("Order Not Found", throwsException.getMessage());
	}
	
	@Test
	@DirtiesContext
	void saveOrder_Test() {
		Order order = new Order(50026, null, "Pune");
		orderService.saveOrder(order);
		assertEquals(50026, order.getCustomerId());
		assertEquals("Pune", order.getAdressOfDelivery());
	}
	
	@Test
	@DirtiesContext
	void totalCostOfOrder_Test() {
		orderService.addItemsToOrder(1, 1);
		orderService.addItemsToOrder(1, 2);
		orderService.addItemsToOrder(1, 3);
		assertEquals(12000.0, orderService.totalCostOfOrder(1));
		
	}
	
	@Test
	@DirtiesContext
	void deleteItemsFromOrder_Test() {
		orderService.addItemsToOrder(1, 1);
		orderService.addItemsToOrder(1, 2);
		orderService.addItemsToOrder(1, 3);
		orderService.deleteItemsFromOrder(1, 3);
		Order order = orderService.getOrderById(1);
		assertEquals(2, order.getItems().size());
	}
	
	@Test
	@DirtiesContext
	void deleteOrderById_Test() {
		orderService.deleteOrder(1);
		Order order = em.find(Order.class, 1);
		assertNull(order);
	}
	
}
