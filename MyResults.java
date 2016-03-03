public class MyResults{
	final int INITIALARRAYSIZE = 10;

	int m_cityCount;
	MyCityLink[][] m_cities;


	public MyResults(){
		m_cities = new MyCityLink[INITIALARRAYSIZE][];
		m_cityCount = 0;
	}

	public void put(MyCityLink[] results){
		m_cities[m_cityCount] = results;
		m_cityCount++;

		if (m_cityCount == m_cities.length){
			resizeCities();
		}
	}


	public MyResults or(MyResults results, enums.Key key){
		return null;
	}

	public MyResults and(MyResults results, enums.Key key){
		return null;
	}


	public void print(MyCity[] database){

		String output = "";
		int counter = 0;

		for (int i = 0; i < m_cityCount; i++){
			if (m_cities[i] != null){

				for (int j = 0; j < m_cities[i].length; j++){
					if (m_cities[i][j] != null){
						output += database[m_cities[i][j].index].name + "\n";
						counter += 1;
					}
				}
			}
		}

		System.out.print(output);
		System.out.println("\n" + counter + " cities found matching the description");
	}

	private void resizeCities(){
		MyCityLink[][] buffer = new MyCityLink[m_cities.length][];
		copy(m_cities, buffer, m_cities.length);

		m_cities = new MyCityLink[m_cities.length*2][];
		copy(buffer, m_cities, buffer.length);
	}

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