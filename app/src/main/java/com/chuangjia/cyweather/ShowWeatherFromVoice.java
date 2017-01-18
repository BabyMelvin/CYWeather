package com.chuangjia.cyweather;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by Melvin on 2017/1/18.
 *
 */

public class ShowWeatherFromVoice extends AppCompatActivity {
    private static final String TAG = "ShowWeatherFromVoice";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_voice_weather);
        Log.d(TAG, "onCreate: ");
        //showDialogAboutWeather();
    }
    private void showDialogAboutWeather(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        final AlertDialog dialog=builder.create();
        View view=View.inflate(this,R.layout.dialog_voice_weather,null);
        // Button button_ok= (Button) view.findViewById(R.id.bt_ok);
        //  Button button_cancel= (Button) view.findViewById(R.id.bt_cancel);
        //  final EditText text_password= (EditText) view.findViewById(R.id.input_password);
        dialog.setView(view);
        dialog.show();
        /*button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEditPassword=text_password.getText().toString();
                String getSavePassword=sharedPreferencesPassword.getString("password",null);
                if(TextUtils.isEmpty(getSavePassword)){
                    Toast.makeText(HomeActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    if(MD5Utils.encode(getEditPassword).equals(getSavePassword)) {
                        Toast.makeText(HomeActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        //跳转到手机防盗页
                        startActivity(new Intent(HomeActivity.this,LostFoundActivity.class));
                    }else{
                        Toast.makeText(HomeActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/
       /*button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });*/
    }

}
