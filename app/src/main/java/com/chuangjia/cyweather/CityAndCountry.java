package com.chuangjia.cyweather;

/**
 * Created by Melvin on 2017/1/16.
 */

public class CityAndCountry {
    private String cityName;
    private String countryName;

    public CityAndCountry(String cityName, String countryName) {
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
