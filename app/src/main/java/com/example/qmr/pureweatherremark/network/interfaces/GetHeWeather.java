package com.example.qmr.pureweatherremark.network.interfaces;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qmr on 2016/9/7.
 */
public interface GetHeWeather {

    @GET("weather")
    Observable<String> getHeWeather(@Query("city") String city, @Query("key") String key);
}
