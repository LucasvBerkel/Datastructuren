// Authors:
// Lucas van Berkel, 10747958
// Joël Meyer, 10003539

import java.io.*;
import java.util.*;

public class array extends extraFunctions{

	public static void main(String[] args){
		File file = new File("wordlist.txt");
		MyArraylist wordList = writeToArrayList(file);
		fileResults(wordList);
	}

	// Save results in .txt file. 
	// Output(s):
	// - arrayResults.txt, text file with results.
	public static void fileResults(MyArraylist wordList){
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("arrayResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				long startTime = System.nanoTime();
				int[] counter = searchInArrayList(f, wordList);
				long endTime   = System.nanoTime();
				long totalTime = endTime - startTime;
				System.out.println(f.getName());
				out.println("Correct samples/total: " + counter[0] + "/" + counter[1]);
				out.println("Nanoseconds: " + totalTime + '\n');
			}
		    out.close();
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	}

	// Read file into array datastructure.
	// Input(s): 
	// - file, file that has to be converted into array datastructure.
	public static MyArraylist writeToArrayList(File file){
		BufferedReader reader = null;
		MyArraylist datastructure = new MyArraylist();
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text = null;
		    while (true){
		   		String line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	datastructure.put(line); 
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

	// Search for word in given file.
	// Input(s):
	// - file, sample file that has to be checked.
	// - m_array, list of reference words.
	// Output(s):
	// - counter, number of occurences of words in sample file that are also in
	// list of reference words .
	public static int[] searchInArrayList(File file, MyArraylist m_array){
		int counter[] = new int[2];
		counter[0] = 0;
		counter[1] = 0;
		m_array.length();
		try {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    while (true){
		   		String line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	counter[0] = m_array.search(line) ? counter[0] + 1 : counter[0];
		    	counter[1] += 1;
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return counter;
	}
}