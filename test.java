import java.io.*;
import java.util.*;

public class test{

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

	public static void main(String[] args){
		fileResults();
	}

	public static String checkSample(String[] samples, String[] wordList){
		int counter = 0;
		for (int i = 0; i < samples.length; i++){
			for(int j = 0; j < wordList.length; j ++){
				if(samples[i].equals(wordList[j])){
					counter++;
					break;
				}
			}
		}
		return counter + "/" + samples.length;
	}

	public static void fileResults(){
		File file = new File("wordlist.txt");
		int counter = getLineCount(file);
		String[] wordList = makeArray(file, counter);
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();

		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("arrayResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				counter = getLineCount(f);
				String[] sampleList = makeArray(f, counter);
				long startTime = System.nanoTime();
				out.println("Correct samples/total: " + checkSample(sampleList, wordList));
				long endTime   = System.nanoTime();
				long totalTime = endTime - startTime;
				out.println("Nanoseconds: " + totalTime + '\n');
			}
		    out.close();
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
	}
}