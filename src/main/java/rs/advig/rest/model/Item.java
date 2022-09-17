package rs.advig.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table(name="item")
@JsonInclude(value = Include.NON_NULL)
public class Item {
	
	public Item() {
		this.labels = new ArrayList<>();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "gtin")
	private String gtin;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "unitLabel")
	private String unitLabel; 
	
	@Column(name = "quantity")
	private double quantity;
	
	@Column(name = "unitPrice")
	private double unitPrice;
	
	@Column(name = "totalAmount")
	private double totalAmount;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Labels> labels;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "request_id", nullable = false)
	@JsonBackReference
	private Request request;
	
	public void addLabel(Labels label) {
		this.labels.add(label);
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	
	public List<Labels> getLabels() {
		return labels;
	}

	public void setLabels(List<Labels> labels) {
		this.labels = labels;
	}

	@JsonIgnore
	public Request getRequest() {
		return request;
	}

	
	public void setRequest(Request request) {
		this.request = request;
	}


	
	
	
	

}
