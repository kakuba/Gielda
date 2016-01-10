package stockExchange.stockExchangeJPA.Strategy.Impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.stockExchangeJPA.CompanyStock.CompanyStock;
import stockExchange.stockExchangeJPA.Stock.Stock;
import stockExchange.stockExchangeJPA.StockBrokerInfo.StockBrokerInfo;
import stockExchange.stockExchangeJPA.Strategy.Strategy;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;
import stockExchange.stockExchangeJPA.TransportObject.WhatToDo;
import stockExchange.stockExchangeModel.To.StockTo;

@Component
public class StrategyImpl implements Strategy {

	private Date fromDate = null;
	private Date toDate = null;
	private Date beginDate = null;
	private Date endDate = null;
	private Date currentDate;
	private int pastDay = 1;
	private float money = 0;
	
	
	@Autowired
	private StockBrokerInfo stockBrokerInfo;
	
	@Override
	public List<TransportObject> giveAdvice(float money, List<Stock> stocks) {
		this.money = money;
		setFromAndToDate();
		List<StockTo> stockToList = stockBrokerInfo.getStockToListByDate(fromDate, toDate);
		while (stockToList.size() == 0) {
			setFromAndToDate();
			stockToList = stockBrokerInfo.getStockToListByDate(fromDate, toDate);
		}
		currentDate = stockToList.get(stockToList.size()-1).getDate();
		List<CompanyStock> companyStockList = makeCompanyStockList(stockToList);
		List<TransportObject> advices = decideWhatToDo(companyStockList, stocks);
		return advices;
	}
	

	private List<TransportObject> decideWhatToDo(List<CompanyStock> companyStockList, List<Stock> stocks) {
		List<TransportObject> advices = new ArrayList<>();
		for (CompanyStock companyStock : companyStockList) {
			advices = decideIsSomethingToBuy(advices, companyStock, money);
			advices = decideIsSomethingToSell(advices, companyStock, stocks);
			advices = decideIsEndAndSellEverything(advices, companyStock, stocks);
			advices = addEmptyAdviceDoNothing(advices);
		}
		return advices;
	}
	
	private List<TransportObject> addEmptyAdviceDoNothing(List<TransportObject> advices) {
		if (advices.size() == 0) {
			advices.add(new TransportObject("Empty", WhatToDo.DO_NOTHING, 0, currentDate));
		}
		return advices;
	}

	private List<TransportObject> decideIsSomethingToBuy(List<TransportObject> advices, CompanyStock companyStock, float money) {
		if (companyStock.getNumberOfOccurrences() == -2 && money > companyStock.getCurrentValue()) {
			int howMuch = decideHowMuchBuy(companyStock.getCurrentValue(), money);
			advices.add(new TransportObject(companyStock.getCompanyName(), WhatToDo.BUY, howMuch, currentDate));
			this.money -= howMuch * companyStock.getCurrentValue();
		}
		return advices;
	}
	private List<TransportObject> decideIsSomethingToSell(List<TransportObject> advices, CompanyStock companyStock, List<Stock> stocks) {
		if (companyStock.getNumberOfOccurrences() == 2) {
			int howMuch = decideHowMuchSell(companyStock.getCompanyName(), stocks);
			advices.add(new TransportObject(companyStock.getCompanyName(), WhatToDo.SELL, howMuch, currentDate));
		} 
		return advices;
	}
	
	private List<TransportObject> decideIsEndAndSellEverything(List<TransportObject> advices, CompanyStock companyStock, List<Stock> stocks){
		if (currentDate.equals(endDate)) {
			if (advices.size() == 0) {
				advices.add(new TransportObject("End", WhatToDo.END, 0, currentDate));
			}
			int howMuch = decideHowMuchSell(companyStock.getCompanyName(), stocks);
			advices.add(new TransportObject(companyStock.getCompanyName(), WhatToDo.SELL, howMuch, currentDate));
		}
		return advices;
	}
	
	private int decideHowMuchBuy(float currentValue, float money) {
		return (int)Math.floor(money / (currentValue*1.02));
	}
	
	private int decideHowMuchSell(String companyName, List<Stock> stocks) {
		int howMuch = 0;
		for (Stock stock : stocks) {
			if (stock.getCompanyName().equals(companyName)) {
				howMuch = stock.getNumberOfStock();
			}
		}
		return howMuch;
	}

	private List<CompanyStock> makeCompanyStockList(List<StockTo> stockToList) {
		List<CompanyStock> companyStockList = new ArrayList<>();
		for (StockTo stockTo : stockToList) {
			CompanyStock companyStock = new CompanyStock(stockTo.getCompanyName(), stockTo.getValue());
			
			if (!companyStockList.contains(companyStock)) {
				companyStockList.add(companyStock);
			} else {
				changeOccurrences(companyStockList, stockTo);
			}
		}
		return companyStockList;
	}

	private List<CompanyStock> changeOccurrences(List<CompanyStock> companyStockList, StockTo stockTo) {
		int companyStockListSize = companyStockList.size();
		for (int i = 0; i < companyStockListSize; i ++) {
			if (companyStockList.get(i).getCompanyName().equals(stockTo.getCompanyName()) && companyStockList.get(i).getCurrentValue() < stockTo.getValue()) {
				companyStockList.get(i).setCurrentValue(stockTo.getValue());
				companyStockList.get(i).setNumberOfOccurrences(companyStockList.get(i).getNumberOfOccurrences() + 1);
			} else if (companyStockList.get(i).getCompanyName().equals(stockTo.getCompanyName()) && companyStockList.get(i).getCurrentValue() > stockTo.getValue()) {
				companyStockList.get(i).setCurrentValue(stockTo.getValue());
				companyStockList.get(i).setNumberOfOccurrences(companyStockList.get(i).getNumberOfOccurrences() - 1);
			}
		}
		return companyStockList;
	}



	@SuppressWarnings("deprecation")
	public void setFromAndToDate() {
		if(this.beginDate == null) {
			this.beginDate = stockBrokerInfo.getBeginAndEndDate().get(0);
			this.endDate = stockBrokerInfo.getBeginAndEndDate().get(1);
			int year = this.beginDate.getYear();
			int month = this.beginDate.getMonth();
			int day = this.beginDate.getDate();
			this.fromDate = new Date(year, month, day -1);
			this.toDate = new Date(year, month, day + pastDay);
		}
		increaseFromAndToDate();
		
	}

	@SuppressWarnings("deprecation")
	private void increaseFromAndToDate() {
		this.fromDate.setDate(fromDate.getDate() + 1);
		this.toDate.setDate(toDate.getDate() + 1);
	}

	
}