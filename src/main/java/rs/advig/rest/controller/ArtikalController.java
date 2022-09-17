package rs.advig.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.advig.rest.model.Artikal;
import rs.advig.rest.service.ArtikalService;
import rs.advig.rest.service.GrupaArtikalaService;
import rs.advig.rest.service.JMService;
import rs.advig.rest.service.PoreskeStopeService;

@RestController
@RequestMapping("/api/artikal")
public class ArtikalController {
	
	ArtikalService aService;
	JMService jmService;
	GrupaArtikalaService gaService;
	PoreskeStopeService psService;
		
	public ArtikalController(ArtikalService aService, JMService jmService, GrupaArtikalaService gaService,
			PoreskeStopeService psService) {
		super();
		this.aService = aService;
		this.jmService = jmService;
		this.gaService = gaService;
		this.psService = psService;
	}

	// build create artikal REST API
	@PostMapping("/create")
	public ResponseEntity<Artikal> saveArtikal(@RequestBody Artikal artikal,  @RequestHeader("authId") String authId){
		artikal.setAuthId(authId);
		artikal.setJmId(jmService.getJMById(artikal.getJmId().getId()));
		artikal.setGrupaArtikalaId(gaService.getGrupaArtikalaById(artikal.getGrupaArtikalaId().getId()));
		artikal.setPoreskeStopeId(psService.getPoreskeStopeById(artikal.getPoreskeStopeId().getId()));
		return new ResponseEntity<Artikal>(aService.saveArtikal(artikal), HttpStatus.CREATED);
	}
	// build get all artikal REST API
	@GetMapping("/getall")
	public List<Artikal> getAllArtikal(@RequestHeader("authId") String authId){
		return aService.getAllArtikliByAuthId(authId);
	}
	
	// build get artikal by id REST API
	@GetMapping("{id}")
	public ResponseEntity<Artikal> getArtikalById(@PathVariable("id") long id){
		return new ResponseEntity<Artikal>(aService.getArtikalById(id),HttpStatus.OK);
	}
	
	
	
	

}
