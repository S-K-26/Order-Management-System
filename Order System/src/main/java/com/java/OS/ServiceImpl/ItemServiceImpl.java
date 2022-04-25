package com.java.OS.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.java.OS.Entity.Item;
import com.java.OS.Exception.ResourceNotFoundException;
import com.java.OS.Repository.ItemRepository;
import com.java.OS.Service.ItemService;

/**
 * ServiceImpl class to implement methods from service class.
 * @Autowired - Used to inject the object dependency.
 * @Service - The Annotation is used to mark the class as service provider.@Service annotation is 
   used with classes that provide some business functionalities. Spring context will auto detect 
   these classes when annotation-based configuration and class path scanning is used.
 */
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<Item> getAllItems() {
		
		return itemRepository.findAll();
	}

	@Override
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public ResponseEntity<Item> getItemBYId(Integer itemId) {
		Item item = itemRepository.findById(itemId).orElseThrow(() ->
		new ResourceNotFoundException("Item Not Found"));
		return ResponseEntity.ok(item);
	}

	@Override
	public ResponseEntity<Item> updateItem(Integer itemId, Item itemDetails) {
		//Get the item of given itemId & allot it to item.
		Item item = itemRepository.findById(itemId).orElseThrow(() ->
		new ResourceNotFoundException("Item Not Found"));
		item.setName(itemDetails.getName());
		item.setCost(itemDetails.getCost());
		// saves the updated item.
		Item updatedItem = itemRepository.save(item);
		return ResponseEntity.ok(updatedItem);
	}

	

}
