import java.io.*;
import java.util.*;

public class test{

	public static void main(String[] args){
		String[] wordList = makeArray("wordlist.txt");
	}

	public String[] makeArray(String textFile){
		File file = new File(textFile);
		BufferedReader firstReader = null;
		BufferedReader secondReader = null;
		Integer counter = 0;
		try {
		    firstReader = new BufferedReader(new FileReader(file));
		    String text = null;
		    while ((text = firstReader.readLine()) != null) counter ++;
		    secondReader = new BufferedReader(new FileReader(file));
		    String[] datastructure = new String[counter];
		    for(int i = 0; i < counter; i++){
		    	datastructure[i] = secondReader.readLine();
		    }
		  	if (firstReader != null) {
		    	firstReader.close();
		    }
		    if (secondReader != null) {
		       	secondReader.close();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return String[] datastructure;	
	}
}