package stockExchange.stockExchangeJPA.Repository.Impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import stockExchange.stockExchangeJPA.Loader.Impl.LoaderImpl;
import stockExchange.stockExchangeJPA.Repository.StockRepository;
import stockExchange.stockExchangeModel.Entity.StockEntity;

@Repository
public class StockRepositoryImpl implements StockRepository {
	private static final Logger logger = Logger.getLogger(LoaderImpl.class.getName());
	
	@Transactional(readOnly = true)
	@Override
	public List<StockEntity> findByDate(Date fromDate, Date toDate) {
		
		String persistanceUnitName = "stockExchangeJPA";
		logger.info("Creating EntityManagerFactory...");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnitName);
		logger.info("EntityManagerFactory created successfully.");
		logger.info("Creating EntityManager...");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		logger.info("EntityManager created successfully.");
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		logger.info("Creating query...");
		String mySQLquery = "SELECT stock "
				+ "FROM StockEntity stock "
				+ "WHERE stock.date >= DATE('" + fromDate.toString() 
				+ "') AND stock.date <= DATE('" + toDate.toString() + "') ORDER BY stock.date ASC";
		
		
		Query query = entityManager.createQuery(mySQLquery);
		logger.info("Query created successfully.");
		
		List<StockEntity> stocksList = query.getResultList();
		logger.info("StockEntityLst created successfully.");
		entityTransaction.commit();
		
		logger.info("Closeing EntityManager...");
		entityManager.close();
		logger.info("EntityManager closed successfully.");
		logger.info("Closeing EntityManagerFatory...");
		entityManagerFactory.close();
		logger.info("EntityManageFactory closed successfully.");
		
		return stocksList;
	}
	@Transactional(readOnly = true)
	@Override
	public List<Date> getBeginAndEndDate() {
		List<Date> beginAndEndDate = new ArrayList<>();
		
		String persistanceUnitName = "stockExchangeJPA";
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnitName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		logger.info("Getting beginDate...");
		String mySQLqueryBeginDate = "SELECT MIN(stock.date) FROM StockEntity stock";
		Query queryBeginDate = entityManager.createQuery(mySQLqueryBeginDate);
		beginAndEndDate.add((Date)queryBeginDate.getSingleResult());
		
		logger.info("Getting endDate...");
		String mySQLqueryEndDate = "SELECT MAX(stock.date) FROM StockEntity stock";
		Query queryEndDate = entityManager.createQuery(mySQLqueryEndDate);
		beginAndEndDate.add((Date)queryEndDate.getSingleResult());
		
		entityTransaction.commit();
		
		entityManager.close();
		entityManagerFactory.close();
		return beginAndEndDate;
	}
	@Transactional(readOnly = true)
	@Override
	public Float getValueByCompanyNameForDate(String companyName, Date currentDate) {
		Float value = 0F;
		String persistanceUnitName = "stockExchangeJPA";
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnitName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		logger.info("Getting beginDate...");
		String mySQLquery = "SELECT stock.value "
				+ "FROM StockEntity stock "
				+ "WHERE stock.companyName = '" + companyName + "' "
				+ "AND stock.date = DATE('" + currentDate + "')";
		Query query = entityManager.createQuery(mySQLquery);
		value = (Float)query.getSingleResult();
		
		entityTransaction.commit();
		
		entityManager.close();
		entityManagerFactory.close();
		
		return value;
	}
	
	
}
