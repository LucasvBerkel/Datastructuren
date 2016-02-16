import java.io.*;
import java.util.*;

public class hashTableOANewHash{

	public static void main(String[] args){

		MyHashMap m_map = new MyHashMap();
		Writer("wordlist.txt", m_map);

		int foundCount = 0;
		foundCount += Searcher("sample_0OXg@T=T55.txt", m_map);
		System.out.println(foundCount);

		while (!terminalCom(m_map));
	}

	private static void Writer(String fileName, MyHashMap map){
		File file = new File(fileName);
		try {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    while (true){
		   		String line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	map.Put(line); 	 
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	// ipv int class Results, aantal items, hoeveelheid foute items, hoeveelheid goede items
	// toString functie toevoegen welke string teruggeeft van de results
	private static int Searcher(String fileName, MyHashMap map){
		int count = 0;
		File file = new File(fileName);
		try {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    while (true){
		   		String line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	count = map.Search(line) ? count + 1 : count;	 
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return count;
	}

	private static boolean terminalCom(MyHashMap map){
		BufferedReader lineOfText = new BufferedReader(new InputStreamReader(System.in));
		try {
			String textLine = lineOfText.readLine();
			if (textLine.equals("exit")){
				return true;
			}
			if (map.Search(textLine)){
				System.out.println("Word found!");
			}
			else {
				System.out.println("Word not found..");
			}
		}
		catch (IOException e) {
		}	
		return false;
	}
}