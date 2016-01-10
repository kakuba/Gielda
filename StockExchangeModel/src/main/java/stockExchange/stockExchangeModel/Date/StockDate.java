package stockExchange.stockExchangeModel.Date;

public class StockDate {
	private Integer yyyy;
	private Integer mm;
	private Integer dd;

	public StockDate(Integer yyyy, Integer mm, Integer dd) {
		this.yyyy = yyyy;
		this.mm = mm;
		this.dd = dd;
	}

	public Integer getYyyy() {
		return yyyy;
	}

	public void setYyyy(Integer yyyy) {
		this.yyyy = yyyy;
	}

	public Integer getMm() {
		return mm;
	}

	public void setMm(Integer mm) {
		this.mm = mm;
	}

	public Integer getDd() {
		return dd;
	}

	public void setDd(Integer dd) {
		this.dd = dd;
	}
	
	
	
}
