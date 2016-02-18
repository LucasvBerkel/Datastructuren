// Authors:
// Lucas van Berkel, 10747958
// Joël Meyer, 10003539

import java.io.*;
import java.util.*;

public class MyHashTableCC{
	final int INVALID = 0xFFFFFFFF;
	final int DIFF_CHARACTERS = 53;
	
	MyArraylist[] m_arraylists;

	// Constructor, the object contains one attribute.
	// Attribute(s):
	// - m_arraylists, array of MyArrayLists.
	public MyHashTableCC(){
		m_arraylists = new MyArraylist[DIFF_CHARACTERS];

		for (int i = 0; i < m_arraylists.length; i++){
			m_arraylists[i] = new MyArraylist();
		}
	}

	// Adds the word at the proper location.
	// Input(s):
	// - word, word that has to be added.
	// Output(s):
	// - false if proper hash is made, word will be added at the proper location.
	public boolean put(String word){

		if (word != null){
			int hash = getHash(word);
			if (hash != INVALID){
				m_arraylists[hash].put(word);
				return false;
			}
		}
		return true;
	}

	// Search for the hashed word.
	// Input(s):
	// - word, hash of word is found, then searched for.
	// Output(s):
	// - true if word is found, false if not.
	public boolean search(String word){
		int hash = getHash(word);
		if (hash != INVALID){
			return m_arraylists[hash].search(word);
		}
		return false;
	}

	// Set m_arraylist to a fixed size.
	public void writeLength(){
		for(int i = 0; i < DIFF_CHARACTERS; i++){
			m_arraylists[i].length();
		}
	}

	// Hashfunction.
	// Input(s):
	// - word, word that has to be hashed.
	// Output(s):
	// - hash, int that specifies at which array the word should be added.
	private int getHash(String word){
		if (word == null){
			return INVALID;
		}

		int hash = 52;
		if ((word.charAt(0) >= 'a') && (word.charAt(0) <= 'z')){
			hash = word.charAt(0) - 'a';
		}
		else if ((word.charAt(0) >= 'A') && (word.charAt(0) <= 'Z')){
			hash = word.charAt(0) - 'A' + 26;
		}
		return hash;
	}
}