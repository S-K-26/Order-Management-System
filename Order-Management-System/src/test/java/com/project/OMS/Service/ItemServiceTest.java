package com.project.OMS.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		Item item = em.find(Item.class,2l);
		logger.info("Item ->{}",item);
		logger.info("Item ->{}",item.getItemId());
		assertEquals("Table", item.getName());
	}
	
	@Test
	@DirtiesContext
	@Transactional
	void deleteItem_Test() {
		itemRepository.deleteById(2l);
		//assertNull(itemRepository.findById(2l));
	}
	
	@Test
	@DirtiesContext
	@Transactional
	void saveItem_Test() {
		Item item = new Item("Pen",150.0);
		itemRepository.save(item);
		assertEquals("Pen", item.getName());
	
	}

}
