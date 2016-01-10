package stockExchange.stockExchangeJPA.Gamer.Impl.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.stockExchangeJPA.Gamer.Impl.GamerImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "GamerTest-context.xml")
public class GamerImplTest {
	
	@Autowired
	private GamerImpl gamerImpl;
	
	@Test
	public void testShouldBuyStocks() {
		//given
		//when
		float money = gamerImpl.palyOnStockExchange();
		//then
		assertEquals(1015, money, 1);
	}

}
