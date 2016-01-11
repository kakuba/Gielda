package stockExchange.stockExchangeJPA.Gamer.Impl.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.stockExchangeJPA.Gamer.Gamer;
import stockExchange.stockExchangeJPA.Loader.Loader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "GamerTest-context.xml")
public class StockExchangePlayTest {
	
	@Autowired
	private Loader loader;
	
	@Autowired
	private Gamer gamer;
	
	@Ignore
	@Before
	public void loadFile() throws ClassNotFoundException, SQLException, IOException{
		String filesPath = "Here set link to data";
		String dataBaseName = "STOCKEXCHANGE";
		
		loader.loadFileToDataBase(dataBaseName, filesPath);
	}
	@Ignore
	@After
	public void reloadFile() throws ClassNotFoundException, SQLException, IOException{
		String filesPath = "C:\\Users\\JWOLAN\\Desktop\\testData.csv";
		String dataBaseName = "STOCKEXCHANGE";
		
		loader.loadFileToDataBase(dataBaseName, filesPath);
	}
	@Ignore
	@Test
	public void test() {
		//given
		//when
		gamer.palyOnStockExchange();
		//then
		assertTrue(true);
	}

}
