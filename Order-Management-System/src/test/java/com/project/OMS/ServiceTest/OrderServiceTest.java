package com.project.OMS.ServiceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.project.OMS.ResourceNotFoundException;
import com.project.OMS.Entity.Order;
import com.project.OMS.Repositpry.OrderRepository;
import com.project.OMS.Service.OrderService;
/*
 * @Test - Used to define the certain method is test method.
 * @DirtiesContest - If a test modifies applicationContext it tells the framework to
  					 close & recreate the context for later tests.
 */

@SpringBootTest
@Transactional
class OrderServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	@DirtiesContext
	void addItemsToOrder_Test() {
		Order order = orderService.addItemsToOrder(1, 2);
		logger.info("Ordered Items-> {}", order.getItems());
		assertEquals(1,order.getOrderId());
	}
	
	@Test
	@DirtiesContext
	void saveOrder_Test() {
		Order order = new Order(100024l, null, "Panvel");
		orderRepository.save(order);
		assertEquals("Panvel", order.getAdressOfDelivery());
		assertEquals(100024l, order.getCustomerId());
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
	void totalCost_Test() {
		orderService.addItemsToOrder(1, 1);
		orderService.addItemsToOrder(1, 2);
		assertEquals("15000.0", orderService.totalCost("1"));
	}
	
	@Test
	@DirtiesContext
	void deleteOrder_Test() {
		orderService.deleteOrders(1);
		Order order = em.find(Order.class, 1);
		assertNull(order);
	}
					
}