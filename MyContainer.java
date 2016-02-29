public class MyContainer{
	final int INITIALARRAYSIZE = 12;

	public char character;
	int m_charIndex;
	int m_cityCount;
	int m_containerCount;

	MyCityLink[] m_cities;
	MyCity[] m_database;
	MyContainer[] m_containers;

	public MyContainer(int charIndex, char charact, MyCity[] database){
		m_database = database;
		m_charIndex = charIndex;
		character = charact;
		m_cities = new MyCityLink[INITIALARRAYSIZE];
		m_containers = new MyContainer[INITIALARRAYSIZE];
		m_cityCount = 0;
		m_containerCount = 0;
	} 

	public MyCityLink[] search(String value){
		if (!(m_charIndex < value.length())){
			int i = m_containerCount;
			while (i-- > 0){
				if (m_containers[i].character == value.charAt(m_charIndex+1)){
					return m_containers[i].search(value);
				}
			}
		} 
		else {
			return m_cities;
		}

		return null;
	}

	public void put(MyCityLink city, int charIndex){
		if ((m_charIndex < city.value_string.length())){ 
			int i = m_containerCount;
					
			if (m_containers[i] != null){
				while (i-- > 0){
					System.out.println("put");
					if (m_containers[i].character == city.value_string.charAt(m_charIndex+1)){

						m_containers[i].put(city, charIndex+1);
						return;
					}
				}
			}
			System.out.println("containers: " + m_containerCount);
			int newIndex = (charIndex+1);
			char newChar = city.value_string.charAt(newIndex);
			
			m_containers[m_containerCount] = new MyContainer(newIndex, newChar, m_database);
			m_containerCount++;

			if (m_containerCount == m_containers.length){
				resizeContainers(); // - m_containers
				System.out.println("resized");
			}
			m_containers[m_containerCount-1].put(city, newIndex);
		}
		else {
			System.out.println("cities: " + m_cityCount);
			m_cities[m_cityCount] = city;
			m_cityCount++;
			if (m_cityCount == m_cities.length){
				resizeCities(); // - m_cities

			}
		}
	}
/*
	private void resize(Object[] array){
		Object[] buffer = new Object[array.length];
		copy(array, buffer, array.length);
		array = new Object[buffer.length*2];
		copy(buffer, array, buffer.length);
	}*/

	private void resizeContainers(){
		MyContainer[] buffer = new MyContainer[m_containers.length];
		copy(m_containers, buffer, m_containers.length);
		m_containers = new MyContainer[buffer.length*2];
		copy(buffer, m_containers, buffer.length);
	}

	private void resizeCities(){
		MyCityLink[] buffer = new MyCityLink[m_cities.length];
		copy(m_cities, buffer, m_cities.length);
		m_cities = new MyCityLink[buffer.length*2];
		copy(buffer, m_cities, buffer.length);
	}

	private void copy(Object[] src, Object[] dst, int length){
		for(int i = 0; i < length; i++){
			dst[i] = src[i];
		}
	}
}


