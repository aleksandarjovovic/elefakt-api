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

import rs.advig.rest.model.Request;
import rs.advig.rest.model.Response;
import rs.advig.rest.service.EmailService;
import rs.advig.rest.service.ItemService;
import rs.advig.rest.service.LabelsService;
import rs.advig.rest.service.RequestService;
import rs.advig.rest.service.ResponseService;

@RestController
@RequestMapping("/api/v1.0/invoice")
public class RequestController {

	RequestService requestService;
	ItemService itemService;
	LabelsService labelsService;
	ResponseService responseService;
	EmailService emailService;

	public RequestController(RequestService rService, ItemService iService, LabelsService lService, ResponseService respService, EmailService emailService) {
		super();
		this.requestService = rService;
		this.itemService = iService;
		this.labelsService = lService;
		this.responseService = respService;
		this.emailService = emailService;
	}

	@PostMapping
	public ResponseEntity<Response> saveInvoice(@RequestBody Request request, @RequestHeader("authId") String authId, @RequestHeader("requestId") String requestId ) {
		
		Response response = null;
		
		Response check = responseService.getResponseByRequestIdValue(requestId);
		if(check != null) {
			System.out.println(check.getRequestIdValue());
			return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
		} else {
			Request r = requestService.saveRequest(request, authId, requestId);
			response = responseService.contactTaxAuth(request);
			if(response != null && r.getOptions().getEmailToBuyer() == 1) {
				emailService.sendInvoice(response);
			}			
			return new ResponseEntity<Response>(response, HttpStatus.OK);
			
		}
	}

	@GetMapping("/all")
	public List<Response> getAllRequests(@RequestHeader("authId") String authId) {
		return responseService.getAllResponsesByAuthId(authId);

	}
	
	@GetMapping("/{requestId}")
	public ResponseEntity<Response> getByRequestId(@PathVariable("requestId") String requestId) {
		Response r = responseService.getResponseByRequestIdValue(requestId);
		if(r==null) {
			return new ResponseEntity<Response>(r, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Response>(r,HttpStatus.OK);

	}

}
