package rs.advig.rest.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.advig.rest.service.StatusService;

@RestController
@RequestMapping("/api/v1.0/status")
public class StatusController {
	
	StatusService sService;
		
	public StatusController(StatusService sService) {
		super();
		this.sService = sService;
	}

	//build status REST API
	@GetMapping
	public ResponseEntity<String> status(@RequestHeader("authId") String authId){
		JSONObject response = new JSONObject();	
		int respCode = sService.status(authId);
		if(respCode == 200) {			
			response.put("message", "Everything is OK");
			return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
		} else {
			response.put("message", "V-SDC is unavailable");
			return new ResponseEntity<String>(response.toString(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		
	}

}
