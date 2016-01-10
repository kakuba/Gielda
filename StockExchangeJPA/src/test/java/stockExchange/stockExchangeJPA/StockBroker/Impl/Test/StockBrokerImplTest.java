package stockExchange.stockExchangeJPA.StockBroker.Impl.Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.stockExchangeJPA.StockBroker.Impl.StockBrokerImpl;
import stockExchange.stockExchangeJPA.TransportObject.Payment;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;
import stockExchange.stockExchangeJPA.TransportObject.WhatToDo;
import stockExchange.stockExchangeModel.To.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = JPAconfig.class, loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(locations = "StockBrokerTest-context.xml")
public class StockBrokerImplTest {
	
	@Autowired
	private StockBrokerImpl stockBrokerImpl;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnStockToListFromBeginDateWhenFromDateIsBeforeBeginDate() {
		//given
		Date fromDate = new Date(114,00,01);
		Date toDate = new Date(115,00,02);
		//when
		List<StockTo> stockToList = stockBrokerImpl.getStockToListByDate(fromDate, toDate);
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
		List<StockTo> stockToList = stockBrokerImpl.getStockToListByDate(fromDate, toDate);
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
		List<StockTo> stockToList = stockBrokerImpl.getStockToListByDate(fromDate, toDate);
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
	public void testShouldBuy100PKOsStocksAndPay350() {
		//given
		String companyName = "PKO";
		WhatToDo whatToDo = WhatToDo.BUY;
		int howMuch = 100;
		Date currentDate = new Date(115,00,02);
		TransportObject transportObject = new TransportObject(companyName, whatToDo, howMuch, currentDate );
		//when
		Payment payment = stockBrokerImpl.buyStocks(transportObject);
		//then
		assertEquals(-350, payment.getMoney(), 0);
		assertEquals(100, payment.getStocks());
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldBuy1000PKOsStocksAndPayAbout3532() {
		//given
		String companyName = "PKO";
		WhatToDo whatToDo = WhatToDo.BUY;
		int howMuch = 1000;
		Date currentDate = new Date(115,00,02);
		TransportObject transportObject = new TransportObject(companyName, whatToDo, howMuch, currentDate );
		//when
		Payment payment = stockBrokerImpl.buyStocks(transportObject);
		//then
		assertEquals(-3532.25, payment.getMoney(), 0.01);
		assertEquals(1000, payment.getStocks());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldNotBuyIfDoNothingAndPay0() {
		//given
		String companyName = "PKO";
		WhatToDo whatToDo = WhatToDo.DO_NOTHING;
		int howMuch = 10;
		Date currentDate = new Date(115,00,02);
		TransportObject transportObject = new TransportObject(companyName, whatToDo, howMuch, currentDate );
		//when
		Payment payment = stockBrokerImpl.buyStocks(transportObject);
		//then
		assertEquals(0, payment.getMoney(), 0);
		assertEquals(10, payment.getStocks());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldNotBuyIfSellAndPay0() {
		//given
		String companyName = "PKO";
		WhatToDo whatToDo = WhatToDo.SELL;
		int howMuch = 10;
		Date currentDate = new Date(115,00,02);
		TransportObject transportObject = new TransportObject(companyName, whatToDo, howMuch, currentDate );
		//when
		Payment payment = stockBrokerImpl.buyStocks(transportObject);
		//then
		assertEquals(0, payment.getMoney(), 0);
		assertEquals(10, payment.getStocks());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldSell100PKOsStocksAndTake360() {
		//given
		String companyName = "PKO";
		WhatToDo whatToDo = WhatToDo.SELL;
		int howMuch = 100;
		Date currentDate = new Date(115,00,02);
		TransportObject transportObject = new TransportObject(companyName, whatToDo, howMuch, currentDate );
		//when
		Payment payment = stockBrokerImpl.sellStocks(transportObject);
		//then
		assertEquals(360, payment.getMoney(), 0);
		assertEquals(100, payment.getStocks());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldSell1000PKOsStocksAndTakeAbout3567() {
		//given
		String companyName = "PKO";
		WhatToDo whatToDo = WhatToDo.SELL;
		int howMuch = 1000;
		Date currentDate = new Date(115,00,02);
		TransportObject transportObject = new TransportObject(companyName, whatToDo, howMuch, currentDate );
		//when
		Payment payment = stockBrokerImpl.sellStocks(transportObject);
		//then
		assertEquals(3567.75, payment.getMoney(), 0.01);
		assertEquals(1000, payment.getStocks());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnBeginAndEndDate() {
		//given
		int beginDate = 0;
		int endDate = 1;
		//when
		List<Date> beginAndEndDate = stockBrokerImpl.getBeginAndEndDate();
		//then
		assertNotNull(beginAndEndDate);
		assertEquals(1, beginAndEndDate.get(beginDate).getDate());
		assertEquals(6, beginAndEndDate.get(endDate).getDate());
		
	}
	
}
