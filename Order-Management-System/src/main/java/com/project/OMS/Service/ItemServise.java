package com.project.OMS.Service;

import java.util.List;

import com.project.OMS.Entity.Item;

public interface ItemServise {
	
	List<Item> getAllItems();
	
	Item saveItem(Item item);
	
	Item getItemById(Long id);
	
	Item updateItem(Item item);
	
	void deleteItem(Long id);

}
