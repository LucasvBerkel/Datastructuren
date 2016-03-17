public class MyCitySearch extends ExtraFunctions{

	MyContainer myContainer;

	public MyCitySearch(MyCity[] database, enums.Key key){
		myContainer = new MyContainer(database);

		int i = database.length;
		while (i-- > 0){
			MyCityLink link = new MyCityLink();

			if (database[i] != null){
				link.index = i;

				switch (key){

				case LATITUDE: 
					link.valueInt = database[i].latitudeInt;
					link.valueString = database[i].latitude;
					break;
				case LONGITUDE: 
					link.valueInt = database[i].longitudeInt;
					link.valueString = database[i].longitude;
					break;
				case COUNTRY: 
					link.valueString = database[i].country;
					break;
				case POPULATION: 
					link.valueInt = database[i].populationInt;
					link.valueString = database[i].population;
					break;
				case ELEVATION: 
					link.valueInt = database[i].elevationInt;
					link.valueString = database[i].elevation;
					break;
				default: 
					return;
				}

				myContainer.put(link, -1); 
			}
		}
	}

	public MyResults search(String landCode){
		MyResults results = new MyResults();
		results.put(myContainer.search(landCode, -1));

		return results;
	}

	public MyResults search(String minValue, String maxValue, String query){
		if(query.equals("LA") || query.equals("LO")){
			minValue = ExtraFunctions.addZeros(minValue);
			maxValue = ExtraFunctions.addZeros(maxValue);
			minValue = minValue.replace(".", "");
			maxValue = maxValue.replace(".", "");
		}

		int minVal = Integer.parseInt(minValue);
		int maxVal = Integer.parseInt(maxValue);

		MyResults results = new MyResults();
		int i = maxVal - minVal;
		while (i-- > 0){
			results.put(myContainer.search(Integer.toString(i + minVal), -1));	
		}
		return results;
	}
}


