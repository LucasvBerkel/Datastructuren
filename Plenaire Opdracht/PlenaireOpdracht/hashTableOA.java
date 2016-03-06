// Authors:
// Lucas van Berkel, 10747958
// Joël Meyer, 10003539

import java.io.*;
import java.util.*;

public class hashTableOA extends extraFunctions{

	// Start function, initializes the vocabulary and makes a temporary array. The temp-array is used to make the categorized HashTableOA,
	// OA stands for Open Addressing
	// Takes no input(s):
	// Delivers no output(s):
	public static void main(String[] args){
		File file = new File("wordlist.txt");
		String[] tempArray = makeArray(file);
		MyHashTableOA m_table = new MyHashTableOA(tempArray.length);
		writeToHashTableOA(tempArray, m_table);
		fileResults(m_table);
	}

	// Lists all the sample files and prints the results a file in a txt file.
	// Input(s):
	// - m_table, objectlike to a HashTable using OpenAddressing
	// Delivers no output(s):
	public static void fileResults(MyHashTableOA m_table){
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("openAddressingResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				long startTime = System.nanoTime();
				int[] counter = searchInHashTableOA(f, m_table);
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

	// Takes the temp-array and copies it more less to the HashTable, categorized using the hashfunction
	// Input(s):
	// - array, the temp-array containing the vocabulary
	// Delivers no output(s):
	public static void writeToHashTableOA(String[] array, MyHashTableOA m_table){
		for(int j = 0; j < m_table.length; j++){
			m_table.add(array[j]);
		}
	}

	// Takes a sample file and retrieves the words from it. Every word is searched through the HashTable
	// Input(s):
	// - file, the sample file to be checked
	// - m_table, the HashTable constructed earlier
	// Output(s):
	// - counter[], a int array containing the matched words and the length of the file
	public static int[] searchInHashTableOA(File file, MyHashTableOA m_table){
		int counter[] = new int[2];
		counter[0] = 0;
		counter[1] = 0;
		try {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    while (true){
		   		String line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	counter[0] = m_table.checkWord(line) ? counter[0] + 1 : counter[0];
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