package rs.advig.rest.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "request")
@JsonInclude(value = Include.NON_NULL)
public class Request {
	
	public Request() {
		this.items = new ArrayList<>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "requestId"/*, unique = true, nullable = false*/)
	private String requestId;
	
	
	@Column(name = "dateAndTimeOfIssue")
	private String dateAndTimeOfIssue;
	
	@Column(name = "cashier", nullable = false)
	private String cashier;
	
	@Column(name = "buyerId")
	private String buyerId;
	
	@Column(name = "buyerCostCenterId")
	private String buyerCostCenterId;
	
	@Column(name = "invoiceType", nullable = false)
	private int invoiceType;
	
	@Column(name = "transactionType", nullable = false)
	private int transactionType;
	
	@Column(name = "invoiceNumber")
	private String invoiceNumber;
	
	@Column(name = "referentDocumentNumber")
	private String referentDocumentNumber;
	
	@Column(name = "referentDocumentDT")
	private String referentDocumentDT;
	
	@Embedded
    private Options options;
	
	@Column(name = "authId")
	private String authId;
	
	@Column(name = "prepaidAmount")
	private double prepaidAmount;
	
	@Column(name = "prepaidTax")
	private double prepaidTax;
	
	@Column(name = "payableAmount")
	private double payableAmount;
	
	@OneToMany(mappedBy = "request",  cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Item> items;
	
	@OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Payment> payment;
	
	@Column(name = "creationTime")
	@CreationTimestamp
	private LocalDateTime creationTime;
	
	public void addItem(Item item){
		this.items.add(item);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getDateAndTimeOfIssue() {
		return dateAndTimeOfIssue;
	}

	public void setDateAndTimeOfIssue(String dateAndTimeOfIssue) {
		this.dateAndTimeOfIssue = dateAndTimeOfIssue;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerCostCenterId() {
		return buyerCostCenterId;
	}

	public void setBuyerCostCenterId(String buyerCostCenterId) {
		this.buyerCostCenterId = buyerCostCenterId;
	}

	public int getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getReferentDocumentNumber() {
		return referentDocumentNumber;
	}

	public void setReferentDocumentNumber(String referentDocumentNumber) {
		this.referentDocumentNumber = referentDocumentNumber;
	}

	public String getReferentDocumentDT() {
		return referentDocumentDT;
	}

	public void setReferentDocumentDT(String referentDocumentDT) {
		this.referentDocumentDT = referentDocumentDT;
	}


	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public Options getOptions() {
		return options;
	}

	public void setOption(Options options) {
		this.options = options;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setOptions(Options options) {
		this.options = options;
	}
	
	
	
	
	
}
