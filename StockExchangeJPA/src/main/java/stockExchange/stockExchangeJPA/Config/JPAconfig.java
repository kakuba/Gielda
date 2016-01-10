package stockExchange.stockExchangeJPA.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import stockExchange.stockExchangeJPA.Loader.Impl.LoaderImpl;
import stockExchange.stockExchangeJPA.Mapper.StockMapper;
import stockExchange.stockExchangeJPA.Repository.Impl.StockRepositoryImpl;
import stockExchange.stockExchangeJPA.Service.Impl.StockServiceImpl;
import stockExchange.stockExchangeJPA.StockBroker.Impl.StockBrokerImpl;
import stockExchange.stockExchangeJPA.StockExchange.Impl.StockExchangeImpl;

@Configuration
//@EnableAspectJAutoProxy
public class JPAconfig {
	
	
	@Bean
	public StockMapper stockMapper() {
		StockMapper stockMapper = new StockMapper();
		return stockMapper;
	}
	
	@Bean
	public LoaderImpl loaderImpl() {
		LoaderImpl loaderImpl = new LoaderImpl();
		return loaderImpl;
	}
	
	@Bean
	public StockRepositoryImpl stockRepositoryImpl() {
		StockRepositoryImpl stockRepositoryImpl = new StockRepositoryImpl();
		return stockRepositoryImpl;
	}
	
	@Bean
	public StockServiceImpl stockServiceImpl() {
		StockServiceImpl stockServiceImpl = new StockServiceImpl();
		return stockServiceImpl;
	}
	
	@Bean
	public StockExchangeImpl stockExchangeImpl() {
		StockExchangeImpl stockExchangeImpl = new StockExchangeImpl();
		return stockExchangeImpl;
	}
	
	@Bean
	public StockBrokerImpl stockBrokerImpl() {
		StockBrokerImpl stockBrokerImpl = new StockBrokerImpl();
		return stockBrokerImpl;
	}
	
}
