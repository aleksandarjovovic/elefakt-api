package rs.advig.rest.service;

import java.util.List;

import rs.advig.rest.model.JM;

public interface JMService {
	
	JM saveJM(JM jm);
	List<JM> getAllJMByAuthId(String authId);
	List<JM> getAllJM();
	JM getJMById(long id);
	JM updateJM(JM jm, long id);
	void deleteJM(long id);

}
