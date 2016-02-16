import java.io.*;
import java.util.*;

public class MyHashMap{
	final int INVALID = 0xFFFFFFFF;
	MyCluster[] m_clusters;

	public MyHashMap(){
		m_clusters = new MyCluster[52];

		for (int i = 0; i < m_clusters.length; i++){
			m_clusters[i] = new MyCluster();
		}
	}

	public boolean Put(String word){

		if (word != null){
			int hash = GetHash(word);
			if (hash != INVALID){
				m_clusters[hash].Put(word);
				return false;
			}
		}
		return true;
	}

	public boolean Search(String word){
		int hash = GetHash(word);
		if (hash != INVALID){
			return m_clusters[hash].Search(word);
		}
		return false;
	}

	private int GetHash(String word){
		if (word == null){
			return INVALID;
		}

		int hash = INVALID;
		if ((word.charAt(0) >= 'a') && (word.charAt(0) <= 'z')){
			hash = word.charAt(0) - 'a';
		}
		else if ((word.charAt(0) >= 'A') && (word.charAt(0) <= 'Z')){
			hash = word.charAt(0) - 'A' + 26;
		}
		return hash;
	}
}