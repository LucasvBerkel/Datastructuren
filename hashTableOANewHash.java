import java.io.*;
import java.util.*;
/* MyHashMap.java,  DeletedEntry.java en MyHashEntry.java heb ik grotendeels van internet gehaald
   om eerst uberhaupt te laten werken en dan te kijken hoe het nou precies werkte. Alleen zit ik 
   nu met een paar foutmeldingen bij o.a. map.put(hashFunction(wordList[i]) en zit ik even vast.
   De fouten heb ik er uit gehaald maar ik zit nu dus even vast.
  */
public class hashTableOANewHash extends MyHashMap{


	public static void main(String[] args){
		File file = new File("wordlist.txt");
		int counter = getLineCount(file);
		String[] wordList = makeArray(file, counter);

		MyHashMap map = new MyHashMap();
		for(int i = 0; i < wordList.length; i++){
			map.put(hashFunction(wordList[i]), i);
		}	

		BufferedReader lineOfText = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
    			String textLine = lineOfText.readLine();
    			if (textLine.equals("exit")){
    				return;
    			}
    			

    		}
    		catch (IOException e) {
    		}
		}	
	}

	  private static int hashFunction(String word){
	        int hash = word.charAt(0);
	        //for(int i = 0; i < word.length(); i++){
	            //  hash = (hash * 31) + word.charAt(i) % 100;
	        //}
	        return hash;
	  }


	// Read file into array datastructure.
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

	// Count number of lines of file to specify size of array.
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

	/*// Function to check whether a sample matches one of the words in the wordlist.txt file.
	public static String checkSample(String[] samples, String[] wordList){
		int counter = 0;
		for (int i = 0; i < samples.length; i++){
			//int startcounter = hashFunction(samples[i]);
			for(int j = startcounter; j < wordList.length; j ++){
				if(samples[i].equals(wordList[j])){
					counter++;
					break;
				}
			}
		}
		return counter + "/" + samples.length;
	}*/
}