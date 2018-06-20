package be.gdepris.casino_roulette.model;

public class RouletteNumber {
	private int number;
	private int timesPicked;
	private int quart;
	
	public RouletteNumber(int number) {
		this(number, 0);
	}
	
	public RouletteNumber(int number, int quart) {
		this(number, 0, quart);
	}
	
	public RouletteNumber(int number, int timesPicked, int quart) {
		this.number = number;
		this.timesPicked = timesPicked;
		this.quart = quart;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getTimesPicked() {
		return timesPicked;
	}
	
	public void setTimesPicked(int timesPicked) {
		this.timesPicked = timesPicked;
	}
	
	public void addPicked(){
		this.timesPicked++;
	}
	
	public boolean isNotPicked(){
		return getTimesPicked() == 0;
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