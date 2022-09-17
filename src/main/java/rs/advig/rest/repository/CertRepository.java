package rs.advig.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.Cert;

public interface CertRepository extends JpaRepository<Cert, Long> {
	
	Cert findCertByAuthId(String authId);

}
