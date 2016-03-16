/*City Search*/
import java.io.*;
import java.util.*;
import java.lang.*;

public class CitySearch{

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

                // Exit
                if (str.equals("exit"))
                {
                	scanner.close();
                	break;
                }
                // Search
				long startTime = 0;
				long endTime = 0;

				String[] queries = str.split(" ");

				MySortedArray results = performQuery(searchers, queries[0]);
				if(results == null){
					continue;
				} else {
					results.print(m_database);
				}

				long totalTime = endTime - startTime;
				System.out.println("\nNanoseconds: " + totalTime);
				printMessage();
            }
        } else {
            throw new RuntimeException("Can't run w/out a console!");
        }
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
			default:	System.out.println("Wrong query given.");
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
		System.out.println("\nEnter min and max Latitude, like this:");
	    System.out.println("LA:12.213,22.242");
	   	System.out.println("Or use a landcode, like this:");
	    System.out.println("LC:NL\n");
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