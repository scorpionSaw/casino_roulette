package be.gdepris.casino_roulette.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.gdepris.casino_roulette.model.Roulette;
import be.gdepris.casino_roulette.model.RouletteNumber;

@Controller
@RequestMapping("/roulette")
public class RouletteController {
	
    @Autowired
    private ApplicationContext appContext;

	@GetMapping("/")
	public ModelAndView getRoulette() {

		return new ModelAndView("roulette", "roulette", null);
	}

	@GetMapping("/getList/{numbers}")
	public ModelAndView getRouletteWithNumbers(@PathVariable String numbers) {
		
		Roulette r = new Roulette();
		List<RouletteNumber> rns = r.getLargestNumberListStack(9, numbers);
		
		return new ModelAndView("roulette", "numbers", numbersToPrettyString(rns));
	}
	
	private String numbersToPrettyString(List<RouletteNumber> rns){
		String o = "";
		
		for(RouletteNumber rn : rns){
			o += "<td class=\"cell ";
			
			o += rn.getColor().toString().toLowerCase() + "\">";
			o += rn.getNumber();	
			
			
			o += "</td>";
		}
		
		return o;
	}
}
