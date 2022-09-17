package rs.advig.rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.advig.rest.exception.ResourceNotFoundException;
import rs.advig.rest.model.GrupaArtikala;
import rs.advig.rest.repository.GrupaArtikalaRepository;
import rs.advig.rest.service.GrupaArtikalaService;

@Service
public class GrupaArtikalaServiceImpl implements GrupaArtikalaService {
	
	private GrupaArtikalaRepository gaRepository;
	
	

	public GrupaArtikalaServiceImpl(GrupaArtikalaRepository gaRepository) {
		super();
		this.gaRepository = gaRepository;
	}

	@Override
	public GrupaArtikala saveGrupaArtikala(GrupaArtikala grupaArtikala) {
		return gaRepository.save(grupaArtikala); 
	}
	
	@Override
	public List<GrupaArtikala> getAllGrupeArtikalaByAuthId(String authId) {
		return gaRepository.findAllByAuthId(authId);
	}


	@Override
	public List<GrupaArtikala> getAllGrupeArtikala() {
		return gaRepository.findAll();
	}

	@Override
	public GrupaArtikala getGrupaArtikalaById(long id) {
		return gaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Grupa artikala", "id", id));
	}

	@Override
	public GrupaArtikala updateGrupaArtikala(GrupaArtikala grupaArtikala, long id) {
		
		GrupaArtikala existingGrupaArtikala = gaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Grupa artikala", "id", id));
		
		existingGrupaArtikala.setAuthId(grupaArtikala.getAuthId());
		existingGrupaArtikala.setNaziv(grupaArtikala.getNaziv());
		gaRepository.save(existingGrupaArtikala);
		return existingGrupaArtikala;
	}

	@Override
	public void deleteGrupaArtikala(long id) {
		
		gaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Grupa Artikala", "id", id));
		
		gaRepository.deleteById(id);

	}

	
}
