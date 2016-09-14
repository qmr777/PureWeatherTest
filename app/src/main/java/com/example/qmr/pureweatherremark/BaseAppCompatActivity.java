package com.example.qmr.pureweatherremark;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.qmr.pureweatherremark.R;
import com.example.qmr.pureweatherremark.base.BaseInterface;

public class BaseAppCompatActivity extends AppCompatActivity implements BaseInterface {

    private static final String TAG = "BaseAppCompatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }


    public void shortToast(CharSequence string){
        Toast.makeText(this, string ,Toast.LENGTH_SHORT).show();
    }

    public void shortToast(@StringRes int stringRes){
        Toast.makeText(this,stringRes,Toast.LENGTH_SHORT).show();
    }
}
