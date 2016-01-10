package stockExchange.stockExchangeJPA.Service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stockExchange.stockExchangeJPA.Mapper.StockMapper;
import stockExchange.stockExchangeJPA.Repository.StockRepository;
import stockExchange.stockExchangeJPA.Service.StockService;
import stockExchange.stockExchangeModel.To.StockTo;

@Service
@Transactional(readOnly = true)
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockMapper stockMapper;
	
	@Override
	public List<StockTo> findByDate(Date fromDate, Date toDate) {
		List<StockTo> stockToList = stockMapper.mapListStockTo(stockRepository.findByDate(fromDate, toDate));
		return stockToList;
	}

	@Override
	public List<Date> getBeginAndEndDate() {
		List<Date> beginAndEndDate = stockRepository.getBeginAndEndDate();
		return beginAndEndDate;
	}

	@Override
	public Float getValueByCompanyNameForDate(String companyName, Date currentDate) {
		Float value = stockRepository.getValueByCompanyNameForDate(companyName, currentDate);
		return value;
	}
	
}
