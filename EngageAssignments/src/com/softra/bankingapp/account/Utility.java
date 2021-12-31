package com.softra.bankingapp.account;

public class Utility {

	public Utility() {
		
	}
	
	public boolean isValidDate(String date) {
		
		String[] dateSplit = date.split("/");
		
		for(String a : dateSplit) {
			try {
				Integer.parseInt(a);
			}catch(NumberFormatException e) {
				System.out.println("Character not valid: " + a);
				return false;
			}
			
		}
		
		return true;
	}
	
	public boolean isValidName(String name) {
		return false;
	}
}
