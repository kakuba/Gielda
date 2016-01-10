package stockExchange.stockExchangeJPA.StockBroker.Impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockExchange.stockExchangeJPA.StockBroker.StockBroker;
import stockExchange.stockExchangeJPA.StockExchange.StockExchange;
import stockExchange.stockExchangeJPA.TransportObject.Payment;
import stockExchange.stockExchangeJPA.TransportObject.TransportObject;
import stockExchange.stockExchangeJPA.TransportObject.WhatToDo;
import stockExchange.stockExchangeModel.To.StockTo;

@Component
public class StockBrokerImpl implements StockBroker {
	private Date beginDate;
	private Date endDate;
	private float percentCommission = Float.parseFloat("0.005");
	
	@Autowired
	private StockExchange stockExchange;
	
	
	@Override
	public List<StockTo> getStockToListByDate(Date fromDate, Date toDate) {
		List<Date> fromAndToDate = checkFromAndToDate(fromDate, toDate);
		List<StockTo> stockToList = stockExchange.getStockListByDate(fromAndToDate.get(0), fromAndToDate.get(1));
		return stockToList;
	}

	@Override
	public Payment buyStocks(TransportObject transportObject) {
		Payment payment = new Payment(0,transportObject.getHowMuch());
		if (transportObject.getWhatToDo().equals(WhatToDo.BUY) && acceptOrNot()) {
			stockExchange.getValueByCompanyNameForDate(transportObject.getCompanyName(), transportObject.getCurrentDate()); 
			payment.setMoney(payMoney(transportObject));
			payment.setStocks(transportObject.getHowMuch());
			
		}
		return payment;
	}
	
	@Override
	public Payment sellStocks(TransportObject transportObject) {
		Payment payment = new Payment(0,0);
		if (transportObject.getWhatToDo().equals(WhatToDo.SELL)) {
			stockExchange.getValueByCompanyNameForDate(transportObject.getCompanyName(), transportObject.getCurrentDate());
			payment.setMoney(takeMoney(transportObject));
			payment.setStocks(transportObject.getHowMuch());
		}
		return payment;
	}
	
	@Override
	public List<Date> getBeginAndEndDate() {
		List<Date> beginAndEndDate = stockExchange.getBeginAndEndDate();
		return beginAndEndDate;
	}
	
	private boolean acceptOrNot() {
		boolean buy = true;
		return buy;
	}
	
	private float payMoney(TransportObject transportObject) {
		float money = 0;
		float howMuch = (float)transportObject.getHowMuch();
		Float value = stockExchange.getValueByCompanyNameForDate(transportObject.getCompanyName(), transportObject.getCurrentDate());
		Float commission = value * percentCommission * howMuch;
		money = (value * howMuch) - checkCommission(commission);
		return -money;
	}
	
	private float takeMoney(TransportObject transportObject) {
		float money = 0;
		float howMuch = (float)transportObject.getHowMuch();
		float value = stockExchange.getValueByCompanyNameForDate(transportObject.getCompanyName(), transportObject.getCurrentDate());
		float commission = value * percentCommission * howMuch;
		money = (value * howMuch) + checkCommission(commission);
		return money;
	}
	
	private float checkCommission(float commission) {
		if(commission < 5) {
			commission = 5;
		}
		return commission;
	}
	
	private List<Date> checkFromAndToDate(Date fromDate, Date toDate) {
		setBeginAndEndDate();
		List<Date> fromAndToDate = new ArrayList<>();
		if(fromDate.before(beginDate)) {
			fromAndToDate.add(beginDate);
		} else {
			fromAndToDate.add(fromDate);
		}
		
		if(toDate.after(endDate)) {
			fromAndToDate.add(endDate);
		} else {
			fromAndToDate.add(toDate);
		}
		return fromAndToDate;
	}
	
	private void setBeginAndEndDate() {
		List<Date> beginAndEndDate = stockExchange.getBeginAndEndDate();
		this.beginDate = beginAndEndDate.get(0);
		this.endDate = beginAndEndDate.get(1);
		
	}


	

}
