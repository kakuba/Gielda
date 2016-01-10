package stockExchange.stockExchangeJPA.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import stockExchange.stockExchangeModel.Entity.StockEntity;
import stockExchange.stockExchangeJPA.Config.JPAconfig;
import stockExchange.stockExchangeModel.To.StockTo;

@Component
//@ContextConfiguration(classes = JPAconfig.class, loader = AnnotationConfigContextLoader.class)
public class StockMapper {
	
	public StockTo mapStock(StockEntity stockEntity) {
		return new StockTo(stockEntity.getCompanyName(), stockEntity.getDate(), stockEntity.getValue());
	}
	
	public StockEntity mapStock(StockTo stockTo) {
		return new StockEntity(stockTo.getCompanyName(), stockTo.getDate(), stockTo.getValue());
	}
	
	public List<StockTo> mapListStockTo(List<StockEntity> stockEntityList) {
		List<StockTo> stockToList = new ArrayList<>();
		for (StockEntity stockEntity : stockEntityList) {
			stockToList.add(mapStock(stockEntity));
		}
		return stockToList ;
	}
	
	public List<StockEntity> mapListStockEntity(List<StockTo> stockToList) {
		List<StockEntity> stockEntityList = new ArrayList<>();
		for (StockTo stockTo : stockToList) {
			stockEntityList.add(mapStock(stockTo));
		}
		return stockEntityList ;
	}
}
