package be.gdepris.casino_roulette.model;

public class RouletteNumber {
	private int number;
	private int timesPicked;
	private int quart;
	private NumberColor color;
	
	public RouletteNumber(int number) {
		this(number, 0, NumberColor.RED);
	}
	
	public RouletteNumber(int number, int quart, NumberColor color) {
		this(number, 0, quart, color);
	}
	
	public RouletteNumber(int number, int timesPicked, int quart, NumberColor color) {
		this.number = number;
		this.timesPicked = timesPicked;
		this.quart = quart;
		this.color = color;
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
	
	public NumberColor getColor() {
		return color;
	}
	
	public void setColor(NumberColor color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return getNumber()+"";
	}
}