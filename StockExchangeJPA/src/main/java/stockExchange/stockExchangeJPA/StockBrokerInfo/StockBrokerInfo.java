package stockExchange.stockExchangeJPA.StockBrokerInfo;

import java.sql.Date;
import java.util.List;

import stockExchange.stockExchangeModel.To.StockTo;

public interface StockBrokerInfo {
	List<StockTo> getStockToListByDate(Date fromDate, Date toDate);
	List<Date> getBeginAndEndDate();
}
