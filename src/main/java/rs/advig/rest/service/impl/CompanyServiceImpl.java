package rs.advig.rest.service.impl;

import org.springframework.stereotype.Service;

import rs.advig.rest.exception.ResourceNotFoundException;
import rs.advig.rest.model.Company;
import rs.advig.rest.repository.CompanyRepository;
import rs.advig.rest.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	public Company save(Company sompany) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company findCompanyBuAuthId(String authId) {
		return companyRepository.findCompanyByAuthId(authId).orElseThrow(() -> new ResourceNotFoundException("Company", "authId", authId));
	}

}
