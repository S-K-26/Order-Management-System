package com.project.OMS.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.OMS.ResourceNotFoundException;
import com.project.OMS.Entity.Item;
import com.project.OMS.Repositpry.ItemRepository;
import com.project.OMS.Service.ItemServise;
/*
 * ServiceImpl class to implement methods from service class.
  
 * @Autowired - Used to inject the object dependency.

 * @Service - The Annotation is used to mark the class as service provider.@Service annotation is 
   used with classes that provide some business functionalities. Spring context will auto detect 
   these classes when annotation-based configuration and class path scanning is used.
 */
@Service
public class ItemServiceImpl implements ItemServise{
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<Item> getAllItems() {

		return itemRepository.findAll();
	}

	@Override
	public Item saveItem(Item item) {
		
		return itemRepository.save(item);
	}

	@Override
	public Item getItemById(Integer id) {
		itemRepository.findById(id).orElseThrow(() ->
					new ResourceNotFoundException("Item Not Found"));
		return itemRepository.getById(id);
	}

	@Override
	public void deleteItem(Integer id) {
		itemRepository.deleteById(id);
		
	}

	@Override
	public Item updateItem(Item item) {
		return itemRepository.save(item);
	}

}
