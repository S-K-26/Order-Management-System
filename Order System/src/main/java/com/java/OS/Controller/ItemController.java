package com.java.OS.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.OS.Entity.Item;
import com.java.OS.Exception.ResourceNotFoundException;
import com.java.OS.Repository.ItemRepository;
import com.java.OS.Service.ItemService;

/**
 * Created Controller Class to handle request for retrieving data.
  
 * @RestController - It indicates that the annotated class is controller.
 * @CrossOrigin - Since we have used Angular for our front end, we have used @CrossOrigin for resource sharing. 
 * @Autowired - Used to inject the object dependency.
 * @PathVariable -It is used to handle template variables in the request URI mapping, and set them as 
 				  method parameters. 
   @RequestBody -Annotation maps the HttpRequest body to a transfer or domain object.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemRepository itemRepository;
	
	/**
	 * @GetMapping - Used to handle the HTTP GET requests matched with given expression.
	 * @return List of All items present in Database in Json format.
	 */
	@GetMapping("/items")
	public List<Item> listOfItems() {
		return itemService.getAllItems();
	}
	
	/**
	 * @PostMapping - Used to annotated classes handle the HTTP POST requests matched with given expression.
	 * @return item in json.
	 */
	@PostMapping("/items")
	public Item saveItem(@RequestBody Item item) {
		return itemService.saveItem(item);
	}
	
	/**
	 * @param itemId of item.
	 * @return item of provided itemId.
	 */
	@GetMapping("/items/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable Integer itemId) {
		return itemService.getItemBYId(itemId);
	}
	
	/**
	 * @PutMapping - Used to update/modify the source so @PutMapping annotation is used for 
	 				 mapping HTTP PUT requests onto specific handler methods.
	 * @param itemId of item
	 * @return updated item.
	 */
	@PutMapping("/items/{itemId}")
	public ResponseEntity<Item> updateItem(@PathVariable Integer itemId,@RequestBody Item item){
		return itemService.updateItem(itemId, item);
	}
	
	/**
	 * @DeleteMapping -Annotation maps HTTP DELETE requests onto specific handler methods.
	 * @param itemId of item.
	 * @return Boolean response(True/False) if item is deleted.
	 */
	@DeleteMapping("/items/{itemId}")
	public ResponseEntity<Map<String, Boolean>> deleteItemFromList(@PathVariable Integer itemId){
		Item item = itemRepository.findById(itemId).orElseThrow(() ->
		new ResourceNotFoundException("Item Not Found"));
		
		itemRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
