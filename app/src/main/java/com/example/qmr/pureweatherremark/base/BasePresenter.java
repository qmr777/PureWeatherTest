package com.example.qmr.pureweatherremark.base;

/**
 * Created by qmr on 2016/9/7.
 */
public abstract class BasePresenter {

    protected abstract void onCreate();
    protected abstract void onStart();
    protected abstract void onResume();
    protected abstract void onPause();
    protected abstract void onRestart();
    protected abstract void onStop();
    protected abstract void onDestroy();

}
