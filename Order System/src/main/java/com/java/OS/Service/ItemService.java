package com.java.OS.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.java.OS.Entity.Item;

/**
 *Created Service @Interface to provide business logic to the application
 */
public interface ItemService {
	
	/**
	 * Gets All Items from database.
	 * @return List of Items.
	 */
	List<Item> getAllItems();
	
	/**
	 * Saves Newly created item.
	 */
	Item saveItem (Item item);
	
	/**
	 * ResponseEntity is used to get HTTP response.
	 * @param itemId of the item.
	 * @return item of provided itemId.
	 */
	ResponseEntity<Item>  getItemBYId(Integer itemId);
	
	/**
	 * Update method to update item of given ItemId.
	 * @param itemId of the item.
	 * @param item that is updated
	 * @return Updated item.
	 */
	ResponseEntity<Item> updateItem(Integer itemId, Item item);

		
}
