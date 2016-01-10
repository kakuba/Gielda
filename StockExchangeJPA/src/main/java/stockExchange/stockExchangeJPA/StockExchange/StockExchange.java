package stockExchange.stockExchangeJPA.StockExchange;

import java.sql.Date;
import java.util.List;

import stockExchange.stockExchangeModel.To.StockTo;

public interface StockExchange {
	List<StockTo> getStockListByDate(Date fromDate, Date toDate);
	List<Date> getBeginAndEndDate();
	Float getValueByCompanyNameForDate(String companyName, Date currentDate);
}
