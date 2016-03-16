import java.lang.*;

public class MyCitySearch extends extraFunctions{

	MyContainer m_container;
	MyCity[] m_database;

	public MyCitySearch(MyCity[] database, enums.Key key){
		m_container = new MyContainer(database);
		m_database = database;

		int i = database.length;
		while (i-- > 0){
			MyCityLink link = new MyCityLink();

			if (database[i] != null){
				link.index = i;

				switch (key){

				case LATITUDE: 
					link.value_int = database[i].latitude_int;
					link.value_string = database[i].latitude;
					break;
				case LONGITUDE: 
					link.value_int = database[i].longitude_int;
					link.value_string = database[i].longitude;
					break;
				case COUNTRY: 
					link.value_string = database[i].country;
					break;
				case POPULATION: 
					link.value_int = database[i].population_int;
					link.value_string = database[i].population;
					break;
				case ELEVATION: 
					link.value_int = database[i].elevation_int;
					link.value_string = database[i].elevation;
					break;
				default: 
					return;
				}

				m_container.put(link, -1); 
			}
		}
	}

	public MyResults search(String landCode){
		MyResults results = new MyResults(m_database);
		results.put(m_container.search(landCode, -1));

		return results;
	}

	public MyResults search(String minValue, String maxValue, String query){
		if(query.equals("LA") || query.equals("LO")){
			minValue = extraFunctions.addZeros(minValue);
			maxValue = extraFunctions.addZeros(maxValue);
			minValue = minValue.replace(".", "");
			maxValue = maxValue.replace(".", "");
		}

		int minVal = Integer.parseInt(minValue);
		int maxVal = Integer.parseInt(maxValue);

		MyResults results = new MyResults(m_database);
		int i = maxVal - minVal;
		while (i-- > 0){
			results.put(m_container.search(Integer.toString(i + minVal), -1));	
		}
		return results;
	}
}


