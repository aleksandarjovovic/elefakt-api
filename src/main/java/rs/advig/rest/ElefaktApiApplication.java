package rs.advig.rest;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import rs.advig.rest.model.Cert;
import rs.advig.rest.repository.CertRepository;

@SpringBootApplication
public class ElefaktApiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ElefaktApiApplication.class, args);
	}

}

@Component
class Initiator implements ApplicationRunner{
	
	private CertRepository cRepository;
	
	public Initiator(CertRepository cRepository) {
		this.cRepository = cRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Cert cert = new Cert();
		cert.setAtktivan(1);
		cert.setAuthId("b66c0f2c-908d-4e8a-ac68-568b2b94078b");
		cert.setNaziv("cert.pfx");
		cert.setPac("DA4VV7");
		cert.setSifra("EJUY7G6U");
		cRepository.save(cert);
		
		
		
	}
	
}
