import java.util.*;

public class MyTrie{

	char c;
	MyTrie[] children;
	boolean word;
	char[] totalCharacters;

	public MyTrie() {
		this.c = 0;
		this.totalCharacters = new char[0];
		this.children = new MyTrie[0];
		this.word = false;
	}

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

	public static int getIndex(char[] totalCharacters, char letter){
		for(int i = 0; i < totalCharacters.length; i++){
			if(totalCharacters[i] == letter){
				return i;
			}
		}
		return -1;
	}
}