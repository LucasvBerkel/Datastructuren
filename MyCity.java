public class MyCity extends ExtraFunctions{
	public String name;
	public String latitude;
	public String longitude;
	public String country;
	public String population;
	public String elevation;

	public int latitudeInt;
	public int longitudeInt;
	public int populationInt;
	public int elevationInt;

	/* 
	The put function initialises all values of a city.
	Input(s):
	- value, name of the city.
	- index, the index of the parameter. 
	- counter, counter of the lines processed until now. 
	Delivers no output(s) but initialises the Strings and ints of MyCity. 
	*/
	public void put(String value, int index, int counter){		

		switch (index) {
		case 0:
			name = value; 
			break;
		case 1:
			value = ExtraFunctions.addZeros(value);
			value = value.replace(".", "");
			latitude = value;
			try {
				latitudeInt = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				System.out.println(value);
				System.out.println(counter);
			}
			break;
		case 2: 
			value = ExtraFunctions.addZeros(value);
			value = value.replace(".", "");		
			longitude = value;
			try {
				longitudeInt = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				System.out.println(value);
				System.out.println(counter);
			}
			break;
		case 3: 
			country = value;
			break;
		case 4: 
			population = value;
			try {
				populationInt = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				System.out.println(value);
				System.out.println(counter);
			}
			break;
		case 5: 
			elevation = value;
			try {
				elevationInt = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				System.out.println(value);
				System.out.println(counter);
			}
			break;
		}
	}
}
