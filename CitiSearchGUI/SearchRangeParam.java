public class SearchRangeParam{
	public boolean active;
	public String min, max;

	SearchRangeParam()
	{

	}

	SearchRangeParam(boolean act, String minValue, String maxValue)
	{
		active = act;
		min = minValue;
		max = maxValue;
	}
}