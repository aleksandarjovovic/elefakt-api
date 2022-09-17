package rs.advig.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.advig.rest.model.JM;
import rs.advig.rest.service.JMService;

@RestController
@RequestMapping("/api/jm")
@CrossOrigin("http://localhost:8081")
public class JMController {
	
	private JMService jmService;

	
	public JMController(JMService jmService) {
		super();
		this.jmService = jmService;
	}
	
	//build create jm REST API
	@PostMapping("/create")
	public ResponseEntity<JM> saveJM(@RequestBody JM jm, @RequestHeader("authId") String authid){
		jm.setAuthId(authid);
		return new ResponseEntity<JM>(jmService.saveJM(jm), HttpStatus.CREATED);
	}
	
	// build get all jm REST API
	@GetMapping("/getall")
	public List<JM> getAllJM(@RequestHeader("authId") String authId){
		return jmService.getAllJMByAuthId(authId);
	}
	
	// build get all jm REST API
		@GetMapping("/getallx")
		public List<JM> getAllJMx(){
			return jmService.getAllJM();
		}
	
	
	// build get jm by id REST API
	@GetMapping("{id}")
	public ResponseEntity<JM> getJMById(@PathVariable("id") long JMid){
		return new ResponseEntity<JM>(jmService.getJMById(JMid),HttpStatus.OK);
	}
	
	// build update jm REST API
	@PostMapping("/update/{id}")
	public ResponseEntity<JM> updateJM(@PathVariable("id") long id, @RequestBody JM jm){
		return new ResponseEntity<JM>(jmService.updateJM(jm, id),HttpStatus.OK);
	}
	
	// build delete jm REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteJM(@PathVariable("id") long id){
		jmService.deleteJM(id);
		return new ResponseEntity<String>("JM deleted succesfully", HttpStatus.OK);
	}

}
