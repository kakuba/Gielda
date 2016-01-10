package stockExchange.stockExchangeJPA.Repository.Impl.Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.stockExchangeJPA.Repository.Impl.StockRepositoryImpl;
import stockExchange.stockExchangeModel.Entity.StockEntity;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = JPAconfig.class, loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(locations = "RepositoryTest-context.xml")
public class StockRepositoryImplTest {

	@Autowired
	StockRepositoryImpl stockRepositoryImpl;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnStockEnitityListWith6ElementsWhereDateIsFrom20150101To20150103() {
		//given
		Date fromDate = new Date(115,00,01);
		Date toDate = new Date(115,00,03);
		//when
		List<StockEntity> stockEntityList = stockRepositoryImpl.findByDate(fromDate, toDate);
		//then
		assertNotNull(stockEntityList.get(0));
		assertNotNull(stockEntityList.get(1));
		assertNotNull(stockEntityList.get(2));
		assertNotNull(stockEntityList.get(3));
		assertNotNull(stockEntityList.get(4));
		assertNotNull(stockEntityList.get(5));
		assertEquals(6, stockEntityList.size());
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnStockEnitityListWith2ElementsWhereDateIsFrom20150102To20150102() {
		//given
		Date fromDate = new Date(115,00,02);
		Date toDate = new Date(115,00,02);
		//when
		List<StockEntity> stockEntityList = stockRepositoryImpl.findByDate(fromDate, toDate);
		//then
		assertNotNull(stockEntityList.get(0));
		assertNotNull(stockEntityList.get(1));
		assertEquals(2, stockEntityList.size());
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnEmptyListWhereDateFromIsGreaterThenDateTo() {
		//given
		Date fromDate = new Date(115,00,03);
		Date toDate = new Date(115,00,01);
		//when
		List<StockEntity> stockEntityList = stockRepositoryImpl.findByDate(fromDate, toDate);
		//then
		assertEquals(0, stockEntityList.size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldReturnBeginAndEndDate() {
		//given
		//when
		List<Date> beginDateAndEnd = stockRepositoryImpl.getBeginAndEndDate();
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
		float value = stockRepositoryImpl.getValueByCompanyNameForDate(companyName, currentDate);
		//then
		assertEquals(3.66, value, 0.001);
	}
	

}
