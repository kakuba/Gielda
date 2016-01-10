package stockExchange.stockExchangeJPA.Mapper.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import stockExchange.stockExchangeJPA.Config.JPAconfig;
import stockExchange.stockExchangeJPA.Mapper.StockMapper;
import stockExchange.stockExchangeModel.Entity.StockEntity;
import stockExchange.stockExchangeModel.To.StockTo;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = JPAconfig.class, loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(locations = "MapperTest-context.xml")
public class MapperTest {
	@Autowired
	StockMapper stockMapper;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldMapToStockTo() {
		//given
		String companyName = "TestCompany";
		Date date =  new Date(00, 01, 01);
		float value = 15.1f;
		StockEntity stockEntity = new StockEntity(companyName, date, value);
		//when
		StockTo stockTo = stockMapper.mapStock(stockEntity);
		//then
		assertNotNull(stockTo);
		assertEquals(companyName, stockTo.getCompanyName());
		assertEquals(date.getYear(), stockTo.getDate().getYear());
		assertEquals(date.getMonth(), stockTo.getDate().getMonth());
		assertEquals(date.getDay(), stockTo.getDate().getDay());
		assertEquals(value, stockTo.getValue(), 0);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testShouldMapToStockEntity() {
		//given
		String companyName = "TestCompany";
		Date date = new Date(2000, 1, 1);
		float value = 15.1f;
		StockTo stockTo = new StockTo(companyName, date, value);
		//when
		StockEntity stockEntity = stockMapper.mapStock(stockTo);
		//then
		assertNotNull(stockEntity);
		assertEquals(companyName, stockEntity.getCompanyName());
		assertEquals(date.getYear(), stockEntity.getDate().getYear());
		assertEquals(date.getMonth(), stockEntity.getDate().getMonth());
		assertEquals(date.getDay(), stockEntity.getDate().getDay());
		assertEquals(value, stockEntity.getValue(), 0);
	}
}
