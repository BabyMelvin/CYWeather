package com.chuangjia.cyweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.chuangjia.cyweather.ShowWeatherFromVoice;

public class VoiceWeatherReceiver extends BroadcastReceiver {
    private static final String TAG = "VoiceWeatherReceiver";
    public VoiceWeatherReceiver() {
        super();
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
       String getVoiceContent=intent.getStringExtra("voiceWeather");
        //Log.d(TAG, "onReceive: "+getVoiceContent);
        Intent intentView=new Intent(context,ShowWeatherFromVoice.class);
        //添加setFlags 避免堵塞起不来
        intentView.putExtra("voiceWeather",getVoiceContent);
        intentView.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentView);
    }

}
