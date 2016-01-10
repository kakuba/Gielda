package stockExchange.stockExchangeJPA.Loader;

import java.io.IOException;
import java.sql.SQLException;

public interface Loader {
	boolean loadFileToDataBase(String dataBaseName, String filesPath) throws ClassNotFoundException, SQLException, IOException;
}
