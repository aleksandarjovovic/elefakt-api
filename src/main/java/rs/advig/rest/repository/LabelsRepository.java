package rs.advig.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.Labels;

public interface LabelsRepository extends JpaRepository<Labels, Long> {
	
}
