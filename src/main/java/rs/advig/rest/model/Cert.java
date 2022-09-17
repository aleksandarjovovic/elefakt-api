package rs.advig.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "certs")
public class Cert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "sifra")
	private String sifra;
	
	@Column(name = "pac")
	private String pac;
	
	@Column(name = "aktivan")
	private int atktivan;
	
	@Column(name = "authId")
	private String authId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getPac() {
		return pac;
	}

	public void setPac(String pac) {
		this.pac = pac;
	}

	public int getAtktivan() {
		return atktivan;
	}

	public void setAtktivan(int atktivan) {
		this.atktivan = atktivan;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}
	
	
	

}
