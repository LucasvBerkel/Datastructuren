public class MyResults{
	final int INITIALARRAYSIZE = 10;

	int m_cityCount;
	MyCityLink[][] m_cities;
	MyCity[] m_database;
	MySortedArray myArray;

	String[][] m_table;


	public MyResults(MyCity[] database){
		m_cities = new MyCityLink[INITIALARRAYSIZE][];
		m_cityCount = 0;
		m_database = database;
		myArray = new MySortedArray();
	}

	public void put(MyCityLink[] results){
		m_cities[m_cityCount] = results;
		m_cityCount++;

		if (m_cityCount == m_cities.length){
			resizeCities();
		}
	}

	public void addToArray(){
		for (int i = 0; i < m_cityCount; i++){
			if (m_cities[i] != null){
				for (int j = 0; j < m_cities[i].length; j++){
					if (m_cities[i][j] != null){
						myArray.insert(m_cities[i][j].index);
					}
				}
			}
		}
		myArray.sort();	
	}

	public void print(){
		System.out.println("Concatenating results:...");
		String output = "";
		int counter = 0;

		for (int i = 0; i < m_cityCount; i++){
			if (m_cities[i] != null){

				for (int j = 0; j < m_cities[i].length; j++){
					if (m_cities[i][j] != null){
						output += m_database[m_cities[i][j].index].name + "\n";
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