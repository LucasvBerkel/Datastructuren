import java.util.*;
import java.io.*;

public class trie extends getChars{

	public static void main(String[] args){
		File file = new File("wordlist.txt");
		MyTrie trie = new MyTrie();
		char[] allChars = getAllChars(file);
		trie.totalCharacters = allChars;
		trie.children = new MyTrie[allChars.length];
		writeToTrie(file, trie);
		fileResults(trie);
	}

	public static void fileResults(MyTrie trie){
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("trieResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				long startTime = System.nanoTime();
				int[] counter = searchInTrie(f.getName(), trie);
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

	private static int[] searchInTrie(String fileName, MyTrie trie){
		int counter[] = new int[2];
		counter[0] = 0;
		counter[1] = 0;
		File file = new File("Samples/"+fileName);
		try {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    while (true){
		   		String line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	counter[0] = trie.isWord(line) ? counter[0] + 1 : counter[0];	 // X ? A : B voert wanneer X true is A uit en wanneer X false is B. 
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