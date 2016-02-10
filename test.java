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
		File file = new File("wordlist.txt");
		int counter = getLineCount(file);
		String[] wordList = makeArray(file, counter);
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		counter = getLineCount(listDir[0]);
		String[] sampleList = makeArray(listDir[0], counter);
		System.out.println(listDir[0]);
		System.out.println(sampleList[90]);
		System.out.println(wordList[90]);
	}
}