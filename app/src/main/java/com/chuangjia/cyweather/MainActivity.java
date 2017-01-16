package com.chuangjia.cyweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Weather> weatherList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWeather();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        WeatherAdapter weatherAdapter=new WeatherAdapter(weatherList);
        recyclerView.setAdapter(weatherAdapter);

    }

    private void initWeather() {
        for(int i=0;i<2;i++){
            Weather monday=new Weather("周一","01-16","多云",R.drawable.cloudy,"1°~3°");
            weatherList.add(monday);
            Weather tuesday =new Weather("周二","01-17","阴",R.drawable.nosun,"1°~3°");
            weatherList.add(tuesday);
            Weather wednesday=new Weather("周三","01-16","多云",R.drawable.cloudy,"1°~8°");
            weatherList.add(wednesday);
            Weather thursday =new Weather("周四","01-18","暴雨",R.drawable.storm,"2°~3°");
            weatherList.add(thursday);
            Weather friday=new Weather("周五","01-19","多云",R.drawable.cloudy,"1°~3°");
            weatherList.add(friday);
            Weather saturday=new Weather("周六","01-20","多云",R.drawable.cloudy,"1°~3°");
            weatherList.add(saturday);
            Weather sunday=new Weather("周日","01-16","多云",R.drawable.cloudy,"1°~3°");
            weatherList.add(sunday);
        }
    }
}
