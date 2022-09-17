package rs.advig.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.advig.rest.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	Optional<Company> findCompanyByAuthId(String authId);

}
