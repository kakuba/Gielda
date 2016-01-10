package stockExchange.stockExchangeModel.Entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "STOCKS")
public class StockEntity {
	@Id
	@Column(name = "id", length = 11, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "stock_date")
	private Date date;
	@Column(name = "value", columnDefinition = "FLOAT(6,2)")
	private float value;
	
	public StockEntity() {
	}
	
	public StockEntity(String companyName, Date date, float value) {
		this.companyName = companyName;
		this.date = date;
		this.value = value;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	
	
}
