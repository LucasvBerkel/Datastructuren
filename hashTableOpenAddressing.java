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

	public static int hashFunction(String sample){
		char letter = sample.charAt(0);
		int location;
		switch(letter){
			case  'A' : location = 0
			case  'B' : location = 11877
			case  'C' : location = 22337
			case  'D' : location = 35178
			case  'E' : location = 41623
			case  'F' : location = 46886
			case  'G' : location = 51097
			case  'H' : location = 57905
			case  'I' : location = 65250
			case  'J' : location = 67918
			case  'K' : location = 70970
			case  'L' : location = 76289
			case  'M' : location = 83551
			case  'N' : location = 95135
			case  'O' : location = 99205
			case  'P' : location = 102553
			case  'Q' : location = 112360
			case  'R' : location = 112853
			case  'S' : location = 118213
			case  'T' : location = 131038
			case  'U' : location = 138724
			case  'V' : location = 139811
			case  'W' : location = 142340
			case  'X' : location = 146154
			case  'Y' : location = 146452
			case  'Z' : location = 147428
			case  'a' : location = 148759
			case  'b' : location = 180420
			case  'c' : location = 205037
			case  'd' : location = 248119
			case  'e' : location = 273786
			case  'f' : location = 292254
			case  'g' : location = 308598
			case  'h' : location = 323488
			case  'i' : location = 341912
			case  'j' : location = 359510
			case  'k' : location = 363110
			case  'l' : location = 368608
			case  'm' : location = 381817
			case  'n' : location = 408057
			case  'o' : location = 426328
			case  'p' : location = 442948
			case  'q' : location = 488956
			case  'r' : location = 491396
			case  's' : location = 514030
			case  't' : location = 567054
			case  'u' : location = 591751
			case  'v' : location = 618131
			case  'w' : location = 625199
			case  'x' : location = 634228
			case  'y' : location = 634860
			case  'z' : location = 636380
			case  'Ã' : location = 638185
		}
		return location;
	}
}