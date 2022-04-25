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

import com.java.OS.Entity.Item;
import com.java.OS.Exception.ResourceNotFoundException;
import com.java.OS.Repository.ItemRepository;
import com.java.OS.Service.ItemService;

@SpringBootTest
@Transactional
class ItemServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private EntityManager em;

	@Test
	void getItemById_Test() {
		Item item = itemRepository.getById(1);
		logger.info("Item ->{}",item);
		assertEquals("Table", item.getName());
		assertEquals(1, item.getItemId());
		
	}
	
	@Test
	@DirtiesContext
	void getAllItems_Test() {
		assertEquals(4, itemService.getAllItems().size());
	}
	
	@Test
	@DirtiesContext
	void deleteItem_Test() {
		itemRepository.deleteById(1);
		Item item = em.find(Item.class, 1);
		assertNull(item);

	}
	
	@Test
	@DirtiesContext
	void addItem_Test() {
		Item item = new Item("Pen", 150.0);
		itemRepository.save(item);
		assertEquals("Pen", item.getName());
		assertEquals(150.0, item.getCost());
	}
	
	@Test
	@DirtiesContext
	void itemNotFoundException_Test() {
		ResourceNotFoundException throwsException = assertThrows(ResourceNotFoundException.class, () -> {
			itemService.getItemBYId(10);
		});

		assertEquals("Item Not Found", throwsException.getMessage());
	}
	
	@Test
	@DirtiesContext
	void updateItem_Test() {
		Item item = em.find(Item.class, 1);
		item.setItemId(100);
		item.setName("Fan");
		item.setCost(1700.0);

		itemService.updateItem(1, item);
		assertEquals("Fan", item.getName());
		assertEquals(100, item.getItemId());
		assertEquals(1700.0, item.getCost());
	}

}
