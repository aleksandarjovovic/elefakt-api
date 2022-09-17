package rs.advig.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.GrupaArtikala;

public interface GrupaArtikalaRepository extends JpaRepository<GrupaArtikala, Long> {
	
	public List<GrupaArtikala> findAllByAuthId(String authId);

}
