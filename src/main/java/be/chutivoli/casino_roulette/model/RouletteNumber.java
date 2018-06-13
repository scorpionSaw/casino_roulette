package be.chutivoli.casino_roulette.model;

public class RouletteNumber {
	private int number;
	private boolean picked;
	private int quart;
	
	public RouletteNumber(int number) {
		this(number, false);
	}
	
	public RouletteNumber(int number, boolean picked) {
		this(number, picked, 1);
	}
	
	public RouletteNumber(int number, int quart) {
		this(number, false, quart);
	}
	
	public RouletteNumber(int number, boolean picked, int quart) {
		this.number = number;
		this.picked = picked;
		this.quart = quart;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public boolean isPicked() {
		return picked;
	}
	
	public void setPicked(boolean picked) {
		this.picked = picked;
	}
	
	public int getQuart() {
		return quart;
	}
	
	public void setQuart(int quart) {
		this.quart = quart;
	}
	
	@Override
	public String toString() {
		return getNumber()+"";
	}
}