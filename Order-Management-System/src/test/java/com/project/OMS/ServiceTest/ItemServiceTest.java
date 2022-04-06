package com.project.OMS.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.project.OMS.Entity.Item;
import com.project.OMS.Repositpry.ItemRepository;
import com.project.OMS.Service.ItemServise;

/*
 * @Test - Used to define the certain method is test method.
 * @DirtiesContest - If a test modifies applicationContext it tells the framework to
  					 close & recreate the context for later tests.
 */

@SpringBootTest
class ItemServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ItemServise itemServise;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	EntityManager em;

	@Test
	void getItemByID_Test() {
		Item item = em.find(Item.class, 2);
		logger.info("Item ->{}", item);
		logger.info("Item ->{}", item.getId());
		assertEquals("Table", item.getName());
	}

	@Test
	@DirtiesContext
	@Transactional
	void deleteItem_Test() {

		itemRepository.deleteById(2);
		//assertNull(itemRepository.getById(2));

	}

	@Test
	@DirtiesContext
	@Transactional
	void saveItem_Test() {
		Item item = new Item("Pen", 150.0);
		itemRepository.save(item);
		assertEquals("Pen", item.getName());
		assertEquals(150.0, item.getCost());

	}

}
