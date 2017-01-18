package com.chuangjia.cyweather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private List<Weather> weatherList=new ArrayList<>();
   // private ImageView imageView;
    private DrawerLayout drawerLayout;
    private static final String TAG = "MainActivity";
    private ImageButton addButton;
    private TextView cityNameTV;
    private TextView countryNameTV;
    private String cityName=new String();
    private String countryName=new String();
    private SharedPreferences sharedPreferences;
   // private ImageView imageViewDown;
    private ListView listViewPop;
    private PopupWindow popWindow;
    private int popHeigth=300;//单位像素
    private  List<CityAndCountry> mListCityAndCountry=new ArrayList<>();
    private ImageButton downButton;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWeather();
        initListViewPop();
        initView();
        initCityAndCountry();
        initListener();
    }

    private void initCityAndCountry() {
        for(int i=0;i<14;i++){
            CityAndCountry cityAndCountry=new CityAndCountry("上海"+i,"松江"+i);
            mListCityAndCountry.add(cityAndCountry);
        }
    }

    private void initListener() {
     //   imageView.setOnClickListener(this);
        addButton.setOnClickListener(this);
        downButton.setOnClickListener(this);
    }

    private void initView() {
        sharedPreferences = getSharedPreferences("data_city",MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //imageView = (ImageView) findViewById(R.id.address_add);
        downButton = (ImageButton) findViewById(R.id.im_tab_down);
        addButton = (ImageButton) findViewById(R.id.address_add);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        cityNameTV = (TextView) findViewById(R.id.tv_city_main);
        //imageViewDown = (ImageView) findViewById(R.id.im_tab_down);
        countryNameTV = (TextView) findViewById(R.id.tv_country_main);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        WeatherAdapter weatherAdapter=new WeatherAdapter(weatherList);
        recyclerView.setAdapter(weatherAdapter);
    }
    /*处理返回键问题,避免出现activity直退出*/
    @Override
    public void onBackPressed() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
      if(mDrawerLayout.isDrawerOpen(findViewById(R.id.choose_area_fragment))){
            mDrawerLayout.closeDrawers();
        }else {
            super.onBackPressed();
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.address_add:
                drawerLayout.setVisibility(View.VISIBLE);
               Toast.makeText(this, "add_clicked", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: "+"add_clicked");
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.im_tab_down:
                createPopWindow();
                Toast.makeText(this, "tab_down", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void createPopWindow() {
        if(popWindow==null){
            popWindow = new PopupWindow(listViewPop, cityNameTV.getWidth(), popHeigth);
        }
        //要让其中的view获取焦点，必须设置 focus为true
        popWindow.setFocusable(true);
        popWindow.setTouchable(true);
        popWindow.setBackgroundDrawable(this.getResources().getDrawable(R.color.colorAccent));
        popWindow.setOutsideTouchable(true);//点击外部可以消失
        popWindow.showAsDropDown(countryNameTV, 0, 0);//anchor在谁的下面
        /*设置其背景透明度*/
        /*WindowManager.LayoutParams params=this.getWindow().getAttributes();
        params.alpha= (float) 1;
        this.getWindow().setAttributes(params);*/
    }

    private void initListViewPop() {
        listViewPop = new ListView(this);
       // listViewPop.setBackgroundResource(R.drawable.icon_search);
        listViewPop.setVerticalScrollBarEnabled(false);
        listViewPop.setClickable(true);
        listViewPop.setAdapter(new MyAdapter());
        listViewPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "poplistClicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setText(Bundle bundle){
        cityName= bundle.getString("cityName");
        countryName=bundle.getString("countryName");
        cityNameTV.setText(cityName);
        countryNameTV.setText(countryName);
        drawerLayout.setVisibility(View.GONE);
    }
    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mListCityAndCountry.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1 =View.inflate(MainActivity.this,R.layout.city_country_item,null);
            TextView itvCity= (TextView) view1.findViewById(R.id.tv_city_name_item);
            TextView tvCountry= (TextView) view1.findViewById(R.id.tv_country_name_item);
            itvCity.setText(mListCityAndCountry.get(position).getCityName());
            tvCountry.setText(mListCityAndCountry.get(position).getCountryName());
            return view1;
        }
    }

}
