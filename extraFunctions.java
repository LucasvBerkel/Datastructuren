import java.io.*;
import java.util.*;
import java.lang.*;

public class extraFunctions{

	final static int NUMBEROFZEROS = 6;

	public static String addZeros(String number){
		String[] values = number.split("\\.");
		String decimals;
		try{
			decimals = values[1];
		} catch (ArrayIndexOutOfBoundsException e){
			decimals = "";
		}
		for(int i = decimals.length(); i < NUMBEROFZEROS; i++){
			decimals = decimals + "0";
		}
		String newNumber = values[0] + "." + decimals;
		return newNumber;
	}
}