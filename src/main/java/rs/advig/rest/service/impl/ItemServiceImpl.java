package rs.advig.rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.advig.rest.exception.ResourceNotFoundException;
import rs.advig.rest.model.Item;
import rs.advig.rest.repository.ItemRepository;
import rs.advig.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	private ItemRepository itemRepository;
	
	

	public ItemServiceImpl(ItemRepository itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}

	@Override
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	@Override
	public Item getItemById(long id) {
		return itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
	}

	@Override
	public Item updateItem(Item item, long id) {
		Item existingItem = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
		return itemRepository.save(existingItem);
	}

	@Override
	public void deleteItem(long id) {
		// TODO Auto-generated method stub

	}

}
