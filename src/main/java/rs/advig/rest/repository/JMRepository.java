package rs.advig.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.JM;

public interface JMRepository extends JpaRepository<JM, Long> {
	
	public List<JM> findAllByAuthId(String authId); //find all JM by authId

}
