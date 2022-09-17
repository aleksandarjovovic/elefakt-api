package rs.advig.rest.service;

import java.util.List;

import rs.advig.rest.model.Request;
import rs.advig.rest.model.Response;

public interface ResponseService {
	
	Response saveResponse(Response response, String authId, Request request);
	List<Response> getAllResponses();
	List<Response> getAllResponsesByAuthId(String authId);
	Response getResponseById(long id);
	Response getResponseByRequestIdValue(String requestIdValue);
	Response updateResponse(Response response, String requestIdValue);
	Response updateResponse(Response response, long requestId);
	Response contactTaxAuth(Request request);
	//byte[] getResponseAsByteStream(long responseId); 

}
