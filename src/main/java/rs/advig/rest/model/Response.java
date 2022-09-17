package rs.advig.rest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "response")
@JsonInclude(value = Include.NON_EMPTY)
@Getter
@Setter
@JsonIgnoreProperties(value = { "encryptedInternalData", "signature", "district", "taxGroupRevision", "mrc", "locationName", "requestId", "authId"})
public class Response {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "requestId", referencedColumnName = "id")
	private Request requestId;
	
	@OneToMany(mappedBy = "response", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<TaxItems> taxItems;
	
	private String requestIdValue;
	
	private String requestedBy;
	
	private String sdcDateTime;
	
	private String invoiceCounter;
	
	private String invoiceCounterExtension;
	
	private String invoiceNumber;
	
	@Type(type = "text")
	private String verificationUrl;
	
	@Type(type = "text")
	private String verificationQRCode;
	
	@Type(type = "text")
	private String journal;
	
	private String signedBy;
	
	private String messages;
	
	private int totalCounter;
	
	private int transactionTypeCounter;
	
	private double totalAmount;
	
	private String businessName;
	
	private String tin;
	
	private String address;
	
	private int arhiviran;
	
	private String authId;
	
}
