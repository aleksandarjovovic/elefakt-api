package rs.advig.rest.service.impl;

import org.springframework.stereotype.Service;

import rs.advig.rest.model.Cert;
import rs.advig.rest.repository.CertRepository;
import rs.advig.rest.service.CertService;

@Service
public class CertServiceImpl implements CertService {
	
	CertRepository certRepository;
	
	

	public CertServiceImpl(CertRepository certRepository) {
		super();
		this.certRepository = certRepository;
	}

	@Override
	public Cert save(Cert cert) {
		return certRepository.save(cert);
	}

	@Override
	public Cert findCertByAuthId(String authId) {
		return certRepository.findCertByAuthId(authId);
	}

}
