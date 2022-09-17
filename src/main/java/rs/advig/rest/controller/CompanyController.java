package rs.advig.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.advig.rest.model.Company;
import rs.advig.rest.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	CompanyService cService;

	public CompanyController(CompanyService cService) {
		super();
		this.cService = cService;
	}
	
	//build get company by authId REST API
	@GetMapping("{authId}")
	public ResponseEntity<Company> getCompanyByAuthId(@PathVariable("authId") String authId){
		return new ResponseEntity<Company>(cService.findCompanyBuAuthId(authId), HttpStatus.OK);
	}

}
