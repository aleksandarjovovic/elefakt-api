package rs.advig.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {
	
	List<Response> findAllResponsesByAuthId(String authId);
	Response findResponseByRequestIdValue(String requestIdValue);

}
