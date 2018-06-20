package be.gdepris.casino_roulette.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Roulette {

	private List<RouletteNumber> numbers = null;
	
	public void initNumbers(){
		numbers = new ArrayList<>(Arrays.asList(
				//1st quart
				new RouletteNumber(0, 1), 
				new RouletteNumber(32, 1), 
				new RouletteNumber(15, 1), 
				new RouletteNumber(19, 1), 
				new RouletteNumber(4, 1), 
				new RouletteNumber(21, 1), 
				new RouletteNumber(2, 1), 
				new RouletteNumber(25, 1), 
				new RouletteNumber(17, 1), 
				new RouletteNumber(34, 1), 
				
				//2nd quart
				new RouletteNumber(6, 2), 
				new RouletteNumber(27, 2), 
				new RouletteNumber(13, 2), 
				new RouletteNumber(36, 2), 
				new RouletteNumber(11, 2), 
				new RouletteNumber(30, 2), 
				new RouletteNumber(8, 2), 
				new RouletteNumber(23, 2), 
				new RouletteNumber(10, 2), 
				
				//3rd quart
				new RouletteNumber(5, 3), 
				new RouletteNumber(24, 3), 
				new RouletteNumber(16, 3), 
				new RouletteNumber(33, 3), 
				new RouletteNumber(1, 3), 
				new RouletteNumber(20, 3), 
				new RouletteNumber(14, 3), 
				new RouletteNumber(31, 3), 
				new RouletteNumber(9, 3), 
				
				//4th quart
				new RouletteNumber(22, 4), 
				new RouletteNumber(18, 4), 
				new RouletteNumber(29, 4), 
				new RouletteNumber(7, 4), 
				new RouletteNumber(28, 4), 
				new RouletteNumber(12, 4), 
				new RouletteNumber(35, 4), 
				new RouletteNumber(3, 4), 
				new RouletteNumber(26, 4)));
	}
	
	public List<RouletteNumber> getNumbers() {
		if(numbers == null || numbers.isEmpty()){
			initNumbers();
		}
		return numbers;
	}
	
	public void setNumberPicked(int number){
		Optional<RouletteNumber> opt = getNumbers().stream().filter(n -> n.getNumber() == number).findFirst();
		if(opt.isPresent()){
			RouletteNumber rn = opt.get();
			rn.addPicked();
		}
	}
	
	
	public List<RouletteNumber> getLargestNumberListStack(int size, String numbers){

		int cpt = 0;
		List<RouletteNumber> tmp = new ArrayList<>();
		List<RouletteNumber> largest = new ArrayList<>();
		
		setNumbersPicked(numbers);
		
		int quartWithLessNumbers = getQuartWithLessNumbers();
		
		
		for(RouletteNumber number : getNumbers()){
			
			if(quartWithLessNumbers == number.getQuart() || !tmp.isEmpty()){
				tmp.add(number);
				cpt++;
				
				if(cpt == size){
					//ok
					largest = new ArrayList<>(tmp);
					break;
				}
				
			} else {
				
				if(largest.size() < tmp.size()){
					largest = new ArrayList<>(tmp);
				}
				
				tmp = new ArrayList<>();
				cpt = 0;
			}
			
		}
		
		return largest;
		
	}
	
	private void setNumbersPicked(String numbers){
		List<String> numberList = new ArrayList<>(Arrays.asList(numbers.split(",")));
		
		for(String number : numberList){
			int n = Integer.parseInt(number);
			setNumberPicked(n);
		}
		
	}
	
	private int getQuartWithLessNumbers(){
		
		int first = 0, second = 0, third = 0, fourth = 0;
		
		for(RouletteNumber num : getNumbers()){
			if(num.isNotPicked()){
				continue;
			}
			switch(num.getQuart()){
				case 1: first+=num.getTimesPicked(); continue;
				case 2: second+=num.getTimesPicked(); continue;
				case 3: third+=num.getTimesPicked(); continue;
				case 4: fourth+=num.getTimesPicked(); continue;
					default: continue;
			}
		}
		
		int oppositeQuart = 1;
		if(first > second && first > third && first > fourth){
			oppositeQuart = 3;
		} else if(second > first && second > third && second > fourth){
			oppositeQuart = 4;
		} else if(third > first && third > second && third > fourth){
			oppositeQuart = 1;
		} else if(fourth > first && fourth > second && fourth > third){
			oppositeQuart = 2;
		}
		
		int quartWithLessNumbers = 1;
		if(first <= second && first <= third && first <= fourth){
			
			if(first == second && oppositeQuart == 2){
				quartWithLessNumbers = 2;
			} else if(first == third && oppositeQuart == 3){
				quartWithLessNumbers = 3;				
			} else if(first == fourth && oppositeQuart == 4){
				quartWithLessNumbers = 4;
			} else {
				quartWithLessNumbers = 1;
			}
			
		} else if(second <= first && second <= third && second <= fourth){
			
			if(second == third && oppositeQuart == 3){
				quartWithLessNumbers = 3;
			} else if(second == fourth && oppositeQuart == 4){
				quartWithLessNumbers = 4;				
			} else if(second == first && oppositeQuart == 1){
				quartWithLessNumbers = 1;
			} else {
				quartWithLessNumbers = 2;
			}
			
		} else if(third <= first && third <= second && third <= fourth){

			if(third == first && oppositeQuart == 1){
				quartWithLessNumbers = 1;
			} else if(third == second && oppositeQuart == 2){
				quartWithLessNumbers = 2;				
			} else if(third == fourth && oppositeQuart == 4){
				quartWithLessNumbers = 4;
			} else {
				quartWithLessNumbers = 3;
			}
			
		} else if(fourth <= first && fourth <= second && fourth <= third){

			if(fourth == first && oppositeQuart == 1){
				quartWithLessNumbers = 1;
			} else if(fourth == second && oppositeQuart == 2){
				quartWithLessNumbers = 2;				
			} else if(fourth == third && oppositeQuart == 3){
				quartWithLessNumbers = 3;
			} else {
				quartWithLessNumbers = 4;
			}
			
		}
		
		System.out.println("First ["+first+"], Second ["+second+"], Third ["+third+"], Fourth ["+fourth+"], Quart with less numbers : "+quartWithLessNumbers);
		
		return quartWithLessNumbers;
	}

}