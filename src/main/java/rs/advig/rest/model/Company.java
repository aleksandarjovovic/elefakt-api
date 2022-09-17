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
@Table(name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "adresa")
	private String adresa;
	
	@Column(name = "pib")
	private String pib;
	
	@Column(name = "mb")
	private String mb;
	
	@Column(name = "kontaktOsoba")
	private String kontaktOsoba;
	
	@Column(name = "telefon")
	private String telefon;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "authId")
	private String authId;
	
	@Column(name = "aktivna")
	private int aktivna;

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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getMb() {
		return mb;
	}

	public void setMb(String mb) {
		this.mb = mb;
	}

	public String getKontaktOsoba() {
		return kontaktOsoba;
	}

	public void setKontaktOsoba(String kontaktOsoba) {
		this.kontaktOsoba = kontaktOsoba;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public int getAktivna() {
		return aktivna;
	}

	public void setAktivna(int aktivna) {
		this.aktivna = aktivna;
	}
	

}
