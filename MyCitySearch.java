import java.lang.*;

public class MyCitySearch{
	public enum Key{
		LATITUDE, 
		LONGITUDE,
		COUNTRY,
		POPULATION,
		ELEVATION
	}

	MyContainer m_container;

	public MyCitySearch(MyCity[] database, Key key){
		m_container = new MyContainer(-1, '0', database);
		int i = database.length;
		while (i-- > 0){
			MyCityLink link = new MyCityLink();
			//System.out.println(i);

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
				m_container.put(link, 0); 
			}
		}
	}

	public MyResults Search(String landCode){
		MyResults results = new MyResults();
		results.put(m_container.search(landCode));

		return results;
	}

	public MyResults Search(String minValue, String maxValue){
		minValue = minValue.replace(".", "");
		maxValue = maxValue.replace(".", "");

		int minVal = Integer.parseInt(minValue);
		int maxVal = Integer.parseInt(maxValue);

		MyResults results = new MyResults();
		int i = maxVal - minVal;
		while (i-- > 0){
			results.put(m_container.search(Integer.toString(i + minVal)));	
		}
		return results;
	}
}


