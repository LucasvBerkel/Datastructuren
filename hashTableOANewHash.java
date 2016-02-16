import java.io.*;
import java.util.*;

public class hashTableOANewHash{

	public static void main(String[] args){

		MyHashMap m_map = new MyHashMap();
		Writer("wordlist.txt", m_map);

		int foundCount = 0;
		long startTime = System.nanoTime();
		foundCount += Searcher("sample_8Lg2uNPd22[.txt", m_map);
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(foundCount);
		System.out.println(totalTime);

		while (!terminalCom(m_map));
	}

	public static void fileResults(){
		File file = new File("wordlist.txt");
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("openAdressingResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				long startTime = System.nanoTime();
				out.println("Correct samples/total: " + "Searcher(f.getName(), m_map))");
				long endTime   = System.nanoTime();
				long totalTime = endTime - startTime;
				out.println("Nanoseconds: " + totalTime + '\n');
			}
		    out.close();
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    }
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
		    	count = map.Search(line) ? count + 1 : count;	 // X ? A : B voert wanneer X true is A uit en wanneer X false is B. 
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