package be.gdepris.casino_roulette.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import be.gdepris.casino_roulette.model.NumberList;
import be.gdepris.casino_roulette.model.Roulette;
import be.gdepris.casino_roulette.model.RouletteNumber;

public class Main {
	
	public static void main(String[] args){
		Roulette r = new Roulette();
		//System.out.println(r.getLargestNumberListStack(9, "32,17,8,9,14,28"));
		/*
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		String pwd = "p@ssw0rd";
		String cryptPwd = crypt.encode(pwd);
		System.out.println(cryptPwd);
		cryptPwd = crypt.encode(pwd);
		System.out.println(cryptPwd);
		cryptPwd = crypt.encode(pwd);
		System.out.println(cryptPwd);
		cryptPwd = crypt.encode(pwd);
		System.out.println(cryptPwd);*/

		Random rand = new Random();

		
		//Mickael
//		List<Integer> mises = new ArrayList<>(Arrays.asList(1,1,1,2,1,1,5,2,1,8,3,2,13,5,2,21,8,3,34,13,5,55,21,8,89,34,13,144,55,21,233,89,34));
		//List<Integer> mises = new ArrayList<>(Arrays.asList(1,1,1,2,2,1,5,3,2,8,5,3,13,8,5,21,13,8,34,21,13,55,34,24,89,55,34,144,89,55,233,144,89));
		//List<Integer> mises = new ArrayList<>(Arrays.asList(2,1,1,2,2,1,5,3,2,8,5,2,13,8,3,21,13,5,34,21,8,55,34,13,89,55,21,144,89,34,233,144,55));
		
		//BEST
//		List<Integer> mises = new ArrayList<>(Arrays.asList(2,1,1,2,2,1,5,3,2,8,5,3,13,8,5,21,13,8,34,21,13,55,21,13,89,55,34,144,89,55,233,144,89));
		
		//VERY BEST
		List<Integer> mises = new ArrayList<>(Arrays.asList(1,1,1,2,2,1,5,3,2,8,5,3,13,8,5,21,13,8,34,21,13,55,34,21,89,55,34,144,89,55,233,144,89));
															
		//List<Integer> mises = new ArrayList<>(Arrays.asList(3,1,1,5,2,1,8,3,2,13,5,3,21,8,3,34,13,5,55,21,8,89,34,13,144,55,21,233,89,34,233,144,55));
		
		double solde = 0; 
		
		double currentMiseCarre = 0;
		double currentMiseCheval = 0;
		double currentMisePlein = 0;
		
		boolean enableLogging = true;
		
		double diviseur = 10.0;
		
		int nbToursTotal = 0;
		
		double totalSolde = 0;
		for(int nbSim=0; nbSim<1; nbSim++){
			solde = 0;
			int indexMise = 2;
			
			
			int number = 7;//rand.nextInt(37);
			System.out.println(number);
			List<RouletteNumber> voisins = r.getVoisins(number, 2);
			System.out.println(voisins);
			
			NumberList carre1 = r.getCarre(voisins.get(0).getNumber());
			NumberList carre2 = r.getCarre(voisins.get(1).getNumber());
			NumberList carre3 = r.getCarre(voisins.get(2).getNumber());
			NumberList carre4 = r.getCarre(voisins.get(3).getNumber());
			NumberList carre5 = r.getCarre(voisins.get(4).getNumber());

			NumberList cheval1 = r.getCheval(voisins.get(0).getNumber());
			NumberList cheval2 = r.getCheval(voisins.get(1).getNumber());
			NumberList cheval3 = r.getCheval(voisins.get(2).getNumber());
			NumberList cheval4 = r.getCheval(voisins.get(3).getNumber());
			NumberList cheval5 = r.getCheval(voisins.get(4).getNumber());
			/*
			System.out.println("Carrés : ");
			System.out.println(carre1);
			System.out.println(carre2);
			System.out.println(carre3);
			System.out.println(carre4);
			System.out.println(carre5);
			System.out.println("Chevaux : ");
			System.out.println(cheval1);
			System.out.println(cheval2);
			System.out.println(cheval3);
			System.out.println(cheval4);
			System.out.println(cheval5);*/
			

			for(int i=0; i<32; i++){
				int n = rand.nextInt(37);
				if(indexMise == 32){
					break;
				}
				nbToursTotal++;
				double currentMise = (double)(mises.get(indexMise) / diviseur);
				
				double miseTotale = indexMise == 0 ? currentMise : indexMise == 1 ? currentMise + 0.1 : currentMise + (mises.get(indexMise-1)/diviseur) + (mises.get(indexMise-2)/diviseur);
				
				
				if(i==0){
					currentMiseCarre = (double)(mises.get(0) / diviseur);
					currentMiseCheval = (double)(mises.get(1) / diviseur);
					currentMisePlein = (double)(mises.get(2) / diviseur);
				}
				
				if(indexMise==0 || indexMise%3==0){
					currentMiseCarre = currentMise;
				} else if((indexMise-1)%3==0){
					currentMiseCheval = currentMise;
				} else if((indexMise-2)%3==0){
					currentMisePlein = currentMise;
				}
				
				if(enableLogging){
					System.out.println(i+1+") MISE CARRE ["+currentMiseCarre+"] CHEVAL ["+currentMiseCheval+"] PLEIN ["+currentMisePlein+"]");
				}
				
				miseTotale = currentMiseCarre * 5 + currentMiseCheval * 5 + currentMisePlein * 5;
				solde -= miseTotale;
				if(enableLogging){
					System.out.println((i+1)+") Number ["+n+"] SOLDE ["+solde+"] CURRENT MISE ["+currentMise+"] MISE TOTALE ["+miseTotale+"]");
				}
				
				if(carre1.isIn(n)){
					solde += (currentMiseCarre * 9);
					if(enableLogging){
						System.out.println((i+1)+") +CARRE ["+solde+"] GAIN ["+currentMiseCarre * 9+"] MISE ["+currentMiseCarre+"]");
					}
				}
				if(carre2.isIn(n)){
					solde += (currentMiseCarre * 9);
					if(enableLogging){
						System.out.println((i+1)+") +CARRE ["+solde+"] GAIN ["+currentMiseCarre * 9+"] MISE ["+currentMiseCarre+"]");
					}
				}
				if(carre3.isIn(n)){
					solde += (currentMiseCarre * 9);
					if(enableLogging){
						System.out.println((i+1)+") +CARRE ["+solde+"] GAIN ["+currentMiseCarre * 9+"] MISE ["+currentMiseCarre+"]");
					}
				}
				if(carre4.isIn(n)){
					solde += (currentMiseCarre * 9);
					if(enableLogging){
						System.out.println((i+1)+") +CARRE ["+solde+"] GAIN ["+currentMiseCarre * 9+"] MISE ["+currentMiseCarre+"]");
					}
				}
				if(carre5.isIn(n)){
					solde += (currentMiseCarre * 9);
					if(enableLogging){
						System.out.println((i+1)+") +CARRE ["+solde+"] GAIN ["+currentMiseCarre * 9+"] MISE ["+currentMiseCarre+"]");
					}
				}
				
				//Cheval
				if(i > 0){
					
					if(cheval1.isIn(n)){
						solde += (currentMiseCheval * 18);
						if(enableLogging){
							System.out.println((i+1)+") +CHEVAL ["+solde+"] GAIN ["+currentMiseCheval * 18+"] MISE ["+currentMiseCheval+"]");
						}
					}
					if(cheval2.isIn(n)){
						solde += (currentMiseCheval * 18);
						if(enableLogging){
							System.out.println((i+1)+") +CHEVAL ["+solde+"] GAIN ["+currentMiseCheval * 18+"] MISE ["+currentMiseCheval+"]");
						}
					}
					if(cheval3.isIn(n)){
						solde += (currentMiseCheval * 18);
						if(enableLogging){
							System.out.println((i+1)+") +CHEVAL ["+solde+"] GAIN ["+currentMiseCheval * 18+"] MISE ["+currentMiseCheval+"]");
						}
					}
					if(cheval4.isIn(n)){
						solde += (currentMiseCheval * 18);
						if(enableLogging){
							System.out.println((i+1)+") +CHEVAL ["+solde+"] GAIN ["+currentMiseCheval * 18+"] MISE ["+currentMiseCheval+"]");
						}
					}
					if(cheval5.isIn(n)){
						solde += (currentMiseCheval * 18);
						if(enableLogging){
							System.out.println((i+1)+") +CHEVAL ["+solde+"] GAIN ["+currentMiseCheval * 18+"] MISE ["+currentMiseCheval+"]");
						}
					}
				}
				
				indexMise++;
				
				//on gagne seulement après 3 coups car on mise sur un numéro plein après 3 coups
				if(i > 1){
					boolean winSerie = false;
					for(RouletteNumber voisin : voisins){
						if(voisin.getNumber() == n){
							solde += currentMisePlein * 36;
							System.out.println("WINNING AFTER "+(i+1)+" ["+n+"] SOLDE ["+solde+"]");
							totalSolde += solde;
							winSerie = true;
							break;
						}
					}
					if(winSerie){
						break;
					}
				}
				
			}
		}
		
		System.out.println("TOTAL ["+totalSolde+"]");
		
		System.out.println("environ "+nbToursTotal+" minutes, soit "+toPrettyTime(nbToursTotal));
		
		
	}
	
	private static String toPrettyTime(int min){
		int hour = min / 60;
		int minutes = min % 60;
		return hour + "h" + minutes+"m";
	}

}
