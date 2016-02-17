import java.util.*;
import java.io.*;

public class getChars{

	public static char[] getAllChars(File file){
		BufferedReader reader = null;
		ArrayList<Character> list = new ArrayList<Character>();
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
		return objectArrayToCharArray(list.toArray());
	}

	public static void checkForUniqueChars(String text, ArrayList<Character> list){
		for(int i = 0; i < text.length(); i++){
			if(!list.contains(text.charAt(i))){
				list.add(text.charAt(i));
			}
		}
	}

	public static char[] objectArrayToCharArray(Object[] temp){
		char[] array = new char[temp.length];
		for(int i = 0; i < temp.length; i ++){
			array[i] = temp[i].toString().charAt(0);
		}
		return array;
	}
}