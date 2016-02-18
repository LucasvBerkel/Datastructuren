import java.io.*;
import java.util.*;

public class test{

	// Run the program.
	public static void main(String[] args){
		fileResults();
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

	// Read file into array datastructure.
	public static String[] makeArray(File file, int counter){
		BufferedReader reader = null;
		String[] datastructure = new String[counter];
		String text;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    for(int i = 0; i < counter; i++){
		    	text = reader.readLine();
		    	put(text, datastructure);
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

	// Function to check whether a sample matches one of the words in the wordlist.txt file.
	public static String checkSample(String[] samples, String[] wordList){
		int counter = 0;
		for (int i = 0; i < samples.length; i++){
			counter += checkWord(samples[i], wordList);
		}
		return counter + "/" + samples.length;
	}

	// Save results in .txt file. 
	public static void fileResults(){
		File file = new File("wordlist.txt");
		int counter = getLineCount(file);
		String[] wordList = makeArray(file, counter);
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("openAddressingResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				counter = getLineCount(f);
				String[] sampleList = makeArray(f, counter);
				long startTime = System.nanoTime();
				String result = checkSample(sampleList, wordList);
				long endTime   = System.nanoTime();
				long totalTime = endTime - startTime;
				System.out.println(f.getName());
				out.println("Correct samples/total: " + result);
				out.println("Nanoseconds: " + totalTime + '\n');
			}
		    out.close();
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	}

	public static int hashFunction(String word, int lengthArray){
		int hash = 26;
		if ((word.charAt(0) >= 'a') && (word.charAt(0) <= 'z')){
			hash = word.charAt(0) - 'a';
		}
		else if ((word.charAt(0) >= 'A') && (word.charAt(0) <= 'Z')){
			hash = word.charAt(0) - 'A';
		}
		int clusterSize = lengthArray/27;
		hash = hash*clusterSize;
		return hash;
	}

	public static void put(String text, String[] datastructure){
		int hash = hashFunction(text, datastructure.length);
		boolean check = true;
		for(int i = hash; i < datastructure.length; i++){
			if(datastructure[i] == null){
				datastructure[i] = text;
				check = false;
				break;
			}
		}
		if(check){
			for(int i = 0; i < hash; i++){
				if(datastructure[i] == null){
					datastructure[i] = text;
					break;
				}
			}
		}
	}

	public static int checkWord(String text, String[] wordList){
		int counter = 0;
		int hash = hashFunction(text, wordList.length);
		boolean check = true;
		for(int j = hash; j < wordList.length; j ++){
			if(text.equals(wordList[j])){
				counter = 1;
				check = false;
				break;
			}
		}
		if(check){
			for(int j = 0; j < hash; j ++){
				if(text.equals(wordList[j])){
					counter = 1;
					break;
				}
			}
		}
		return counter;
	}
}