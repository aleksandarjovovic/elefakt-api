package rs.advig.rest.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Embeddable
@JsonInclude(value = Include.NON_NULL)
public class Options {
	

	
	@Column(name = "omitQRCodeGen")
	@ColumnDefault(value = "0")
	private String omitQRCodeGen;
	
	@Column(name = "omitTextualRepresentation")
	@ColumnDefault(value = "0")
	private String omitTextualRepresentation;
	
	@Column(name = "nazivKupca")
	private String nazivKupca;
	
	@Column(name = "emailToBuyer")
	private int emailToBuyer;
	
	@Column(name = "buyerEmailAddress")
	private String buyerEmailAddress;

	public String getOmitQRCodeGen() {
		return omitQRCodeGen;
	}

	public void setOmitQRCodeGen(String omitQRCodeGen) {
		this.omitQRCodeGen = omitQRCodeGen;
	}

	public String getOmitTextualRepresentation() {
		return omitTextualRepresentation;
	}

	public void setOmitTextualRepresentation(String omitTextualRepresentation) {
		this.omitTextualRepresentation = omitTextualRepresentation;
	}

	public int getEmailToBuyer() {
		return emailToBuyer;
	}

	public void setEmailToBuyer(int emailToBuyer) {
		this.emailToBuyer = emailToBuyer;
	}

	public String getBuyerEmailAddress() {
		return buyerEmailAddress;
	}

	public void setBuyerEmailAddress(String buyerEmailAddress) {
		this.buyerEmailAddress = buyerEmailAddress;
	}

	public String getNazivKupca() {
		return nazivKupca;
	}

	public void setNazivKupca(String nazivKupca) {
		this.nazivKupca = nazivKupca;
	}

	

}
