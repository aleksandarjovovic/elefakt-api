package rs.advig.rest.service;

import java.util.List;

import rs.advig.rest.model.Request;


public interface RequestService {
	
	Request saveRequest(Request request, String authId, String requestId);
	List<Request> getAllRequests();
	List<Request> getAllRequestsByAuthId(String authId);
	Request getRequestById(long id);
	Request updateRequest(Request request, long id);
	void deleteRequest(long id);
	Request getRequestByRequestId(String requestId);

}
