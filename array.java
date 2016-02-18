import java.io.*;
import java.util.*;

public class array extends extraFunctions{

	// Run the program.
	public static void main(String[] args){
		fileResults();
	}

	// Save results in .txt file. 
	public static void fileResults(){
		File file = new File("wordlist.txt");
		MyArraylist wordList = writeToArrayList(file);
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

	public static int[] searchInArrayList(File file, MyArraylist m_array){
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
		    	counter[0] = m_array.search(line) ? counter[0] + 1 : counter[0];	 // X ? A : B voert wanneer X true is A uit en wanneer X false is B. 
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