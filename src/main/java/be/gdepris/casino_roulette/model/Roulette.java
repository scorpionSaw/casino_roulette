package be.gdepris.casino_roulette.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class Roulette {

	private List<RouletteNumber> numbers = null;
	
	public void initNumbers(){
		numbers = new ArrayList<>(Arrays.asList(
				//1st quart
				new RouletteNumber(0, 1, NumberColor.GREEN), 
				new RouletteNumber(32, 1, NumberColor.RED), 
				new RouletteNumber(15, 1, NumberColor.BLACK), 
				new RouletteNumber(19, 1, NumberColor.RED), 
				new RouletteNumber(4, 1, NumberColor.BLACK), 
				new RouletteNumber(21, 1, NumberColor.RED), 
				new RouletteNumber(2, 1, NumberColor.BLACK), 
				new RouletteNumber(25, 1, NumberColor.RED), 
				new RouletteNumber(17, 1, NumberColor.BLACK), 
				new RouletteNumber(34, 1, NumberColor.RED), 
				
				//2nd quart
				new RouletteNumber(6, 2, NumberColor.BLACK), 
				new RouletteNumber(27, 2, NumberColor.RED), 
				new RouletteNumber(13, 2, NumberColor.BLACK), 
				new RouletteNumber(36, 2, NumberColor.RED), 
				new RouletteNumber(11, 2, NumberColor.BLACK), 
				new RouletteNumber(30, 2, NumberColor.RED), 
				new RouletteNumber(8, 2, NumberColor.BLACK), 
				new RouletteNumber(23, 2, NumberColor.RED), 
				new RouletteNumber(10, 2, NumberColor.BLACK), 
				
				//3rd quart
				new RouletteNumber(5, 3, NumberColor.RED), 
				new RouletteNumber(24, 3, NumberColor.BLACK), 
				new RouletteNumber(16, 3, NumberColor.RED), 
				new RouletteNumber(33, 3, NumberColor.BLACK), 
				new RouletteNumber(1, 3, NumberColor.RED), 
				new RouletteNumber(20, 3, NumberColor.BLACK), 
				new RouletteNumber(14, 3, NumberColor.RED), 
				new RouletteNumber(31, 3, NumberColor.BLACK), 
				new RouletteNumber(9, 3, NumberColor.RED), 
				
				//4th quart
				new RouletteNumber(22, 4, NumberColor.BLACK), 
				new RouletteNumber(18, 4, NumberColor.RED), 
				new RouletteNumber(29, 4, NumberColor.BLACK), 
				new RouletteNumber(7, 4, NumberColor.RED), 
				new RouletteNumber(28, 4, NumberColor.BLACK), 
				new RouletteNumber(12, 4, NumberColor.RED), 
				new RouletteNumber(35, 4, NumberColor.BLACK), 
				new RouletteNumber(3, 4, NumberColor.RED), 
				new RouletteNumber(26, 4, NumberColor.BLACK)));
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
	
	private RouletteNumber getNumber(int number){
		Optional<RouletteNumber> opt = getNumbers().stream().filter(n -> n.getNumber() == number).findAny();
		if(opt.isPresent()){
			return opt.get();
		}
		return null;
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
	
	public NumberList getVoisinsList(int number, int voisinsSize){
		return new NumberList(getVoisins(number, voisinsSize));
	}
	
	
	public List<RouletteNumber> getVoisins(int number, int voisinsSize){
		
		List<RouletteNumber> voisins = new ArrayList<>();
		
		int index=0;
		for(RouletteNumber n : getNumbers()){
			
			if(n.getNumber() == number){

				for(int i=1; i<=voisinsSize; i++){
				//for(int i=voisinsSize; i>0; i--){
					
					if(index - i < 0){
						index = getNumbers().size() - 1;
						voisins.add(getNumbers().get(index));
					} else {
						voisins.add(getNumbers().get(index-i));
					}
					
				}
				voisins.add(n);
				index = getNumbers().indexOf(n);
				for(int i=1; i<=voisinsSize; i++){
					
					if(index+i >= getNumbers().size()){
						index = 0;
						voisins.add(getNumbers().get(index));
					} else {
						voisins.add(getNumbers().get(index+i));
					}
					
				}
				
				break;
			}
			index++;
		}
		
		return voisins;
	}
	
	public NumberList getCarre(int number){
		if(number == 0){
			return new NumberList().addNumbers(getNumber(0), getNumber(1), getNumber(2), getNumber(3));
		} else if(number >= 34){
			switch(number){
				case 34:
					return new NumberList().addNumbers(getNumber(31), getNumber(32), getNumber(34), getNumber(35));
				case 35:
					return new NumberList().addNumbers(getNumber(32), getNumber(33), getNumber(35), getNumber(36));
				case 36:
					return new NumberList().addNumbers(getNumber(32), getNumber(33), getNumber(35), getNumber(36));
				default:
					return new NumberList().addNumbers(getNumber(32), getNumber(33), getNumber(35), getNumber(36));
			}
		} else {
			
			//rangée du dessus
			if(number % 3 == 0){
				return new NumberList().addNumbers(getNumber(number-1), getNumber(number), getNumber(number+2), getNumber(number+3));
			}
			
			return new NumberList().addNumbers(getNumber(number), getNumber(number+1), getNumber(number+3), getNumber(number+4));
		}
	}
	
	public NumberList getCheval(int number){
		if(number == 0){
			return new NumberList().addNumbers(getNumber(0), getNumber(3));
		} else if(number >= 34){
			return new NumberList().addNumbers(getNumber(number), getNumber(number-3));
		} else {
			return new NumberList().addNumbers(getNumber(number), getNumber(number+3));
		}
	}

}
