package com.chuangjia.cyweather.utils;

import android.text.TextUtils;

import com.chuangjia.cyweather.R;
import com.chuangjia.cyweather.Weather;
import com.chuangjia.cyweather.db.City;
import com.chuangjia.cyweather.db.County;
import com.chuangjia.cyweather.db.Province;
import com.chuangjia.cyweather.json.WeatherContent;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Melvin on 2017/1/16.
 * parse the data of location depends on the prior;
 */

public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    private static final String TAG = "Utility";
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static WeatherContent handleContentFromVoice(String response){
        try {
            Gson gson=new Gson();
           // JSONObject jsonObject = new JSONObject(response);
          //  JSONArray jsonArray = jsonObject.getJSONArray("answer");
          //  String voiceContent = jsonArray.getJSONObject(0).toString();
            return gson.fromJson(response, WeatherContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getDrawableByName(String name){
        int RID=0;
        switch (name){
            case "多云":
                RID= R.drawable.cloudy;
                break;
            case "晴":
                RID= R.drawable.fine;
                break;
            case "暴雨":
                RID= R.drawable.rainstorm;
                break;
            case "暴雪":
                RID= R.drawable.snowstorm;
                break;
            case "冰雹":
                RID= R.drawable.hail;
                break;
            case "雷电":
                RID= R.drawable.thunder;
                break;
            case "小雪":
                RID= R.drawable.tinysnow;
                break;
            case "小雨":
                RID= R.drawable.sprinkle;
                break;
            case "阴":
                RID= R.drawable.shade;
                break;
            case "雨雪":
                RID= R.drawable.sleet;
                break;
            case "阵雪":
                RID= R.drawable.flurrysonw;
                break;
            case "阵雨":
               RID = R.drawable.showerrain;
                break;
            case "中雪":
               RID= R.drawable.moderatesnow;
                break;
            case "中雨":
                RID=R.drawable.moderaterain;
                break;
            default:
                break;
        }
        return RID;
    }
}
