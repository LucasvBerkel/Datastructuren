/*City Search*/
import java.io.*;
import java.util.*;
import java.lang.*;

public class CitySearch{

	static final String csvFile = "cities1000Correct.csv";

	static MyCity[] m_database;
	static MyCitySearch m_search;

	public static void main(String[] args){
		int arraySize = getLineCount(csvFile);
		writeToDatabase(csvFile, arraySize);
		m_search = new MyCitySearch(m_database, MyCitySearch.Key.ELEVATION);


		/*Console objConsole = System.console();

        if (objConsole != null) {

            System.out.print("Enter your full name : ");
            
            // reader method call.
            Scanner scanner = new Scanner(objConsole.reader());
            while (scanner.hasNext()) {
                String str = scanner.next();
                System.out.println(str);
            }
        } else {
            throw new RuntimeException("Can't run w/out a console!");
        }*/
	}

	private static void writeToDatabase(String fileName, int arraySize){
		File file = new File(fileName);

		m_database = new MyCity[arraySize];
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";

		int i = 0;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			int counter = 1; 
			while ((line = br.readLine()) != null) {
				String[] values = line.split(csvSplitBy);
				counter++;

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