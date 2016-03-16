import java.io.*;
import java.util.*;
import java.lang.*;

public class extraFunctions{

	final static int NUMBEROFZEROS = 6;

	public static String addZeros(String number){
		String[] values = number.split("\\.");
		String decimals;
		if(values.length == 2){
			decimals = values[1];
		} else {
			decimals = "";
		}
		for(int i = decimals.length(); i < NUMBEROFZEROS; i++){
			decimals = decimals + "0";
		}
		String newNumber = values[0] + "." + decimals;
		return newNumber;
	}

	public static boolean checkInput(String input){
		String[] parts = input.split(" ");
		if(parts.length % 2 == 0){
			System.out.println("Inproper amount of queries");
			return false;
		}
		String regex1 = "^(LA|LO|PO|EL):\\d*\\.?\\d*,\\d*\\.?\\d*$|^LC:\\w{2}$";
		String regex2 = "^AND$|^OR$";
		for(int i = 0; i < parts.length; i+=2){
			if(!parts[i].matches(regex1)){
				System.out.println("Query does not fit format: " + parts[i]);
				return false;
			}
			if(i+1 != parts.length){
				if(!parts[i+1].matches(regex2)){
					System.out.println("Conjuctions does not fit format: " + parts[i+1]);
					return false;
				}
			}
		}
		return true;
	}
}