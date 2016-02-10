import java.io.*;
import java.util.*;

public class test{

	public static int getLineCount(String textFile){
		File file = new File(textFile);
		BufferedReader firstReader = null;
		Integer counter = 0;	
		try {
		    firstReader = new BufferedReader(new FileReader(file));
		    String text = null;
		    while ((text = firstReader.readLine()) != null) counter ++;
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
			try{
		   		if (firstReader != null) {
		    		firstReader.close();
		    	}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		return counter;
	}

	public static String[] makeArray(String textFile, int counter){
		File file = new File(textFile);
		BufferedReader secondReader = null;
		String[] datastructure = new String[counter];
		try {
		    secondReader = new BufferedReader(new FileReader(file));
		    for(int i = 0; i < counter; i++){
		    	datastructure[i] = secondReader.readLine();
		    }
		    if (secondReader != null) {
		       	secondReader.close();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return datastructure;	
	}

	public static void main(String[] args){
		int counter = getLineCount("wordlist.txt");
		String[] wordList = makeArray("wordlist.txt", counter);
		System.out.println(wordList[10000]);
	}
}