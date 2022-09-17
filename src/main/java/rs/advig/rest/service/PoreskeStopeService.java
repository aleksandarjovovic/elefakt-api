package rs.advig.rest.service;

import rs.advig.rest.model.PoreskeStope;

import java.util.List;


public interface PoreskeStopeService {
	
	PoreskeStope savePoreskeStope(PoreskeStope ps);
	List<PoreskeStope> getAllPoreskeStope();
	List<PoreskeStope> getAllPoreskeStopeByAuthId(String authId);
	PoreskeStope getPoreskeStopeById(long id);
	PoreskeStope updatePoreskeStope(PoreskeStope ps, long id);
	void deletePoreskeStope(long id);	

}
