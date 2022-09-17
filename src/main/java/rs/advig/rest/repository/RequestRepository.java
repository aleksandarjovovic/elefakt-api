package rs.advig.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

	List<Request> findAllRequestsByAuthId(String authId);

	Request findRequestByRequestId(String requestId);
}
