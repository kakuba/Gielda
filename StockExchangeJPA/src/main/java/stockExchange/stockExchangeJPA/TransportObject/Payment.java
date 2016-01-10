package stockExchange.stockExchangeJPA.TransportObject;

public class Payment {
	private float money;
	private int stocks;

	public Payment() {
	}
	public Payment(float money, int stocks) {
		this.money = money;
		this.stocks = stocks;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public int getStocks() {
		return stocks;
	}

	public void setStocks(int stocks) {
		this.stocks = stocks;
	}
	
	
}
