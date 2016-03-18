public class MyContainer{
	final int INITIALARRAYSIZE = 10;

	public char CHARACTER;
	int myContainerCounter;
	int myCityCount;

	MyCity[] myDatabase;
	MyCityLink[] myCities;
	MyContainer[] myContainers;

	// MyContainer constructor for new characters. 
	public MyContainer(char character, MyCity[] database){
		this(database);

		CHARACTER = character;
	} 


	// Initial MyContainer constructor.
	public MyContainer(MyCity[] database){
		myDatabase = database;

		myCities = new MyCityLink[INITIALARRAYSIZE];
		myCityCount = 0;

		myContainers = new MyContainer[INITIALARRAYSIZE];
		myContainerCounter = 0;
	} 

	/* 
	The search function checks if the last character is reached, if not, check for the next 
	container that contains the next character and repeat this process until the last character
	is reached. If the last character is reached than you have a MyContainer containing the right
	results. All containers in a row will represent the value that is searched for. 
	Input(s):
	- value, the value that is searched for.
	- charIndex, the index of the character in the string. 
	Output(s):
	- myCities, the MyCityLinks matching the query. */
	public MyCityLink[] search(String value, int charIndex){
		int newIndex = charIndex + 1;

		if (newIndex < value.length()){
			int i = myContainerCounter;
			while (i-- > 0){
				if (myContainers[i].CHARACTER == value.charAt(newIndex)){
					return myContainers[i].search(value, newIndex);
				}
			}
		} 
		else {
			return myCities;
		}

		return null;
	}

	/* 
	The put function uses the values like a string, every new character is a new 'level'
	of a MyContainer. The function checks whether the last character is reached, if not, the
	MyCityLink will be put in a MyContainer. If it is the last character of the string, then 
	the MyCityLink is saved in that same MyContainer. 
	Input(s):
	- city, the MyCityLink.
	- charIndex, the index of the string that has to be put in a MyContainer. 
	Delivers no output(s), but saves the MyCityLinks in MyContainers.
	*/
	public void put(MyCityLink city, int charIndex){
		int newIndex = charIndex + 1;

		// Add new container.
		if (newIndex < city.valueString.length()) {
			
			int i = myContainerCounter;
			while (i-- > 0){
				if (myContainers[i].CHARACTER == city.valueString.charAt(newIndex)){
					myContainers[i].put(city, newIndex);
					return;
				}
			}
			
			char newChar = city.valueString.charAt(newIndex);
			myContainers[myContainerCounter] = new MyContainer(newChar, myDatabase);
			myContainers[myContainerCounter].put(city, newIndex);
			myContainerCounter++;

			if (myContainerCounter == myContainers.length){
				resizeContainers(); 
			}
		}

		// Save the city.
		else {
			myCities[myCityCount] = city;
			myCityCount++;

			if (myCityCount == myCities.length){
				resizeCities(); 

			}
		}
	}

	/*
	Resizes the MyContainer array twice the size.
	Takes no input(s).
	Delivers no output(s).
	*/
	private void resizeContainers(){
		MyContainer[] buffer = new MyContainer[myContainers.length];
		copy(myContainers, buffer, myContainers.length);

		myContainers = new MyContainer[myContainers.length*2];
		copy(buffer, myContainers, buffer.length);
	}

	/*
	Resizes the MyCityLink aray twice the size.
	Takes no input(s).
	Delivers no output(s).
	*/
	private void resizeCities(){
		MyCityLink[] buffer = new MyCityLink[myCities.length];
		copy(myCities, buffer, myCities.length);

		myCities = new MyCityLink[myCities.length*2];
		copy(buffer, myCities, buffer.length);
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
	private <T> void copy(T[] src, T[] dst, int length){
		int i = length;
		while(i-- > 0)
		{
			dst[i] = src[i];
		}
	}
}


