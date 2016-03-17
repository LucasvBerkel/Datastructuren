public class MyContainer{
	final int INITIALARRAYSIZE = 10;

	public char CHARACTER;
	int myContainerCounter;
	int myCityCount;

	MyCity[] myDatabase;
	MyCityLink[] myCities;
	MyContainer[] myContainers;

	public MyContainer(char character, MyCity[] database){
		this(database);

		CHARACTER = character;
	} 

	public MyContainer(MyCity[] database){
		myDatabase = database;

		myCities = new MyCityLink[INITIALARRAYSIZE];
		myCityCount = 0;

		myContainers = new MyContainer[INITIALARRAYSIZE];
		myContainerCounter = 0;
	} 

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

	public void put(MyCityLink city, int charIndex){
		int newIndex = charIndex + 1;

		// Add new container
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

		// Save the city
		else {
			myCities[myCityCount] = city;
			myCityCount++;

			if (myCityCount == myCities.length){
				resizeCities(); 

			}
		}
	}

	private void resizeContainers(){
		MyContainer[] buffer = new MyContainer[myContainers.length];
		copy(myContainers, buffer, myContainers.length);

		myContainers = new MyContainer[myContainers.length*2];
		copy(buffer, myContainers, buffer.length);
	}

	private void resizeCities(){
		MyCityLink[] buffer = new MyCityLink[myCities.length];
		copy(myCities, buffer, myCities.length);

		myCities = new MyCityLink[myCities.length*2];
		copy(buffer, myCities, buffer.length);
	}

	private <T> void copy(T[] src, T[] dst, int length){
		int i = length;
		while(i-- > 0)
		{
			dst[i] = src[i];
		}
	}
}


