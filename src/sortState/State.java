package sortState;

public class State
{
	// instance variables
	private String name;
	private String capital;
	private String nickname;
	private int population;
	
	public State(String n, String c, String nn, int p)
	{
		name = n;
		capital = c;
		nickname = nn;
		population = p;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getCapital()
	{
		return capital;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public int getPopulation()
	{
		return population;
	}
}