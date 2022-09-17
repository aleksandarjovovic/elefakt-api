package rs.advig.rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.advig.rest.exception.ResourceNotFoundException;
import rs.advig.rest.model.Artikal;
import rs.advig.rest.repository.ArtikalRepository;
import rs.advig.rest.service.ArtikalService;

@Service
public class ArtikalServiceImpl implements ArtikalService {
	
	private ArtikalRepository artikalRepository;
	
	

	public ArtikalServiceImpl(ArtikalRepository artikalRepository) {
		super();
		this.artikalRepository = artikalRepository;
	}

	@Override
	public Artikal saveArtikal(Artikal artikal) {
		return artikalRepository.save(artikal);
	}

	@Override
	public List<Artikal> getAllArtikli() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Artikal> getAllArtikliByAuthId(String authId) {
		return artikalRepository.findAllByAuthId(authId);
	}

	@Override
	public Artikal getArtikalById(long id) {
		return artikalRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Artikal", "id", id));
	}

	@Override
	public Artikal updateArtikal(Artikal artikal, long id) {
		Artikal existingArtikal = artikalRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Artikal", "id", id));
		
		existingArtikal.setGtin(artikal.getGtin());
		existingArtikal.setSifra(artikal.getSifra());
		existingArtikal.setNaziv(artikal.getNaziv());
		existingArtikal.setCena(artikal.getCena());
		existingArtikal.setAuthId(artikal.getAuthId());
		existingArtikal.setJmId(artikal.getJmId());
		existingArtikal.setPoreskeStopeId(artikal.getPoreskeStopeId());
		existingArtikal.setGrupaArtikalaId(artikal.getGrupaArtikalaId());
		
		artikalRepository.save(existingArtikal);
		return existingArtikal;

	}

	@Override
	public void deleteArtikal(long id) {
		// TODO Auto-generated method stub

	}

	

}
