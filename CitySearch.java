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

		MyCitySearch searchCountry = new MyCitySearch(m_database, enums.Key.COUNTRY);
		MyCitySearch searchLatitude = new MyCitySearch(m_database, enums.Key.LATITUDE);

		Console objConsole = System.console();
        if (objConsole != null) {

            System.out.println("\nEnter min and max Latitude, like this:");
            System.out.println("12.122,13.782");
            System.out.println("Or use a landcode, like this:");
            System.out.println("NL\n");
            
            // reader method call.
            Scanner scanner = new Scanner(objConsole.reader());
            while (scanner.hasNext()) {
                String str = scanner.next();

                // Exit
                if (str.equals("exit"))
                {
                	scanner.close();
                	break;
                }

                // Search
                MyResults results;
				long startTime;
				long endTime;

				// check invoer
				// verdeel invoer
				// array van binary trees
				// combineer die trees
				// print resulting tree

                // Landcode
                if ((str.charAt(0) >= 'A') && (str.charAt(0) <= 'Z'))
                {
                	startTime = System.nanoTime();
	                results = searchCountry.search(str);
					endTime = System.nanoTime();

	                results.print();
                }

                // Range
                else
                {
	                String[] values = str.split(",");

	                startTime = System.nanoTime();
	                results = searchLatitude.search(values[0], values[1]);	
	                //results = searchLatitude.search("16.94253", "33.94253");
	                endTime = System.nanoTime();

	                results.print();
                }

				long totalTime = endTime - startTime;
				System.out.println("\nNanoseconds: " + totalTime);

	            System.out.println("\nEnter min and max Latitude, like this:");
	            System.out.println("12.122,13.782");
	            System.out.println("Or use a landcode, like this:");
	            System.out.println("NL\n");
            }
        } else {
            throw new RuntimeException("Can't run w/out a console!");
        }
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