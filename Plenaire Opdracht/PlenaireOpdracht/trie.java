// Authors:
// Lucas van Berkel, 10747958
// Joël Meyer, 10003539

import java.util.*;
import java.io.*;

public class trie extends extraFunctions{

	// Start function, initializes the root of the trie. The allChars function takes the vocabulary 
	// and creates an array containing all the characters in the vocabulary.
	// From there the amount of children is initialised.
	// Takes no input(s):
	// Delivers no output(s):
	public static void main(String[] args){
		File file = new File("wordlist.txt");
		MyTrie trie = new MyTrie();
		char[] allChars = getAllChars(file);
		trie.totalCharacters = allChars;
		trie.children = new MyTrie[allChars.length];
		writeToTrie(file, trie);
		fileResults(trie);
	}

	// Lists all the sample files and prints the results a file in a txt file.
	// Input(s):
	// - trie, objectlike to a trie, the used datastructure
	// Delivers no output(s):
	public static void fileResults(MyTrie trie){
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("trieResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				long startTime = System.nanoTime();
				int[] counter = searchInTrie(f, trie);
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

	// Takes the vocabulary and adds them to the trie.
	// Input(s):
	// - file, the vocabulary file
	// Delivers no output(s):
	private static void writeToTrie(File file, MyTrie trie){
		try {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    while (true){
		   		String line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	trie.add(line);	 
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	// Takes the sample file and searches each word in the trie.
	// Input(s):
	// - file, the sample file to be checked
	// - trie, the used datastructure
	// Output(s):
	// - counter[], a int array containing the matched words and the length of the file
	private static int[] searchInTrie(File file, MyTrie trie){
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
		    	counter[0] = trie.isWord(line) ? counter[0] + 1 : counter[0]; 
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