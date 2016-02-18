// Authors:
// Lucas van Berkel, 10747958
// Joël Meyer, 10003539

import java.util.*;

public class MyTrie{

	char c;
	MyTrie[] children;
	boolean word;
	char[] totalCharacters;

	// The constructor, each trie contains four attributes.
	// Attributes(s):
	// - c, the letter of the trie
	// - totalCharacters, an array containing all the characters in the vocabulary
	// - children, an array of tries, essential to the datastructure
	// - word, boolean to notify if current trie is also the end of a word or not
	public MyTrie() {
		this.c = 0;
		this.totalCharacters = new char[0];
		this.children = new MyTrie[0];
		this.word = false;
	}

	// Function that adds a word to the trie, works recursively through the children of each trie
	// Input(s):
	// s, String containing the word or part of the word which has to be saved
	// Delivers no output(s):
	public void add(String s) {
		if(s.isEmpty()){
			this.word = true;
			return;
		}
		char letter = s.charAt(0);
		int index = getIndex(totalCharacters, letter);
		if(this.children[index] == null){
			this.children[index] = new MyTrie();
			this.children[index].c = letter;
			this.children[index].totalCharacters = totalCharacters;
			this.children[index].children = new MyTrie[totalCharacters.length];
		}
		this.children[index].add(s.substring(1));
	}

	// Function to check if word(or part of original word) is saved in the datastructure, works also recursively
	// Input(s):
	// - s, String containing the word or part of the word which has to be checked
	// Output(s):
	// - boolean, true if word is saved in datastructure, false if not
	public boolean isWord(String s){
		if(s.isEmpty()){
			return this.word;
		}
		char letter = s.charAt(0);
		int index = getIndex(totalCharacters, letter);
		if(index == -1){
			return false;
		}
		if(this.children[index] == null){
			return false;
		}
		return this.children[index].isWord(s.substring(1));
	}

	// Function to retrieve the index of the given letter in the totalCharacters-array, since totalCharacters-array is the
	// same for every trie/subtrie, the index will always be same for every letter.
	// Input(s):
	// - totalCharacters, array containing all the characters known to the vocabulary
	// - letter, given letter to return the index from
	// Output(s):
	// - index of the given letter in the totalCharacters-array
	public static int getIndex(char[] totalCharacters, char letter){
		for(int i = 0; i < totalCharacters.length; i++){
			if(totalCharacters[i] == letter){
				return i;
			}
		}
		return -1;
	}
}