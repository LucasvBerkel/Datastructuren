import java.util.Arrays;

public class MySortedArray{
	final int INITIALARRAYSIZE = 10;
	int currentIndex;
	int[] m_array;

	public MySortedArray(){
		m_array = new int[INITIALARRAYSIZE];
		currentIndex = 0;
	}

	public void insert(int value){
		if(m_array.length <= currentIndex){
			resizeArray();
		}
		m_array[currentIndex] = value;
		currentIndex++;
	}

	public void sort(){
		int[] buffer = new int[currentIndex];
		buffer = copy(m_array, buffer, buffer.length);

		m_array = new int[currentIndex];
		m_array = copy(buffer, m_array, buffer.length);
		Arrays.sort(m_array);
	}

	public boolean search(int value){
		if(value < m_array[0] || value > m_array[currentIndex-1]){
			return false;
		} else {
		return search(value, 0, currentIndex-1);
		}
	}

	private boolean search(int value, int begin, int end){
		int temp = (end+begin)/2;
		if (m_array[temp] == value){
			return true;
		} else if (begin == end){
			return false;
		} else if (m_array[temp] > value){
			return search(value, begin, temp);
		} else {
			return search(value, temp+1, end);
		}
	}

	public void print(MyCity[] database){
		for (int element:m_array){
			System.out.println(database[element].name);
		}

		System.out.println("\n" + m_array.length + " cities were found matching the description.");
	}

	private void resizeArray(){
		int[] buffer = new int[m_array.length];
		buffer = copy(m_array, buffer, m_array.length);

		m_array = new int[m_array.length*2];
		m_array = copy(buffer, m_array, buffer.length);
	}

	private int[] copy(int[] src, int[] dst, int length){
		int i = length;
		while(i-- > 0)
		{
			dst[i] = src[i];
		}
		return dst;
	}
}