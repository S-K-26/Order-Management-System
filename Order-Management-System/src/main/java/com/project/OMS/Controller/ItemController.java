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

@Controller
public class ItemController {
	
	@Autowired
	private ItemServise itemServise;
	
	// handler method to handle list item
	@GetMapping("/items")
	public String listOfItems(Model model) {
		model.addAttribute("items", itemServise.getAllItems());
		return "items";
	}
	
	@GetMapping("/items/edit")
	public String editPage(Model model) {
		model.addAttribute("items", itemServise.getAllItems());
		return "edit";	
	}
	
	@GetMapping("/items/new")
	public String createNewItem(Model model) {
		Item item = new Item();
		model.addAttribute("item", item);
		return "add-item";
	}
	
	@PostMapping("/items")
	public String saveItem(@ModelAttribute("item") Item item) {
		itemServise.saveItem(item);
		return "redirect:/items/edit";
	}
	
	@GetMapping("/items/edit_item/{id}")
	public String editListOfItems(@PathVariable Long id,Model model) {
		model.addAttribute("item",itemServise.getItemById(id));
		return "edit-item";
	}
	
	@PostMapping("/items/{id}")
	public String updateItem(@PathVariable Long id,
			@ModelAttribute("item") Item item,Model model) {
		//Will get item from database by id
		Item existingItem = itemServise.getItemById(id);
		existingItem.setItemId(id);
		existingItem.setName(item.getName());
		existingItem.setCost(item.getCost());
		
		// save Updated Item Object
		itemServise.updateItem(existingItem);
		return "redirect:/items/edit";
	}
	
	@GetMapping("/items/{id}")
	public String deleteItem(@PathVariable Long id) {
		itemServise.deleteItem(id);
		return "redirect:/items/edit";
	}
}
