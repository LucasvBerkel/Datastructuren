// Authors:
// Lucas van Berkel, 10747958
// Joël Meyer, 10003539

import java.util.*;
import java.io.*;

public class MyHashTableOA{
	String[] m_array = null;
	int length;

	// The constructor, the object only contains an array and its length
	// Input(s):
	// - size, the size of the array to be constructed
	// Attribute(s):
	// - m_array, the array of strings containing the words
	// - length, the length of the array containing the words
	public MyHashTableOA(int size){
		m_array = new String[size];
		length = size;
	}

	// The hashfunctions, calculates the most probable index of the word using its first letter
	// Input(s):
	// - word, the word from which the most probable index in the HashTable must be calculated
	// Output(s):
	// - hash, index of word in HashTable
	public int hashFunction(String word){
		int hash = 26;
		if ((word.charAt(0) >= 'a') && (word.charAt(0) <= 'z')){
			hash = word.charAt(0) - 'a';
		}
		else if ((word.charAt(0) >= 'A') && (word.charAt(0) <= 'Z')){
			hash = word.charAt(0) - 'A';
		}
		int clusterSize = this.length/27;
		hash = hash*clusterSize;
		return hash;
	}

	// The checkword function takes the word from the sample file and checks it through the HashTable
	// Input(s):
	// - text, the word to be checked
	// Output(s):
	// - boolean, if word exists in HashTable or not. 
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