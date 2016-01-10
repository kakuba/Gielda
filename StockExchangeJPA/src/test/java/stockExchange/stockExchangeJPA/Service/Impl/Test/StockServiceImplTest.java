package stockExchange.stockExchangeJPA.Service.Impl.Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.stockExchangeJPA.Service.Impl.StockServiceImpl;
import stockExchange.stockExchangeModel.To.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = JPAconfig.class, loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(locations = "ServiceTest-context.xml")
public class StockServiceImplTest {

	@Autowired
	private StockServiceImpl stockServiceImpl; 
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnStockToListWith4ElementsWhereDateIsFrom20150101To20150103() {
		Date fromDate = new Date(115,00,01);
		Date toDate = new Date(115,00,02);
		List<StockTo> stockToList = stockServiceImpl.findByDate(fromDate, toDate);
		assertNotNull(stockToList.get(0));
		assertNotNull(stockToList.get(1));
		assertNotNull(stockToList.get(2));
		assertNotNull(stockToList.get(3));
		assertEquals(4, stockToList.size());
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnEmptyListWhereDateFromIsGreaterThenDateTo() {
		Date fromDate = new Date(115,00,02);
		Date toDate = new Date(115,00,01);
		List<StockTo> stockToList = stockServiceImpl.findByDate(fromDate, toDate);
		assertEquals(0, stockToList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnBeginAndEndDate() {
		//given
		//when
		List<Date> beginDateAndEnd = stockServiceImpl.getBeginAndEndDate();
		//then
		assertEquals(new Date(115,00,01), beginDateAndEnd.get(0));
		assertEquals(new Date(115,00,06), beginDateAndEnd.get(1));
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnValueByCompanyNameAndForCurrentDate() {
		//given
		String companyName = "PKO";
		Date currentDate = new Date(115,00,03);
		//when
		float value = stockServiceImpl.getValueByCompanyNameForDate(companyName, currentDate);
		//then
		assertEquals(3.66, value, 0.001);
	}
}
