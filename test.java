import java.io.*;
import java.util.*;

public class test{

	public static void main(String[] args){
		File file = new File("wordlist.txt");
		BufferedReader firstReader = null;
		BufferedReader secondReader = null;
		Integer counter = 0;
		try {
		    firstReader = new BufferedReader(new FileReader(file));
		    String text = null;

		    while ((text = firstReader.readLine()) != null) counter ++;
		    System.out.println(counter);
		    secondReader = new BufferedReader(new FileReader(file));
		    String[] firstDatastructure;
		    firstDatastructure = new String[counter];
		    counter = 0;
		    while ((text = secondReader.readLine()) != null){ 
		    	firstDatastructure[counter] = text;
		    	counter ++;
		    }
		    for(int i = 638275; i < 638285; i++){
		    	System.out.println(firstDatastructure[i]);
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (firstReader != null) {
		            firstReader.close();
		            		        }
		        if (secondReader != null) {
		        	secondReader.close();
		        }
		    } catch (IOException e) {
		    }
		}	
	}
}