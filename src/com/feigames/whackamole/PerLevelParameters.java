package com.feigames.whackamole;

public class PerLevelParameters {
	private int level;
	
	public void PerLevelParameters()
	{
		level=0;
	}
	
	public void setLevel(int lvl)
	{
		level=lvl;
	}
	public int MoleAboveTRangeGet ()
	{
		if (level<11)
			return 10*level;
		else return 100;
	}
	public int MoleAboveTMinGet ()
	{
		return 1100-100*level;
	}
	public int MoleBelowTRangeGet ()
	{
		if (level<11)
			return 10*level;
		else return 100;
	}
	public int MoleBelowTMinGet ()
	{
		return 1500-100*level;
	}
	
}
