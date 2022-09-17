package rs.advig.rest.service;

import java.util.List;

import rs.advig.rest.model.GrupaArtikala;

public interface GrupaArtikalaService {
	
	GrupaArtikala saveGrupaArtikala(GrupaArtikala grupaArtikala);
	List<GrupaArtikala> getAllGrupeArtikala();
	List<GrupaArtikala> getAllGrupeArtikalaByAuthId(String authId);
	GrupaArtikala getGrupaArtikalaById(long id);
	GrupaArtikala updateGrupaArtikala(GrupaArtikala grupaArtikala, long id);
	void deleteGrupaArtikala(long id);

}
