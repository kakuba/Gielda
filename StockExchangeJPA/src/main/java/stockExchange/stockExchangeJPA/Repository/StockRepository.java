package stockExchange.stockExchangeJPA.Repository;

import java.sql.Date;
import java.util.List;


import stockExchange.stockExchangeModel.Entity.StockEntity;

public interface StockRepository {
	List<StockEntity> findByDate(Date fromDate, Date toDate);
	List<Date> getBeginAndEndDate();
	Float getValueByCompanyNameForDate(String companyName, Date currentDate);
}
