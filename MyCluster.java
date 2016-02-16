import java.io.*;
import java.util.*;

public class MyCluster{
	final int MINARRAYSIZE = 128;

	int m_arrayCnt = 0;
	String[] m_array = null;


	public MyCluster(){
		m_arrayCnt = 0;
		m_array = new String[MINARRAYSIZE];
		ClearData(0, MINARRAYSIZE);
	} 

	public boolean Search(String word){
		for(int i = 0; i < m_array.length; i++){
			if (m_array[i] == null) {
				return false;
			}
			else if (m_array[i].equals(word)){
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

	private void Resize(){
		String[] buffer = new String[m_array.length];
		Copy(m_array, buffer, m_array.length);

		m_array = new String[buffer.length + MINARRAYSIZE];
		Copy(buffer, m_array, buffer.length);
		ClearData(buffer.length, MINARRAYSIZE);
	}

	private void Copy(String[] src, String[] dst, int length){
		for(int i = 0; i < length; i++){
			dst[i] = src[i];
		}
	}

	private void ClearData(int index, int length){
		for(int i = 0; i < length; i++){
			m_array[i + index] = null;
		}
	}	
}