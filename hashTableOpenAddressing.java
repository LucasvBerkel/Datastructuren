import java.io.*;
import java.util.*;

public class hashTableOpenAddressing{
	public static void main(String[] args){
		File file = new File("wordlist.txt");
		BufferedReader reader = null;
		Integer counter = 0;	
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;
		    String tempText = " ";
		    while ((text = reader.readLine()) != null){
		    	if(text.charAt(0) != tempText.charAt(0)){
		    		System.out.printf("%s %s \n",text.charAt(0),(int)counter);
		    		tempText = text;
		    	}
		    counter++;
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
			try{
		   		if (reader != null) {
		    		reader.close();
		    	}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}



	// Read file into array datastructure.
	public static String[] makeArray(File file, int counter){
		BufferedReader reader = null;
		String[] datastructure = new String[counter];
		try {
		    reader = new BufferedReader(new FileReader(file));
		    for(int i = 0; i < counter; i++){
		    	datastructure[i] = reader.readLine();
		    }
		    if (reader != null) {
		       	reader.close();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return datastructure;	
	}

	// Count number of lines of file to specify size of array.
	public static int getLineCount(File file){
		BufferedReader reader = null;
		Integer counter = 0;	
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;
		    while ((text = reader.readLine()) != null) counter ++;
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
			try{
		   		if (reader != null) {
		    		reader.close();
		    	}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		return counter;
	}

	// Function to check whether a sample matches one of the words in the wordlist.txt file.
	public static String checkSample(String[] samples, String[] wordList){
		int counter = 0;
		for (int i = 0; i < samples.length; i++){
			for(int j = 0; j < wordList.length; j ++){
				if(samples[i].equals(wordList[j])){
					counter++;
					break;
				}
			}
		}
		return counter + "/" + samples.length;
	}

	public static int hashFunction(String sample, int length){
		//char letter = sample;
		return 1;
	}
}