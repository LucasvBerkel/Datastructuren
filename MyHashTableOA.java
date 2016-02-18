import java.util.*;
import java.io.*;

public class MyHashTableOA{
	String[] m_array = null;
	int length;

	public MyHashTableOA(int size){
		m_array = new String[size];
		length = size;
	}

	public void toHashTable(String[] array){
		int hash = 0;
		for(int j = 0; j < this.length; j++){
			hash = hashFunction(array[j]);
			boolean check = true;
			for(int i = hash; i < this.length; i++){
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

	public int hashFunction(String word){
		int hash = word.length();
		for(int i = 0; i < word.length(); i++){
			hash = (hash * 31 + word.charAt(i));
		}
		return Math.abs(hash % m_array.length);

	/*int hash = 26;
		if ((word.charAt(0) >= 'a') && (word.charAt(0) <= 'z')){
			hash = word.charAt(0) - 'a';
		}
		else if ((word.charAt(0) >= 'A') && (word.charAt(0) <= 'Z')){
			hash = word.charAt(0) - 'A';
		}
		int clusterSize = this.length/27;
		hash = hash*clusterSize;
		return hash;*/
	}

	public boolean checkWord(String text){
		int hash = hashFunction(text);
		for(int j = hash; j < this.length; j ++){
			if(text.equals(this.m_array[j])){
				return true;
			}
		}
		for(int j = 0; j < hash; j ++){
			if(text.equals(this.m_array[j])){
				return true;
			}
		}
		return false;
	}
}