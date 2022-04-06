package com.project.OMS.Service;

import java.util.List;

import com.project.OMS.Entity.Item;

//Created Service Class to provide business logic to the application

public interface ItemServise {
	
	// It will give list of all items present.
	List<Item> getAllItems();
	
	// To save the item in the database.
	Item saveItem(Item item);
	
	// To search the item by Item Id.
	Item getItemById(Integer id);
	
	// To update the item.
	Item updateItem(Item item);
	
	// To delete the item by Id.
	void deleteItem(Integer id);

}
