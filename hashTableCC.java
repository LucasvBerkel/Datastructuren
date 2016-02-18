// Authors:
// Lucas van Berkel, 10747958
// Joël Meyer, 10003539

import java.io.*;
import java.util.*;

public class hashTableCC extends extraFunctions{

	public static void main(String[] args){
		MyHashTableCC m_table = new MyHashTableCC();
		writeToHashTableCC("wordlist.txt", m_table);
		fileResults(m_table);
	}

	// Save results in .txt file
	// Input(s):
	// - m_table, datastructure collision chaining
	// Output(s):
	// - collisionChainResults.txt, text file with results.
	public static void fileResults(MyHashTableCC m_table){
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("collisionChainResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				long startTime = System.nanoTime();
				int[] counter = searchInHashTableCC(f, m_table);
				long endTime   = System.nanoTime();
				long totalTime = endTime - startTime;
				out.println("Correct samples/total: " + counter[0] + "/" + counter[1]);
				out.println("Nanoseconds: " + totalTime + '\n');
				System.out.println(f.getName());
			}
		    out.close();
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	}

	// Reads the file and puts its content in the hashtable.  
	// Input(s):
	// - fileName, name of the reference words file.
	// - m_table, the hash table that implements collision chaining.
	private static void writeToHashTableCC(String fileName, MyHashTableCC m_table){
		File file = new File(fileName);
		try {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    while (true){
		   		String line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	m_table.put(line); 	 
		    }
		    m_table.writeLength();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	// Search through hashtable.
	// Input(s):
	// - file, name of the sample words file.
	// - m_table, the hash table that implements collision chaining.
	private static int[] searchInHashTableCC(File file, MyHashTableCC m_table){
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

		    	counter[0] = m_table.search(line) ? counter[0] + 1 : counter[0];
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