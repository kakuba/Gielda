package stockExchange.stockExchangeJPA.Loader.Impl;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;

import stockExchange.stockExchangeJPA.Loader.Loader;

@Component
public class LoaderImpl implements Loader{
	static final String jdbcDriver = "com.mysql.jdbc.Driver";
	static final String dbUrl = "jdbc:mysql://localhost:3306";
	
	static final String user = "user";
	static final String password = "Password.";
	
	private static final Logger logger = Logger.getLogger(LoaderImpl.class.getName());
	
	@Override
	public boolean loadFileToDataBase(String dataBaseName, String filesPath) throws ClassNotFoundException, SQLException, IOException {
		boolean result = false;
		createDataBase(dataBaseName);
		createTableAndLoadData(dataBaseName, filesPath);
		result = true;
		return result;
	}
	
	private void createTableAndLoadData(String dataBaseName, String filesPath) throws ClassNotFoundException, SQLException, IOException {
		Connection connection = null;
		Statement statement = null;
		List<String> lineList = loadFile(filesPath);
		
		Class.forName(jdbcDriver);
		logger.info("Connecting to database");
		connection = DriverManager.getConnection(dbUrl + "/" + dataBaseName, user, password);
		logger.info("Creating database...");
		statement = connection.createStatement();
		
		logger.info("Creating table...");
		String tableName = "STOCKS";
		String sqlCreateTable = "CREATE TABLE " + tableName +
								"(id INTEGER NOT NULL AUTO_INCREMENT, " +
								"company_name VARCHAR(100), " +
								"stock_date DATE, " +
								"value FLOAT(6,2), " +
								"PRIMARY KEY ( id ))";
		statement.executeUpdate(sqlCreateTable);
		logger.info("Table created successfully.");
			
		logger.info("Loading data from file...");
		String sqlAddRecord = null;
		for (String line : lineList) {
			List<String> values = splitLine(line);
			sqlAddRecord = "INSERT INTO " + tableName + 
							" (company_name, stock_date, value) VALUES('" + values.get(0) + "', '"
																			  + values.get(1) + "', "	
																			  + values.get(2) + ")";
			statement.executeUpdate(sqlAddRecord);
		}
		logger.info("Data loaded successfully.");
		
		statement.close();
		connection.close();
	}

	private List<String> loadFile(String filesPath) throws IOException {
		List<String> lineList = new ArrayList<>();
		File file = new File(filesPath);
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
			lineList.add(scanner.nextLine());
		}
		return lineList;
	}
	
	private List<String> splitLine(String line) {
		List<String> values = Splitter.on(",").splitToList(line);
		return values;
	}
	
	private void createDataBase(String dataBaseName) throws ClassNotFoundException, SQLException{
		
		Connection connection = null;
		Statement statement = null;
		
		Class.forName(jdbcDriver);
		logger.info("Connecting to database");
		connection = DriverManager.getConnection(dbUrl, user, password);
		statement = connection.createStatement();
		logger.info("Deleting old database...");
		statement.execute("DROP DATABASE IF EXISTS " + dataBaseName);
		logger.info("Old database deleted successfully.");
		logger.info("Creating database...");
		String sql = "CREATE DATABASE IF NOT EXISTS " + dataBaseName;
		statement.executeUpdate(sql);
			
		logger.info("Database created successfully.");
			
			
		statement.close();
		connection.close();
			
	}

}
