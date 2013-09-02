package com.huige.mines;


import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main extends Activity { 

	private SoundPool soundPool;
	private int clickSound;
	private boolean OpenSound = true;  
	private TextView ChartString;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	/*	AdManager.init(this,"d72e9f61b58f5ee5", "fb9dfc2456866caf",  30, true); */
		
		setContentView(R.layout.main);
		ChartString = (TextView) findViewById(R.id.chartString);
		initSoundPool();
		ChartString.setText(chartMessage());
		//		setTypeface();

/* 		//��ʼ�������ͼ
 		AdView adView = new AdView(this);
 		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
 		//���ù����ֵ�λ��(��������Ļ���½�)		 
 		params.gravity=Gravity.BOTTOM|Gravity.RIGHT; 
 		//�������ͼ����Activity��
 		addContentView(adView, params); */
	}

	@Override
	public void onResume(){
		super.onResume();
		OpenSound = SetActivity.getOpenSound(this);
		ChartString.setText(chartMessage());
	}

	private String chartMessage(){
		int value;
		StringBuilder builder = new StringBuilder();

		value = SetActivity.getHigh(this);
		if(value>=0){
			builder.append(" �߼���ѣ�");
			builder.append(value);
		}

		value = SetActivity.getMiddle(this);
		if(value>=0){
			builder.append(" �м���ѣ�");
			builder.append(value);
		}

		value = SetActivity.getPrimary(this);
		if(value>=0){
			builder.append(" ������ѣ�");
			builder.append(value);
		}

		return builder.toString();
	}

	/*	private void setTypeface(){
		Typeface tf=Typeface.createFromAsset(getAssets(), "STXINGKA.TTF");
		Button btn = (Button)findViewById(R.id.start);
		btn.setTypeface(tf);

		((Button)findViewById(R.id.exit)).setTypeface(tf);

	}*/

	public void onClickStart(View view){
		playSoundPool();
		Intent intent = new Intent(this, PlayActivity.class);
		startActivity(intent);
	}
	public void onClickSet(View view){
		playSoundPool();
		Intent intent = new Intent(this, SetActivity.class);
		startActivity(intent);
	}

	public void onClickCharts(View view){
		playSoundPool();
		Intent intent = new Intent(this, ChartsActivity.class);
		startActivity(intent);
	}

	public void onClickExit(View view){
		playSoundPool();
		this.finish();
	}

	public void onClickHelp(View view){
		playSoundPool();
		Intent intent = new Intent(this, HelpActivity.class);
		startActivity(intent);
	}

	private boolean initSoundPool(){
		OpenSound = SetActivity.getOpenSound(this);
		//ָ�������ص������Ƶ����ĿΪ10������Ʒ��Ϊ5  
		soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);  
		//������Ƶ���������ڳ��е�id  
		clickSound = soundPool.load(this, R.raw.command, 0);
		return OpenSound;
	}

	private void playSoundPool(){
		if(OpenSound){
			soundPool.play(clickSound, 0.5f, 0.5f, 0, 0, 1);
		}
	}
}
