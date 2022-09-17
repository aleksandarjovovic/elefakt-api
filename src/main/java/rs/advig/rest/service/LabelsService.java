package rs.advig.rest.service;

import java.util.List;

import rs.advig.rest.model.Labels;


public interface LabelsService {
	
	Labels saveLabels(Labels labels);
	List<Labels> getAllLabels();
	Labels getLabelsById(long id);
	Labels updateLabels(Labels label, long id);
	void deleteLabels(long id);

}
