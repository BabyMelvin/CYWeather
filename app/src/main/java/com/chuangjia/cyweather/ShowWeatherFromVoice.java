package com.chuangjia.cyweather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chuangjia.cyweather.json.WeatherContent;
import com.chuangjia.cyweather.utils.Utility;

/**
 * Created by Melvin on 2017/1/18.
 *
 */

public class ShowWeatherFromVoice extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ShowWeatherFromVoice";
    private ImageButton imageButtonHint;
    private ImageButton imageButtonWeather;
    private LinearLayout linearLayoutHint;
    private boolean mLinearLayoutHintVisibleStatue =false;
    private int mViewStatus;
    private final int TIMEOUT=1;
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
          switch (msg.what){
              case TIMEOUT:
                  finish();
                  break;
              case 2:
                  break;
          }
        }
    };
    private TimerDialog mTimer;
    private WeatherContent weatherContent;
    private TextView dataVoice;
    private TextView locationVoice;
    private TextView weatherVoice;
    private TextView temperatureVoice;
    private TextView directionVoice;
    private TextView powerVoice;
    private ImageButton imageButtonWeather1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog_activity);
        Log.d(TAG, "onCreate: ");
        initView();
        initListener();
        initThread();
    }

    private void initThread() {
        mTimer = new TimerDialog();
        if(linearLayoutHint.getVisibility()==View.INVISIBLE) {
            mTimer.start();
        }
    }
    class TimerDialog extends Thread {
        @Override
        public void run() {
            try {
                    Thread.sleep(5000);
                    Message message = new Message();
                    message.what = TIMEOUT;
                    handler.sendMessage(message);
                    Log.d(TAG, "run: " + "    thread run" );
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.d(TAG, "run: "+"interrupted");
            }
        }
    }

    private void initListener() {
        imageButtonHint.setOnClickListener(this);
        imageButtonWeather.setOnClickListener(this);
        linearLayoutHint = (LinearLayout) findViewById(R.id.ly_hint_voice);
    }

    private void initView() {
        //获得语音遥控信息，并解析内容
        Intent intent=getIntent();
        String getContentVoice =intent.getStringExtra("voiceWeather");
        //Log.d(TAG, "initView: "+getContentVoice);
        weatherContent = Utility.handleContentFromVoice(getContentVoice);
        Log.d(TAG, "initView: "+ weatherContent.resultS);
        //
        imageButtonHint = (ImageButton) findViewById(R.id.ib_hint_voice);
        imageButtonWeather = (ImageButton) findViewById(R.id.iv_weather_voice);
        locationVoice = (TextView) findViewById(R.id.tv_location_voice);
        temperatureVoice = (TextView) findViewById(R.id.tv_temperature_voice);
        dataVoice = (TextView) findViewById(R.id.tv_data_voice);
        weatherVoice = (TextView) findViewById(R.id.tv_weather_voice);
        directionVoice = (TextView) findViewById(R.id.tv_direction_voice);
        powerVoice = (TextView) findViewById(R.id.tv_power_voice);
        imageButtonWeather1 = (ImageButton) findViewById(R.id.iv_weather_voice);
        imageButtonWeather.setImageResource(Utility.getDrawableByName(weatherContent.answer.content.descriptionS));
        directionVoice.setText(weatherContent.answer.content.windDir);
        powerVoice.setText(weatherContent.answer.content.windPower);
        weatherVoice.setText(weatherContent.answer.content.descriptionS);
        dataVoice.setText(weatherContent.answer.content.dateS);
        locationVoice.setText(weatherContent.answer.content.loactionS);
        temperatureVoice.setText(weatherContent.answer.content.temperatureL+"°"+"~"+weatherContent.answer.content.tempeatureH+"°");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ib_hint_voice:
                Log.d(TAG, "onClick: "+ mLinearLayoutHintVisibleStatue);
                if(mLinearLayoutHintVisibleStatue ==false){
                    mViewStatus=View.VISIBLE;
                    mTimer.interrupt();
                }else{
                    mViewStatus=View.INVISIBLE;
                    if(mTimer==null) {
                        mTimer=new TimerDialog();
                    }
                   // mTimer.start();
                }
                if(linearLayoutHint==null){
                    linearLayoutHint = (LinearLayout) findViewById(R.id.ly_hint_voice);
                }
                linearLayoutHint.setVisibility(mViewStatus);
                mLinearLayoutHintVisibleStatue =!mLinearLayoutHintVisibleStatue;
                break;
            case R.id.iv_weather_voice:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(linearLayoutHint.getVisibility()==View.VISIBLE) {
            if (linearLayoutHint == null) {
                linearLayoutHint = (LinearLayout) findViewById(R.id.ly_hint_voice);
            }
            linearLayoutHint.setVisibility(View.INVISIBLE);
            mLinearLayoutHintVisibleStatue =!mLinearLayoutHintVisibleStatue;
        }else {
            super.onBackPressed();
        }
    }
}
