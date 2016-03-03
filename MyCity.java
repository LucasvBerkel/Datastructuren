public class MyCity extends extraFunctions{
	public String name;
	public String latitude;
	public String longitude;
	public String country;
	public String population;
	public String elevation;

	public int latitude_int;
	public int longitude_int;
	public int population_int;
	public int elevation_int;

	public void put(String value, int index, int counter){		

		switch (index) {
		case 0:
			name = value; 
			break;
		case 1:
			value = extraFunctions.addZeros(value);
			value = value.replace(".", "");
			latitude = value;
			try {
				latitude_int = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				System.out.println(value);
				System.out.println(counter);
			}
			break;
		case 2: 
			value = extraFunctions.addZeros(value);
			value = value.replace(".", "");		
			longitude = value;
			try {
				longitude_int = Integer.parseInt(value);
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
				population_int = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				System.out.println(value);
				System.out.println(counter);
			}
			break;
		case 5: 
			elevation = value;
			try {
				elevation_int = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				System.out.println(value);
				System.out.println(counter);
			}
			break;
		}
	}
}
