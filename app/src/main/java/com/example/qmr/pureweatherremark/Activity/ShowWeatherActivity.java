package com.example.qmr.pureweatherremark.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.qmr.pureweatherremark.BaseAppCompatActivity;
import com.example.qmr.pureweatherremark.R;
import com.example.qmr.pureweatherremark.bean.HeWeatherBean;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowWeatherActivity extends BaseAppCompatActivity implements ShowWeatherActivityI {

    private static final String TAG = "ShowWeatherActivity";

    ShowWeatherPresenter presenter = new ShowWeatherPresenter(this);

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_cond_text)
    TextView tv_cond_text;//阴晴雨雪
    @BindView(R.id.tv_temp)
    TextView tv_temp;//气温
    @BindView(R.id.tv_temp_feel)
    TextView tv_temp_feel;//体感温度
    @BindView(R.id.tv_air_condition)
    TextView tv_air_cond;//空气状况
    @BindView(R.id.chart_temp_today)//今日气温变化
    LineChart lc_temp_today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);
        ButterKnife.bind(this);
        initView();
        presenter.onCreate();
    }

    void initView(){
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: store");
    }

    @Override
    public void showWeather(final HeWeatherBean heWeatherBean) {
        if(!heWeatherBean.getHeWeatherData().isEmpty()) {
            HeWeatherBean.HeWeatherData data = heWeatherBean.getHeWeatherData().get(0);

            //String s = data.getStatus();

            toolbar.setTitle(data.getBasic().getCity());
            tv_air_cond.setText(getString(R.string.text_air_cond,
                    data.getAqi().getCity().getQlty()));
            //,data.getAqi().getCity().getAqi())
            tv_cond_text.setText(data.getNow().getCond().getTxt());
            tv_temp.setText(getString(R.string.text_temp,data.getNow().getFl()));
            tv_temp_feel.setText(getString(R.string.text_temp_feel,data.getNow().getTmp()));

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
