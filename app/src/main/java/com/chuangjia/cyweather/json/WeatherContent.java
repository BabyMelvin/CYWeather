package com.chuangjia.cyweather.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Melvin on 2017/1/19.
 */

public class WeatherContent {
    @SerializedName("result")
    public  String resultS;
    public Answer answer;
}
