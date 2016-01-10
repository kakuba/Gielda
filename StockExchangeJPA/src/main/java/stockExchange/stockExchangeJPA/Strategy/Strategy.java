package stockExchange.stockExchangeJPA.Strategy;

import java.util.List;

import stockExchange.stockExchangeJPA.Stock.Stock;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;

public interface Strategy {
	List<TransportObject> giveAdvice(float money, List<Stock> stocks);
}
