package rs.advig.rest.service;

import java.util.List;

import rs.advig.rest.model.Item;

public interface ItemService {
	Item saveItem(Item item);
	List<Item> getAllItems();
	Item getItemById(long id);
	Item updateItem(Item item, long id);
	void deleteItem(long id);

}
