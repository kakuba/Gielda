package stockExchange.stockExchangeJPA.Loader.Impl.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import stockExchange.stockExchangeJPA.Loader.Impl.LoaderImpl;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = JPAconfig.class, loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(locations = "LoaderTest-context.xml")
public class LoaderImplTest {
	@Autowired
	LoaderImpl loaderImpl;
	
	@Ignore
	@Test
	public void testShouldCreateDatabase() throws ClassNotFoundException, SQLException, IOException {
		//given
		String filesPath = "C:\\Users\\JWOLAN\\Desktop\\testData.csv";
		String dataBaseName = "STOCKEXCHANGE";
		//when
		boolean result = loaderImpl.loadFileToDataBase(dataBaseName, filesPath);
		//then
		assertTrue(result);
	}

}
