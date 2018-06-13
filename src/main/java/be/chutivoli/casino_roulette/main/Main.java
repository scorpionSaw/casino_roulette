package be.chutivoli.casino_roulette.main;

import be.chutivoli.casino_roulette.model.Roulette;

public class Main {
	
	public static void main(String[] args){
		Roulette r = new Roulette();
		System.out.println(r.getLargestNumberListStack(9, "32,17,8,9,14,28"));
	}

}
