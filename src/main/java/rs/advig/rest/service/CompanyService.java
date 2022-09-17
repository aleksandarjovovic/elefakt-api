package rs.advig.rest.service;

import rs.advig.rest.model.Company;

public interface CompanyService {
	
	Company save(Company sompany);
	Company findCompanyBuAuthId(String authId);

}
