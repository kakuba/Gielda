package stockExchange.stockExchangeJPA.StockBrokerInfo.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.stockExchangeJPA.StockBroker.StockBroker;
import stockExchange.stockExchangeJPA.StockBrokerInfo.StockBrokerInfo;
import stockExchange.stockExchangeModel.To.StockTo;

@Component
public class StockBrokerInfoImpl implements StockBrokerInfo {

	@Autowired
	private StockBroker stockBorker;
	
	@Override
	public List<StockTo> getStockToListByDate(Date fromDate, Date toDate) {
		List<StockTo> stockToList = stockBorker.getStockToListByDate(fromDate, toDate);
		return stockToList;
	}

	@Override
	public List<Date> getBeginAndEndDate() {
		List<Date> beginAndEndDate = stockBorker.getBeginAndEndDate();
		return beginAndEndDate;
	}
	
}
