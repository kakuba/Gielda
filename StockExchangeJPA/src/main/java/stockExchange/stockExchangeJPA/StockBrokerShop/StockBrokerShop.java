package stockExchange.stockExchangeJPA.StockBrokerShop;

import stockExchange.stockExchangeJPA.TransportObject.Payment;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;

public interface StockBrokerShop {
	Payment buyStocks(TransportObject transportObject);
	Payment sellStocks(TransportObject transportObject);
}
