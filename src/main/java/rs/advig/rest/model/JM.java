package rs.advig.rest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "jm")
public class JM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "skr")
	private String skr;
	
	@Column(name = "authid")
	private String authId;	
	
	@OneToMany(mappedBy = "jmId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Artikal> artikli;

	public String getNaziv() {
		return naziv;
	}

	public long getId() {
		return id;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSkr() {
		return skr;
	}

	public void setSkr(String skr) {
		this.skr = skr;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}
	
}
