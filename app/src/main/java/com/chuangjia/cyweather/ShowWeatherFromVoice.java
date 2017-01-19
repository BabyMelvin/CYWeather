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
        public boolean run=true;
        public void stopThread(){
            run=false;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                    Message message=new Message();
                    message.what=TIMEOUT;
                    handler.sendMessage(message);
                    Log.d(TAG, "run: "+"    thread run"+run);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initListener() {
        imageButtonHint.setOnClickListener(this);
        imageButtonWeather.setOnClickListener(this);
        linearLayoutHint = (LinearLayout) findViewById(R.id.ly_hint_voice);
    }

    private void initView() {
        imageButtonHint = (ImageButton) findViewById(R.id.ib_hint_voice);
        imageButtonWeather = (ImageButton) findViewById(R.id.iv_weather_voice);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_hint_voice:
                Log.d(TAG, "onClick: "+ mLinearLayoutHintVisibleStatue);
                if(mLinearLayoutHintVisibleStatue ==false){
                    mViewStatus=View.VISIBLE;
                }else{
                    mViewStatus=View.INVISIBLE;
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
