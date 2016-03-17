public class ExtraFunctions{

	final static int NUMBEROFZEROS = 6;

	/*
	Functions takes the values and add zeros after point, in order to make values with six figures after the point
	equal to values with zero figures after the point
	Input(s):
	- number, a string containing an uncertain amount of zeros after the point
	Output(s):
	- newNumber, a string containing a certain amount of zeros after the point
	*/
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

	/*
	Takes the entire input String and checks if the given query is a valid one
	Input(s):
	- input, the entire input String
	Output(s):
	- boolean, false if input is wrong, true if input is valid
	*/
	public static boolean checkInput(String input){
		String[] parts = input.split(" ");
		if(parts.length % 2 == 0){
			System.out.println("Inproper amount of queries");
			return false;
		}
		String regex1 = "^(LA|LO|PO|EL):-?\\d*\\.?\\d*,-?\\d*\\.?\\d*$|^LC:\\w{2}$";
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