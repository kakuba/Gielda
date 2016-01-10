package stockExchange.stockExchangeJPA.Stock;


public class Stock {
	private String companyName;
	private int numberOfStock = 0;
	
	public Stock(){
	}
	
	public Stock(String companyName, int numberOfStock) {
		this.companyName = companyName;
		this.numberOfStock = numberOfStock;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getNumberOfStock() {
		return numberOfStock;
	}

	public void setNumberOfStock(int numberOfStock) {
		this.numberOfStock = numberOfStock;
	}
	
	public void addNumberOfStock(int profitStock) {
		this.numberOfStock += profitStock;
	}
	
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object instanceof Stock && ((Stock)object).getCompanyName().equals(companyName)) {
			result = true;
		}
		return result;
	}
	
	
}
