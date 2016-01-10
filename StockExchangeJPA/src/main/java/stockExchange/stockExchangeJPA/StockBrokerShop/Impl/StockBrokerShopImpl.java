package stockExchange.stockExchangeJPA.StockBrokerShop.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.stockExchangeJPA.StockBroker.StockBroker;
import stockExchange.stockExchangeJPA.StockBrokerShop.StockBrokerShop;
import stockExchange.stockExchangeJPA.TransportObject.Payment;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;

@Component
public class StockBrokerShopImpl implements StockBrokerShop {
	
	@Autowired
	private StockBroker stockBroker;
	
	@Override
	public Payment buyStocks(TransportObject transportObject) {
		Payment payment = stockBroker.buyStocks(transportObject);
		return payment;
	}

	@Override
	public Payment sellStocks(TransportObject transportObject) {
		Payment payment = stockBroker.sellStocks(transportObject);
		return payment;
	}
	
}
