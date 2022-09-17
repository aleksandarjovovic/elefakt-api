package rs.advig.rest.service;

import java.util.List;

import rs.advig.rest.model.Artikal;

public interface ArtikalService {
	
	Artikal saveArtikal(Artikal artikal);
	List<Artikal> getAllArtikli();
	List<Artikal> getAllArtikliByAuthId(String authId);
	Artikal getArtikalById(long id);
	Artikal updateArtikal(Artikal artikal, long id);
	void deleteArtikal(long id);

}
