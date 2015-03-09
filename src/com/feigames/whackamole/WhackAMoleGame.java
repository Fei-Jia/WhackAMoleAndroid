package com.feigames.whackamole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.feigames.whackamole.R;

public class WhackAMoleGame extends ActionBarActivity {
	private int but[]= {0,0,0,0,0,0,0,0,0};
	private boolean MoleOUT=false;   // Boolean for two situations true for mole out of hole (hit-able) false for underground (not hit-able)
	private int MoleCur=1;           // Hole Number that the Mole is in (Position of Mole)
	private int CurScore=0;          // Current Score
	private boolean inLeveL = false; // To see if currently in a level so as to turn on the playing functions
	private int CurLvL=1;
	private int MoleAboveTimeRange=0;
	private int MoleAboveTimeMin=0;
	private int MoleBelowTimeRange=0;
	private int MoleBelowTimeMin=0;

	private void randomhole(){
		Random holeoutR = new Random();
		MoleCur = holeoutR.nextInt(10);
	}
	
	public void DisplayMoleT(){   // Method to display the mole when it's time for him to come above ground, MoleCur is the hole number)
		MoleOUT=true;
		ImageButton MolePOS =(ImageButton) findViewById(R.id.ImageButton31);
			if(MoleOUT&&inLeveL){
				switch (MoleCur){
				case 0:  MolePOS =(ImageButton) findViewById(R.id.ImageButton11);
					break;
				case 1:  MolePOS =(ImageButton) findViewById(R.id.ImageButton12);
					break;
				case 2:  MolePOS =(ImageButton) findViewById(R.id.ImageButton13);
					break;
				case 3:  MolePOS =(ImageButton) findViewById(R.id.ImageButton21);
					break;
				case 4:  MolePOS =(ImageButton) findViewById(R.id.ImageButton22);
					break;
				case 5:  MolePOS =(ImageButton) findViewById(R.id.ImageButton23);
					break;
				case 6:  MolePOS =(ImageButton) findViewById(R.id.ImageButton31);
					break;
				case 7:  MolePOS =(ImageButton) findViewById(R.id.ImageButton32);
					break;
				case 8:  MolePOS =(ImageButton) findViewById(R.id.ImageButton33);
					break;
			}	
				displayMoleS(MolePOS);}
			}
				
	public void EraseMoleT(){   // Method to make the mole disappear when it's time for him to go underground
		MoleOUT=false;
		ImageButton MolePOSe =(ImageButton) findViewById(R.id.ImageButton31);
			 if(!MoleOUT||!inLeveL){
					switch (MoleCur){
					case 0:  MolePOSe =(ImageButton) findViewById(R.id.ImageButton11);
						break;
					case 1:  MolePOSe =(ImageButton) findViewById(R.id.ImageButton12);
						break;
					case 2:  MolePOSe =(ImageButton) findViewById(R.id.ImageButton13);
						break;
					case 3:  MolePOSe =(ImageButton) findViewById(R.id.ImageButton21);
						break;
					case 4:  MolePOSe =(ImageButton) findViewById(R.id.ImageButton22);
						break;
					case 5:  MolePOSe =(ImageButton) findViewById(R.id.ImageButton23);
						break;
					case 6:  MolePOSe =(ImageButton) findViewById(R.id.ImageButton31);
						break;
					case 7:  MolePOSe =(ImageButton) findViewById(R.id.ImageButton32);
						break;
					case 8:  MolePOSe =(ImageButton) findViewById(R.id.ImageButton33);
						break;
				}	
					ereaseMoleS(MolePOSe);
				}}

	public void displayMoleS(ImageButton MButton){
		MButton.setBackgroundResource(R.drawable.mole_pic);
	}
	
	public void ereaseMoleS(ImageButton EButton){
		EButton.setBackgroundColor(Color.TRANSPARENT);
	}
	
	private void CheckerHIT(){   // check if there was a hit
		TextView ScoreText = (TextView) findViewById(R.id.ScoreView);
		TextView LevelText = (TextView) findViewById(R.id.LeveLView);
		if(MoleOUT && inLeveL){
			for (int j=0; j<9; j++){
				if(but[j]==1 && MoleCur==j) 
					{CurScore=CurScore+1;
					 MediaPlayer mole_scream_sound = MediaPlayer.create(this, R.raw.mole_scream);  // mole scream
					 try {
						 mole_scream_sound.prepare();
			         } catch (IllegalStateException e) {
			             e.printStackTrace();
			         } catch (IOException e) {
			             e.printStackTrace();
			         }
					 mole_scream_sound.start();		
			        
					 mole_scream_sound.setOnCompletionListener(new OnCompletionListener() {
			             public void onCompletion(MediaPlayer mole_scream_sound) {
			            	 mole_scream_sound.release();

			             };
			         });
					}
			}
		}
		String strS = Integer.toString(CurScore);  // Display Score
		ScoreText.setText(strS);
		String strL = Integer.toString(CurLvL);  // Display Level
		LevelText.setText(strL);
	}
		
	public void LvLPlay(View view){
		PerLevelParameters perlvl = new PerLevelParameters();
		RandomMoleBehaviour behave = new RandomMoleBehaviour();
		perlvl.setLevel(CurLvL);
		if(!inLeveL){
			LvLPlay2(view, behave.GetBelowT(perlvl.MoleBelowTRangeGet(), perlvl.MoleBelowTMinGet()), 
					behave.GetAboveT(perlvl.MoleAboveTRangeGet(), perlvl.MoleAboveTMinGet()));
		}
		else{}
	}
	
	public void LvLPlay2(View view, int BT, int DT){
		inLeveL=true;
		final int BetweenTime=BT;  // Time between mole appearances
		final int DisMoleTime=DT;  // Time that mole stays above ground
		final MediaPlayer levelsound = MediaPlayer.create(this, R.raw.lvl_music);
		levelsound.start();

		final Handler handlerwhile1 = new Handler();
		final Handler handlerwhile2 = new Handler();
		final Handler handlerwhile3 = new Handler();
		final Handler handlerwhile4 = new Handler();
		final Handler handlerwhile5 = new Handler();
		final Handler handlerwhile6 = new Handler();
		final Handler handlerwhile7 = new Handler();
		final Handler handlerwhile8 = new Handler();
		final Handler handlerwhile9 = new Handler();
		final Handler handlerwhile10 = new Handler();
		final Handler handlerwhile11 = new Handler();
		final Handler handlerwhile12 = new Handler();
		final Handler handlerwhile13 = new Handler();
		final Handler handlerwhile14 = new Handler();
		final Handler handlerwhile15 = new Handler();
		final Handler handlerwhile16 = new Handler();
		final Handler handlerwhile17 = new Handler();
		final Handler handlerwhile18 = new Handler();
		final Handler handlerwhile19 = new Handler();
		final Handler handlerwhile20 = new Handler();
		
		CheckerHIT();
		
			handlerwhile1.postDelayed(new Runnable() {public void run() {
				randomhole();
				DisplayMoleT();
				handlerwhile2.postDelayed(new Runnable() {public void run() {
					CheckerHIT();
					EraseMoleT();
					handlerwhile3.postDelayed(new Runnable() {public void run() {
						randomhole();
						DisplayMoleT();
						handlerwhile4.postDelayed(new Runnable() {public void run() {
							CheckerHIT();
							EraseMoleT();
							handlerwhile5.postDelayed(new Runnable() {public void run() {
								randomhole();
								DisplayMoleT();
								handlerwhile6.postDelayed(new Runnable() {public void run() {
									CheckerHIT();
									EraseMoleT();
									handlerwhile7.postDelayed(new Runnable() {public void run() {
										randomhole();
										DisplayMoleT();
											handlerwhile8.postDelayed(new Runnable() {public void run() {
											CheckerHIT();
											EraseMoleT();
											handlerwhile9.postDelayed(new Runnable() {public void run() {
												randomhole();
												DisplayMoleT();
												handlerwhile10.postDelayed(new Runnable() {public void run() {
													CheckerHIT();
													EraseMoleT();
													handlerwhile11.postDelayed(new Runnable() {public void run() {
														randomhole();
														DisplayMoleT();
														handlerwhile12.postDelayed(new Runnable() {public void run() {
															CheckerHIT();
															EraseMoleT();
															handlerwhile13.postDelayed(new Runnable() {public void run() {
																randomhole();
																DisplayMoleT();
																handlerwhile14.postDelayed(new Runnable() {public void run() {
																	CheckerHIT();
																	EraseMoleT();
																	handlerwhile15.postDelayed(new Runnable() {public void run() {
																		randomhole();
																		DisplayMoleT();
																		handlerwhile16.postDelayed(new Runnable() {public void run() {
																			CheckerHIT();
																			EraseMoleT();
																			handlerwhile17.postDelayed(new Runnable() {public void run() {
																				randomhole();
																				DisplayMoleT();
																				handlerwhile18.postDelayed(new Runnable() {public void run() {
																					CheckerHIT();
																					EraseMoleT();
																					handlerwhile19.postDelayed(new Runnable() {public void run() {
																						randomhole();
																						DisplayMoleT();
																						handlerwhile20.postDelayed(new Runnable() {public void run() {
																							CheckerHIT();
																							EraseMoleT();
																						}}, DisMoleTime);
																					}}, BetweenTime);
																				}}, DisMoleTime);
																			}}, BetweenTime);
																		}}, DisMoleTime);
																	}}, BetweenTime);
																}}, DisMoleTime);
															}}, BetweenTime);
														}}, DisMoleTime);
													}}, BetweenTime);
												}}, DisMoleTime);
											}
												}, BetweenTime);
										}}, DisMoleTime);
									}
										}, BetweenTime);
								}}, DisMoleTime);
								}
								}, BetweenTime);
						}}, DisMoleTime);
					}
						}, BetweenTime);
				}}, DisMoleTime);
			}
				}, BetweenTime);
			CheckerHIT();
		final Handler handlerSc = new Handler();
		handlerSc.postDelayed(new Runnable() {public void run() {inLeveL=false; CurLvL++;levelsound.release();CheckerHIT();}},10*BetweenTime+10*DisMoleTime+1000);
		
	}
	
	public void releasebutimg0(){
		ImageButton released0 = (ImageButton) findViewById(R.id.ImageButton11);
		released0.setBackgroundColor(Color.TRANSPARENT);
		Handler handler_rel0 = new Handler();
		handler_rel0.postDelayed(new Runnable() {public void run() {but[0]=0;}}, 600);}
	public void releasebutimg1(){
		ImageButton released1 = (ImageButton) findViewById(R.id.ImageButton12);
		released1.setBackgroundColor(Color.TRANSPARENT);
		Handler handler_rel1 = new Handler();
		handler_rel1.postDelayed(new Runnable() {public void run() {but[1]=0;}}, 600);}
	public void releasebutimg2(){
		ImageButton released2 = (ImageButton) findViewById(R.id.ImageButton13);
		released2.setBackgroundColor(Color.TRANSPARENT);
		Handler handler_rel2 = new Handler();
		handler_rel2.postDelayed(new Runnable() {public void run() {but[2]=0;}}, 600);}
	public void releasebutimg3(){
		ImageButton released3 = (ImageButton) findViewById(R.id.ImageButton21);
		released3.setBackgroundColor(Color.TRANSPARENT);
		Handler handler_rel3 = new Handler();
		handler_rel3.postDelayed(new Runnable() {public void run() {but[3]=0;}}, 600);}
	public void releasebutimg4(){
		ImageButton released4 = (ImageButton) findViewById(R.id.ImageButton22);
		released4.setBackgroundColor(Color.TRANSPARENT);
		Handler handler_rel4 = new Handler();
		handler_rel4.postDelayed(new Runnable() {public void run() {but[4]=0;}}, 600);}
	public void releasebutimg5(){
		ImageButton released5 = (ImageButton) findViewById(R.id.ImageButton23);
		released5.setBackgroundColor(Color.TRANSPARENT);
		Handler handler_rel5 = new Handler();
		handler_rel5.postDelayed(new Runnable() {public void run() {but[5]=0;}}, 600);}
	public void releasebutimg6(){
		ImageButton released6 = (ImageButton) findViewById(R.id.ImageButton31);
		released6.setBackgroundColor(Color.TRANSPARENT);
		Handler handler_rel6 = new Handler();
		handler_rel6.postDelayed(new Runnable() {public void run() {but[6]=0;}}, 600);}
	public void releasebutimg7(){
		ImageButton released7 = (ImageButton) findViewById(R.id.ImageButton32);
		released7.setBackgroundColor(Color.TRANSPARENT);
		Handler handler_rel7 = new Handler();
		handler_rel7.postDelayed(new Runnable() {public void run() {but[7]=0;}}, 600);}
	public void releasebutimg8(){
		ImageButton released8 = (ImageButton) findViewById(R.id.ImageButton33);
		released8.setBackgroundColor(Color.TRANSPARENT);
		Handler handler_rel8 = new Handler();
		handler_rel8.postDelayed(new Runnable() {public void run() {but[8]=0;}}, 600);}

	public void buttonhit (View view){
		ImageButton clickedButton = (ImageButton) view;
		ImageButton imgButton = clickedButton;
		switch (clickedButton.getId()){
			case R.id.ImageButton11:  {imgButton =(ImageButton) findViewById(R.id.ImageButton11); but[0]=1;hand0();}
				break;
			case R.id.ImageButton12:  {imgButton =(ImageButton) findViewById(R.id.ImageButton12); but[1]=1;hand1();}
				break;
			case R.id.ImageButton13:  {imgButton =(ImageButton) findViewById(R.id.ImageButton13); but[2]=1;hand2();}
				break;
			case R.id.ImageButton21:  {imgButton =(ImageButton) findViewById(R.id.ImageButton21); but[3]=1;hand3();}
				break;
			case R.id.ImageButton22:  {imgButton =(ImageButton) findViewById(R.id.ImageButton22); but[4]=1;hand4();}
				break;
			case R.id.ImageButton23:  {imgButton =(ImageButton) findViewById(R.id.ImageButton23); but[5]=1;hand5();}
				break;
			case R.id.ImageButton31:  {imgButton =(ImageButton) findViewById(R.id.ImageButton31); but[6]=1;hand6();}
				break;
			case R.id.ImageButton32:  {imgButton =(ImageButton) findViewById(R.id.ImageButton32); but[7]=1;hand7();}
				break;
			case R.id.ImageButton33:  {imgButton =(ImageButton) findViewById(R.id.ImageButton33); but[8]=1;hand8();}
				break;
		}
		
		imgButton.setBackgroundResource(R.drawable.hammer);
		MediaPlayer hammersound = MediaPlayer.create(this, R.raw.hammer);
		try {
            hammersound.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        hammersound.start();		
        
        hammersound.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer hammersound) {
                hammersound.release();

            };
        });
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whack_amole_game);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
			
		public void hand0(){
		if(but[0]==1){final Handler handler0 = new Handler();
			handler0.postDelayed(new Runnable() {public void run() {releasebutimg0();}}, 400);}}
		
		public void hand1(){
		if(but[1]==1){final Handler handler1 = new Handler();
		handler1.postDelayed(new Runnable() {public void run() {releasebutimg1();}}, 400);}}
		
		public void hand2(){
		if(but[2]==1){final Handler handler2 = new Handler();
		handler2.postDelayed(new Runnable() {public void run() {releasebutimg2();}}, 400);}}
		
		public void hand3(){
		if(but[3]==1){final Handler handler3 = new Handler();
		handler3.postDelayed(new Runnable() {public void run() {releasebutimg3();}}, 400);}}
		
		public void hand4(){
		if(but[4]==1){final Handler handler4 = new Handler();
		handler4.postDelayed(new Runnable() {public void run() {releasebutimg4();}}, 400);}}
		
		public void hand5(){
		if(but[5]==1){final Handler handler5 = new Handler();
		handler5.postDelayed(new Runnable() {public void run() {releasebutimg5();}}, 400);}}
		
		public void hand6(){
		if(but[6]==1){final Handler handler6 = new Handler();
		handler6.postDelayed(new Runnable() {public void run() {releasebutimg6();}}, 400);}}
		
		public void hand7(){
		if(but[7]==1){final Handler handler7 = new Handler();
		handler7.postDelayed(new Runnable() {public void run() {releasebutimg7();}}, 400);}}
		
		public void hand8(){
		if(but[8]==1){final Handler handler8 = new Handler();
		handler8.postDelayed(new Runnable() {public void run() {releasebutimg8();}}, 400);}}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.whack_amole_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_whack_amole_game, container, false);
			return rootView;
		}
	}

}
