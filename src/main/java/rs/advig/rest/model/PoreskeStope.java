package rs.advig.rest.model;

import java.util.Date;
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
@Table(name = "poreskestope")
public class PoreskeStope {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "skracenica")
	private String skracenica;
	
	@Column(name = "oznaka")
	private String oznaka;
	
	@Column(name = "stopa")
	private double stopa;
	
	@Column(name="authid")
	private String authId;
	
	@Column(name="validnaOd")
	private Date validnaOd;
	
	@Column(name="validnaDo")
	private Date validnaDo;
	
	@OneToMany(mappedBy = "poreskeStopeId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Artikal> artikli;

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

	public String getSkracenica() {
		return skracenica;
	}

	public void setSkracenica(String skracenica) {
		this.skracenica = skracenica;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public double getStopa() {
		return stopa;
	}

	public void setStopa(double stopa) {
		this.stopa = stopa;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public Date getValidnaOd() {
		return validnaOd;
	}

	public void setValidnaOd(Date validnaOd) {
		this.validnaOd = validnaOd;
	}

	public Date getValidnaDo() {
		return validnaDo;
	}

	public void setValidnaDo(Date validnaDo) {
		this.validnaDo = validnaDo;
	}
	
	

}
