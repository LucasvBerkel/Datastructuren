import java.io.*;
import java.util.*;

public class hashTableOANewHash extends getChars{

	// Run the program.
	public static void main(String[] args){
		fileResults();
	}

	// Save results in .txt file. 
	public static void fileResults(){
		File file = new File("wordlist.txt");
		String[] tempArray = makeArray(file);
		MyHashTableOA m_table = new MyHashTableOA(tempArray.length);
		m_table.writeToHashTableOA(tempArray);
		File dir = new File("test/");
		File listDir[] = dir.listFiles();
		try {
	      	PrintStream out = new PrintStream(new FileOutputStream("openAddressingResults.txt"));
		    for(File f : listDir){
				out.println("Filename: " + f.getName());
				long startTime = System.nanoTime();
				String result = searchInHashTableOA(f, m_table);
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

	public static void writeToHashTableOA(String[] array, MyHashTableOA m_table){
		int hash = 0;
		for(int j = 0; j < m_table.length; j++){
			hash = hashFunction(array[j]);
			boolean check = true;
			for(int i = hash; i < m_table.length; i++){
				if(m_array[i] == null){
					m_array[i] = array[i];
					check = false;
					break;
				}
			}
			if(check){
				for(int i = 0; i < hash; i++){
					if(m_array[i] == null){
						m_array[i] = array[i];
						break;
					}
				}
			}
		}
	}

	public static int[] searchInHashTableOA(File file, MyHashTableOA m_table){
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