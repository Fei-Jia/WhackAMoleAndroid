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
		// set per level mole above ground parameters
		//if (MoleAboveTMin>=100)
			//MoleAboveTMin=1600-100*level;  // Mole appears for shorter periods
		//else{}
		//if (MoleAboveTRange<=MoleAboveTMin*0.9)
			//MoleAboveTRange=50*level;  // Range becomes more erratic, this gets harder
		//else
			//MoleAboveTRange=(int) (MoleAboveTMin*0.9);	
		// set per level mole below time parameters
		//if (MoleBelowTMin>=500)
			
		//else{}
		
			
		//else
			//MoleBelowTRange=(int) (MoleBelowTMin*0.9);
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
