package stockExchange.stockExchangeJPA.Wallet;

import java.util.ArrayList;
import java.util.List;


import stockExchange.stockExchangeJPA.Money.Money;
import stockExchange.stockExchangeJPA.Stock.Stock;


public class Wallet {
	
	private Money money = new Money(0, 0);
	private List<Stock> stocks = new ArrayList<>();
	
	
	
	public Wallet(Money money, List<Stock> stocks) {
		this.money = money;
		this.stocks = stocks;
	}
	public Money getMoney() {
		return money;
	}
	public void addMoney(Money profitMoney) {
		this.money.addMoney(profitMoney);
	}
	public List<Stock> getStocks() {
		return stocks;
	}
	public void addStocks(List<Stock> profitStocks) {
		this.stocks = profitStocks;
		
	}
	

}