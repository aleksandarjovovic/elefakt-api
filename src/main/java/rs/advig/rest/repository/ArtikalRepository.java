package rs.advig.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.Artikal;

public interface ArtikalRepository extends JpaRepository<Artikal, Long> {
	
	List<Artikal> findAllByAuthId(String authId);

}
