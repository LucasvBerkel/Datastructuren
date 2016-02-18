import java.io.*;
import java.util.*;

public class MyHashMap{
	final int INVALID = 0xFFFFFFFF;
	MyArraylist[] m_arraylists;

	public MyHashMap(){
		m_arraylists = new MyArraylist[53];

		for (int i = 0; i < m_arraylists.length; i++){
			m_arraylists[i] = new MyArraylist();
		}
	}

	public boolean Put(String word){

		if (word != null){
			int hash = GetHash(word);
			if (hash != INVALID){
				m_arraylists[hash].Put(word);
				return false;
			}
		}
		return true;
	}

	public boolean Search(String word){
		int hash = GetHash(word);
		if (hash != INVALID){
			return m_arraylists[hash].Search(word);
		}
		return false;
	}

	private int GetHash(String word){
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