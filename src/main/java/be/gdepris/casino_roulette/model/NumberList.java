package be.gdepris.casino_roulette.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumberList {
	
	private List<RouletteNumber> numbers = new ArrayList<>();
	
	public NumberList() {
		this.numbers = new ArrayList<>();
	}
	
	public NumberList(List<RouletteNumber> numbers){
		getNumbers().addAll(numbers);
	}
	
	public List<RouletteNumber> getNumbers() {
		if(numbers == null){
			numbers = new ArrayList<>();
		}
		return numbers;
	}
	
	public NumberList addNumber(RouletteNumber number) {
		getNumbers().add(number);
		return this;
	}
	
	public NumberList addNumbers(RouletteNumber... numbers) {
		getNumbers().addAll(Arrays.asList(numbers));
		return this;
	}
	
	public boolean isIn(int number){
		Optional<RouletteNumber> opt = getNumbers().stream().filter(n -> n.getNumber() == number).findAny();
		if(opt.isPresent()){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String r = "";
		for(RouletteNumber n : getNumbers()){
			if(!r.isEmpty()){
				r += ", ";
			}
			r += n.toString();
		}
		return r;
	}

}
