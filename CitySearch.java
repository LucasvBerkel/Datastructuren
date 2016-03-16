import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import java.util.Scanner;

public class CitySearch extends extraFunctions{

	static final String csvFile = "cities1000Correct.csv";
	static MyCity[] m_database;

	public static void main(String[] args){
		int arraySize = getLineCount(csvFile);
		writeToDatabase(csvFile, arraySize);

		MyCitySearch searchLatitude = new MyCitySearch(m_database, enums.Key.LATITUDE);
		MyCitySearch searchLongitude = new MyCitySearch(m_database, enums.Key.LONGITUDE);
		MyCitySearch searchCountry = new MyCitySearch(m_database, enums.Key.COUNTRY);
		MyCitySearch searchPopulation = new MyCitySearch(m_database, enums.Key.POPULATION);
		MyCitySearch searchElevation = new MyCitySearch(m_database, enums.Key.ELEVATION);

		MyCitySearch[] searchers = new MyCitySearch[5];
		searchers[0] = searchLatitude;
		searchers[1] = searchLongitude;
		searchers[2] = searchCountry;
		searchers[3] = searchPopulation;
		searchers[4] = searchElevation;

		Console objConsole = System.console();
        if (objConsole != null) {

        	printMessage();
            
            // reader method call.
            Scanner scanner = new Scanner(objConsole.reader());
            while (scanner.hasNext()) {

                String str = scanner.nextLine();
                System.out.println("");

                // Exit
                if (str.equals("exit"))
                {
                	scanner.close();
                	break;
                }
                // Search
				long startTime = 0;
				long endTime = 0;

				if(!extraFunctions.checkInput(str)){
					printMessage();
					continue;
				}

				String[] splittedString = str.split(" ");
				String[] queries = abstractParts(splittedString, 0);
				String[] conjuctions = abstractParts(splittedString, 1);

				startTime = System.nanoTime();

				MySortedArray[] results = new MySortedArray[queries.length];
				for(int i = 0; i< results.length; i++){
					results[i] = performQuery(searchers, queries[i]);
				}
				if(results.length != 1){
					MySortedArray endResults = combine(results, conjuctions);
				}

				endTime = System.nanoTime();

				results[results.length-1].print(m_database);

				long totalTime = endTime - startTime;
				System.out.println("\nNanoseconds: " + totalTime);
				printMessage();
            }
        } else {
            throw new RuntimeException("Can't run w/out a console!");
        }
	}

	private static MySortedArray combine(MySortedArray[] results, String[] conjuctions){
		for(int i = 0; i < conjuctions.length; i++){
			if(conjuctions[i].equals("AND")){
				results[i+1] = and(results[i], results[i+1]);
			} else {
				results[i+1] = or(results[i], results[i+1]);
			}
		}
		return results[results.length-1];
	}

	private static MySortedArray and(MySortedArray array1, MySortedArray array2){
		MySortedArray resultArray = new MySortedArray();
		for(int i = 0; i < array1.m_array.length; i++){
			if(array2.search(array1.m_array[i])){
				resultArray.insert(array1.m_array[i]);
			}
		}
		resultArray.sort();
		return resultArray;
	}

	private static MySortedArray or(MySortedArray array1, MySortedArray array2){
		MySortedArray resultArray = new MySortedArray();
		for(int i = 0; i < array2.m_array.length; i++){
			resultArray.insert(array2.m_array[i]);
		}
		for(int i = 0; i < array1.m_array.length; i++){
			if(!array2.search(array1.m_array[i])){
				resultArray.insert(array1.m_array[i]);
			}
		}
		resultArray.sort();
		return resultArray;
	}

	public static MySortedArray performQuery(MyCitySearch[] searchers, String query){
		MyResults results;
        String[] subQuery = query.split(":");
        MyCitySearch searcher;
        boolean check = true;

		switch(subQuery[0]){
			case "LA":	searcher = searchers[0];
     					break;
			case "LO":	searcher = searchers[1];
						break;
			case "LC": 	searcher = searchers[2];
						check = false;
						break;
			case "PO":	searcher = searchers[3];
						break;
			case "EL":	searcher = searchers[4];
						break;
			default:	System.out.println("Wrong query given.\n");
						printMessage();
						return null;
        }
        if (check){
          	String[] values = subQuery[1].split(",");
	        results = searcher.search(values[0], values[1], subQuery[0]);	
	    } else {
	        results = searcher.search(subQuery[1]);
	    }
		results.addToArray();
	    return results.myArray;
	}

	private static void printMessage(){
		System.out.println("\nEnter min and max Latitude(LA)/Longitude(LO)/Population(PO)/Elevation(EL), like this:");
	    System.out.println("LA:12.213,22.242");
	   	System.out.println("Or use a Landcode(LC), like this:");
	    System.out.println("LC:NL\n");
	    System.out.println("You can combine queries by the conjuctions AND and OR, like this:");
	    System.out.println("EL:3,10 AND LC:DE\n");
	}

	private static String[] abstractParts(String[] splittedString, int begin){
		int length = splittedString.length-(splittedString.length/2)-begin;
		String[] subParts = new String[length];

		for(int i = 0; i<subParts.length; i++){
			subParts[i] = splittedString[begin];
			begin += 2;
		} 
		return subParts;
	}

	private static void writeToDatabase(String fileName, int arraySize){
		m_database = new MyCity[arraySize];

		File file = new File(fileName);
		BufferedReader br = null;

		String line;
		int i = 0;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();

			int counter = 1; 
			while ((line = br.readLine()) != null) {
				counter++;
				String[] values = line.split(",");

				MyCity city = new MyCity();
				for (int j = 0; j < values.length; j++){
					city.put(values[j], j, counter);
				}
				m_database[i++] = city;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
  	}

  	public static int getLineCount(String fileName){
  		File file = new File(fileName);

		BufferedReader reader = null;
		int counter = 0;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line;
		    while (true){
		   		line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	counter++; 
		    }
		    if (reader != null) {
		       	reader.close();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return counter;
	}
}