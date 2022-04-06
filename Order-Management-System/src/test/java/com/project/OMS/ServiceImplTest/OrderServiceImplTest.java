package com.project.OMS.ServiceImplTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

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
class OrderServiceImplTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	@DirtiesContext
	void testAddItemsToOrder() {
		Order order = orderService.addItemsToOrder(1, 2);
		logger.info("Ordered Items-> {}", order.getItems());
		assertEquals(1,order.getOrderId());
	}
	
	@Test
	@DirtiesContext
	void saveOrder() {
		Order order = new Order(100024l, null, "Panvel");
		orderRepository.save(order);
		assertEquals("Panvel", order.getAdressOfDelivery());
	}
	
	@Test
	@DirtiesContext
	@Transactional
	void totalCost_Test() {
		orderService.addItemsToOrder(1, 1);
		orderService.addItemsToOrder(1, 2);
		assertEquals("15000.0", orderService.totalCost("1"));
	}
					
}