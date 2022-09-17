package rs.advig.rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.advig.rest.exception.ResourceNotFoundException;
import rs.advig.rest.model.JM;
import rs.advig.rest.repository.JMRepository;
import rs.advig.rest.service.JMService;

@Service
public class JMServiceImpl implements JMService {

	private JMRepository jmRepository;
	
	
	
	public JMServiceImpl(JMRepository jmRepository) {
		super();
		this.jmRepository = jmRepository;
	}

	@Override
	public JM saveJM(JM jm) {	
		return jmRepository.save(jm);
	}
	
	@Override
	public List<JM> getAllJM() {
		return jmRepository.findAll();
	}

	@Override
	public List<JM> getAllJMByAuthId(String authId) {
		return jmRepository.findAllByAuthId(authId);
	}

	@Override
	public JM getJMById(long id) {
		return jmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Jedinica mere", "id", id));
	}

	@Override
	public JM updateJM(JM jm, long id) {
		JM existingJM = jmRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Jedinica mere", "id", id));
		
		existingJM.setNaziv(jm.getNaziv());
		existingJM.setSkr(jm.getSkr());
		existingJM.setAuthId(jm.getAuthId());
		jmRepository.save(existingJM);
		return existingJM;
	}

	@Override
	public void deleteJM(long id) {
		jmRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Jedinica mere", "id", id));
		
		jmRepository.deleteById(id);

	}


}
