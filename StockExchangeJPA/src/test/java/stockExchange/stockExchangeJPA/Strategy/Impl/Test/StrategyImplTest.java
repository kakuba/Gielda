package stockExchange.stockExchangeJPA.Strategy.Impl.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.stockExchangeJPA.Stock.Stock;
import stockExchange.stockExchangeJPA.Strategy.Impl.StrategyImpl;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;
import stockExchange.stockExchangeJPA.TransportObject.WhatToDo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "StrategyTest-context.xml")
public class StrategyImplTest {
	
	@Autowired
	private StrategyImpl strategyImpl;
	
	@Test
	public void testShouldGiveAdviceBuy() {
		float money = 1000;
		List<Stock> stocks = new ArrayList<>();
		//given
		//when
		List<TransportObject> advices = strategyImpl.giveAdvice(money, stocks);
		//then
		assertNotNull(advices);
		assertTrue(advices.get(0).getWhatToDo().equals(WhatToDo.SELL));
		assertEquals(0, advices.get(0).getHowMuch());
		assertTrue(advices.get(1).getWhatToDo().equals(WhatToDo.BUY));
		assertEquals(158, advices.get(1).getHowMuch());
		
		
	}
	
}
