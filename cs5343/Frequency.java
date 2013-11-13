package cs5343;

import java.util.Scanner;

public class Frequency {
	static void getAbb(String unit, int quantity){
		if(unit.equals("Hz"))
			if(quantity>=30 && quantity<300)
				System.out.println("ELF");
			else if(quantity>300 && quantity<=3000)
				System.out.println("VF");
			else
				System.out.println("unknown");
		else if(unit.equals("KHz"))
			if(quantity>=3 && quantity<=30) 
				System.out.println("VLF"); 
			else if(quantity>30 && quantity<=300) 
				System.out.println("LF"); 
			else if(quantity>300 && quantity<=3000) 
				System.out.println("MF");
			else
				System.out.println("unknown");
		else if(unit.equals("MHz"))
			if(quantity>=3 && quantity<=30) 
				System.out.println("HF");
			else if(quantity>30 && quantity<=300)
				System.out.println("VHF");
			else if(quantity>300 && quantity<=3000)
				System.out.println("UHF");
			else
				System.out.println("unknown");
		else if(unit.equals("GHz"))
			if(quantity>=3 && quantity<=30)
				System.out.println("SHF");
			else if(quantity>30 && quantity<=300)
				System.out.println("EHF");
			else
				System.out.println("unkown");
		else
			System.out.println("unkown");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);                                  // Create a Scanner
		  
        System.out.println("Please type in frequence quantity:");                     // Prompt the user to input a frequence
        int quantity = Integer.parseInt(input.nextLine());

        System.out.println("Please type in frequence unit:");                     // Prompt the user to input a frequence
        String unit = input.nextLine();
        
        getAbb(unit, quantity);
	}

}
