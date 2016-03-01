public class MyContainer{
	final int INITIALARRAYSIZE = 10;

	public char CHARACTER;
	int m_containerCount;
	int m_cityCount;

	MyCity[] m_database;
	MyCityLink[] m_cities;
	MyContainer[] m_containers;

	public MyContainer(char character, MyCity[] database){
		this(database);

		CHARACTER = character;
	} 

	public MyContainer(MyCity[] database){
		m_database = database;

		m_cities = new MyCityLink[INITIALARRAYSIZE];
		m_cityCount = 0;

		m_containers = new MyContainer[INITIALARRAYSIZE];
		m_containerCount = 0;
	} 

	public MyCityLink[] search(String value, int charIndex){
		int newIndex = charIndex + 1;

		if (newIndex < value.length()){
			int i = m_containerCount;
			while (i-- > 0){
				if (m_containers[i].CHARACTER == value.charAt(newIndex)){
					return m_containers[i].search(value, newIndex);
				}
			}
		} 
		else {
			return m_cities;
		}

		return null;
	}

	public void put(MyCityLink city, int charIndex){
		int newIndex = charIndex + 1;

		// Add new container
		if (newIndex < city.value_string.length()) {
			
			int i = m_containerCount;
			while (i-- > 0){
				if (m_containers[i].CHARACTER == city.value_string.charAt(newIndex)){
					m_containers[i].put(city, newIndex);
					return;
				}
			}
			
			char newChar = city.value_string.charAt(newIndex);
			m_containers[m_containerCount] = new MyContainer(newChar, m_database);
			m_containers[m_containerCount].put(city, newIndex);
			m_containerCount++;

			if (m_containerCount == m_containers.length){
				resizeContainers(); 
			}
		}

		// Save the city
		else {
			m_cities[m_cityCount] = city;
			m_cityCount++;

			if (m_cityCount == m_cities.length){
				resizeCities(); 

			}
		}
	}


	private void resizeContainers(){
		MyContainer[] buffer = new MyContainer[m_containers.length];
		copy(m_containers, buffer, m_containers.length);

		m_containers = new MyContainer[m_containers.length*2];
		copy(buffer, m_containers, buffer.length);
	}

	private void resizeCities(){
		MyCityLink[] buffer = new MyCityLink[m_cities.length];
		copy(m_cities, buffer, m_cities.length);

		m_cities = new MyCityLink[m_cities.length*2];
		copy(buffer, m_cities, buffer.length);
	}


	private <T> void copy(T[] src, T[] dst, int length){
		int i = length;
		while(i-- > 0)
		{
			dst[i] = src[i];
		}
	}
}


