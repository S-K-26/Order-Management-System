package com.project.OMS.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.OMS.Entity.Item;
import com.project.OMS.Repositpry.ItemRepository;
import com.project.OMS.Service.ItemServise;

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
	public Item getItemById(Long id) {
		return itemRepository.getById(id);
	}

	@Override
	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
		
	}

	@Override
	public Item updateItem(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

}
