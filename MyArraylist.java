import java.io.*;
import java.util.*;

public class MyArraylist{
	final int INITIALARRAYSIZE = 128;

	int m_arrayCnt = 0;
	String[] m_array = null;
	int length;


	public MyArraylist(){
		m_arrayCnt = 0;
		m_array = new String[INITIALARRAYSIZE];
		ClearData(0);
		length = 0;
	} 

	public boolean Search(String word){
		for(int i = 0; i < this.length; i++){
			if (m_array[i].equals(word)){
				return true;
			} 
		}
		return false;
	}

	public void Put(String word){
		m_array[m_arrayCnt] = word;
		m_arrayCnt++;

		if (m_arrayCnt == m_array.length){
			Resize();
		}
	}

	public void length(){
		int counter = 0;
		while(m_array[counter] != null) counter++;
		this.length = counter;
	}

	private void Resize(){
		String[] buffer = new String[m_array.length];
		Copy(m_array, buffer, m_array.length);
		m_array = new String[buffer.length*2];
		Copy(buffer, m_array, buffer.length);
		ClearData(buffer.length);
	}

	private void Copy(String[] src, String[] dst, int length){
		for(int i = 0; i < length; i++){
			dst[i] = src[i];
		}
	}

	private void ClearData(int index){
		for(int i = 0; i < index; i++){
			m_array[i + index] = null;
		}
	}	
}