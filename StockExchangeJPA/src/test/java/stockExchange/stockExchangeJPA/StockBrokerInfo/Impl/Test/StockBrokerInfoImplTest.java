package stockExchange.stockExchangeJPA.StockBrokerInfo.Impl.Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.stockExchangeJPA.StockBrokerInfo.Impl.StockBrokerInfoImpl;
import stockExchange.stockExchangeModel.To.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "StockBrokerInfoTest-context.xml")
public class StockBrokerInfoImplTest {
	
	@Autowired
	private StockBrokerInfoImpl stockBrokerInfoImpl;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnStockToListFromBeginDateWhenFromDateIsBeforeBeginDate() {
		//given
		Date fromDate = new Date(114,00,01);
		Date toDate = new Date(115,00,02);
		//when
		List<StockTo> stockToList = stockBrokerInfoImpl.getStockToListByDate(fromDate, toDate);
		//then
		assertNotNull(stockToList.get(0));
		assertNotNull(stockToList.get(1));
		assertNotNull(stockToList.get(2));
		assertNotNull(stockToList.get(3));
		assertEquals(4, stockToList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnStockToListToEndDateWhenToDateIsAfterEndDate() {
		//given
		Date fromDate = new Date(115,00,02);
		Date toDate = new Date(115,01,02);
		//when
		List<StockTo> stockToList = stockBrokerInfoImpl.getStockToListByDate(fromDate, toDate);
		//then
		assertNotNull(stockToList.get(0));
		assertNotNull(stockToList.get(1));
		assertNotNull(stockToList.get(2));
		assertNotNull(stockToList.get(3));
		assertEquals(10, stockToList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnStockToListWith4ElementsWhereDateIsFrom20150101To20150103() {
		//given
		Date fromDate = new Date(115,00,01);
		Date toDate = new Date(115,00,03);
		//when
		List<StockTo> stockToList = stockBrokerInfoImpl.getStockToListByDate(fromDate, toDate);
		//then
		assertNotNull(stockToList.get(0));
		assertNotNull(stockToList.get(1));
		assertNotNull(stockToList.get(2));
		assertNotNull(stockToList.get(3));
		assertNotNull(stockToList.get(4));
		assertNotNull(stockToList.get(5));
		assertEquals(6, stockToList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnBeginDate() {
		//given
		int beginDate = 0;
		int endDate = 1;
		//when
		List<Date> beginAndEndDate = stockBrokerInfoImpl.getBeginAndEndDate();
		//then
		assertNotNull(beginAndEndDate);
		assertEquals(1, beginAndEndDate.get(beginDate).getDate());
		assertEquals(6, beginAndEndDate.get(endDate).getDate());
		
	}
}
