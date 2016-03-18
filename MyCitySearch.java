/* 
   File: MyCitySearch.java
   Authors: Lucas van Berkel(lucasvberkel@gmail.com) 10747958, 
			Joël Meyer(rinyjoel@live.nl) 10003539.
   Date: 	18-03-2016
   Description: This class saves the relevant parameter in the datastructure. 
*/
public class MyCitySearch extends ExtraFunctions{

	MyContainer myContainer;

	/* 
	The MyCitySearch constructor checks which parameter is asked for and puts the relevant 
	values in MyCityLink that contains the index of MyCity that has to be retreived later.
	In this loop every MyCity is converted to a MyCityLink. Every MyCityLink is put in
	a MyContainer.
    Input(s):
    - database, the MyCity[] database.
    key, the parameter that is asked for. 
    Delivers no output(s) but put's the values in MyContainer. 
    */
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

	/* 
	The search function for searching a landcode. 
	Input(s): 
	- landCode, the landCode that has to be searched. 
	Output(s): 
	- results, the results matching the landCode. 
	*/
	public MyResults search(String landCode){
		MyResults results = new MyResults();
		results.put(myContainer.search(landCode, -1));

		return results;
	}

	/* 
	The search function for searching a range. 
	Input(s):
	- minValue, start of range.
	- maxValue, end of range.
	- query, type of query to make values correct.
	Output(s):
	- results, the results matching the range.
	*/
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


