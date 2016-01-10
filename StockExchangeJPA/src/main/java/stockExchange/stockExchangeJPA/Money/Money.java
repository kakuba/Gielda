package stockExchange.stockExchangeJPA.Money;

public class Money {
	private float pln;
	private float euro;

	public Money(float pln, float euro) {
		this.pln = pln;
		this.euro = euro;
	}
	
	public Money addMoney(Money profitMoney) {
		
		float newPln = addPln(profitMoney.getPln());
		float newEuro = (addEuro(profitMoney.getEuro()));
		return new Money(newPln, newEuro);
	}
	
	private float addPln(float profitPln) {
		this.pln += profitPln;
		return pln;
	}
	private float addEuro(float profitEuro) {
		this.euro += profitEuro;
		return euro;
	}
	
	public float getPln() {
		return pln;
	}

	public void setPln(float pln) {
		this.pln = pln;
	}

	public float getEuro() {
		return euro;
	}

	public void setEuro(float euro) {
		this.euro = euro;
	}
	
}
