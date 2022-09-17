package rs.advig.rest.service;

import java.util.List;

import rs.advig.rest.model.Options;


public interface OptionsService {
	
	Options saveOption(Options item);
	List<Options> getAllOptions();
	Options getOptionById(long id);
	Options updateOption(Options item, long id);
	void deleteOption(long id);

}
