public class MyResults{
	final int INITIALARRAYSIZE = 20;

	int m_cityCount;
	MyCityLink[][] m_cities;

	public MyResults(){
		m_cities = new MyCityLink[INITIALARRAYSIZE][];
		m_cityCount = 0;
	}

	public void put(MyCityLink[] results){
		m_cities[m_cityCount] = results;
	}
}