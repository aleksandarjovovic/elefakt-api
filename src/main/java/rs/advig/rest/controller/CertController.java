package rs.advig.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.advig.rest.model.Cert;
import rs.advig.rest.service.CertService;

@RestController
@RequestMapping("/api/cert")
public class CertController {
	
	CertService certService;

	public CertController(CertService certService) {
		super();
		this.certService = certService;
	}
	
	
	// build get cert by authId REST API
	@GetMapping("/{authId}")
	public Cert getCertByAuthId(@PathVariable("authId") String authId) {
		return certService.findCertByAuthId(authId);
	}
	
}
