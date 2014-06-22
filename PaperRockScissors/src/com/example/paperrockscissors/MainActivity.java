package com.example.paperrockscissors;

import java.util.Random;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.text.Layout;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	ImageButton btnPaper;
	ImageButton btnRock;
	ImageButton btnScissor;
	TextView matchPlayer;
	TextView playerScore;
	TextView matchCpu;
	TextView cpuScore;
	ImageView cpuImage;
	
	TextView streakWin;
	TextView streakLose;
	TextView streakTie;
	
	int playerScoreNum;
	int playerMatch;
	int cpuScoreNum;
	int cpuMatch;
	int winNum;
	int loseNum;
	int tieNum;
	int winMax;
	int loseMax;
	int tieMax;
	
	static String statusMessage;
	
	private void varSet() {
		btnPaper = (ImageButton) findViewById(R.id.imageButtonPaper);
		btnRock = (ImageButton) findViewById(R.id.imageButtonRock);
		btnScissor = (ImageButton) findViewById(R.id.imageButtonScissor);
		matchCpu = (TextView) findViewById(R.id.matchCpu);
		cpuScore = (TextView) findViewById(R.id.cpuScore);
		matchPlayer = (TextView) findViewById(R.id.matchPlayer);
		playerScore = (TextView) findViewById(R.id.playerScore);
		cpuImage = (ImageView) findViewById(R.id.cpuImage);
		
		streakWin = (TextView) findViewById(R.id.streakWin);
		streakLose = (TextView) findViewById(R.id.streakLose);
		streakTie = (TextView) findViewById(R.id.streakTie);
		
		playerScoreNum = 0;
		playerMatch = 0;
		cpuScoreNum = 0;
		cpuMatch = 0;
		winNum = 0;
		loseNum = 0;
		tieNum = 0;
		winMax = 0;
		loseMax = 0;
		tieMax = 0;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		varSet();
		
		setupButton(btnPaper, 1, "Paper");
		setupButton(btnRock, 2, "Rock");
		setupButton(btnScissor, 3, "Scissors");

	};
	
	public void setupButton(View buttonView, final int type, final String message) {
		
		buttonView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Create Object from nothing
				/*
				ImageView kevImg = new ImageView(MainActivity.this);
				LinearLayout kevLay = (LinearLayout) findViewById(R.id.matchViews);
				kevImg.setImageResource(R.drawable.icon_point);
				
				kevLay.addView(kevImg);
				*/
						
				Random randGen = new Random();
				int cpuPlay = randGen.nextInt(3) + 1;
				
				// VIEW Objects can be LinearLayouts and etc
				View playerViews = (View) findViewById(R.id.playerViews);
				
				if (cpuPlay == 1) {
					
					cpuImage.setImageResource(R.drawable.icon_paper);
					
				} else if (cpuPlay == 2) {
					
					cpuImage.setImageResource(R.drawable.icon_rock);
					
				} else if (cpuPlay == 3) {
					
					cpuImage.setImageResource(R.drawable.icon_scissor);
					
				}
				
				btnPaper.setClickable(false);
				btnRock.setClickable(false);
				btnScissor.setClickable(false);
				
				if(cpuPlay == 1 && type == 3 || cpuPlay == 2 && type == 1 || cpuPlay == 3 && type == 2) {
					
					//
					// PLAYER WIN
					//
					
					playerMove(v, 200);
					shake(cpuImage, 100);
					
					playerViews.bringToFront();
					
					playerMatch++;
					
					if(playerMatch == 3){
						playerScoreNum++;
						String score = Integer.toString(playerScoreNum);
						playerScore.setText(score);
						
						playerMatch = 0;
						cpuMatch = 0;
						
						matchPlayer.setText("0");
						matchCpu.setText("0");
					}
					
					
					
					winNum++;
					if(winNum > winMax){
						String abc = Integer.toString(winNum);
						streakWin.setText(abc);
						winMax = winNum;
					}
					loseNum = 0;
					tieNum = 0;
					
					String match = Integer.toString(playerMatch);
					matchPlayer.setText(match);
					
					if(playerScoreNum == 5){
						Intent kevIntent = new Intent(v.getContext(), DisplayMessageActivity.class);
						kevIntent.putExtra(statusMessage, "YOU WIN!!!");
				    	startActivity(kevIntent);
					}
						
				}
				
				if(cpuPlay == 1 && type == 2 || cpuPlay == 2 && type == 3 || cpuPlay == 3 && type == 1) {
					
					//
					// CPU WIN
					//
					
					cpuMove(v, 200);
					shake(v, 100);
					
					cpuImage.bringToFront();
					
					cpuMatch++;
					
					if(cpuMatch == 3){
						cpuScoreNum++;
						String score = Integer.toString(cpuScoreNum);
						cpuScore.setText(score);
						
						playerMatch = 0;
						cpuMatch = 0;
						
						matchPlayer.setText("0");
						matchCpu.setText("0");
					}
					
					loseNum++;
					if(loseNum > loseMax){
						String abc = Integer.toString(loseNum);
						streakLose.setText(abc);
						loseMax = loseNum;
					}
					winNum = 0;
					tieNum = 0;
					
					String match = Integer.toString(cpuMatch);
					matchCpu.setText(match);
					
					if(cpuScoreNum == 5){
						Intent kevIntent = new Intent(v.getContext(), DisplayMessageActivity.class);
						kevIntent.putExtra(statusMessage, "YOU LOSE :(");
						startActivity(kevIntent);
					}
					
				}
				
				if(cpuPlay == type) {
					
					//
					// TIE
					//
					
					shake(v, 100);
					shake(cpuImage, 100);
					
					
					tieNum++;
					if(tieNum > tieMax){
						String abc = Integer.toString(tieNum);
						streakTie.setText(abc);
						tieMax = tieNum;
					}
					winNum = 0;
					loseNum = 0;
					
					
				}
				
			}
		});
	}
	
	public void shake (View kevView, int duration) {
		
		PropertyValuesHolder trans = PropertyValuesHolder.ofFloat(kevView.ROTATION, 30);
		
		ObjectAnimator moveObj = ObjectAnimator.ofPropertyValuesHolder(kevView, trans);
		moveObj.setStartDelay(100);
		moveObj.setRepeatCount(3);
		moveObj.setDuration(duration);
		moveObj.setRepeatMode(ValueAnimator.REVERSE);
		moveObj.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                
            	btnPaper.setClickable(true);
				btnRock.setClickable(true);
				btnScissor.setClickable(true);

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                
            }
        });
		moveObj.start();
		
	}
	
	public void playerMove (View kevView, int duration) {
		
		// VIEW Objects can be LinearLayouts and etc
		View playerViews = (View) findViewById(R.id.playerViews);
		
		PropertyValuesHolder transX = PropertyValuesHolder.ofFloat(kevView.X, cpuImage.getX()-90);
		PropertyValuesHolder transY = PropertyValuesHolder.ofFloat(kevView.Y, -(playerViews.getY()-cpuImage.getY()));
		
		ObjectAnimator moveObj = ObjectAnimator.ofPropertyValuesHolder(kevView, transX, transY);
		moveObj.setRepeatCount(1);
		moveObj.setDuration(duration);
		moveObj.setRepeatMode(ValueAnimator.REVERSE);
		moveObj.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                
            	btnPaper.setClickable(true);
				btnRock.setClickable(true);
				btnScissor.setClickable(true);

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                
            }
        });
		moveObj.start();
	}
	
	public void cpuMove (View kevView, int duration) {
		
		// VIEW Objects can be LinearLayouts and etc
		View playerViews = (View) findViewById(R.id.playerViews);
		
		PropertyValuesHolder transX = PropertyValuesHolder.ofFloat(cpuImage.X, kevView.getX()+90);
		PropertyValuesHolder transY = PropertyValuesHolder.ofFloat(cpuImage.Y, playerViews.getY());
		
		ObjectAnimator moveObj = ObjectAnimator.ofPropertyValuesHolder(cpuImage, transX, transY);
		moveObj.setRepeatCount(1);
		moveObj.setDuration(duration);
		moveObj.setRepeatMode(ValueAnimator.REVERSE);
		moveObj.start();
	}
	
	

}
