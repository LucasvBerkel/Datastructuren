import java.io.*;
import java.util.*;

public class hashTableCCNewHash{

	public static void main(String[] args){
		MyHashTable m_table = new MyHashTable();
		writer("wordlist.txt", m_table);
		fileResults(m_table);
	}

	public static void fileResults(MyHashTable m_table){
		File dir = new File("Samples/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("collisionChainResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				long startTime = System.nanoTime();
				int[] counter = searcher(f.getName(), m_table);
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

	private static void writer(String fileName, MyHashTable m_table){
		File file = new File(fileName);
		try {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    while (true){
		   		String line = reader.readLine();
		   		if (line == null){
		   			break;
		   		}
		    	m_table.put(line); 	 
		    }
		    m_table.writeLength();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	// ipv int class Results, aantal items, hoeveelheid foute items, hoeveelheid goede items
	// toString functie toevoegen welke string teruggeeft van de results
	private static int[] searcher(String fileName, MyHashTable m_table){
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

		    	counter[0] = m_table.search(line) ? counter[0] + 1 : counter[0];	 // X ? A : B voert wanneer X true is A uit en wanneer X false is B. 
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