package com.chuangjia.cyweather;

/**
 * Created by Melvin on 2017/1/15.
 */

public class Weather {
    private String dateChinese;
    private String dateNum;
    private String weather;
    private int imageId;
    private String temperature;

    public Weather(String dateChinese, String dateNum, String weather, int imageId, String temperature) {
        this.dateChinese = dateChinese;
        this.dateNum = dateNum;
        this.weather = weather;
        this.imageId = imageId;
        this.temperature = temperature;
    }

    public String getDateChinese() {
        return dateChinese;
    }

    public void setDateChinese(String dateChinese) {
        this.dateChinese = dateChinese;
    }

    public String getDateNum() {
        return dateNum;
    }

    public void setDateNum(String dateNum) {
        this.dateNum = dateNum;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
