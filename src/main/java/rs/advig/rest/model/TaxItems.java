package rs.advig.rest.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@Table(name = "taxitems")
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(value = {"id"})
public class TaxItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int categoryType;
	
	private String label;
	
	private double amount;
	
	private double rate;
	
	private String categoryName;
	
	private String authId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "response_id", nullable = false)
	@JsonBackReference
	private Response response;

}
