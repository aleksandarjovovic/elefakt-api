package rs.advig.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	

}
