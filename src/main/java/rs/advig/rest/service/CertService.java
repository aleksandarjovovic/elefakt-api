package rs.advig.rest.service;

import rs.advig.rest.model.Cert;

public interface CertService {
	
	Cert save(Cert cert);
	Cert findCertByAuthId(String authId);
	

}
