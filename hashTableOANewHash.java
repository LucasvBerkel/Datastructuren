import java.io.*;
import java.util.*;

public class hashTableOANewHash extends getChars{

	// Run the program.
	public static void main(String[] args){
		fileResults();
	}

	// Function to check whether a sample matches one of the words in the wordlist.txt file.
	public static String checkSample(String[] samples, MyHashTableOA m_table){
		int counter = 0;
		for (int i = 0; i < samples.length; i++){
			counter += m_table.checkWord(samples[i]);
		}
		return counter + "/" + samples.length;
	}

	// Save results in .txt file. 
	public static void fileResults(){
		File file = new File("wordlist.txt");
		String[] tempArray = makeArray(file);
		MyHashTableOA m_table = new MyHashTableOA(tempArray.length);
		m_table.toHashTable(tempArray);
		File dir = new File("test/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("openAddressingResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				String[] sampleList = makeArray(f);
				long startTime = System.nanoTime();
				String result = checkSample(sampleList, m_table);
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
}