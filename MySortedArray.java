import java.util.Arrays;

public class MySortedArray{
	final int INITIALARRAYSIZE = 10;
	int currentIndex;
	int[] myArray;

	/*
	SortedArray is an object that contains the results of the queries.
	*/
	public MySortedArray(){
		myArray = new int[INITIALARRAYSIZE];
		currentIndex = 0;
	}

	/*
	Takes a int value and places it in the next slot in the array.
	Input(s):
	- value, an int value.
	Delivers no output(s).
	*/
	public void insert(int value){
		if(myArray.length <= currentIndex){
			resizeArray();
		}
		myArray[currentIndex] = value;
		currentIndex++;
	}

	/*
	If called upon, reduces the size of the array to the total number of added values
	(other words, zeros are discarded) and sorts the array, to enable binary search.
	Takes no input(s).
	Delivers no output(s).
	*/
	public void sort(){
		int[] buffer = new int[currentIndex];
		buffer = copy(myArray, buffer, buffer.length);

		myArray = new int[currentIndex];
		myArray = copy(buffer, myArray, buffer.length);
		Arrays.sort(myArray);
	}

	/*
	Takes value to be searched, if value falls outside range, false is returned, if not, 
	binary search is performed.
	Input(s):
	- value, int value to be searched.
	Output(s):
	- boolean, true if array contains given value.
	*/
	public boolean search(int value){
		if(value < myArray[0] || value > myArray[currentIndex-1]){
			return false;
		} else {
			return search(value, 0, currentIndex-1);
		}
	}


	/*
	Binary search, takes the value, begin and end. The last to values are the window in which
	the search is performed. Due to recursion, the window narrows every step.
	Input(s):
	- value, int value to be searched
	- begin, lowest value of the window in the array
	- end, highest value of the window in the array
	Output(s):
	- boolean, true if array contains given value
	*/
	private boolean search(int value, int begin, int end){
		int temp = (end+begin)/2;
		if (myArray[temp] == value){
			return true;
		} else if (begin == end){
			return false;
		} else if (myArray[temp] > value){
			return search(value, begin, temp);
		} else {
			return search(value, temp+1, end);
		}
	}

	/*
	Prints the cities which are present in the array.
	Input(s):
	- database, database used to retrieve of the names of the cities which indexes are saved in 
	the array.
	Delivers no output(s).
	*/
	public void print(MyCity[] database){
		for (int element:myArray){
			System.out.println(database[element].name);
		}

		System.out.println("\n" + myArray.length + " cities were found matching the description.");
	}

	/*
	Resizes the array twice the size.
	Takes no input(s).
	Delivers no output(s).
	*/
	private void resizeArray(){
		int[] buffer = new int[myArray.length];
		buffer = copy(myArray, buffer, myArray.length);

		myArray = new int[myArray.length*2];
		myArray = copy(buffer, myArray, buffer.length);
	}

	/*
	Copies the values of one array to another.
	Input(s):
	- src, the source array.
	- dst, the destination array.
	- length, the total number of elements to be copied.
	Output(s):
	- dst, the resulting array.
	*/
	private int[] copy(int[] src, int[] dst, int length){
		int i = length;
		while(i-- > 0)
		{
			dst[i] = src[i];
		}
		return dst;
	}
}