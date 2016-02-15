import java.io.*;
import java.util.*;

public class hashTableOANewHash{
	public static void main(String[] args){
		File file = new File("wordlist.txt");
		int counter = getLineCount(file);
		//String[] wordList = makeArray(file, counter);
		//Int[] keys;
		//for (int i = 0; wordList.length; i++){
		//	keys[i] = keyFunction(wordList[i]);
		//}
		boolean[] hasBeenUsed;

		BufferedReader lineOfText = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
    			String textLine = lineOfText.readLine();
    			if (textLine.equals("exit")){
    				return;
    			}
    		}
    		catch (IOException e) {
    		}
		}	
	}
	/*
	private static boolean searchFunction(int[] keys, int key){
		// zolang een van de keys != key of een van de keys = "DeleteMe", 
		// blijf proberen tot het lukt, dan return true
		// als geen succes en een lege plek return false
	}

	private static int insertFunction(String[] keys, int[] value){
		// blijf proberen tot er een lege plek wordt gevonden, insert
		// element op die plek. 
	}

	private static String deleteFunction(int key){
		// vervang verwijderde item met "DeleteMe"
	}

	private static int hashFunction(int[] key, int trialCount){
		// moet een permutatie worden van 0,1,..,m-1   m = wordList.length 
	}
	*/
	private static int keyFunction(String word){
        int key = 7; 
        for(int i = 0; i < word.length(); i++){
            key = (key * 31) + word.charAt(i) % 100;
        }
        return key;
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

	/*// Function to check whether a sample matches one of the words in the wordlist.txt file.
	public static String checkSample(String[] samples, String[] wordList){
		int counter = 0;
		for (int i = 0; i < samples.length; i++){
			//int startcounter = hashFunction(samples[i]);
			for(int j = startcounter; j < wordList.length; j ++){
				if(samples[i].equals(wordList[j])){
					counter++;
					break;
				}
			}
		}
		return counter + "/" + samples.length;
	}*/
}