import java.io.*;
import java.util.*;

public class array{

	// Run the program.
	public static void main(String[] args){
		fileResults();
	}

	// Read file into array datastructure.
	public static MyArraylist makeArraylist(File file){
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

	// Function to check whether a sample matches one of the words in the wordlist.txt file.
	public static String checkSample(MyArraylist samples, MyArraylist wordList){
		int counter = 0;
		samples.length();
		for (int i = 0; i < samples.length; i++){
			if(wordList.search(samples.m_array[i])){
				counter++;
			}
		}
		return counter + "/" + samples.length;
	}

	// Save results in .txt file. 
	public static void fileResults(){
		File file = new File("wordlist.txt");
		MyArraylist wordList = makeArraylist(file);
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("arrayResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				MyArraylist sampleList = makeArraylist(f);
				wordList.length();
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
}