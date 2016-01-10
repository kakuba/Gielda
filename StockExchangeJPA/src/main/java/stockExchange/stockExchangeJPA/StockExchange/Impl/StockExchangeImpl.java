package stockExchange.stockExchangeJPA.StockExchange.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.stockExchangeJPA.Service.StockService;
import stockExchange.stockExchangeJPA.StockExchange.StockExchange;
import stockExchange.stockExchangeModel.To.StockTo;

@Component
public class StockExchangeImpl implements StockExchange {
	
	@Autowired
	private StockService stockService;
	
	@Override
	public List<StockTo> getStockListByDate(Date fromDate, Date toDate) {
		List<StockTo> stockToList = stockService.findByDate(fromDate, toDate);
		return stockToList;
	}

	@Override
	public List<Date> getBeginAndEndDate() {
		List<Date> beginAndEndDate = stockService.getBeginAndEndDate();
		return beginAndEndDate;
	}

	@Override
	public Float getValueByCompanyNameForDate(String companyName, Date currentDate) {
		float value = stockService.getValueByCompanyNameForDate(companyName, currentDate);
		return value;
	}
}
