package stockExchange.stockExchangeJPA.TransportObject;

import java.sql.Date;

public class TransportObject {
	private String companyName;
	private WhatToDo whatToDo = WhatToDo.DO_NOTHING;
	private Integer howMuch = null;
	private Date currentDate;
	
	
	
	public TransportObject(String companyName, WhatToDo whatToDo, int howMuch, Date currentDate) {
		this.companyName = companyName;
		this.whatToDo = whatToDo;
		this.howMuch = howMuch;
		this.currentDate = currentDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public WhatToDo getWhatToDo() {
		return whatToDo;
	}
	public void setWhatToDo(WhatToDo whatToDo) {
		this.whatToDo = whatToDo;
	}
	public int getHowMuch() {
		return howMuch;
	}
	public void setHowMuch(int howMuch) {
		this.howMuch = howMuch;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	
}
