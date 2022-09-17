package rs.advig.rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.advig.rest.exception.ResourceNotFoundException;
import rs.advig.rest.model.Labels;
import rs.advig.rest.repository.LabelsRepository;
import rs.advig.rest.service.LabelsService;

@Service
public class LabelsServiceImpl implements LabelsService {
	
	private LabelsRepository lRepository;
	
	

	public LabelsServiceImpl(LabelsRepository lRepository) {
		super();
		this.lRepository = lRepository;
	}

	@Override
	public Labels saveLabels(Labels labels) {
		return lRepository.save(labels);
	}

	@Override
	public List<Labels> getAllLabels() {
		return lRepository.findAll();
	}

	@Override
	public Labels getLabelsById(long id) {
		return lRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Label", "id", id));
	}

	@Override
	public Labels updateLabels(Labels label, long id) {
		Labels existingLabel = lRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Label", "id", id));
		
	
		
		return lRepository.save(existingLabel);
	}

	@Override
	public void deleteLabels(long id) {
		// TODO Auto-generated method stub
		
	}

}
