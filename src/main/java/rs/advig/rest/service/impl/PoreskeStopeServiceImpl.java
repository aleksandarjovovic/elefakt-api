package rs.advig.rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.advig.rest.exception.ResourceNotFoundException;
import rs.advig.rest.model.PoreskeStope;
import rs.advig.rest.repository.PoreskeStopeRepository;
import rs.advig.rest.service.PoreskeStopeService;

@Service
public class PoreskeStopeServiceImpl implements PoreskeStopeService{

private PoreskeStopeRepository psRepository;
	
	

	public PoreskeStopeServiceImpl(PoreskeStopeRepository psRepository) {
		super();
		this.psRepository = psRepository;
	}

	@Override
	public PoreskeStope savePoreskeStope(PoreskeStope poreskeStope) {
		return psRepository.save(poreskeStope); 
	}

	@Override
	public List<PoreskeStope> getAllPoreskeStope() {
		return psRepository.findAll();
	}
	
	@Override
	public List<PoreskeStope> getAllPoreskeStopeByAuthId(String authId) {
		return psRepository.findAllByAuthId(authId);
	}

	@Override
	public PoreskeStope getPoreskeStopeById(long id) {
		return psRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Poreske Stope", "id", id));
	}

	@Override
	public PoreskeStope updatePoreskeStope(PoreskeStope poreskeStope, long id) {
		
		PoreskeStope existingPoreskeStope = psRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Poreske Stope", "id", id));
		
		existingPoreskeStope.setAuthId(poreskeStope.getAuthId());
		existingPoreskeStope.setNaziv(poreskeStope.getNaziv());
		existingPoreskeStope.setOznaka(poreskeStope.getOznaka());
		existingPoreskeStope.setSkracenica(poreskeStope.getSkracenica());
		existingPoreskeStope.setStopa(poreskeStope.getStopa());
		psRepository.save(existingPoreskeStope);
		return existingPoreskeStope;
	}

	@Override
	public void deletePoreskeStope(long id) {
		
		psRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Poreske Stope", "id", id));
		psRepository.deleteById(id);

	}

	

}
