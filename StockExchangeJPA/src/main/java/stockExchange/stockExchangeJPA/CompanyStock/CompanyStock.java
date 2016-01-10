package stockExchange.stockExchangeJPA.CompanyStock;

public class CompanyStock {
	private String companyName = "";
	private int numberOfOccurrences = 0;
	private float currentValue;
	
	public CompanyStock() {
		
	}

	public CompanyStock(String companyName, int numberOfOccurrences, float currentValue) {
		this.companyName = companyName;
		this.numberOfOccurrences = numberOfOccurrences;
		this.currentValue = currentValue;
	}
	
	public CompanyStock(String companyName, float currentValue) {
		this.companyName = companyName;
		this.currentValue = currentValue;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getNumberOfOccurrences() {
		return numberOfOccurrences;
	}

	public void setNumberOfOccurrences(int numberOfOccurrences) {
		this.numberOfOccurrences = numberOfOccurrences;
	}

	public float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}
	
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object instanceof CompanyStock && ((CompanyStock)object).getCompanyName().equals(companyName)) {
			result = true;
		}
		return result;
	}
}
