package com.feigames.whackamole;

import java.util.Random;

public class RandomMoleBehaviour {
	//private int MoleAboveTime;
	//private int MoleBelowTime;
	
	public void RandomMoleBehaviour()
	{
		//MoleAboveTime = 1500;
		//MoleBelowTime = 2000;
	}
	
	public int GetAboveT(int AboveRange, int AboveMin)
	{
		Random rand1 = new Random();
		return Math.abs(rand1.nextInt(AboveRange)+AboveMin);
	}
	
	public int GetBelowT(int BelowRange, int BelowMin)
	{
		Random rand2 = new Random();
		return Math.abs(rand2.nextInt(BelowRange)+BelowMin);
	}


}
