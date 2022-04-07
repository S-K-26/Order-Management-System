package com.project.OMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.project.OMS.Entity.Item;
import com.project.OMS.Service.ItemServise;
/*
 * Created Controller Class to handle request for retrieving data.
  
 * @Controller - It indicates that the annotated class is controller 
 * @Autowired - Used to inject the object dependency.
 * @GetMapping - Used to handle the HTTP GET requests matched with given expression.
 * @PostMapping - Used to annotated classes handle the HTTP POST requests matched with given expression.
 * @PathVariable -It is used to handle template variables in the request URI mapping, and set them as 
 				  method parameters.
 * @@ModelAttribute - Used to bind the object "item" to item entity . Used to retrieve the argument from the model. 				  
  
 * Since we have used thymleaf for our forntend part We have returned views below such as items,edit 
   etc. which are shown in the src/main/resources/templates. 
   
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemServise itemServise;
	
	/*
	  Handler method to handle list item //Will get all the items from database.
	 */	
	@GetMapping("/items")
	public String listOfItems(Model model) {
		model.addAttribute("items", itemServise.getAllItems());
		return "items";
	}
	
	//Handler method to go to item editing page.
	@GetMapping("/items/edit")
	public String editPage(Model model) {
		model.addAttribute("items", itemServise.getAllItems());
		return "edit";	
	}
	
	// handles Request for adding new item.
	@GetMapping("/items/new")
	public String createNewItem(Model model) {
		Item item = new Item();
		model.addAttribute("item", item);
		return "add-item";
	}
	
	// to save added item
	@PostMapping("/items")
	public String saveItem(@ModelAttribute("item") Item item) {
		itemServise.saveItem(item);
		return "redirect:/items/edit";
	}
	
	// to edit already added item
	@GetMapping("/items/edit_item/{id}")
	public String editListOfItems(@PathVariable Integer id,Model model) {
		model.addAttribute("item",itemServise.getItemById(id));
		return "edit-item";
	}
	
	@PostMapping("/items/{id}")
	public String updateItem(@PathVariable Integer id,
			@ModelAttribute("item") Item item,Model model) {
		
		//Will get item from database by id
		
		Item existingItem = itemServise.getItemById(id);
		existingItem.setId(id);
		existingItem.setName(item.getName());
		existingItem.setCost(item.getCost());
		
		// save Updated Item Object
		
		itemServise.updateItem(existingItem);
		return "redirect:/items/edit";
	}
	
	// to delete the item.
	@GetMapping("/items/{id}")
	public String deleteItem(@PathVariable Integer id) {
		itemServise.deleteItem(id);
		return "redirect:/items/edit";
	}
}
