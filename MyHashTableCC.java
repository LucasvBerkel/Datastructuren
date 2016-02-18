import java.io.*;
import java.util.*;

public class MyHashTableCC{
	final int INVALID = 0xFFFFFFFF;
	MyArraylist[] m_arraylists;

	public MyHashTableCC(){
		m_arraylists = new MyArraylist[53];

		for (int i = 0; i < m_arraylists.length; i++){
			m_arraylists[i] = new MyArraylist();
		}
	}

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

	public boolean search(String word){
		int hash = getHash(word);
		if (hash != INVALID){
			return m_arraylists[hash].search(word);
		}
		return false;
	}

	public void writeLength(){
		for(int i = 0; i < 53; i++){
			m_arraylists[i].length();
		}
	}

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