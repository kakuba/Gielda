package stockExchange.stockExchangeModel.To;

import java.sql.Date;


public class StockTo {
	private String CompanyName;
	private Date stockDate;
	private float value;
	
	public StockTo(String companyName, Date stockDate, float value) {
		this.CompanyName = companyName;
		this.stockDate = stockDate;
		this.value = value;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public Date getDate() {
		return stockDate;
	}

	public void setDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
	
}
