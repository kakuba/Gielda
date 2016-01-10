package stockExchange.stockExchangeJPA.StockBroker;

import java.sql.Date;
import java.util.List;

import stockExchange.stockExchangeJPA.TransportObject.Payment;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;
import stockExchange.stockExchangeModel.To.StockTo;

public interface StockBroker {
	List<StockTo> getStockToListByDate(Date fromDate, Date toDate);
	Payment buyStocks(TransportObject transportObject);
	Payment sellStocks(TransportObject transportObject);
	List<Date> getBeginAndEndDate();
	
}
