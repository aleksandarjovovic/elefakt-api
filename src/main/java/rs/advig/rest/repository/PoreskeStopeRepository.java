package rs.advig.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.PoreskeStope;

public interface PoreskeStopeRepository extends JpaRepository<PoreskeStope, Long> {
	
	List<PoreskeStope> findAllByAuthId(String authId);

}
