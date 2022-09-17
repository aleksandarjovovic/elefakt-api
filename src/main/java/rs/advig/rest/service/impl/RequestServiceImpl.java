package rs.advig.rest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import rs.advig.rest.exception.ResourceNotFoundException;
import rs.advig.rest.model.Request;
import rs.advig.rest.repository.RequestRepository;
import rs.advig.rest.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
	
	private RequestRepository requestRepository;
	
	

	public RequestServiceImpl(RequestRepository requestRepository) {
		super();
		this.requestRepository = requestRepository;
	}

	@Override
	public Request saveRequest(Request request, String authId, String requestId) {
		request.setAuthId(authId);
		request.setRequestId(requestId);
		request = requestRepository.save(request);
		
		return request;
	}

	@Override
	public List<Request> getAllRequests() {
		return requestRepository.findAll();
	}

	@Override
	public List<Request> getAllRequestsByAuthId(String authId) {
		
		return requestRepository.findAllRequestsByAuthId(authId);
	}

	@Override
	public Request getRequestById(long id) {
		return requestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Request", "id", id));
	}

	@Override
	public Request updateRequest(Request request, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRequest(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Request getRequestByRequestId(String requestId) {
		return requestRepository.findRequestByRequestId(requestId);
		
	}

}
