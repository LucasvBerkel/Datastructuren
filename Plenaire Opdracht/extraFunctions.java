// Authors:
// Lucas van Berkel, 10747958
// Joël Meyer, 10003539

import java.util.*;
import java.io.*;

public class extraFunctions{

	// Function to obtain an array containing all the characters in the given file
	// Input(s):
	// - file, file from which every unique character has to be obtained
	// Output(s):
	// - char[], array containing the unique chars
	public static char[] getAllChars(File file){
		BufferedReader reader = null;
		MyArraylist list = new MyArraylist();
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String text;
		    while ((text = reader.readLine()) != null){
		    	checkForUniqueChars(text, list);
		    }
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
		return objectArrayToCharArray(list);
	}

	// Function to put unique chars of a word if they are not represented in the list
	// Input(s):
	// - text, the word which will be dissected in individual chars
	// - list, the MyArrayList containing all the chars current known in the file
	// Delivers no output(s):
	public static void checkForUniqueChars(String text, MyArraylist list){
		for(int i = 0; i < text.length(); i++){
			if(!list.contains(text.charAt(i))){
				list.put(String.valueOf(text.charAt(i)));
				list.length();
			}
		}
	}

	// Takes an MyArrayList and converts it to an char-array, which is simplier to handle later on
	// Input(s):
	// list, an MyArrayList containing all the unique chars(saved as String)
	// Output(s):
	// char[], containing all the unique chars
	public static char[] objectArrayToCharArray(MyArraylist list){
		list.length();
		char[] array = new char[list.length];
		for(int i = 0; i < list.length; i ++){
			array[i] = list.m_array[i].toString().charAt(0);
		}
		return array;
	}

	// Function to convert an vocabulary file into an array
	// Input(s):
	// - file, file to be converted into an array
	// Output(s):
	// - String[], array containing all the words in the file
	public static String[] makeArray(File file){
		int size = getLineCount(file);
		BufferedReader reader = null;
		String[] sampleArray = new String[size];
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line;
		   	for(int i = 0; i < size; i++){
		   		line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	sampleArray[i] = line;
		    }
		    if (reader != null) {
		       	reader.close();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return sampleArray;
	}

	// Function to count the number of words in a file, used to contstruct the array
	// Input(s):
	// - file, file from which the lines must be counted
	// Output(s):
	// - counter, total number of lines 
	public static int getLineCount(File file){
		BufferedReader reader = null;
		int counter = 0;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line;
		    while (true){
		   		line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	counter++; 
		    }
		    if (reader != null) {
		       	reader.close();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return counter;
	}
}