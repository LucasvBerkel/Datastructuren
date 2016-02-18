import java.util.*;
import java.io.*;

public class getChars{

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

	public static void checkForUniqueChars(String text, MyArraylist list){
		for(int i = 0; i < text.length(); i++){
			if(!list.contains(text.charAt(i))){
				list.put(String.valueOf(text.charAt(i)));
				list.length();
			}
		}
	}

	public static char[] objectArrayToCharArray(MyArraylist list){
		list.length();
		char[] array = new char[list.length];
		for(int i = 0; i < list.length; i ++){
			array[i] = list.m_array[i].toString().charAt(0);
		}
		return array;
	}
}