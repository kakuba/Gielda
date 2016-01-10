package stockExchange.stockExchangeJPA.StockBrokerShop.Impl.Test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.stockExchangeJPA.StockBrokerShop.Impl.StockBrokerShopImpl;
import stockExchange.stockExchangeJPA.TransportObject.Payment;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;
import stockExchange.stockExchangeJPA.TransportObject.WhatToDo;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = JPAconfig.class, loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(locations = "StockBrokerShopTest-context.xml")
public class StockBrokerShopImplTest {
	
	@Autowired
	private StockBrokerShopImpl stockBrokerShopImpl;
	
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
		Payment payment = stockBrokerShopImpl.buyStocks(transportObject);
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
		Payment payment = stockBrokerShopImpl.buyStocks(transportObject);
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
		Payment payment = stockBrokerShopImpl.buyStocks(transportObject);
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
		Payment payment = stockBrokerShopImpl.buyStocks(transportObject);
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
		Payment payment = stockBrokerShopImpl.sellStocks(transportObject);
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
		Payment payment = stockBrokerShopImpl.sellStocks(transportObject);
		//then
		assertEquals(3567.75, payment.getMoney(), 0.01);
		assertEquals(1000, payment.getStocks());
	}
	
}
