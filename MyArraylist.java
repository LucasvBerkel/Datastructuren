// Authors:
// Lucas van Berkel, 10747958
// Joël Meyer, 10003539

import java.io.*;
import java.util.*;

public class MyArraylist{
	final int INITIALARRAYSIZE = 128;

	int m_arrayCnt = 0;
	String[] m_array = null;
	int length;

	// Constructor, the object contains three attributes.
	// Attribute(s):
	// m_arrayCnt, counter to keep track of already filled indeces.
	// m_array, array set to its initial size.
	// length, to be able to retrieve length of object. 
	public MyArraylist(){
		m_arrayCnt = 0;
		m_array = new String[INITIALARRAYSIZE];
		clearData(0);
		length = 0;
	} 

	// Search the object for the given word.
	// Input(s):
	// - word, the word that needs to be checked.
	// Output(s):
	// - true if word is found, false if not.
	public boolean search(String word){
		for(int i = 0; i < this.length; i++){
			if (m_array[i].equals(word)){
				return true;
			} 
		}
		return false;
	}

	// Put word in the object.
	// Input(s): 
	// - word, the word that needs to be saved.
	public void put(String word){
		m_array[m_arrayCnt] = word;
		m_arrayCnt++;

		if (m_arrayCnt == m_array.length){
			resize();
		}
	}

	// Get the length of the object.
	public void length(){
		int counter = 0;
		while(m_array[counter] != null) counter++;
		this.length = counter;
	}

	// Checks if object contains a certain character. 
	// Input(s):
	// - c, character that needs to be checked. 
	// Output(s):
	// - true if 
	public boolean contains(char c){
		String letter = "";
		for(int i = 0; i < m_array.length; i++){
			letter = m_array[i];
			if(letter == null){
				continue;
			}
			if(letter.charAt(0) == c){
				return true;
			}
		}
		return false;
	}

	// Resize the array if array is full. Old array is saved in buffer,
	// new array is twice as big as old array.
	private void resize(){
		String[] buffer = new String[m_array.length];
		copy(m_array, buffer, m_array.length);
		m_array = new String[buffer.length*2];
		copy(buffer, m_array, buffer.length);
		clearData(buffer.length);
	}

	// Copy an array to another array. 
	// Input(s):
	// - src, source array that has to be copied.
	// - dst, destination array that has to be filled.
	// - length, length of the source of array.
	private void copy(String[] src, String[] dst, int length){
		for(int i = 0; i < length; i++){
			dst[i] = src[i];
		}
	}

	// Initialise values of array to null.
	// Input(s):
	// - index, index from where to null inputs.
	private void clearData(int index){
		for(int i = 0; i < index; i++){
			m_array[i + index] = null;
		}
	}	
}