import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import java.util.Scanner;

public class CitySearch extends ExtraFunctions{

	static final String csvFile = "cities1000Correct.csv";
	static MyCity[] myDatabase;

	/* 
	The Main function, initializes the tree's, from where the input is first check for correctness,
	after that the queries are performed first individualy, after that the SortedArray are combined
	using binary search.
	Takes no input(s)
	Output(s):
	- prints the city matching the description
	*/
	public static void main(String[] args){
		int arraySize = getLineCount(csvFile);
		writeToDatabase(csvFile, arraySize);

		MyCitySearch searchLatitude = new MyCitySearch(myDatabase, enums.Key.LATITUDE);
		MyCitySearch searchLongitude = new MyCitySearch(myDatabase, enums.Key.LONGITUDE);
		MyCitySearch searchCountry = new MyCitySearch(myDatabase, enums.Key.COUNTRY);
		MyCitySearch searchPopulation = new MyCitySearch(myDatabase, enums.Key.POPULATION);
		MyCitySearch searchElevation = new MyCitySearch(myDatabase, enums.Key.ELEVATION);

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

				if(!ExtraFunctions.checkInput(str)){
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

				results[results.length-1].print(myDatabase);

				long totalTime = endTime - startTime;
				System.out.println("\nNanoseconds: " + totalTime);
				printMessage();
            }
        } else {
            throw new RuntimeException("Can't run w/out a console!");
        }
	}

	/* 
	The combine-function takes the SortedArrays and the array with conjunctions, in order to enable
	more advanced queries, the AND conjunction is given preference before the OR conjunction
	Input(s):
	- results, the array with the SortedArray's
	- conjunctions, the array with the given conjunctions.
	Output(s):
	- result[p], the final SortedArray containing the final result
	*/
	private static MySortedArray combine(MySortedArray[] results, String[] conjuctions){
		int counter = 0;
		int[] conjuctionsOR = new int[conjuctions.length];
		for(int i = 0; i < conjuctions.length; i++){
			if(conjuctions[i].equals("AND")){
				results[i+1] = and(results[i], results[i+1]);
				results[i] = null;
			} else {
				conjuctionsOR[counter] = i;
				counter++;
			}
		}
		int p = conjuctions.length-1;
		for(int j = 0; j < counter; j++){
			p = conjuctionsOR[j] + 1;
			MySortedArray firstArray = results[p-1];
			MySortedArray secondArray = results[p];
			while(secondArray == null){
				p++;
				secondArray = results[p];
			}
			results[p] = or(firstArray, secondArray);
		}
		return results[p];
	}

	/* 
	This function combines two SortedArray's by adding all elements available in both SortedArray's
	Input(s):
	- array1, first SortedArray of two to be combined
	- array2, second SortedArray of two to be combined
	Output(s):
	- resultArray, the combined SortedArray
	*/
	private static MySortedArray and(MySortedArray array1, MySortedArray array2){
		MySortedArray resultArray = new MySortedArray();
		for(int i = 0; i < array1.myArray.length; i++){
			if(array2.search(array1.myArray[i])){
				resultArray.insert(array1.myArray[i]);
			}
		}
		resultArray.sort();
		return resultArray;
	}

	/*
	This function combines two SortedArray's by adding all elements from the first array to the 
	resultarray and adding the remaining elements in array2 which are not present in array1
	Input(s):
	- array1, first SortedArray of two to be combined
	- array2, second SortedArray of two to be combined
	Output(s):
	- resultArray, the combined SortedArray	
	*/
	private static MySortedArray or(MySortedArray array1, MySortedArray array2){
		MySortedArray resultArray = new MySortedArray();
		for(int i = 0; i < array2.myArray.length; i++){
			resultArray.insert(array2.myArray[i]);
		}
		for(int i = 0; i < array1.myArray.length; i++){
			if(!array2.search(array1.myArray[i])){
				resultArray.insert(array1.myArray[i]);
			}
		}
		resultArray.sort();
		return resultArray;
	}

	/*
	Takes a query, retrieves the right tree and returns the resulting SortedArray
	Input(s):
	- searchers, array of tree's, used to perform the query
	- query, bit of entire query, in the form of "EL:3,10"
	Output(s):
	- results.myArray, the SortedArray with the results of the query
	*/
	private static MySortedArray performQuery(MyCitySearch[] searchers, String query){
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

	/*
	Prints query form in order to help the user
	Takes no input(s)
	Delivers no output(s)
	*/
	private static void printMessage(){
		System.out.println("\nEnter min and max Latitude(LA)/Longitude(LO)/Population(PO)/Elevation(EL), like this:");
	    System.out.println("LA:12.213,22.242");
	   	System.out.println("Or use a Landcode(LC), like this:");
	    System.out.println("LC:NL\n");
	    System.out.println("You can combine queries by the conjuctions AND and OR, like this:");
	    System.out.println("EL:3,10 AND LC:DE\n");
	}

	/*
	Takes the entire querystring and returns the array of queries or conjunctions, 
	given the begin(1 or 0).
	Input(s):
	- splittedString, the entire query splitted by spacebars
	- begin, 0 when to retrieve the queries, 1 to retrieve the conjunctions
	Output(s):
	- subParts, array of Strings containing the queries or conjunctions
	*/
	private static String[] abstractParts(String[] splittedString, int begin){
		int length = splittedString.length-(splittedString.length/2)-begin;
		String[] subParts = new String[length];

		for(int i = 0; i<subParts.length; i++){
			subParts[i] = splittedString[begin];
			begin += 2;
		} 
		return subParts;
	}

	/*
	Function takes the filename and the length of the file and makes the array of MyCity's, 
	which is the database.
	Input(s):
	- fileName, the name of the file containing the data
	- arraySize, the linecount of the data-file
	Delivers no output(s), but initializes the database
	*/
	private static void writeToDatabase(String fileName, int arraySize){
		myDatabase = new MyCity[arraySize];

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
				myDatabase[i++] = city;
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

  	/*
	Takes the name of the data-file and counts the number of lines in it
	Input(s):
	- fileName, the name of the file containing the data
	Output(s):
	- counter, total number of lines in data-file
  	*/
  	private static int getLineCount(String fileName){
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