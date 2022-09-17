package rs.advig.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.advig.rest.model.PoreskeStope;
import rs.advig.rest.service.PoreskeStopeService;

@RestController
@RequestMapping("/api/v1.0/taxRates")
public class PoreskeStopeController {

	PoreskeStopeService psService;

	public PoreskeStopeController(PoreskeStopeService psService) {
		super();
		this.psService = psService;
	}

	// build create poreske stope REST API
	@PostMapping("/create")
	public ResponseEntity<PoreskeStope> savePoreskeStope(@RequestBody PoreskeStope poreskeStope, @RequestHeader("authId") String authId) {
		poreskeStope.setAuthId(authId);
		return new ResponseEntity<PoreskeStope>(psService.savePoreskeStope(poreskeStope), HttpStatus.CREATED);
	}

	// build get all poreske stope REST API
	@GetMapping("/authId")
	public List<PoreskeStope> getAllPoreskeStope(@RequestHeader("authId") String authid) {
		return psService.getAllPoreskeStopeByAuthId(authid);
	}
	
	@GetMapping
	public List<PoreskeStope>getAllPoreskeStope(){
		return psService.getAllPoreskeStope();
	}

	// build get poreske stope by id REST API
	@GetMapping("{id}")
	public ResponseEntity<PoreskeStope> getPoreskeStopeById(@PathVariable("id") long id) {
		return new ResponseEntity<PoreskeStope>(psService.getPoreskeStopeById(id), HttpStatus.OK);
	}

	// build update grupa artikala REST API
	@PostMapping("/update/{id}")
	public ResponseEntity<PoreskeStope> updateGrupaArtikala(@PathVariable("id") long id,
			@RequestBody PoreskeStope poreskeStope) {
		return new ResponseEntity<PoreskeStope>(psService.updatePoreskeStope(poreskeStope, id), HttpStatus.OK);
	}

	// build delete grupa artikala by id REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePoreskeStope(@PathVariable("id") long id) {
		psService.deletePoreskeStope(id);
		return new ResponseEntity<String>("Poreska Stopa deleted succesfully", HttpStatus.OK);
	}

}
