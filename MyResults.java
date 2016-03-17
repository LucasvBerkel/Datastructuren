public class MyResults{
	final int INITIALARRAYSIZE = 10;

	int myCityCount;
	MyCityLink[][] myCities;
	MySortedArray myArray;

	/*
	Object contains the array's with the CityLinks which matches the query, when called upon the values of
	the two-dimensional array are inserted into the SortedArray.
	*/
	public MyResults(){
		myCities = new MyCityLink[INITIALARRAYSIZE][];
		myCityCount = 0;
		myArray = new MySortedArray();
	}

	/*
	Takes the array with CityLinks and add them to the two-dimensional array
	Input(s):
	- results, the array containing the CityLinks matching the query
	Delivers no output(s)
	*/
	public void put(MyCityLink[] results){
		myCities[myCityCount] = results;
		myCityCount++;

		if (myCityCount == myCities.length){
			resizeCities();
		}
	}

	/*
	When called upon, takes the two-dimensional array and add every non null value to the SortedArray
	Takes no input(s)
	Delivers no output(s)
	*/
	public void addToArray(){
		for (int i = 0; i < myCityCount; i++){
			if (myCities[i] != null){
				for (int j = 0; j < myCities[i].length; j++){
					if (myCities[i][j] != null){
						myArray.insert(myCities[i][j].index);
					}
				}
			}
		}
		myArray.sort();	
	}

	/*
	Doubles the size of the CityLink array
	Takes no input(s)
	Delivers no output(s)
	*/
	private void resizeCities(){
		MyCityLink[][] buffer = new MyCityLink[myCities.length][];
		copy(myCities, buffer, myCities.length);

		myCities = new MyCityLink[myCities.length*2][];
		copy(buffer, myCities, buffer.length);
	}

	/*
	Copies the data from one CityLink array to another
	Input(s):
	- src, the source array
	- dst, the destination array
	- length, the total number of elements to be copied
	Delivers no output(s)
	*/
	private void copy(MyCityLink[][] src, MyCityLink[][] dst, int length){
		for (int i = 0; i < length; i++){
			if (src[i] != null){
				dst[i] = new MyCityLink[src[i].length];

				for (int j = 0; j < src[i].length; j++){
					if (src[i][j] != null){
						dst[i][j] = src[i][j];
					}
				}
			}
		}
	}
}