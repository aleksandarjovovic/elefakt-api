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

import rs.advig.rest.model.GrupaArtikala;
import rs.advig.rest.service.GrupaArtikalaService;

@RestController
@RequestMapping("/api/grupeArtikala")
public class GrupaArtikalaController {

	GrupaArtikalaService gaService;

	public GrupaArtikalaController(GrupaArtikalaService gaService) {
		super();
		this.gaService = gaService;
	}

	// build create grupa artikala REST API
	@PostMapping("/create")
	public ResponseEntity<GrupaArtikala> saveGrupaArtikala(@RequestBody GrupaArtikala grupaArtikala, @RequestHeader("authId") String authId) {
		grupaArtikala.setAuthId(authId);
		return new ResponseEntity<GrupaArtikala>(gaService.saveGrupaArtikala(grupaArtikala), HttpStatus.CREATED);
	}

	// build get all grupe artikala by authId REST API
	@GetMapping("/getall")
	public List<GrupaArtikala> getAllGrupeArtikala(@RequestHeader("authId") String authId) {
		return gaService.getAllGrupeArtikalaByAuthId(authId);
	}

	// build get grupa artikala by id REST API
	@GetMapping("{id}")
	public ResponseEntity<GrupaArtikala> getGrupaArtikalaById(@PathVariable("id") long id) {
		return new ResponseEntity<GrupaArtikala>(gaService.getGrupaArtikalaById(id), HttpStatus.OK);
	}

	// build update grupa artikala REST API
	@PostMapping("/update/{id}")
	public ResponseEntity<GrupaArtikala> updateGrupaArtikala(@PathVariable("id") long id,
			@RequestBody GrupaArtikala grupaArtikala) {
		return new ResponseEntity<GrupaArtikala>(gaService.updateGrupaArtikala(grupaArtikala, id), HttpStatus.OK);
	}

	// build delete grupa artikala by id REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteGrupaArtikala(@PathVariable("id") long id) {
		gaService.deleteGrupaArtikala(id);
		return new ResponseEntity<String>("Grupa artikala deleted succesfully", HttpStatus.OK);
	}

}
