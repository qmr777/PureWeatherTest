package com.example.qmr.pureweatherremark.network.interfaces;

import com.example.qmr.pureweatherremark.bean.CityListBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qmr on 2016/9/7.
 *
 */
public interface GetCityList {
    //https://api.heweather.com/x3/citylist?search=类型&key=你的认证key
    @GET("citylist")
    Observable<CityListBean> getCityList (@Query("search") String cityType , @Query("key") String key);
}
