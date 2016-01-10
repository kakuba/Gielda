package stockExchange.stockExchangeJPA.Service;

import java.sql.Date;
import java.util.List;

import stockExchange.stockExchangeModel.To.StockTo;

public interface StockService {
	List<StockTo> findByDate(Date fromDate, Date toDate);
	List<Date> getBeginAndEndDate();
	Float getValueByCompanyNameForDate(String companyName, Date currentDate);
}
