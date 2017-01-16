package com.chuangjia.cyweather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Melvin on 2017/1/15.
 */

class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{

    private List<Weather> mWeatherList;

    public WeatherAdapter(List<Weather> weatherList){
        mWeatherList = weatherList;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView dateChinese;
        private TextView dateNum;
        private TextView weather;
        private ImageView weatherImage;
        private TextView temperature;

        public ViewHolder(View itemView) {
            super(itemView);
            dateChinese = (TextView) itemView.findViewById(R.id.tv_date_chinese_item);
            dateNum = (TextView) itemView.findViewById(R.id.tv_date_num_item);
            weather = (TextView) itemView.findViewById(R.id.tv_weather_item);
            weatherImage = (ImageView) itemView.findViewById(R.id.iv_weather_item);
            temperature = (TextView) itemView.findViewById(R.id.tv_temperature_item);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Weather weather=mWeatherList.get(position);
        holder.dateChinese.setText(weather.getDateChinese());
        holder.dateNum.setText(weather.getDateNum());
        holder.weather.setText(weather.getWeather());
        holder.weatherImage.setImageResource(weather.getImageId());
        holder.temperature.setText(weather.getTemperature());
    }
    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }
}
