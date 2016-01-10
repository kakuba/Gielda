package stockExchange.stockExchangeJPA.Gamer.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.stockExchangeJPA.Gamer.Gamer;
import stockExchange.stockExchangeJPA.Loader.Impl.LoaderImpl;
import stockExchange.stockExchangeJPA.Money.Money;
import stockExchange.stockExchangeJPA.Stock.Stock;
import stockExchange.stockExchangeJPA.StockBrokerShop.StockBrokerShop;
import stockExchange.stockExchangeJPA.Strategy.Strategy;
import stockExchange.stockExchangeJPA.TransportObject.Payment;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;
import stockExchange.stockExchangeJPA.TransportObject.WhatToDo;
import stockExchange.stockExchangeJPA.Wallet.Wallet;

@Component
public class GamerImpl implements Gamer {
	private Wallet wallet = new Wallet(new Money(1000, 0), new ArrayList<>());
	private Money money;
	private float moneyPln;
	private List<Stock> stocks;
	
	private static final Logger logger = Logger.getLogger(LoaderImpl.class.getName());
	
	@Autowired
	private StockBrokerShop stockBrokerShop;
	
	@Autowired
	private Strategy strategy;
	
	
	@Override
	public float palyOnStockExchange() {
		stocks = wallet.getStocks();
		money = wallet.getMoney();
		
		play();
		
		float allMoney = getAllMoneyPln();
		logger.info("Now you have:" + allMoney + " money PLN");
		return allMoney;
	}

	private void agreeOrNot(List<TransportObject> advices) {
		for (TransportObject advice : advices) {
			buyStock(advice);
			sellStock(advice);
		}
		
	}

	private float getAllMoneyPln() {
		float money = wallet.getMoney().getPln();
		return money;
	}
	
	private void buyStock(TransportObject advice) {
		if (advice.getWhatToDo().equals(WhatToDo.BUY)) {
			Payment payment = stockBrokerShop.buyStocks(advice);
			money.addMoney(new Money(payment.getMoney(), 0));
			Stock stock = new Stock(advice.getCompanyName(), payment.getStocks());
			if (stocks.contains(stock)){
				int index = findStocksIndexByCompanyName(advice.getCompanyName());
				stocks.get(index).addNumberOfStock(payment.getStocks());
			} else {
				stocks.add(stock);
			}
			
		} 
	}
	private void sellStock(TransportObject advice) {
		if (advice.getWhatToDo().equals(WhatToDo.SELL) && advice.getHowMuch() != 0) {
			Payment payment = stockBrokerShop.sellStocks(advice);
			money.addMoney(new Money(payment.getMoney(), 0));
			int index = findStocksIndexByCompanyName(advice.getCompanyName());
			stocks.remove(index);
		} 
	}


	private int findStocksIndexByCompanyName(String companyName) {
		int index = 0;
		for (int i = 0; i < stocks.size(); i ++) {
			String stockCompanyName = stocks.get(i).getCompanyName();
			if (stockCompanyName.equals(companyName)) {
				index = i;
			}
		}
		return index;
	}

	private void play() {
		moneyPln = getAllMoneyPln();
		List<TransportObject> advices = strategy.giveAdvice(moneyPln, stocks);
		playUntilDoNothing(advices);
		
	}

	private void playUntilDoNothing(List<TransportObject> advices) {
		while (!advices.get(0).getWhatToDo().equals(WhatToDo.END) && !advices.get(0).getCompanyName().equals("End")) {
			agreeOrNot(advices);
			moneyPln = getAllMoneyPln();
			advices = strategy.giveAdvice(moneyPln, stocks);
		}
		if (advices.get(0).getWhatToDo().equals(WhatToDo.END) && advices.get(0).getCompanyName().equals("End")) {
			agreeOrNot(advices);
			moneyPln = getAllMoneyPln();
		}
	}
}
