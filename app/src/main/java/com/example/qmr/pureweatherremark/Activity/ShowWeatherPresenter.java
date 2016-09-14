package com.example.qmr.pureweatherremark.Activity;

import android.util.Log;

import com.example.qmr.pureweatherremark.base.BasePresenter;
import com.example.qmr.pureweatherremark.bean.HeWeatherBean;
import com.example.qmr.pureweatherremark.network.interfaces.GetHeWeather;
import com.example.qmr.pureweatherremark.network.RetrofitUtils;
import com.example.qmr.pureweatherremark.utils.AppConfig;
import com.google.gson.Gson;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by qmr on 2016/9/7.
 *
 */
public class ShowWeatherPresenter extends BasePresenter {

    public static final String TAG = ShowWeatherPresenter.class.getSimpleName();

    ShowWeatherActivityI showWeatherActivity;

    public ShowWeatherPresenter(ShowWeatherActivityI i){
        this.showWeatherActivity = i;
    }

    @Override
    protected void onCreate() {
        RetrofitUtils.GetStringRetrofit().create(GetHeWeather.class)
                .getHeWeather("laoshan", AppConfig.HEFENG_KEY)
                .map(new Func1<String, HeWeatherBean>() {
                    @Override
                    public HeWeatherBean call(String s) {
                        Log.i(TAG, "call: " + s);
                        return new Gson().fromJson(
                                s.replaceFirst("HeWeather data service 3.0","HeWeatherData"),HeWeatherBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HeWeatherBean>() {
                    @Override
                    public void call(HeWeatherBean heWeatherBean) {
                        showWeatherActivity.showWeather(heWeatherBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onResume() {

    }

    @Override
    protected void onPause() {

    }

    @Override
    protected void onRestart() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onDestroy() {

    }

}
