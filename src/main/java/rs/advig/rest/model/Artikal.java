package rs.advig.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "artikli")
public class Artikal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "gtin")
	private String gtin;
	
	@Column(name = "sifra")
	private String sifra;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "cena")
	private double cena;
	
	@Column(name = "authid")
	private String authId;
	
	@ManyToOne
	@JoinColumn(name = "jmId")
	private JM jmId;
	
	@ManyToOne
	@JoinColumn(name = "poreskeStopeId")
	private PoreskeStope poreskeStopeId;
	
	@ManyToOne
	@JoinColumn(name = "grupaArtikalaId")
	private GrupaArtikala grupaArtikalaId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public JM getJmId() {
		return jmId;
	}

	public void setJmId(JM jmId) {
		this.jmId = jmId;
	}

	public PoreskeStope getPoreskeStopeId() {
		return poreskeStopeId;
	}

	public void setPoreskeStopeId(PoreskeStope poreskaStopaId) {
		this.poreskeStopeId = poreskaStopaId;
	}

	public GrupaArtikala getGrupaArtikalaId() {
		return grupaArtikalaId;
	}

	public void setGrupaArtikalaId(GrupaArtikala grupaArtikalaId) {
		this.grupaArtikalaId = grupaArtikalaId;
	}
	
	

}
